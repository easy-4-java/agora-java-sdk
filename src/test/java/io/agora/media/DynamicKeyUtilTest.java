package io.agora.media;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicKeyUtilTest {

    @Test
    void shouldEncodeHmacWithKeyString() throws Exception {
        byte[] result = DynamicKeyUtil.encodeHMAC("testkey", "testmsg".getBytes());
        assertNotNull(result);
        assertEquals(20, result.length); // SHA1 produces 20 bytes
    }

    @Test
    void shouldEncodeHmacWithKeyBytes() throws Exception {
        byte[] result = DynamicKeyUtil.encodeHMAC("testkey".getBytes(), "testmsg".getBytes());
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    void shouldProduceConsistentHmac() throws Exception {
        byte[] sig1 = DynamicKeyUtil.encodeHMAC("key", "msg".getBytes());
        byte[] sig2 = DynamicKeyUtil.encodeHMAC("key", "msg".getBytes());
        assertArrayEquals(sig1, sig2);
    }

    @Test
    void shouldProduceDifferentHmacForDifferentKeys() throws Exception {
        byte[] sig1 = DynamicKeyUtil.encodeHMAC("key1", "msg".getBytes());
        byte[] sig2 = DynamicKeyUtil.encodeHMAC("key2", "msg".getBytes());
        assertFalse(java.util.Arrays.equals(sig1, sig2));
    }

    @Test
    void shouldConvertBytesToHex() {
        byte[] data = {(byte) 0xAB, (byte) 0xCD, (byte) 0xEF};
        String hex = DynamicKeyUtil.bytesToHex(data);
        assertEquals("abcdef", hex);
    }

    @Test
    void shouldConvertEmptyBytesToEmptyHex() {
        String hex = DynamicKeyUtil.bytesToHex(new byte[0]);
        assertEquals("", hex);
    }

    @Test
    void shouldConvertSingleByteToHex() {
        assertEquals("ff", DynamicKeyUtil.bytesToHex(new byte[]{(byte) 0xFF}));
        assertEquals("00", DynamicKeyUtil.bytesToHex(new byte[]{0}));
        assertEquals("0a", DynamicKeyUtil.bytesToHex(new byte[]{10}));
    }
}
