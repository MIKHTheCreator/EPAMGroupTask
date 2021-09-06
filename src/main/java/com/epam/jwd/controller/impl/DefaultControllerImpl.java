package com.epam.jwd.controller.impl;

import com.epam.jwd.controller.api.ExecuteController;

public class DefaultControllerImpl implements ExecuteController {
    @Override
    public void execute() {
        System.out.println("Введите существующие ф-ции.");
        ControllerImpl.start();
    }
}
