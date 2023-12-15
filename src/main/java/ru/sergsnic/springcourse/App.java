package ru.sergsnic.springcourse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sergsnic.springcourse.model.Passport;
import ru.sergsnic.springcourse.model.Person;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Passport.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();;
        Session session = sessionFactory.getCurrentSession();
//        try(sessionFactory) {
//            session.beginTransaction();
//            //создание человека с паспортом
//            Person person = new Person("Testig name",50);
//            Passport passport = new Passport(123456);
//            person.setPassport(passport);
//            session.save(person);
//            session.getTransaction().commit();
//        }

//        try(sessionFactory) {
//            //получение номера паспорта по человеку
//            session.beginTransaction();
//            Person person = session.get(Person.class, 1);
//            System.out.println("номер паспорта personId = 1: " + person.getPassport().getPassportNumber());
//            //получение имени человека по паспорту
//            Passport passport = session.get(Passport.class, 2);
//            System.out.println("имя человека id = 2" + passport.getPerson().getName());
//            session.getTransaction().commit();
//        }
//        try(sessionFactory) {
//            //Изменение номера паспорта
//            session.beginTransaction();
//            Person person = session.get(Person.class, 2);
//            person.getPassport().setPassportNumber(7777777);
//            session.getTransaction().commit();
//        }
        try(sessionFactory) {
            //Удаление человека и каскадом паспорта
            session.beginTransaction();
            Person person = session.get(Person.class, 5);
            session.remove(person);
            session.getTransaction().commit();
        }

    }
}
