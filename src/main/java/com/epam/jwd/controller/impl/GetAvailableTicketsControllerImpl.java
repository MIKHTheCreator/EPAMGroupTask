package com.epam.jwd.controller.impl;

import com.epam.jwd.controller.api.ExecuteController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetAvailableTicketsControllerImpl implements ExecuteController {
    private static final Logger log = LogManager.getLogger(GetAvailableTicketsControllerImpl.class);

    @Override
    public void execute() {

          ControllerImpl.ourUserService.getAvailableForKidsTickets();
    }
    //  private static final String
    //  private static final String
}
