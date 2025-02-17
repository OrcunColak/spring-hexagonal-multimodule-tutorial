package com.colak.locailzation.impl;

import com.colak.dataaccess.i18n.LocalizedMessage;
import com.colak.locailzation.LocalizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocalizationServiceImpl implements LocalizationService {

    private final LocalizationTransactionalService localizationTransactionalService;

    @Override
    public String localizeMessage(String messageKey, Object... arguments) {

        // Try to fetch the message
        Locale locale = LocaleContextHolder.getLocale();

        String localeVariant = locale.toString();

        Optional<LocalizedMessage> optionalEntity = localizationTransactionalService
                .findByMessageKeyAndLocale(messageKey, localeVariant);

        // If not found fallback to system locale
        if (optionalEntity.isEmpty()) {
            Locale sytemLocale = Locale.getDefault();
            localeVariant = sytemLocale.toString();
            optionalEntity = localizationTransactionalService
                    .findByMessageKeyAndLocale(messageKey, localeVariant);
        }
        return formatMessage(optionalEntity, messageKey, localeVariant, arguments);

    }

    @Override
    public String localizeMessage(String messageKey, String localeVariant, Object... arguments) {
        Optional<LocalizedMessage> optionalEntity = localizationTransactionalService
                .findByMessageKeyAndLocale(messageKey, localeVariant);
        return formatMessage(optionalEntity, messageKey, localeVariant, arguments);
    }

    private String formatMessage(Optional<LocalizedMessage> optionalEntity, String messageKey, String localeVariant, Object[] arguments) {
        return optionalEntity
                .map(entity -> {
                    String messageValue = entity.getMessageValue();
                    return MessageFormat.format(messageValue, arguments);
                })
                .orElseGet(() -> String.format("The value is missing for messageKey: %s localeVariant : %s parameters : %s",
                        messageKey, localeVariant, Arrays.toString(arguments)));
    }
}
