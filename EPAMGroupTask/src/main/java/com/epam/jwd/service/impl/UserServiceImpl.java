package com.epam.jwd.service.impl;

import com.epam.jwd.repository.api.TicketRepository;
import com.epam.jwd.repository.api.UserRepository;
import com.epam.jwd.repository.impl.TicketRepositoryImpl;
import com.epam.jwd.repository.impl.UserRepositoryImpl;
import com.epam.jwd.repository.model.Ticket;
import com.epam.jwd.repository.model.User;
import com.epam.jwd.service.api.UserService;
import com.epam.jwd.service.exception.IllegalAgeException;
import com.epam.jwd.service.exception.IllegalEmailException;
import com.epam.jwd.service.exception.IllegalNameSizeException;
import com.epam.jwd.service.exception.NoCashException;
import com.epam.jwd.service.exception.NoUserException;
import com.epam.jwd.service.validation.UserValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {

    private static final String INVALID_NAME_MESSAGE = "Name must be 1 symbol or longer";
    private static final String NO_CASH_MESSAGE = "You haven't got enough cash to pay";
    private static final String INVALID_EMAIL = "Invalid email address";
    private static final String ILLEGAL_AGE_EXCEPTION = "Age must be positive number";
    private static final String ERROR_MESSAGE = "There is no user!";
    private User user;
    private final UserRepository<Long, User> userRepository = UserRepositoryImpl.getInstance();
    private final TicketRepository<Long, Ticket> ticketRepository = TicketRepositoryImpl.getInstance();

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public void registration(User user) {
        userRepository.save(user);
        this.user = userRepository.findUser(user);
    }

    @Override
    public double checkBalance() {
        return user.getBalance();
    }

    @Override
    public void changeUserName(String userName) {
        try {
            if(UserValidation.isValidName(userName)){
                user.setName(userName);
            }
        } catch (IllegalNameSizeException e) {
            System.out.println(INVALID_NAME_MESSAGE);

        }
    }

    @Override
    public void changeUserAge(int age) {
        try {
            if(UserValidation.isPositiveAge(age)){
                user.setAge(age);
            }
        } catch (IllegalAgeException e) {
            System.out.println(ILLEGAL_AGE_EXCEPTION);
            log.error( ILLEGAL_AGE_EXCEPTION, e);
        }
    }

    @Override
    public void changeUserEmail(String userEmail) {
        try {
            if(UserValidation.isEmail(userEmail)){
                user.setEmail(userEmail);
            }
        } catch (IllegalEmailException e) {
            System.out.println(INVALID_EMAIL);
            log.error(INVALID_EMAIL, e);
        }
    }

    @Override
    public void buyTicket(String movieName){
        Ticket ticket = ticketRepository.findByMovieName(movieName).stream().findFirst().orElse(null);

        try {
            if(ticket != null){
                if (UserValidation.isEnoughCash(user, ticket.getPrice())) {
                    user.addTicket(ticket);
                    ticketRepository.delete(ticket);
                    user.setBalance(user.getBalance() - ticket.getPrice());
                }
            }
        } catch (NoCashException e) {
            System.out.println(NO_CASH_MESSAGE);
            log.error(NO_CASH_MESSAGE, e);
        }
    }

    @Override
    public double checkTicketPrice(String movieName) {
        return Objects.requireNonNull(ticketRepository.findByMovieName(movieName).stream().findFirst().orElse(null))
                .getPrice();
    }

    @Override
    public List<Ticket> getAvailableForKidsTickets() {
        return ticketRepository.findAllAvailableTicketsForKids();
    }

    @Override
    public List<Ticket> getTicketsByMovieName(String movieName) {
        return ticketRepository.findByMovieName(movieName);
    }

    @Override
    public void signIn(String userName) throws NoUserException {
        this.user = userRepository.findByUserName(userName);
        if(user == null) {
            throw new NoUserException(ERROR_MESSAGE);
        }
    }

    @Override
    public void signOut() {
        this.user = null;
    }
}
