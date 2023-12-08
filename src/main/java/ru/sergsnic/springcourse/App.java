package ru.sergsnic.springcourse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sergsnic.springcourse.model.Person;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            //изменение
            Person person1 = session.get(Person.class, 3);
            person1.setName("New name");
            //удаление
            Person person2 = session.get(Person.class, 2);
            session.delete(person2);
            //получение id новой записи в таблице.
            Person person3 = new Person("Bob", 30);
            session.save(person3);

            session.getTransaction().commit();

            //получение id новой записи в таблице.
            System.out.println("Id новой записи " + person3.getId());

        } finally {
            sessionFactory.close();
        }
    }
}
