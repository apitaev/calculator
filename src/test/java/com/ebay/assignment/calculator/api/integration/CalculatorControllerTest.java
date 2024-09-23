package com.ebay.assignment.calculator.api.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalculatorControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/api/calculator/", String.class);
        assertThat(response.getBody()).isEqualTo("Greetings from Calculator Service!");
    }

    @Test
    public void getAdd() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/api/calculator/add?firstSummand=5&secondSummand=1&argumentsType=INTEGER", String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertThat(response.getBody()).isEqualTo("6");
    }

    @Test
    public void getAddWithBadRequest() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/api/calculator/add?firstSummand=5&secondSummand=1", String.class);
        assertTrue(response.getStatusCode().is4xxClientError());
        assertThat(response.getBody()).contains("Bad Request");
    }

    @Test
    public void getAddWithStackOverflow() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/api/calculator/add?firstSummand=2147483647&secondSummand=1&argumentsType=INTEGER", String.class);
        assertTrue(response.getStatusCode().is5xxServerError());
        assertThat(response.getBody()).contains("integer overflow");
    }

    @Test
    public void getSubtract() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/api/calculator/subtract?minuend=5&subtrahend=1&argumentsType=INTEGER", String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertThat(response.getBody()).isEqualTo("4");
    }

    @Test
    public void getSubtractWithBadRequest() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/api/calculator/subtract?minuend=5&argumentsType=INTEGER", String.class);
        assertTrue(response.getStatusCode().is4xxClientError());
        assertThat(response.getBody()).contains("Bad Request");
    }

    @Test
    public void getMultiply() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/api/calculator/multiply?multiplicand=5&multiplier=10&argumentsType=INTEGER", String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertThat(response.getBody()).isEqualTo("50");
    }

    @Test
    public void getMultiplyWithBadRequest() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/api/calculator/multiply?multiplier=10&argumentsType=INTEGER", String.class);
        assertTrue(response.getStatusCode().is4xxClientError());
        assertThat(response.getBody()).contains("Bad Request");
    }

    @Test
    public void getMultiplyWithStackOverflow() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/api/calculator/multiply?multiplicand=2147483647&multiplier=10&argumentsType=INTEGER", String.class);
        assertTrue(response.getStatusCode().is5xxServerError());
        assertThat(response.getBody()).contains("integer overflow");
    }

    @Test
    public void getDivide() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/api/calculator/divide?dividend=5&divisor=2&argumentsType=INTEGER", String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertThat(response.getBody()).isEqualTo("2");
    }

    @Test
    public void getDivideWithBadRequest() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/api/calculator/divide?dividend=5&divisor=2", String.class);
        assertTrue(response.getStatusCode().is4xxClientError());
        assertThat(response.getBody()).contains("Bad Request");
    }

    @Test
    public void getDivideWithByZero() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/api/calculator/divide?dividend=5&divisor=0&argumentsType=INTEGER", String.class);
        assertTrue(response.getStatusCode().is5xxServerError());
        // TODO Division by zero is a bad request
        assertThat(response.getBody()).contains("/ by zero");
    }
}
