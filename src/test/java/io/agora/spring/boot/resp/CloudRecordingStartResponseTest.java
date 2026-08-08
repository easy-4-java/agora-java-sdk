package io.agora.spring.boot.resp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CloudRecordingStartResponseTest {

    @Test
    void shouldCreateWithDefaults() {
        CloudRecordingStartResponse response = new CloudRecordingStartResponse();
        assertNotNull(response);
    }

    @Test
    void shouldSetAndGetSid() {
        CloudRecordingStartResponse response = new CloudRecordingStartResponse();
        response.setSid("test-sid");
        assertEquals("test-sid", response.getSid());
    }

    @Test
    void shouldSetAndGetResourceId() {
        CloudRecordingStartResponse response = new CloudRecordingStartResponse();
        response.setResourceId("test-resource-id");
        assertEquals("test-resource-id", response.getResourceId());
    }

    @Test
    void shouldInheritFromAgoraResponse() {
        CloudRecordingStartResponse response = new CloudRecordingStartResponse();
        response.setCode(200);
        response.setSuccess(true);
        assertEquals(200, response.getCode());
        assertTrue(response.isSuccess());
    }

    @Test
    void shouldImplementEqualsAndHashCode() {
        CloudRecordingStartResponse r1 = new CloudRecordingStartResponse();
        r1.setSid("s1");
        CloudRecordingStartResponse r2 = new CloudRecordingStartResponse();
        r2.setSid("s1");
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void shouldImplementToString() {
        CloudRecordingStartResponse response = new CloudRecordingStartResponse();
        assertNotNull(response.toString());
    }
}
