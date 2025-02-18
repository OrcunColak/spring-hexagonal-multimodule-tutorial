package com.colak.dataaccess.i18n;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LocalizedMessage {

    @Id
    private Long id;

    private String messageKey;

    private String messageValue;

    private String language;
}
