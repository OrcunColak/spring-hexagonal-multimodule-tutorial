package com.colak.dataaccess.i18n;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizedMessageRepository extends JpaRepository<LocalizedMessage, Long> {

    LocalizedMessage findByMessageKeyAndLanguage(String messageKey, String language);
}
