package io.agora.cloud.resp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CloudRecordingQueryResponseTest {

    @Test
    void shouldCreateWithDefaults() {
        CloudRecordingQueryResponse response = new CloudRecordingQueryResponse();
        assertNotNull(response);
    }

    @Test
    void shouldInheritFromAgoraResponse() {
        CloudRecordingQueryResponse response = new CloudRecordingQueryResponse();
        response.setCode(200);
        response.setSuccess(true);
        assertEquals(200, response.getCode());
        assertTrue(response.isSuccess());
    }

    @Test
    void shouldImplementEqualsAndHashCode() {
        CloudRecordingQueryResponse r1 = new CloudRecordingQueryResponse();
        r1.setCode(200);
        CloudRecordingQueryResponse r2 = new CloudRecordingQueryResponse();
        r2.setCode(200);
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void shouldImplementToString() {
        CloudRecordingQueryResponse response = new CloudRecordingQueryResponse();
        assertNotNull(response.toString());
    }
}
