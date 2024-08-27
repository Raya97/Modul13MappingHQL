package org.example;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.example.service.ClientCrudService;
import org.example.service.PlanetCrudService;
import org.example.service.TicketCrudService;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;

import java.time.Instant;

/**
 * The Main class serves as the entry point for the application.
 * It demonstrates the use of CRUD operations on Client, Planet, and Ticket entities,
 * along with the execution of database migrations using Flyway.
 */
public class Main {
    private static TicketCrudService ticketService;
    public static void main(String[] args) {

        // Configure Flyway for database migration
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:~/12hibernate", "Raisa", "")
                .locations("classpath:db/migration")
                .load();

        // Execute the database migrations
        flyway.migrate();

        // Initialize SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Initialize CRUD services with SessionFactory
        ClientCrudService clientService = new ClientCrudService(sessionFactory);
        PlanetCrudService planetService = new PlanetCrudService(sessionFactory);
        TicketCrudService ticketService = new TicketCrudService(sessionFactory);

        // Clear existing data before inserting new records
        ticketService.deleteAllTickets();
        clientService.deleteAllClients();
        planetService.deleteAllPlanets();

        // Create and save clients
        Client client1 = new Client();
        client1.setName("John Doe");
        clientService.createClient(client1);

        Client client2 = new Client();
        client2.setName("Jane Doe");
        clientService.createClient(client2);

        // Create and save planets
        Planet planet1 = new Planet();
        planet1.setId("MARS");
        planet1.setName("Mars");
        planetService.createPlanet(planet1);

        Planet planet2 = new Planet();
        planet2.setId("VEN");
        planet2.setName("Venus");
        planetService.createPlanet(planet2);

        // Create and save tickets
        Ticket ticket1 = new Ticket();
        ticket1.setCreatedAt(Instant.now());
        ticket1.setClient(client1);
        ticket1.setFromPlanet(planet1);
        ticket1.setToPlanet(planet2);
        ticketService.createTicket(ticket1);

        Ticket ticket2 = new Ticket();
        ticket2.setCreatedAt(Instant.now());
        ticket2.setClient(client2);
        ticket2.setFromPlanet(planet2);
        ticket2.setToPlanet(planet1);
        ticketService.createTicket(ticket2);

        // Print all clients
        System.out.println("Clients:");
        for (Client client : clientService.getAllClients()) {
            System.out.println(client.getName());
        }

        // Print all planets
        System.out.println("Planets:");
        for (Planet planet : planetService.getAllPlanets()) {
            System.out.println(planet.getName());
        }

        // Print all tickets
        System.out.println("Tickets:");
        for (Ticket ticket : ticketService.getAllTickets()) {
            System.out.println("Ticket client = " + ticket.getClient().getName());
            System.out.println("Ticket from planet = " + ticket.getFromPlanet().getName());
            System.out.println("Ticket to planet = " + ticket.getToPlanet().getName());
        }

        // Update client
        client1.setName("John Smith");
        clientService.updateClient(client1);



        // Print all clients after update
        System.out.println("Clients after update:");
        for (Client client : clientService.getAllClients()) {
            System.out.println(client.getName());
        }

        // Print all planets after deletion
        System.out.println("Planets after deletion:");
        for (Planet planet : planetService.getAllPlanets()) {
            System.out.println(planet.getName());
        }

        // Print all tickets after updates
        System.out.println("Tickets after updates:");
        for (Ticket ticket : ticketService.getAllTickets()) {
            System.out.println("Ticket client = " + ticket.getClient().getName());
            System.out.println("Ticket from planet = " + ticket.getFromPlanet().getName());
            System.out.println("Ticket to planet = " + ticket.getToPlanet().getName());
        }
    }
}
