package com.colak.core.api.adapters.in.rest.consoles.command;

import com.colak.core.api.ports.in.rest.consoles.command.UpdateConsoleUseCase;
import com.colak.core.api.ports.in.rest.consoles.model.ConsoleCommand;
import com.colak.core.model.consoles.ConsoleModel;
import org.springframework.stereotype.Service;

@Service
public class UpdateConsoleUseCaseImpl implements UpdateConsoleUseCase {

    @Override
    public ConsoleModel create(ConsoleCommand consoleCommand) {
        return null;
    }
}
