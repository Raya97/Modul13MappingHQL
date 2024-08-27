package org.example.service;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.example.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TicketCrudService {

    private final SessionFactory sessionFactory;
    private static final String LOG_FILE = "error.log";
    public TicketCrudService(SessionFactory sessionFactory) {
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
     * Creates a new ticket record in the database.
     *
     * @param ticket The `Ticket` object to be saved.
     */
    public void createTicket(Ticket ticket) {
        if (ticket.getClient() == null || ticket.getFromPlanet() == null || ticket.getToPlanet() == null) {
            throw new IllegalArgumentException("Client and planets must not be null");
        }

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Check the existence of the client and planets
            Client client = session.get(Client.class, ticket.getClient().getId());
            if (client == null) {
                throw new IllegalArgumentException("Client does not exist");
            }

            Planet fromPlanet = session.get(Planet.class, ticket.getFromPlanet().getId());
            if (fromPlanet == null) {
                throw new IllegalArgumentException("FromPlanet does not exist");
            }

            Planet toPlanet = session.get(Planet.class, ticket.getToPlanet().getId());
            if (toPlanet == null) {
                throw new IllegalArgumentException("ToPlanet does not exist");
            }

            // Save the ticket
            session.save(ticket);
            transaction.commit();
        } catch (IllegalArgumentException e) {
            // Roll back the transaction and rethrow the IllegalArgumentException
            if (transaction != null && transaction.getStatus() != null && transaction.getRollbackOnly()) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            // Roll back the transaction and wrap all other exceptions in an IllegalStateException
            if (transaction != null && transaction.getStatus() != null && transaction.getRollbackOnly()) {
                transaction.rollback();
            }
            throw new IllegalStateException("Unexpected error occurred during ticket creation", e);
        }
    }




    /**
     * Retrieves all ticket records from the database.
     *
     * @return A list of all `Ticket` objects.
     */
    public List<Ticket> getAllTickets() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Ticket", Ticket.class).list();
        } catch (Exception e) {
            logError("Failed to fetch all tickets", e);
            return null;
        }
    }

    /**
     * Deletes all ticket records from the database.
     */
    public void deleteAllTickets() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Ticket").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logError("Failed to delete all tickets", e);
        }
    }
}
