package io.agora.media;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicKey3Test {

    private static final String APP_ID = "970ca35de60c44645bba356700014e37";
    private static final String APP_CERT = "5dfd83dd06fd4c838a640e2b8209bcae";

    @Test
    void shouldGenerateDynamicKey3() throws Exception {
        String key = DynamicKey3.generate(APP_ID, APP_CERT, "channel", 1234, 5678, 100L, 0);
        assertNotNull(key);
        assertFalse(key.isEmpty());
        assertTrue(key.startsWith("003"));
    }

    @Test
    void shouldGenerateConsistentKey() throws Exception {
        String key1 = DynamicKey3.generate(APP_ID, APP_CERT, "channel", 1234, 5678, 100L, 0);
        String key2 = DynamicKey3.generate(APP_ID, APP_CERT, "channel", 1234, 5678, 100L, 0);
        assertEquals(key1, key2);
    }

    @Test
    void shouldGenerateDifferentKeysForDifferentUids() throws Exception {
        String key1 = DynamicKey3.generate(APP_ID, APP_CERT, "channel", 1234, 5678, 100L, 0);
        String key2 = DynamicKey3.generate(APP_ID, APP_CERT, "channel", 1234, 5678, 200L, 0);
        assertNotEquals(key1, key2);
    }

    @Test
    void shouldGenerateDifferentKeysForDifferentExpiredTs() throws Exception {
        String key1 = DynamicKey3.generate(APP_ID, APP_CERT, "channel", 1234, 5678, 100L, 1000);
        String key2 = DynamicKey3.generate(APP_ID, APP_CERT, "channel", 1234, 5678, 100L, 2000);
        assertNotEquals(key1, key2);
    }
}
