package ru.sergsnic.springcourse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sergsnic.springcourse.model.Actor;
import ru.sergsnic.springcourse.model.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Actor.class).addAnnotatedClass(Movie.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();;

        try(sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            //Добавление Фильма и актеров
//            Movie movie = new Movie("Pulp fiction", 1994);
//            Actor actor1 = new Actor("Harvey Keytel",81);
//            Actor actor2 = new Actor("Samuel L. Jackson",72);
//            movie.setActors(new ArrayList<>(List.of(actor1,actor2)));
//            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            session.save(movie);
//            session.save(actor1);
//            session.save(actor2);
            //Добавление фильма и назначение актера из списка
//            Movie movie = new Movie("Reservoir Dogs", 1998);
//            Actor actor = session.get(Actor.class,3);
//            movie.setActors(new ArrayList<>(Collections.singletonList(actor)));
//            actor.getMovies().add(movie);
//            session.save(actor);

            //session.save(movie);
            //вывод списка актеров и фильмов
//            Movie movie = session.get(Movie.class,2);
//            movie.getActors().stream().forEach(System.out::println);
//            Actor actor = session.get(Actor.class,4);
//            actor.getMovies().stream().forEach(System.out::println);
            //Удалим фильм у актера и самого актера
            Actor actor = session.get(Actor.class, 4);
            Movie movie = actor.getMovies().get(0);
            actor.getMovies().remove(0);
            movie.getActors().remove(actor);

            session.getTransaction().commit();
        }

    }
}
