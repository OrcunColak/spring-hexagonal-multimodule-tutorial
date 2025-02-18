package com.colak.locailzation;

import com.colak.dataaccess.i18n.LocalizedMessage;
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
        LocalizedMessage localizedArgumentMessage = new LocalizedMessage();
        localizedArgumentMessage.setId(1L);
        localizedArgumentMessage.setMessageKey("color");
        localizedArgumentMessage.setMessageValue("Türkçe renkler {0}");
        localizedArgumentMessage.setLanguage("tr_TR");
        repository.saveAndFlush(localizedArgumentMessage);

        // Save message with no arguments
        LocalizedMessage localizedNoArgumentMessage = new LocalizedMessage();
        localizedNoArgumentMessage.setId(2L);
        localizedNoArgumentMessage.setMessageKey("days");
        localizedNoArgumentMessage.setMessageValue("Türkçe günler");
        localizedNoArgumentMessage.setLanguage("tr_TR");
        repository.saveAndFlush(localizedNoArgumentMessage);
    }

    @Test
    void testNonExistingMessage() {
        String localizeMessage = localizationService.localizeMessage("foo", 100);
        assertThat(localizeMessage)
                .isEqualTo("The value is missing for messageKey: foo language : en_US parameters : [100]");
    }

    @Test
    void testMessageWithArguments() {
        String localizeMessage = localizationService.localizeMessage("color", "tr_TR", "çok kolay");
        assertThat(localizeMessage)
                .isEqualTo("Türkçe renkler çok kolay");
    }

    @Test
    void testMessageWithNoArguments() {
        String localizeMessage = localizationService.localizeMessage("days", "tr_TR", "extra arguments");
        assertThat(localizeMessage)
                .isEqualTo("Türkçe günler");
    }
}
