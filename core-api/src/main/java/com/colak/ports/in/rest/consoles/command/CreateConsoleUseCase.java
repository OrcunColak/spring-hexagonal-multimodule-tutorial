package com.colak.ports.in.rest.consoles.command;

import com.colak.consoles.ConsoleModel;
import com.colak.ports.in.rest.consoles.model.ConsoleCommand;

public interface CreateConsoleUseCase {

    ConsoleModel create(ConsoleCommand consoleCommand);
}
