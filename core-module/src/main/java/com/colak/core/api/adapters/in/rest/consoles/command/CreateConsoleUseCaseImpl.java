package com.colak.core.api.adapters.in.rest.consoles.command;

import com.colak.core.api.ports.in.rest.consoles.command.CreateConsoleUseCase;
import com.colak.core.api.ports.in.rest.consoles.model.ConsoleCommand;
import com.colak.core.model.consoles.ConsoleModel;
import org.springframework.stereotype.Service;

@Service
public class CreateConsoleUseCaseImpl implements CreateConsoleUseCase {

    @Override
    public ConsoleModel create(ConsoleCommand consoleCommand) {
        return null;
    }
}
