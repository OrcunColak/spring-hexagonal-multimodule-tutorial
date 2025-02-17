package com.colak.core.api.ports.in.rest.consoles.query;

import com.colak.core.model.consoles.ConsoleModel;

import java.util.List;

public interface QueryConsoleUseCase {

    List<ConsoleModel> findAll();
}
