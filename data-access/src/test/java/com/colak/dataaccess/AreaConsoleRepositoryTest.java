package com.colak.dataaccess;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
// Create a new SpringBoot context after each test
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ActiveProfiles("test")
class AreaConsoleRepositoryTest {

    @Autowired
    private AreaConsoleRepository repository;

    @Test
    @Order(1)
    void testSave() {

    }


}