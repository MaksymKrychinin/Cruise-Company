package com.tccompany.tcvalidation;

import com.tccompany.tcvalidation.exception.ValidationException;
import com.tccompany.tcvalidation.utils.ValidationUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ValidationUtilsTest {

    @Mock
    private Validator validator;

    @InjectMocks
    private ValidationUtils validationUtils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validateWithValidEntityShouldNotThrowException() {
        Object validEntity = new Object();
        when(validator.validate(validEntity)).thenReturn(new HashSet<>());

        assertDoesNotThrow(() -> validationUtils.validate(validEntity));
    }

    @Test
    void validateWithInvalidEntityShouldThrowValidationException() {
        Object invalidEntity = new Object();
        Set<ConstraintViolation<Object>> violations = new HashSet<>();
        ConstraintViolation<Object> violation = mock(ConstraintViolation.class);
        when(violation.getMessage()).thenReturn("Invalid field");
        violations.add(violation);
        when(validator.validate(invalidEntity)).thenReturn(violations);

        ValidationException thrown = assertThrows(ValidationException.class, () -> validationUtils.validate(invalidEntity));
        assertTrue(thrown.getMessage().contains("Invalid field"));
    }

    @Test
    void validateWithNullEntityShouldNotThrowException() {
        assertDoesNotThrow(() -> validationUtils.validate(null));
    }

    @Test
    void validateWithMultipleViolationsShouldConcatenateMessages() {
        Object entityWithMultipleViolations = new Object();
        Set<ConstraintViolation<Object>> violations = new HashSet<>();
        ConstraintViolation<Object> firstViolation = mock(ConstraintViolation.class);
        ConstraintViolation<Object> secondViolation = mock(ConstraintViolation.class);
        when(firstViolation.getMessage()).thenReturn("First invalid field");
        when(secondViolation.getMessage()).thenReturn("Second invalid field");
        violations.add(firstViolation);
        violations.add(secondViolation);
        when(validator.validate(entityWithMultipleViolations)).thenReturn(violations);

        ValidationException thrown = assertThrows(ValidationException.class, () -> validationUtils.validate(entityWithMultipleViolations));

        String message = thrown.getMessage();
        assertTrue(message.contains("First invalid field") && message.contains("Second invalid field"), "The error message should contain both violation messages");
    }

    @Test
    void validateWithEntityHavingNoViolationsShouldPass() {
        Object entityWithNoViolations = new Object();
        when(validator.validate(entityWithNoViolations)).thenReturn(new HashSet<>());

        assertDoesNotThrow(() -> validationUtils.validate(entityWithNoViolations));
    }
}