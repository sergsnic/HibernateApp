package ru.sergsnic.springcourse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sergsnic.springcourse.model.Person;

import java.util.List;

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
            //Вывод всех людей из БД
            List<Person> people1 = session.createQuery("FROM Person").getResultList();
            for (Person person: people1)
                System.out.println(person);
            //Вывод всех людей из БД старше 30
            List<Person> people2 = session.createQuery("FROM Person where age > 30").getResultList();
            for (Person person: people2)
                System.out.println(person);
            //Вывод всех людей из БД c именем начинающимся на букву T
            List<Person> people3 = session.createQuery("FROM Person where name LIKE 'T%'").getResultList();
            for (Person person: people3)
                System.out.println(person);
            //Обновляем имя всех людей возраст которых = 30
            session.createQuery("update Person set name = 'Test' where age = 30").executeUpdate();
            //Удалим всех людей возраст которых больше или равен 30
            session.createQuery("delete from Person where age >= 30").executeUpdate();
            session.getTransaction().commit();


        } finally {
            sessionFactory.close();
        }
    }
}
