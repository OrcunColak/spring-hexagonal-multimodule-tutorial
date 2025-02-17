package com.colak.dataaccess.i18n;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocalizedMessageRepository extends JpaRepository<LocalizedMessage, Long> {

    Optional<LocalizedMessage> findByMessageKeyAndLocale(String messageKey, String locale);
}
