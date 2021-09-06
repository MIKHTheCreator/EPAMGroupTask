package com.epam.jwd.controller.impl;

import com.epam.jwd.controller.api.ExecuteController;
import com.epam.jwd.repository.exception.NoFindMovieException;
import com.epam.jwd.service.exception.NoCashException;
import com.epam.jwd.service.exception.UnavailableTicketException;
import com.epam.jwd.service.exception.UserNotActiveException;
import com.epam.jwd.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BuyTicketControllerImpl implements ExecuteController {
    private static final Logger log = LogManager.getLogger(BuyTicketControllerImpl.class);

    //  private static final String
    //  private static final String
    @Override
    public void execute() {
        System.out.println("Какой фильм вы желаете посмотреть :");
        try {
            ControllerImpl.ourUserService.buyTicket(ControllerImpl.inputFromUser.nextLine().trim());
        } catch (UnavailableTicketException e) {
            e.printStackTrace();
        } catch (UserNotActiveException e) {
            log.error("Пользователь не доступен", e);
        } catch (NoCashException e) {
            log.error("Недостаточно средств", e);
        } catch (NoFindMovieException e) {
            log.error("Фильм не найден", e);
        }

    }


}
