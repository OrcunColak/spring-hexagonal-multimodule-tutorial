package com.colak.core.api.adapters.in.rest.consoles.query;

import com.colak.core.api.ports.in.rest.consoles.query.QueryConsoleUseCase;
import com.colak.core.model.consoles.ConsoleModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryConsoleUseCaseImpl implements QueryConsoleUseCase {

    @Override
    public List<ConsoleModel> findAll() {
        return List.of();
    }
}
