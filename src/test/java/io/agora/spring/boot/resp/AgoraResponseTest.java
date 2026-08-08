package io.agora.spring.boot.resp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgoraResponseTest {

    @Test
    void shouldCreateWithDefaults() {
        AgoraResponse response = new AgoraResponse();
        assertEquals(0, response.getCode());
        assertFalse(response.isSuccess());
    }

    @Test
    void shouldSetAndGetCode() {
        AgoraResponse response = new AgoraResponse();
        response.setCode(200);
        assertEquals(200, response.getCode());
    }

    @Test
    void shouldSetAndGetSuccess() {
        AgoraResponse response = new AgoraResponse();
        response.setSuccess(true);
        assertTrue(response.isSuccess());
    }

    @Test
    void shouldImplementEqualsAndHashCode() {
        AgoraResponse r1 = new AgoraResponse();
        r1.setCode(200);
        r1.setSuccess(true);
        AgoraResponse r2 = new AgoraResponse();
        r2.setCode(200);
        r2.setSuccess(true);
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void shouldImplementToString() {
        AgoraResponse response = new AgoraResponse();
        assertNotNull(response.toString());
    }
}
