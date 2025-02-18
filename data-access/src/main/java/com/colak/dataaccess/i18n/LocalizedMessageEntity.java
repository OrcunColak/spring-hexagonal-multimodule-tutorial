package com.colak.dataaccess.i18n;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LocalizedMessageEntity {

    @Id
    private Long id;

    private String localizationKey;

    private String localizedMessage;

    private String language;
}
