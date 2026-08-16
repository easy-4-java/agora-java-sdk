package io.agora.cloud.resp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CloudRecordingStopResponseTest {

    @Test
    void shouldCreateWithDefaults() {
        CloudRecordingStopResponse response = new CloudRecordingStopResponse();
        assertNotNull(response);
    }

    @Test
    void shouldInheritFromAgoraResponse() {
        CloudRecordingStopResponse response = new CloudRecordingStopResponse();
        response.setCode(200);
        response.setSuccess(true);
        assertEquals(200, response.getCode());
        assertTrue(response.isSuccess());
    }

    @Test
    void shouldImplementEqualsAndHashCode() {
        CloudRecordingStopResponse r1 = new CloudRecordingStopResponse();
        r1.setCode(200);
        CloudRecordingStopResponse r2 = new CloudRecordingStopResponse();
        r2.setCode(200);
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void shouldImplementToString() {
        CloudRecordingStopResponse response = new CloudRecordingStopResponse();
        assertNotNull(response.toString());
    }
}
