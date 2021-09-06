package com.epam.jwd.controller.impl;

import com.epam.jwd.controller.api.ExecuteController;
import com.epam.jwd.repository.exception.NoFindMovieException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckTicketPriceControllerImpl implements ExecuteController {
    private static final Logger log = LogManager.getLogger(CheckTicketPriceControllerImpl.class);

    @Override
    public void execute() {
        System.out.println("Введите название фильма :");
        try {
            ControllerImpl.ourUserService.checkTicketPrice(ControllerImpl.inputFromUser.nextLine());
        } catch (NoFindMovieException e) {
            log.error("Фильм не найден",e);
            execute();
        }

    }
    //  private static final String
    //  private static final String
}
