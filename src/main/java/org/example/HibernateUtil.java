package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
/**
 * The HibernateUtil class provides a utility to manage Hibernate's SessionFactory.
 *
 * This class is responsible for initializing and providing access to the SessionFactory,
 * which is used to create sessions for interacting with the database.
 */
public class HibernateUtil {
    // Singleton instance of SessionFactory, which is created once and reused throughout the application.
    private static SessionFactory sessionFactory;

    // Static block to initialize the SessionFactory when the class is loaded.
    static {
        // Create a StandardServiceRegistry, which holds the Hibernate configuration.
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            // Build the SessionFactory from the MetadataSources using the configuration.
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // If an error occurs during the creation of the SessionFactory, destroy the registry.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    /**
     * Provides access to the singleton SessionFactory instance.
     *
     * @return The SessionFactory object for creating sessions.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}