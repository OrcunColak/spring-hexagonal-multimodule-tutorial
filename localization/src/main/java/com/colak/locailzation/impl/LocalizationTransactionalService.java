package com.colak.locailzation.impl;

import com.colak.dataaccess.i18n.LocalizedMessage;
import com.colak.dataaccess.i18n.LocalizedMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocalizationTransactionalService {

    private final LocalizedMessageRepository repository;

    @Transactional(readOnly = true)
    public Optional<LocalizedMessage> findByMessageKeyAndLocale(String messageKey, String locale) {
        return repository.findByMessageKeyAndLocale(messageKey, locale);
    }

}
