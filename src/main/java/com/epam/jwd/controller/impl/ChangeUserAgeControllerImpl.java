package com.epam.jwd.controller.impl;

import com.epam.jwd.controller.api.ExecuteController;
import com.epam.jwd.service.exception.IllegalAgeException;
import com.epam.jwd.service.exception.IllegalNameSizeException;
import com.epam.jwd.service.exception.UserNotActiveException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeUserAgeControllerImpl implements ExecuteController {
    private static final Logger log = LogManager.getLogger(ChangeUserAgeControllerImpl.class);
    private int newAge;

    @Override
    public void execute() {
        System.out.println("Введите новый возраст :");
        try {
            while (!ControllerImpl.inputFromUser.hasNextInt()) {
                System.out.println("Введите цифру.");
                ControllerImpl.inputFromUser.nextInt();
            }
            newAge = ControllerImpl.inputFromUser.nextInt();
            ControllerImpl.ourUserService.changeUserAge(newAge);
        } catch (IllegalAgeException e) {
            log.error("Введите другой возраст", e);
            execute();
        } catch (UserNotActiveException e) {
            log.error("Пользователь недоступен", e);
        }
    }
    //  private static final String
    //  private static final String
}
