package io.agora.spring.boot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestMethodTest {

    @Test
    void shouldHaveAllHttpMethods() {
        RequestMethod[] values = RequestMethod.values();
        assertEquals(8, values.length);
    }

    @Test
    void shouldContainGet() {
        assertNotNull(RequestMethod.GET);
    }

    @Test
    void shouldContainPost() {
        assertNotNull(RequestMethod.POST);
    }

    @Test
    void shouldContainPut() {
        assertNotNull(RequestMethod.PUT);
    }

    @Test
    void shouldContainDelete() {
        assertNotNull(RequestMethod.DELETE);
    }

    @Test
    void shouldContainPatch() {
        assertNotNull(RequestMethod.PATCH);
    }

    @Test
    void shouldContainHead() {
        assertNotNull(RequestMethod.HEAD);
    }

    @Test
    void shouldContainOptions() {
        assertNotNull(RequestMethod.OPTIONS);
    }

    @Test
    void shouldContainTrace() {
        assertNotNull(RequestMethod.TRACE);
    }

    @Test
    void shouldParseFromValue() {
        assertEquals(RequestMethod.GET, RequestMethod.valueOf("GET"));
        assertEquals(RequestMethod.POST, RequestMethod.valueOf("POST"));
    }
}
