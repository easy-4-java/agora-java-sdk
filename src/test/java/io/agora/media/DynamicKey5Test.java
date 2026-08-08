package io.agora.media;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicKey5Test {

    private static final String APP_ID = "970ca35de60c44645bba356700014e37";
    private static final String APP_CERT = "5dfd83dd06fd4c838a640e2b8209bcae";

    @Test
    void shouldHaveCorrectVersion() {
        assertEquals("005", DynamicKey5.version);
    }

    @Test
    void shouldHaveCorrectServiceTypeConstants() {
        assertEquals(1, DynamicKey5.MEDIA_CHANNEL_SERVICE);
        assertEquals(2, DynamicKey5.RECORDING_SERVICE);
        assertEquals(3, DynamicKey5.PUBLIC_SHARING_SERVICE);
        assertEquals(4, DynamicKey5.IN_CHANNEL_PERMISSION);
    }

    @Test
    void shouldHaveCorrectPermissionKeyConstants() {
        assertEquals(1, DynamicKey5.ALLOW_UPLOAD_IN_CHANNEL);
    }

    @Test
    void shouldGeneratePublicSharingKey() throws Exception {
        String key = DynamicKey5.generatePublicSharingKey(APP_ID, APP_CERT, "channel", 1234, 5678, 100L, 0);
        assertNotNull(key);
        assertTrue(key.startsWith("005"));
    }

    @Test
    void shouldGenerateRecordingKey() throws Exception {
        String key = DynamicKey5.generateRecordingKey(APP_ID, APP_CERT, "channel", 1234, 5678, 100L, 0);
        assertNotNull(key);
        assertTrue(key.startsWith("005"));
    }

    @Test
    void shouldGenerateMediaChannelKey() throws Exception {
        String key = DynamicKey5.generateMediaChannelKey(APP_ID, APP_CERT, "channel", 1234, 5678, 100L, 0);
        assertNotNull(key);
        assertTrue(key.startsWith("005"));
    }

    @Test
    void shouldGenerateInChannelPermissionKey() throws Exception {
        String key = DynamicKey5.generateInChannelPermissionKey(APP_ID, APP_CERT, "channel", 1234, 5678, 100L, 0, DynamicKey5.noUpload);
        assertNotNull(key);
        assertTrue(key.startsWith("005"));
    }

    @Test
    void shouldGenerateDifferentKeysForDifferentServices() throws Exception {
        String pubKey = DynamicKey5.generatePublicSharingKey(APP_ID, APP_CERT, "channel", 1234, 5678, 100L, 0);
        String recKey = DynamicKey5.generateRecordingKey(APP_ID, APP_CERT, "channel", 1234, 5678, 100L, 0);
        assertNotEquals(pubKey, recKey);
    }

    @Test
    void shouldParseValidDynamicKey5() throws Exception {
        String key = DynamicKey5.generateMediaChannelKey(APP_ID, APP_CERT, "channel", 1234, 5678, 100L, 0);
        DynamicKey5 dk5 = new DynamicKey5();
        assertTrue(dk5.fromString(key));
        assertNotNull(dk5.content);
    }

    @Test
    void shouldReturnFalseForInvalidVersion() {
        DynamicKey5 dk5 = new DynamicKey5();
        assertFalse(dk5.fromString("004invalid"));
    }

    @Test
    void shouldReturnFalseForEmptyBase64() {
        DynamicKey5 dk5 = new DynamicKey5();
        // "005" followed by invalid base64
        assertFalse(dk5.fromString("005"));
    }

    @Test
    void shouldHaveCorrectUploadConstants() {
        assertEquals("0", DynamicKey5.noUpload);
        assertEquals("3", DynamicKey5.audioVideoUpload);
    }
}
