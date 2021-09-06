package com.epam.jwd.controller.impl;

import com.epam.jwd.controller.api.ExecuteController;
import com.epam.jwd.repository.exception.UnavailableSaveUserException;
import com.epam.jwd.repository.model.User;
import com.epam.jwd.service.exception.IllegalEmailException;
import com.epam.jwd.service.exception.UserNotFoundException;
import com.epam.jwd.service.logic.IdGenerator;
import com.epam.jwd.service.validation.UserValidation;
import com.epam.jwd.view.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrationControllerImpl implements ExecuteController {
    private static final Logger log = LogManager.getLogger(RegistrationControllerImpl.class);
    private String name ;
    private double balance;
    private int age;
    private String email;

    @Override
    public void execute() {

        try {
            System.out.println("Введите ваше имя:");
            name = ControllerImpl.inputFromUser.nextLine();

            System.out.println("Введите сколько вы хотите положить на счёт:");
            while (!ControllerImpl.inputFromUser.hasNextInt()) {
                System.out.println("Введите сумму корректно.");
                ControllerImpl.inputFromUser.nextInt();
            }
            balance = Double.parseDouble(ControllerImpl.inputFromUser.nextLine());
            System.out.println("Введите сколько вам лет:");
            while (!ControllerImpl.inputFromUser.hasNextInt()) {
                System.out.println("Введите года корректно.");
                ControllerImpl.inputFromUser.nextInt();
            }
            age = ControllerImpl.inputFromUser.nextInt();
            System.out.println("Введите ваш email:");
            email = ControllerImpl.inputFromUser.nextLine();
            UserValidation.isEmail(email);
            ControllerImpl.ourUserService.registration(new User(IdGenerator.generateId(), name, balance, age, email, true));

        } catch (UserNotFoundException e) {
            log.error("Пользователь не найден", e);
        } catch (UnavailableSaveUserException e) {
            log.error("Не получилось сохранить пользователя", e);
        } catch (IllegalEmailException e) {
            log.error("Вы ввели неправильный email при регистрации", e);
            System.out.println("Введите данные заново");
            execute();
        }

        Menu.getUserMenu();
        ControllerImpl.start();
    }
    //  private static final String
    //  private static final String
}
