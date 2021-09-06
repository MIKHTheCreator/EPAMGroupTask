package com.epam.jwd.controller;

import com.epam.jwd.repository.model.User;
import com.epam.jwd.service.api.SellerService;
import com.epam.jwd.service.api.UserService;
import com.epam.jwd.service.exception.IllegalAgeException;
import com.epam.jwd.service.exception.IllegalEmailException;
import com.epam.jwd.service.exception.IllegalNameSizeException;
import com.epam.jwd.service.exception.NoCashException;
import com.epam.jwd.service.exception.NoUserException;
import com.epam.jwd.service.exception.UnavailableSaveTicketException;
import com.epam.jwd.service.impl.SellerServiceImpl;
import com.epam.jwd.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static com.epam.jwd.service.logic.IdGenerator.generateId;
import static com.epam.jwd.view.Menu.*;

public class Controller {

    private static final Logger log = LogManager.getLogger(Controller.class);

    public static void main(String[] args) {

        UserService service = new UserServiceImpl();
        SellerService seller = new SellerServiceImpl();

        try {
            seller.createFranceMovieTicketList();
            seller.createRussianMovieTicketList();
            seller.createUSAMovieTicketList();
        } catch (UnavailableSaveTicketException e) {
            log.error(e);
        }

        printMenu();
        printChoose();

        Scanner scanner = getScanner();
        while (scanner.hasNext()) {
            String function = scanner.nextLine();
            switch (function) {
                case "1" -> {
                    signIn(service);
                }
                case "2" -> {
                    registration(service);
                }
                case "3" -> {
                    buyTicket(service);
                }
                case "4" -> {
                    checkBalance(service);
                }
                case "5" -> {
                    getTicketPrice(service);
                }
                case "6" -> {
                    changeUserName(service);
                }
                case "7" -> {
                    changeUserAge(service);
                }
                case "8" -> {
                    changeUserEmail(service);
                }
                case "9" -> {
                    getTicketList(service);
                }
                case "10" -> {
                    signOut(service);
                }
            }
        }
    }

    private static void signOut(UserService service) {
        service.signOut();
        printMenu();
    }

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }

    public static void signIn(UserService service) {
        Scanner scanner = getScanner();
        printInputUserName();

        if (scanner.hasNext()) {
            String userName = scanner.nextLine();

            try {
                service.signIn(userName);
                printFunctions();
            } catch (NoUserException e) {
                log.error(e);
                printMenu();
            }
        }
    }

    public static void registration(UserService service) {
        Scanner scanner = getScanner();
        printInputUserName();

        while (scanner.hasNext()) {
            String userName = scanner.nextLine();
            int age = 0;
            double balance = 0;
            String email = "";
            printInputUserAge();

            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                printInputUserBalance();
            } else {
                printErrorAge();
                printMenu();
                break;
            }

            if (scanner.hasNextDouble()) {
                balance = scanner.nextDouble();
                printInputUserEMAIL();
            } else {
                printErrorEmail();
                printMenu();
                break;
            }

            if (scanner.hasNext()) {
                email = scanner.nextLine();
            } else {
                printErrorEmail();
                printMenu();
                break;
            }
            service.registration(new User(generateId(), userName, balance, age, email));
            printFunctions();
            break;
        }
    }

    public static void buyTicket(UserService service) {
        Scanner scanner = getScanner();
        printInputMovieName();

        if (scanner.hasNext()) {
            String movieName = scanner.nextLine();
            try {
                service.buyTicket(movieName);
            } catch (NoCashException e) {
                printNoCash();
                log.error(e);
            }
        } else {
            printErrorMovieMessage();
        }
        printFunctions();
    }

    public static void checkBalance(UserService service) {
        printUserBalance(service.checkBalance());
    }

    public static void getTicketPrice(UserService service) {
        Scanner scanner = getScanner();

        printInputMovieName();

        if (scanner.hasNext()) {
            String movieName = scanner.nextLine();
            printTicketPrice(service.checkTicketPrice(movieName));
        } else {
            printErrorMovieMessage();
        }
        printFunctions();
    }

    public static void changeUserName(UserService service) {
        Scanner scanner = getScanner();
        printInputUserName();

        if (scanner.hasNext()) {
            try {
                service.changeUserName(scanner.nextLine());
            } catch (IllegalNameSizeException e) {
                printErrorName();
                log.error(e);
            }
        }
        printFunctions();
    }

    public static void changeUserAge(UserService service) {
        Scanner scanner = getScanner();
        printInputUserAge();

        if (scanner.hasNextInt()) {
            try {
                service.changeUserAge(scanner.nextInt());
            } catch (IllegalAgeException e) {
                printErrorAge();
                log.error(e);
            }
        }
        printFunctions();
    }

    public static void changeUserEmail(UserService service) {
        Scanner scanner = getScanner();
        printInputUserEMAIL();

        if (scanner.hasNext()) {
            try {
                service.changeUserEmail(scanner.nextLine());
            } catch (IllegalEmailException e) {
                printErrorEmail();
                log.error(e);
            }
        }
        printFunctions();
    }

    public static void getTicketList(UserService service) {
        printAvailableForKidsTickets();
        service.getAvailableForKidsTickets();
        printFunctions();
    }
}
