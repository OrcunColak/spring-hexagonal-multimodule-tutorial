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

        // Get the user's locale. This will never return null
        Locale locale = LocaleContextHolder.getLocale();

        String language = toLanguageIdentifier(locale);

        LocalizedMessage localizedMessage = localizationTransactionalService
                .findByMessageKeyAndLanguage(messageKey, language);

        // If not found fallback to system locale
        if (localizedMessage == null) {

            Locale sytemLocale = Locale.getDefault();
            String systemLanguage = toLanguageIdentifier(sytemLocale);

            // Optimization : If user's locale is different from system locale
            if (!language.equals(systemLanguage)) {
                language = systemLanguage;
                localizedMessage = localizationTransactionalService
                        .findByMessageKeyAndLanguage(messageKey, systemLanguage);
            }
        }
        return formatMessage(localizedMessage, messageKey, language, arguments);

    }

    @Override
    public String localizeMessage(String messageKey, String language, Object... arguments) {
        LocalizedMessage localizedMessage = localizationTransactionalService
                .findByMessageKeyAndLanguage(messageKey, language);
        return formatMessage(localizedMessage, messageKey, language, arguments);
    }

    private String toLanguageIdentifier(Locale locale) {
        return locale.getLanguage() + "_" + locale.getCountry();
    }

    private String formatMessage(LocalizedMessage localizedMessage,
                                 String messageKey,
                                 String language,
                                 Object... arguments) {
        if (localizedMessage == null) {
            return String.format("The value is missing for messageKey: %s language : %s parameters : %s",
                    messageKey, language, Arrays.toString(arguments));
        }

        String messageValue = localizedMessage.getMessageValue();
        try {
            return MessageFormat.format(messageValue, arguments);
        } catch (IllegalArgumentException exception) {
            return String.format("The messageKey: %s has illegal argument.", messageKey);
        }
    }
}
