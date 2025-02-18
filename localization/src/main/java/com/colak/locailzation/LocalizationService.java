package com.colak.locailzation;

public interface LocalizationService {

    String localizeMessage(String localizationKey, Object... arguments);

    String localizeMessage(String localizationKey, String language, Object... arguments);
}
