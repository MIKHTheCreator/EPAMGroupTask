package com.epam.jwd.controller.impl;

import com.epam.jwd.controller.api.ExecuteController;
import com.epam.jwd.service.exception.UserNotActiveException;
import com.epam.jwd.view.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckBalanceControllerImpl implements ExecuteController {
    private static final Logger log = LogManager.getLogger(CheckBalanceControllerImpl.class);

    @Override
    public void execute() {
        try {
            Menu.getMessageBalance(ControllerImpl.ourUserService.getBalance());
        } catch (UserNotActiveException e) {
            log.error("Пользователь недоступен", e);
        }

    }
    //  private static final String
    //  private static final String
}
