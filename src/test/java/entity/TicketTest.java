package entity;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    /**
     * Test the getters and setters for the Ticket class.
     */
    @Test
    void testGettersAndSetters() {
        Ticket ticket = new Ticket();

        // Set the ID and creation time for the ticket
        ticket.setId(1L);
        ticket.setCreatedAt(Instant.now());

        // Create and set the client associated with the ticket
        Client client = new Client();
        client.setId(2L);
        client.setName("Jane Doe");

        // Create and set the 'from' planet for the ticket
        Planet fromPlanet = new Planet();
        fromPlanet.setId("EARTH");
        fromPlanet.setName("Earth");

        // Create and set the 'to' planet for the ticket
        Planet toPlanet = new Planet();
        toPlanet.setId("MARS");
        toPlanet.setName("Mars");

        // Set the client, fromPlanet, and toPlanet for the ticket
        ticket.setClient(client);
        ticket.setFromPlanet(fromPlanet);
        ticket.setToPlanet(toPlanet);

        // Verify that the ID was set correctly
        assertEquals(1L, ticket.getId());

        // Verify that the creation time is not null
        assertNotNull(ticket.getCreatedAt());

        // Verify that the client was set correctly
        assertEquals(client, ticket.getClient());

        // Verify that the 'from' planet was set correctly
        assertEquals(fromPlanet, ticket.getFromPlanet());

        // Verify that the 'to' planet was set correctly
        assertEquals(toPlanet, ticket.getToPlanet());
    }
}
