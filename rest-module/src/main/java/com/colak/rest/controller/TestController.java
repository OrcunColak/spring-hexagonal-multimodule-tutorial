package com.colak.rest.controller;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@Valid
public class TestController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> testEndpoint(@RequestBody TestRequest request) {
        return ResponseEntity.ok("Valid request");
    }

    public record TestRequest(TestEnum testEnum, String name) {}

    public enum TestEnum {
        VALUE1, VALUE2, VALUE3
    }
}
