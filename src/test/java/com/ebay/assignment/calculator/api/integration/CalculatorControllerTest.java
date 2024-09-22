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
    public void getSubtract() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/api/calculator/subtract?minuend=5&subtrahend=1&argumentsType=INTEGER", String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertThat(response.getBody()).isEqualTo("4");
    }

    @Test
    public void getMultiply() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/api/calculator/multiply?multiplicand=5&multiplier=10&argumentsType=INTEGER", String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertThat(response.getBody()).isEqualTo("50");
    }

    @Test
    public void getDivide() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/api/calculator/divide?dividend=5&divisor=2&argumentsType=INTEGER", String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertThat(response.getBody()).isEqualTo("2");
    }
}
