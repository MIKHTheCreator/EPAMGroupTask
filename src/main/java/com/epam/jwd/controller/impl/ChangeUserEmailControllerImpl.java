package com.epam.jwd.controller.impl;

import com.epam.jwd.controller.api.ExecuteController;
import com.epam.jwd.service.exception.IllegalEmailException;
import com.epam.jwd.service.exception.UserNotActiveException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeUserEmailControllerImpl implements ExecuteController {
    private static final Logger log = LogManager.getLogger(ChangeUserEmailControllerImpl.class);

    @Override
    public void execute() {
        System.out.println("Введите новый email");
        try {
            ControllerImpl.ourUserService.changeUserEmail(ControllerImpl.inputFromUser.nextLine());
        } catch (IllegalEmailException e) {
            log.error("Некорректный email", e);
            execute();
        } catch (UserNotActiveException e) {
            log.error("Пользователь недоступен", e);
        }

    }
    //  private static final String
    //  private static final String
}
