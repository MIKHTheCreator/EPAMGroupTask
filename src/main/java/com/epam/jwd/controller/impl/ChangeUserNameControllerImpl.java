package com.epam.jwd.controller.impl;

import com.epam.jwd.controller.api.ExecuteController;
import com.epam.jwd.service.exception.IllegalNameSizeException;
import com.epam.jwd.service.exception.UserNotActiveException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeUserNameControllerImpl implements ExecuteController {
    private static final Logger log = LogManager.getLogger(ChangeUserNameControllerImpl.class);

    @Override
    public void execute() {
        System.out.println("Введите ваше новое имя :");
        try {
            ControllerImpl.ourUserService.changeUserName(ControllerImpl.inputFromUser.nextLine().trim());
        } catch (IllegalNameSizeException e) {
            log.error("Слишком длинное имя",e);
            execute();
        } catch (UserNotActiveException e) {
            log.error("Пользователь недоступен",e);
        }
    }
    //  private static final String
    //  private static final String
}
