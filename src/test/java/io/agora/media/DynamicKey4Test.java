package io.agora.media;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicKey4Test {

    private static final String APP_ID = "970ca35de60c44645bba356700014e37";
    private static final String APP_CERT = "5dfd83dd06fd4c838a640e2b8209bcae";

    @Test
    void shouldGeneratePublicSharingKey() throws Exception {
        String key = DynamicKey4.generatePublicSharingKey(APP_ID, APP_CERT, "channel", 1234, 5678, 100L, 0);
        assertNotNull(key);
        assertTrue(key.startsWith("004"));
    }

    @Test
    void shouldGenerateRecordingKey() throws Exception {
        String key = DynamicKey4.generateRecordingKey(APP_ID, APP_CERT, "channel", 1234, 5678, 100L, 0);
        assertNotNull(key);
        assertTrue(key.startsWith("004"));
    }

    @Test
    void shouldGenerateMediaChannelKey() throws Exception {
        String key = DynamicKey4.generateMediaChannelKey(APP_ID, APP_CERT, "channel", 1234, 5678, 100L, 0);
        assertNotNull(key);
        assertTrue(key.startsWith("004"));
    }

    @Test
    void shouldGenerateDifferentKeysForDifferentServices() throws Exception {
        String pubKey = DynamicKey4.generatePublicSharingKey(APP_ID, APP_CERT, "channel", 1234, 5678, 100L, 0);
        String recKey = DynamicKey4.generateRecordingKey(APP_ID, APP_CERT, "channel", 1234, 5678, 100L, 0);
        String medKey = DynamicKey4.generateMediaChannelKey(APP_ID, APP_CERT, "channel", 1234, 5678, 100L, 0);
        assertNotEquals(pubKey, recKey);
        assertNotEquals(recKey, medKey);
    }
}
