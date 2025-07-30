package com.paulofranklim.inditex.test.infrastructure.api.v1.handler;

import com.paulofranklim.inditex.test.domain.exception.PriceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ApiExceptionHandlerTest {

    private ApiExceptionHandler apiExceptionHandler;

    @BeforeEach
    void setUp() {
        apiExceptionHandler = new ApiExceptionHandler();
    }

    @Test
    void handlePriceNotFound_shouldReturnNotFoundStatusAndDetails() {
        Long brandId = 1L;
        Long productId = 35455L;
        LocalDateTime date = LocalDateTime.now();
        PriceNotFoundException ex = new PriceNotFoundException(brandId, productId, date);

        ResponseEntity<Object> responseEntity = apiExceptionHandler.handlePriceNotFound(ex);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        Map<String, Object> body = (Map<String, Object>) responseEntity.getBody();
        assertEquals("Price not found", body.get("error"));
        assertEquals(ex.getMessage(), body.get("details"));
        assertNotNull(body.get("timestamp"));
    }

    @Test
    void handleBadRequest_shouldReturnBadRequestStatusAndDetails() {
        MethodArgumentTypeMismatchException ex = mock(MethodArgumentTypeMismatchException.class);
        when(ex.getMessage()).thenReturn("Failed to convert value");

        ResponseEntity<Object> responseEntity = apiExceptionHandler.handleBadRequest(ex);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        Map<String, Object> body = (Map<String, Object>) responseEntity.getBody();
        assertEquals("Invalid request", body.get("error"));
        assertEquals(ex.getMessage(), body.get("details"));
        assertNotNull(body.get("timestamp"));
    }

    @Test
    void handleGeneric_shouldReturnInternalServerErrorStatusAndDetails() {
        Exception ex = new RuntimeException("Something went wrong");

        ResponseEntity<Object> responseEntity = apiExceptionHandler.handleGeneric(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        Map<String, Object> body = (Map<String, Object>) responseEntity.getBody();
        assertEquals("Internal error", body.get("error"));
        assertEquals(ex.getMessage(), body.get("details"));
        assertNotNull(body.get("timestamp"));
    }
}
