package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            System.out.println("Создание сессии");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            Transaction transaction = session.beginTransaction();

            String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(20), " +
                "lastName VARCHAR(20), " +
                "age TINYINT)";

            Query query = session.createSQLQuery(sql);

            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //       session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            System.out.println("Создание сессии");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            Transaction transaction = session.beginTransaction();

            String sql = "DROP TABLE IF EXISTS users";

            Query query = session.createSQLQuery(sql);

            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            System.out.println("Создание сессии");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        session.close();
    }

    @Override
    public void removeUserById(long id) {

        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            System.out.println("Создание сессии");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            session.beginTransaction();
            session.createQuery("delete User where id = :x")
                .setLong("x", id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            System.out.println("Создание сессии");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        List<User> users = null;
        try {
            session.beginTransaction();
            users = session.createQuery("FROM User").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            System.out.println("Создание сессии");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        session.close();
    }
}
