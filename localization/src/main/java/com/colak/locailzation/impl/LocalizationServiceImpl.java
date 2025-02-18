package com.colak.locailzation.impl;

import com.colak.dataaccess.i18n.LocalizedMessageEntity;
import com.colak.locailzation.LocalizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class LocalizationServiceImpl implements LocalizationService {

    private final LocalizationTransactionalService localizationTransactionalService;

    @Override
    public String localizeMessage(String localizationKey, Object... arguments) {

        // Get the user's locale. This will never return null
        Locale locale = LocaleContextHolder.getLocale();

        String language = toLanguageIdentifier(locale);

        LocalizedMessageEntity entity = localizationTransactionalService
                .findByLocalizationKeyAndLanguage(localizationKey, language);

        // If not found fallback to system locale
        if (entity == null) {

            Locale sytemLocale = Locale.getDefault();
            String systemLanguage = toLanguageIdentifier(sytemLocale);

            // Optimization : If user's locale is different from system locale
            if (!language.equals(systemLanguage)) {
                language = systemLanguage;
                entity = localizationTransactionalService
                        .findByLocalizationKeyAndLanguage(localizationKey, systemLanguage);
            }
        }
        return formatMessage(entity, localizationKey, language, arguments);

    }

    @Override
    public String localizeMessage(String localizationKey, String language, Object... arguments) {
        LocalizedMessageEntity entity = localizationTransactionalService
                .findByLocalizationKeyAndLanguage(localizationKey, language);
        return formatMessage(entity, localizationKey, language, arguments);
    }

    private String toLanguageIdentifier(Locale locale) {
        return locale.getLanguage() + "_" + locale.getCountry();
    }

    private String formatMessage(LocalizedMessageEntity entity,
                                 String localizationKey,
                                 String language,
                                 Object... arguments) {
        if (entity == null) {
            return String.format("No value found in database for localizationKey : %s language : %s parameters : %s",
                    localizationKey, language, Arrays.toString(arguments));
        }

        String localizedMessage = entity.getLocalizedMessage();

        // If we have fewer arguments than expected, MessageFormat.format does not throw an exception
        // but simply returns the original localizedMessage
        // Give a warning about this situation
        if (hasIncompleteArguments(localizedMessage, arguments)) {
            return String.format("The localizationKey : %s has incomplete arguments", localizationKey);
        }

        // If localizedMessage pattern is not correct, MessageFormat.format throws IllegalArgumentException
        try {
            return MessageFormat.format(localizedMessage, arguments);
        } catch (IllegalArgumentException exception) {
            return String.format("The localizationKey : %s has illegal pattern : %s", localizationKey, localizedMessage);
        }
    }

    private boolean hasIncompleteArguments(String localizedMessage, Object... arguments) {
        // Regular expression to find placeholders like {0}, {1} etc.
        // \\{ matches literal {. Since { is a special character in regex, we escape it with \\
        // \d+ captures one or more digits
        // } matches literal to close the placeholder
        Pattern placeholderPattern = Pattern.compile("\\{\\d+}");
        Matcher matcher = placeholderPattern.matcher(localizedMessage);

        long placeholderCount = matcher.results()
                .count();

        return arguments.length < placeholderCount;
    }
}
