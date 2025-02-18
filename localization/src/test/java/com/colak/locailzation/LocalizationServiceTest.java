package com.colak.locailzation;

import com.colak.dataaccess.i18n.LocalizedMessageEntity;
import com.colak.dataaccess.i18n.LocalizedMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
// Create a new SpringBoot context after each test
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ActiveProfiles("test")
class LocalizationServiceTest extends BaseSqlServerTest {

    @Autowired
    private LocalizationService localizationService;

    @Autowired
    private LocalizedMessageRepository repository;

    @BeforeEach
    void setUp() {
        // Save message with arguments
        {
            LocalizedMessageEntity entity = new LocalizedMessageEntity();
            entity.setId(1L);
            entity.setLocalizationKey("color");
            entity.setLocalizedMessage("Türkçe renkler {0}");
            entity.setLanguage("tr_TR");
            repository.saveAndFlush(entity);
        }

        {
            // Save message with no arguments
            LocalizedMessageEntity entity = new LocalizedMessageEntity();
            entity.setId(2L);
            entity.setLocalizationKey("days");
            entity.setLocalizedMessage("Türkçe günler");
            entity.setLanguage("tr_TR");
            repository.saveAndFlush(entity);
        }
    }

    @Test
    void testNonExistingMessage() {
        String localizeMessage = localizationService.localizeMessage("foo", 100);
        assertThat(localizeMessage)
                .isEqualTo("No value found in database for localizationKey : foo language : en_US parameters : [100]");
    }

    @Test
    void testMessageWithArguments() {
        String localizedMessage = localizationService.localizeMessage("color", "tr_TR", "çok kolay");
        assertThat(localizedMessage)
                .isEqualTo("Türkçe renkler çok kolay");
    }

    @Test
    void testMessageWithNoArguments() {
        String localizedMessage = localizationService.localizeMessage("days", "tr_TR", "extra arguments");
        assertThat(localizedMessage)
                .isEqualTo("Türkçe günler");
    }
}
