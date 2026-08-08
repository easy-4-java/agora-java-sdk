package io.agora.spring.boot.resp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AcquireResourceResponseTest {

    @Test
    void shouldCreateWithDefaults() {
        AcquireResourceResponse response = new AcquireResourceResponse();
        assertNotNull(response);
    }

    @Test
    void shouldSetAndGetResourceId() {
        AcquireResourceResponse response = new AcquireResourceResponse();
        response.setResourceId("res-123");
        assertEquals("res-123", response.getResourceId());
    }

    @Test
    void shouldSetAndGetCname() {
        AcquireResourceResponse response = new AcquireResourceResponse();
        response.setCname("channel-1");
        assertEquals("channel-1", response.getCname());
    }

    @Test
    void shouldInheritFromAgoraResponse() {
        AcquireResourceResponse response = new AcquireResourceResponse();
        response.setCode(200);
        response.setSuccess(true);
        assertEquals(200, response.getCode());
        assertTrue(response.isSuccess());
    }

    @Test
    void shouldImplementEqualsAndHashCode() {
        AcquireResourceResponse r1 = new AcquireResourceResponse();
        r1.setResourceId("r1");
        AcquireResourceResponse r2 = new AcquireResourceResponse();
        r2.setResourceId("r1");
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void shouldImplementToString() {
        AcquireResourceResponse response = new AcquireResourceResponse();
        assertNotNull(response.toString());
    }
}
