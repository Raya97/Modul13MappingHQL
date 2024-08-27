package org.example.service;

import org.example.entity.Planet;
import org.example.HibernateUtil;
import org.example.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class PlanetCrudService {


    private static final String LOG_FILE = "error.log";

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
     * Creates a new planet record in the database.
     *
     * @param planet The `Planet` object to be saved.
     */
    public void createPlanet(Planet planet) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Null check
            if (planet == null || planet.getId() == null || planet.getId().isEmpty()) {
                throw new IllegalArgumentException("Planet or planet ID cannot be null or empty.");
            }

            session.save(planet);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Print stack trace for debugging.
            logError("Failed to create planet", e);
        }
    }

    /**
     * Retrieves a planet record from the database by its ID.
     *
     * @param id The identifier of the planet to retrieve.
     * @return The `Planet` object matching the given ID, or null if not found.
     */
    public Planet getPlanet(String id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Planet.class, id);
        } catch (Exception e) {
            logError("Failed to fetch planet by ID: " + id, e);
            return null;
        }
    }

    /**
     * Retrieves all planet records from the database.
     *
     * @return A list of all `Planet` objects.
     */
    public List<Planet> getAllPlanets() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Planet", Planet.class).list();
        } catch (Exception e) {
            logError("Failed to fetch all planets", e);
            return null;
        }
    }

    /**
     * Updates an existing planet record in the database.
     *
     * @param planet The `Planet` object with updated values.
     */
    public void updatePlanet(Planet planet) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(planet);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logError("Failed to update planet", e);
        }
    }

    /**
     * Deletes all planet records from the database.
     */
    public void deleteAllPlanets() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Planet").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logError("Failed to delete all planets", e);
        }
    }

    /**
     * Deletes a planet record from the database by its ID.
     *
     * @param id The identifier of the planet to delete.
     */
    public void deletePlanet(String id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Get all tickets referencing this planet and delete them
            List<Ticket> tickets = session.createQuery("from Ticket where fromPlanet.id = :planetId or toPlanet.id = :planetId", Ticket.class)
                    .setParameter("planetId", id)
                    .list();

            for (Ticket ticket : tickets) {
                session.delete(ticket);
            }

            // Delete the planet
            Planet planet = session.get(Planet.class, id);
            if (planet != null) {
                session.delete(planet);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logError("Failed to delete planet by ID: " + id, e);
        }
    }
}
