package org.example.service;

import org.example.entity.Client;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ClientCrudService {

    private static final Logger logger = LoggerFactory.getLogger(ClientCrudService.class);
    private final SessionFactory sessionFactory;
    private static final String LOG_FILE = "error.log";

    // Constructor accepting SessionFactory
    public ClientCrudService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Logs an error message to a file.
     *
     * @param message The error message to log.
     * @param e The exception that occurred.
     */
    private void logError(String message, Exception e) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println("ERROR: " + message);
            e.printStackTrace(writer);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Creates a new client record in the database.
     *
     * @param client The `Client` object to be saved.
     */
    public void createClient(Client client) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Null check
            if (client == null || client.getName() == null || client.getName().isEmpty()) {
                throw new IllegalArgumentException("Client or client name cannot be null or empty.");
            }
            session.save(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Print stack trace for debugging.
            logError("Failed to create client", e);
        }
    }

    /**
     * Retrieves all client records from the database.
     *
     * @return A list of all `Client` objects.
     */
    public List<Client> getAllClients() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Client", Client.class).list();
        } catch (Exception e) {
            logError("Failed to fetch all clients", e);
            return null;
        }
    }

    /**
     * Deletes all client records from the database.
     */
    public void deleteAllClients() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Client").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logError("Failed to delete all clients", e);
        }
    }

    /**
     * Updates an existing client record in the database.
     *
     * @param client The `Client` object with updated values.
     */
    public void updateClient(Client client) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logError("Failed to update client", e);
        }
    }
}
