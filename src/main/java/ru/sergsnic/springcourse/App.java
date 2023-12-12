package ru.sergsnic.springcourse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sergsnic.springcourse.model.Item;
import ru.sergsnic.springcourse.model.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            //Получить человека и все его товары
            Person person = session.get(Person.class,3);
            System.out.println(person);
            List<Item> items = person.getItems();
            System.out.println(items);
            //Получить товар и человека его получившего
            Item item = session.get(Item.class,6);
            System.out.println(item);
            Person person1 = item.getOwner();
            System.out.println(person1);
            //Добавление товара человеку
            Person person2 = session.get(Person.class,2);
            Item newItem = new Item("Item from Hibernate",person2);//добавляем товар на стороне товара
            person2.getItems().add(newItem);//Добавляем товар на стороне человека.В БД код изменений не вносит, только в КЭШ.
            session.save(newItem);//Сохраняет новый товар в таблицу Item БД.
            System.out.println(person2);
            System.out.println(person2.getItems());
            //Создать нового человека с новым товаром
            Person person3 = new Person("Victor",35);
            Item newItem1 = new Item("TV", person3);
            person3.setItems(new ArrayList<>(Collections.singletonList(newItem1)));//конструкция добавляет новый список с одним товаром
            session.save(person3);// в дальнейшем будет настроено каскадирование и сохранять можно будет одну сущность
            session.save(newItem1);
            System.out.println(person3);
            System.out.println(person3.getItems());
            //Удаление товаров выбранного человека
            Person person4 = session.get(Person.class,3);
            List<Item> items1 = person4.getItems();
            for (Item itemm: items1)
                session.remove(itemm);//порождает запрос SQL
            person4.getItems().clear();//нет запроса SQL
            //Удаление человека(в БД у нас каскадирование на товар, удалится автоматически)
            Person person5 = session.get(Person.class,2);
            session.remove(person5);// удаляем человека БД
            person5.getItems().forEach(i->i.setOwner(null));// удаляем человека в КЭШ(объект Item, поле owner)
            //Изменение владельца товара
            Item item1 = session.get(Item.class, 1);
            item1.getOwner().getItems().remove(item1);//КЭШ
            Person person6 = session.get(Person.class,4);
            item1.setOwner(person6);//SQL
            person6.getItems().add(item1);//КЭШ
            System.out.println(item1);
            System.out.println(item1.getOwner());
            System.out.println(item1.getOwner().getItems());
            System.out.println(person6);
            System.out.println(person6.getItems());


            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
