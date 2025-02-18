package com.colak.locailzation.impl;

import com.colak.dataaccess.i18n.LocalizedMessageEntity;
import com.colak.dataaccess.i18n.LocalizedMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LocalizationTransactionalService {

    private final LocalizedMessageRepository repository;

    @Transactional(readOnly = true)
    public LocalizedMessageEntity findByLocalizationKeyAndLanguage(String messageKey, String language) {
        return repository.findByLocalizationKeyAndLanguage(messageKey, language);
    }

}
