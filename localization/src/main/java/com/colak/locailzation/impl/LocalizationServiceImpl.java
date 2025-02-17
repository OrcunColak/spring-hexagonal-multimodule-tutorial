package com.colak.locailzation.impl;

import com.colak.dataaccess.i18n.LocalizedMessage;
import com.colak.locailzation.LocalizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class LocalizationServiceImpl implements LocalizationService {

    private final LocalizationTransactionalService localizationTransactionalService;

    @Override
    public String localizeMessage(String messageKey, Object... arguments) {

        // Try to fetch the message
        Locale locale = LocaleContextHolder.getLocale();

        String localeVariant = locale.toString();

        LocalizedMessage localizedMessage = localizationTransactionalService
                .findByMessageKeyAndLocale(messageKey, localeVariant);

        // If not found fallback to system locale
        if (localizedMessage == null) {
            Locale sytemLocale = Locale.getDefault();
            localeVariant = sytemLocale.toString();

            localizedMessage = localizationTransactionalService
                    .findByMessageKeyAndLocale(messageKey, localeVariant);
        }
        return formatMessage(localizedMessage, messageKey, localeVariant, arguments);

    }

    @Override
    public String localizeMessage(String messageKey, String localeVariant, Object... arguments) {
        LocalizedMessage localizedMessage = localizationTransactionalService
                .findByMessageKeyAndLocale(messageKey, localeVariant);
        return formatMessage(localizedMessage, messageKey, localeVariant, arguments);
    }

    private String formatMessage(LocalizedMessage localizedMessage,
                                 String messageKey,
                                 String localeVariant,
                                 Object... arguments) {
        if (localizedMessage == null) {
            return String.format("The value is missing for messageKey: %s localeVariant : %s parameters : %s",
                    messageKey, localeVariant, Arrays.toString(arguments));
        }

        String messageValue = localizedMessage.getMessageValue();
        return MessageFormat.format(messageValue, arguments);
    }
}
