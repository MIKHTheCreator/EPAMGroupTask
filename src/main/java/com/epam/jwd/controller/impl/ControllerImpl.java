package com.epam.jwd.controller.impl;

import com.epam.jwd.controller.api.ExecuteController;
import com.epam.jwd.repository.exception.UnavailableSaveUserException;
import com.epam.jwd.repository.model.User;
import com.epam.jwd.service.api.UserService;
import com.epam.jwd.service.exception.IllegalEmailException;
import com.epam.jwd.service.exception.UserNotFoundException;
import com.epam.jwd.service.impl.UserServiceImpl;
import com.epam.jwd.service.logic.IdGenerator;
import com.epam.jwd.service.validation.UserValidation;
import com.epam.jwd.view.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.Provider;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ControllerImpl {
    private static final Logger log = LogManager.getLogger(ControllerImpl.class);
    private static final Map<String, ExecuteController> ourActives = new HashMap<>();
    public static final Scanner inputFromUser = new Scanner(System.in);
    public static String enterInformation;
    public static final UserService ourUserService = new UserServiceImpl();


    static {
     //   ourActives.put("1", new SignInControllerImpl());
     //   ourActives.put("2", new RegistrationControllerImpl());
        ourActives.put("1", new BuyTicketControllerImpl());
        ourActives.put("2", new GetAllTicketsOrderedByUserControllerImpl());
        ourActives.put("3", new CheckBalanceControllerImpl());
        ourActives.put("4", new GetAvailableForKidsTicketsControllerImpl());
        ourActives.put("5", new GetTicketsByMovieNameControllerImpl());
        ourActives.put("6", new ChangeUserNameControllerImpl());
        ourActives.put("7", new ChangeUserAgeControllerImpl());
        ourActives.put("8", new ChangeUserEmailControllerImpl());
        ourActives.put("9", new GetAvailableTicketsControllerImpl());
        ourActives.put("10", new CheckTicketPriceControllerImpl());
        ourActives.put("11", new DefaultControllerImpl());
        ourActives.put("12", new SignOutControllerImpl());
    }



    public static void start() {
        System.out.println("Введите пункт с которым хотите поработать: ");
        String inputUser = inputFromUser.nextLine();
        ourActives.forEach((key, value) -> {
            if (key == inputUser.trim()) {
                value.execute();
            } else {
                new DefaultControllerImpl().execute();
            }
        });

    }




}
