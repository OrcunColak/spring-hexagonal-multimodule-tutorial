package com.colak.ports.in.rest.consoles.query;

import com.colak.consoles.ConsoleModel;

import java.util.List;

public interface QueryConsoleUseCase {

    List<ConsoleModel> findAll();
}
