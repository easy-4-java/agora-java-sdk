package io.agora.signal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignalingTokenTest {

    @Test
    void shouldGenerateSignalingToken() throws Exception {
        String token = SignalingToken.getToken("970ca35de60c44645bba356700014e37",
                "5dfd83dd06fd4c838a640e2b8209bcae", "user123", 1000000);
        assertNotNull(token);
        assertFalse(token.isEmpty());
        assertTrue(token.startsWith("1:"));
    }

    @Test
    void shouldGenerateConsistentToken() throws Exception {
        String token1 = SignalingToken.getToken("appid", "cert", "user", 1000);
        String token2 = SignalingToken.getToken("appid", "cert", "user", 1000);
        assertEquals(token1, token2);
    }

    @Test
    void shouldGenerateDifferentTokensForDifferentAccounts() throws Exception {
        String token1 = SignalingToken.getToken("appid", "cert", "user1", 1000);
        String token2 = SignalingToken.getToken("appid", "cert", "user2", 1000);
        assertNotEquals(token1, token2);
    }

    @Test
    void shouldGenerateDifferentTokensForDifferentExpiry() throws Exception {
        String token1 = SignalingToken.getToken("appid", "cert", "user", 1000);
        String token2 = SignalingToken.getToken("appid", "cert", "user", 2000);
        assertNotEquals(token1, token2);
    }

    @Test
    void shouldContainAppIdInToken() throws Exception {
        String appId = "970ca35de60c44645bba356700014e37";
        String token = SignalingToken.getToken(appId, "cert", "user", 1000);
        assertTrue(token.contains(appId));
    }

    @Test
    void shouldContainExpiryInToken() throws Exception {
        String token = SignalingToken.getToken("appid", "cert", "user", 12345);
        assertTrue(token.contains("12345"));
    }

    @Test
    void shouldHexlifyBytes() {
        byte[] data = {(byte) 0xAB, (byte) 0xCD, (byte) 0xEF};
        String hex = SignalingToken.hexlify(data);
        assertEquals("abcdef", hex);
    }

    @Test
    void shouldHexlifyEmptyBytes() {
        String hex = SignalingToken.hexlify(new byte[0]);
        assertEquals("", hex);
    }

    @Test
    void shouldHexlifySingleByte() {
        assertEquals("ff", SignalingToken.hexlify(new byte[]{(byte) 0xFF}));
        assertEquals("00", SignalingToken.hexlify(new byte[]{0}));
    }

    @Test
    void shouldHexlifyAllByteValues() {
        byte[] data = new byte[256];
        for (int i = 0; i < 256; i++) {
            data[i] = (byte) i;
        }
        String hex = SignalingToken.hexlify(data);
        assertEquals(512, hex.length());
        assertEquals("00", hex.substring(0, 2));
        assertEquals("ff", hex.substring(510, 512));
    }
}
