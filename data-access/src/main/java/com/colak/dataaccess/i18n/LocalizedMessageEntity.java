package com.colak.dataaccess.i18n;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "uq_localized_message_key_language",
                columnNames = {"localizationKey", "language"})
)
@Getter
@Setter
public class LocalizedMessageEntity {

    @Id
    private Long id;

    @Column(columnDefinition = "NVARCHAR(500)", nullable = false)
    private String localizationKey;

    @Column(columnDefinition = "NVARCHAR(10)", nullable = false)
    private String language;

    @Column(columnDefinition = "NVARCHAR(1000)", nullable = false)
    private String localizedMessage;
}
