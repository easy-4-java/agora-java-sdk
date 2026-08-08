package io.agora.media;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicKeyTest {

    private static final String APP_ID = "970ca35de60c44645bba356700014e37";
    private static final String APP_CERT = "5dfd83dd06fd4c838a640e2b8209bcae";

    @Test
    void shouldGenerateDynamicKey() throws Exception {
        String key = DynamicKey.generate(APP_ID, APP_CERT, "channel", 1234, 5678);
        assertNotNull(key);
        assertFalse(key.isEmpty());
    }

    @Test
    void shouldGenerateConsistentKey() throws Exception {
        String key1 = DynamicKey.generate(APP_ID, APP_CERT, "channel", 1234, 5678);
        String key2 = DynamicKey.generate(APP_ID, APP_CERT, "channel", 1234, 5678);
        assertEquals(key1, key2);
    }

    @Test
    void shouldGenerateDifferentKeysForDifferentChannels() throws Exception {
        String key1 = DynamicKey.generate(APP_ID, APP_CERT, "channel1", 1234, 5678);
        String key2 = DynamicKey.generate(APP_ID, APP_CERT, "channel2", 1234, 5678);
        assertNotEquals(key1, key2);
    }

    @Test
    void shouldGenerateDifferentKeysForDifferentTimestamps() throws Exception {
        String key1 = DynamicKey.generate(APP_ID, APP_CERT, "channel", 1000, 5678);
        String key2 = DynamicKey.generate(APP_ID, APP_CERT, "channel", 2000, 5678);
        assertNotEquals(key1, key2);
    }
}
