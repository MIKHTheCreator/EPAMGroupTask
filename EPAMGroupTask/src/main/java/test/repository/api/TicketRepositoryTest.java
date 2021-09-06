package test.repository.api;

import com.epam.jwd.repository.impl.TicketRepositoryImpl;
import com.epam.jwd.repository.model.Ticket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketRepositoryTest {

    private static TicketRepositoryImpl repository;
    private static List<Ticket> storage;
    private static final long ID = 0L;
    private static final String NAME = "Titanic";
    private Ticket ticket;

    @BeforeAll
    static void init() {
        repository = TicketRepositoryImpl.getInstance();
        storage = repository.findAll();
    }

    @BeforeEach
    void setUp() {
        ticket = new Ticket(ID, NAME, 14.00, true);
        repository.save(ticket);
    }

    @AfterEach
    void tearDown() {
        storage.clear();
    }

    @Test
    void shouldFindTicketByIdWhenIdExists() {
        assertSame(ticket, repository.findById(ID));
    }

    @Test
    void shouldReturnNullWhenIdDoesNotExist() {
        storage.clear();
        assertNull(repository.findById(ID));
    }

    @Test
    void shouldFindTicketByNameWhenNameExists() {
        List<Ticket> tickets = repository.findByMovieName(NAME);
        assertEquals(1, tickets.size());
        assertSame(ticket, tickets.get(0));
    }

    @Test
    void shouldReturnEmptyListWhenNameDoesNotExist() {
        storage.clear();
        assertEquals(0, repository.findByMovieName(NAME).size());
    }
}
