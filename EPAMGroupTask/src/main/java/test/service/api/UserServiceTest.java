package test.service.api;

import com.epam.jwd.repository.impl.TicketRepositoryImpl;
import com.epam.jwd.repository.impl.UserRepositoryImpl;
import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.repository.model.User;
import com.epam.jwd.service.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private static UserServiceImpl service;
    private static List<User> userStorage;
    private static List<Ticket> ticketStorage;
    private static User validUser;

    @BeforeAll
    static void init() {
        userStorage = UserRepositoryImpl.getInstance().findAll();
        ticketStorage = TicketRepositoryImpl.getInstance().findAll();
    }

    @BeforeEach
    void setUp() {
        service = new UserServiceImpl();
        validUser = new User(0L, "Jack", 100, 20, "jack@mail.ru");
        service.registration(validUser);
    }

    @AfterEach
    void tearDown() {
        userStorage.clear();
        ticketStorage.clear();
    }

    @Test
    void shouldRegisterUserWhenTheUserIsValid() {
        User currentUser = userStorage.remove(0);
        assertSame(validUser, currentUser);
    }

    @Test
    void shouldBuyTicketWhenTheFilmExistsAndEnoughCash() {
        String filmName = "Titanic";
        double price = 14.00;
        double expectedCash = validUser.getBalance() - price;
        Ticket ticket =
                new Ticket(0L, filmName, price, true);
        ticketStorage.add(ticket);
        service.buyTicket(filmName);
        assertEquals(0, ticketStorage.size());
        assertSame(ticket, validUser.getTickets().remove(0));
        assertEquals(expectedCash, validUser.getBalance(),
                "Money should decrease, when buying");
    }

    @Test
    void shouldNotDoAnythingWhenTheFilmDoesNotExist() {
        String filmName = "Titanic";
        ticketStorage.clear();
        service.buyTicket(filmName);
        assertEquals(0, validUser.getTickets().size());
    }

    @Test
    void shouldNotBuyTicketWhenTheUserDoesNotHaveEnoughCash() {
        User poorUser = new User(0L, "Jack", 0, 20, "jack@mail.ru");
        service.registration(poorUser);
        String filmName = "Titanic";
        Ticket ticket =
                new Ticket(0L, filmName, 14.00, true);
        ticketStorage.add(ticket);
        service.buyTicket(filmName);
        assertEquals(0, poorUser.getTickets().size(),
                "User should have no tickets");
        assertSame(ticket, ticketStorage.remove(0),
                "Ticket should stay in storage");
    }
}
