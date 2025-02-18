package com.colak.dataaccess.i18n;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizedMessageRepository extends JpaRepository<LocalizedMessageEntity, Long> {

    LocalizedMessageEntity findByLocalizationKeyAndLanguage(String localizationKey, String language);
}
