package com.colak.core.api.ports.in.rest.consoles.command;

import com.colak.core.model.consoles.ConsoleModel;
import com.colak.core.api.ports.in.rest.consoles.model.ConsoleCommand;

public interface UpdateConsoleUseCase {

    ConsoleModel create(ConsoleCommand consoleCommand);
}
