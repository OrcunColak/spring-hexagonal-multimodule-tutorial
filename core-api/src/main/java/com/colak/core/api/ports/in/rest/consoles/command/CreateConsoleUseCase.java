package com.colak.core.api.ports.in.rest.consoles.command;

import com.colak.core.model.consoles.ConsoleModel;
import com.colak.core.api.ports.in.rest.consoles.model.ConsoleCommand;

public interface CreateConsoleUseCase {

    ConsoleModel create(ConsoleCommand consoleCommand);
}
