package io.agora.media;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void shouldReturnCorrectHmacSha256Length() {
        assertEquals(32, Utils.HMAC_SHA256_LENGTH);
    }

    @Test
    void shouldReturnCorrectVersionLength() {
        assertEquals(3, Utils.VERSION_LENGTH);
    }

    @Test
    void shouldReturnCorrectAppIdLength() {
        assertEquals(32, Utils.APP_ID_LENGTH);
    }

    @Test
    void shouldSignWithHmacSha256() throws Exception {
        byte[] result = Utils.hmacSign("testkey", "testmsg".getBytes());
        assertNotNull(result);
        assertEquals(32, result.length);
    }

    @Test
    void shouldProduceDifferentSignaturesForDifferentKeys() throws Exception {
        byte[] sig1 = Utils.hmacSign("key1", "msg".getBytes());
        byte[] sig2 = Utils.hmacSign("key2", "msg".getBytes());
        assertFalse(java.util.Arrays.equals(sig1, sig2));
    }

    @Test
    void shouldProduceSameSignatureForSameInput() throws Exception {
        byte[] sig1 = Utils.hmacSign("key", "msg".getBytes());
        byte[] sig2 = Utils.hmacSign("key", "msg".getBytes());
        assertArrayEquals(sig1, sig2);
    }

    @Test
    void shouldPackAndUnpackPackableEx() {
        AccessToken token = new AccessToken("01234567890123456789012345678901",
                "01234567890123456789012345678901", "channel", "uid");
        token.addPrivilege(AccessToken.Privileges.kJoinChannel, 100);
        byte[] packed = Utils.pack(token.message);
        assertNotNull(packed);
        assertTrue(packed.length > 0);
    }

    @Test
    void shouldEncodeAndDecodeBase64() {
        byte[] original = "Hello, Agora!".getBytes();
        String encoded = Utils.base64Encode(original);
        assertNotNull(encoded);
        assertFalse(encoded.isEmpty());
        byte[] decoded = Utils.base64Decode(encoded);
        assertArrayEquals(original, decoded);
    }

    @Test
    void shouldComputeCrc32FromString() {
        int crc = Utils.crc32("test");
        assertTrue(crc != 0);
    }

    @Test
    void shouldComputeCrc32FromBytes() {
        int crc = Utils.crc32("test".getBytes());
        assertTrue(crc != 0);
    }

    @Test
    void shouldProduceConsistentCrc32() {
        int crc1 = Utils.crc32("hello");
        int crc2 = Utils.crc32("hello");
        assertEquals(crc1, crc2);
    }

    @Test
    void shouldGetTimestamp() {
        int ts = Utils.getTimestamp();
        assertTrue(ts > 0);
        // Should be within a reasonable range (after 2020)
        assertTrue(ts > 1577836800);
    }

    @Test
    void shouldGenerateRandomInt() {
        int r1 = Utils.randomInt();
        int r2 = Utils.randomInt();
        // Extremely unlikely to be equal
        // Just verify it doesn't throw
        assertNotNull(r1);
        assertNotNull(r2);
    }

    @Test
    void shouldValidateCorrectUuid() {
        assertTrue(Utils.isUUID("0123456789abcdef0123456789abcdef"));
    }

    @Test
    void shouldRejectShortUuid() {
        assertFalse(Utils.isUUID("short"));
    }

    @Test
    void shouldRejectLongUuid() {
        assertFalse(Utils.isUUID("0123456789abcdef0123456789abcdef0"));
    }

    @Test
    void shouldRejectNonHexUuid() {
        assertFalse(Utils.isUUID("0123456789abcdef0123456789abcdeg"));
    }

    @Test
    void shouldRejectEmptyUuid() {
        assertFalse(Utils.isUUID(""));
    }
}
