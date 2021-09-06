package com.epam.jwd.controller.impl;

import com.epam.jwd.controller.api.ExecuteController;
import com.epam.jwd.service.exception.UserNotFoundException;
import com.epam.jwd.view.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignInControllerImpl implements ExecuteController {
    private static final Logger log = LogManager.getLogger(SignInControllerImpl.class);


    @Override
    public void execute() {

        try {
            System.out.println("Вы зарегистрированы? 1 - да. 2 - нет.");
            ControllerImpl.enterInformation = ControllerImpl.inputFromUser.nextLine();

            if (ControllerImpl.enterInformation.trim().equals("1")) {
                System.out.println("Введите ваше имя пользователя:");
                ControllerImpl.enterInformation = ControllerImpl.inputFromUser.nextLine();
                ControllerImpl.ourUserService.signIn(ControllerImpl.enterInformation);
                Menu.getUserMenu();
                ControllerImpl.start();
            } else if (ControllerImpl.enterInformation.trim().equals("2")) {
                new RegistrationControllerImpl().execute();
            } else {
                System.out.println("Введите цифру корректно.");
                execute();
            }
        } catch (UserNotFoundException e) {
            log.error("Пользователь не найден.", e);
        }

    }

    //  private static final String
    //  private static final String


}
