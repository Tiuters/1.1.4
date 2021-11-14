package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.metamodel.Metadata;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Util {
    private static String dbURL = "jdbc:mysql://localhost:3306/pp1_schema"; //?autoReconnect=true&useSSL=false
    private static String dbUsername = "root";
    private static String dbPassword = "ert45dfc67";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        Properties prop = new Properties();

        prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/sch_forhiber");
        prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        prop.setProperty("hibernate.connection.username", "hiber");
        prop.setProperty("hibernate.connection.password", "hiber");
        prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        prop.setProperty("hibernate.show_sql", "true");

        sessionFactory = new Configuration().addAnnotatedClass(User.class)
            .buildSessionFactory(new StandardServiceRegistryBuilder()
                .applySettings(prop).build());

        return sessionFactory;
    }
}













