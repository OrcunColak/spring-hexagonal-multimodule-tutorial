package com.colak.exception;

import com.colak.TestApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest(classes = TestApplication.class) // Use the test app
@AutoConfigureMockMvc
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldReturnBadRequestForInvalidJson() throws Exception {
        // Missing quotes around the value
        String invalidJson = """
                {
                    "testEnum": VALUE1,
                    "name": "Test Name"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andDo(result -> {
                    // Output the JSON response to the console
                    System.out.println("Response Body: " + result.getResponse().getContentAsString());
                })
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Invalid request payload"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.detail").value("The request body contains invalid JSON"));
    }

    @Test
    void shouldReturnBadRequestForInvalidEnum() throws Exception {
        String invalidEnumJson = """
                {
                    "testEnum": "INVALID_VALUE",
                    "name": "Test Name"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidEnumJson))
                .andDo(result -> {
                    // Output the JSON response to the console
                    System.out.println("Response Body: " + result.getResponse().getContentAsString());
                })
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Invalid Enum or JSON format"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.detail").value("Some fields contain invalid values"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.testEnum.invalid-value").value("INVALID_VALUE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.testEnum.allowed-values[0]").value("VALUE1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.testEnum.allowed-values[1]").value("VALUE2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.testEnum.allowed-values[2]").value("VALUE3"));
    }
}