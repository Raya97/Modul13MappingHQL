package service;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.example.service.TicketCrudService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TicketCrudServiceTest {

    private TicketCrudService ticketCrudService;
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    /**
     * Sets up the necessary mocks and initializes the TicketCrudService before each test.
     */
    @BeforeEach
    void setUp() {
        sessionFactory = mock(SessionFactory.class);
        session = mock(Session.class);
        transaction = mock(Transaction.class);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        ticketCrudService = new TicketCrudService(sessionFactory);
    }

    /**
     * Tests that an IllegalArgumentException is thrown when the client is null.
     */
    @Test
    void createTicket_ShouldThrowException_WhenClientIsNull() {
        Ticket ticket = new Ticket();
        ticket.setClient(null); // Client is null

        assertThrows(IllegalArgumentException.class, () -> ticketCrudService.createTicket(ticket));

        // Verify that the save method was not called
        verify(session, never()).save(any(Ticket.class));
    }

    /**
     * Tests that an IllegalArgumentException is thrown when the client does not exist in the database.
     */
    @Test
    void createTicket_ShouldThrowException_WhenClientDoesNotExist() {
        Ticket ticket = new Ticket();
        Client nonExistentClient = new Client();
        nonExistentClient.setId(999L); // Set a non-existent client ID
        ticket.setClient(nonExistentClient);

        when(session.get(Client.class, nonExistentClient.getId())).thenReturn(null); // Клиент не существует в базе

        assertThrows(IllegalArgumentException.class, () -> ticketCrudService.createTicket(ticket));

        // Verify that the save method was not called
        verify(session, never()).save(any(Ticket.class));
    }

    /**
     * Tests that an IllegalArgumentException is thrown when the fromPlanet is null.
     */
    @Test
    void createTicket_ShouldThrowException_WhenFromPlanetIsNull() {
        Ticket ticket = new Ticket();
        Client client = new Client();
        client.setId(1L);
        ticket.setClient(client);
        ticket.setFromPlanet(null); // From planet is null

        assertThrows(IllegalArgumentException.class, () -> ticketCrudService.createTicket(ticket));

        // Verify that the save method was not called
        verify(session, never()).save(any(Ticket.class));
    }

    /**
     * Tests that an IllegalArgumentException is thrown when the toPlanet is null.
     */
    @Test
    void createTicket_ShouldThrowException_WhenToPlanetIsNull() {
        Ticket ticket = new Ticket();
        Client client = new Client();
        client.setId(1L);
        ticket.setClient(client);
        Planet fromPlanet = new Planet();
        fromPlanet.setId("MARS");
        ticket.setFromPlanet(fromPlanet);
        ticket.setToPlanet(null); // To planet is null

        assertThrows(IllegalArgumentException.class, () -> ticketCrudService.createTicket(ticket));

        // Verify that the save method was not called
        verify(session, never()).save(any(Ticket.class));
    }

    /**
     * Tests that an IllegalArgumentException is thrown when the fromPlanet does not exist in the database.
     */
    @Test
    void createTicket_ShouldThrowException_WhenFromPlanetDoesNotExist() {
        Ticket ticket = new Ticket();
        Client client = new Client();
        client.setId(1L);
        ticket.setClient(client);
        Planet nonExistentFromPlanet = new Planet();
        nonExistentFromPlanet.setId("NON_EXISTENT_PLANET");
        ticket.setFromPlanet(nonExistentFromPlanet);

        when(session.get(Planet.class, nonExistentFromPlanet.getId())).thenReturn(null);  // From planet does not exist in the database

        assertThrows(IllegalArgumentException.class, () -> ticketCrudService.createTicket(ticket));

        // Verify that the save method was not called
        verify(session, never()).save(any(Ticket.class));
    }

    /**
     * Tests that an IllegalArgumentException is thrown when the toPlanet does not exist in the database.
     */
    @Test
    void createTicket_ShouldThrowException_WhenToPlanetDoesNotExist() {
        // Setup
        Ticket ticket = new Ticket();
        Client client = new Client();
        client.setId(1L);
        ticket.setClient(client);
        Planet fromPlanet = new Planet();
        fromPlanet.setId("MARS");
        ticket.setFromPlanet(fromPlanet);
        Planet nonExistentToPlanet = new Planet();
        nonExistentToPlanet.setId("NON_EXISTENT_PLANET");
        ticket.setToPlanet(nonExistentToPlanet);

        when(session.get(Planet.class, nonExistentToPlanet.getId())).thenReturn(null); // To planet does not exist in the database

        // Verify that the correct exception is thrown
        assertThrows(IllegalArgumentException.class, () -> ticketCrudService.createTicket(ticket));

        // Verify that the save method was not called
        verify(session, never()).save(any(Ticket.class));
    }


}
