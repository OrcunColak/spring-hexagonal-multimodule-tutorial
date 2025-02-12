package com.colak.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {

        // The cause could be
        // com.fasterxml.jackson.core.JsonParseException
        // com.fasterxml.jackson.databind.exc.InvalidFormatException
        if (exception.getCause() instanceof InvalidFormatException invalidFormatException) {
            ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
            problemDetail.setTitle("Invalid Enum or JSON format");
            problemDetail.setDetail("Some fields contain invalid values");

            Map<String, Map<String, Object>> invalidFields = new LinkedHashMap<>();
            for (JsonMappingException.Reference reference : invalidFormatException.getPath()) {
                String fieldName = reference.getFieldName();
                Class<?> targetType = invalidFormatException.getTargetType();

                if (targetType.isEnum()) {
                    Map<String, Object> fieldError = new LinkedHashMap<>();
                    fieldError.put("invalid-value", invalidFormatException.getValue());
                    fieldError.put("allowed-values", List.of(targetType.getEnumConstants()));
                    invalidFields.put(fieldName, fieldError);
                }
            }

            // Attach structured properties to ProblemDetail
            invalidFields.forEach(problemDetail::setProperty);

            return problemDetail;
        }

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Invalid request payload");
        problemDetail.setDetail("The request body contains invalid JSON");
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Validation error");
        problemDetail.setDetail("Method argument validation failed");

        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        // Iterate over each error and set a new property for it
        fieldErrors.forEach(fieldError -> {
            String fieldName = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();
            problemDetail.setProperty(fieldName, errorMessage);
        });

        return problemDetail;
    }
}
