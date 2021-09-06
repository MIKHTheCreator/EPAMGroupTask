package com.epam.jwd.controller.impl;

import com.epam.jwd.controller.api.ExecuteController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetTicketsByMovieNameControllerImpl implements ExecuteController {
    private static final Logger log = LogManager.getLogger(GetTicketsByMovieNameControllerImpl.class);

    @Override
    public void execute() {
        System.out.println("Введите название фильма :");
        ControllerImpl.ourUserService.getTicketsByMovieName(ControllerImpl.inputFromUser.nextLine());
        System.out.println("Данного фильма не существут. Попробуйте ввести еще.");
        execute();
    }
}


