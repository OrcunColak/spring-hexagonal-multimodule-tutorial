package com.colak.locailzation;

public interface LocalizationService {

    String localizeMessage(String messageKey, Object... arguments);

    String localizeMessage(String messageKey, String localeVariant, Object... arguments);
}
