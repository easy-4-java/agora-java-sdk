package io.agora.media;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccessTokenTest {

    private static final String APP_ID = "970ca35de60c44645bba356700014e37";
    private static final String APP_CERT = "5dfd83dd06fd4c838a640e2b8209bcae";
    private static final String CHANNEL = "test_channel";
    private static final String UID = "12345";

    @Test
    void shouldReturnVersion() {
        assertEquals("006", AccessToken.getVersion());
    }

    @Test
    void shouldBuildTokenWithValidCredentials() throws Exception {
        AccessToken token = new AccessToken(APP_ID, APP_CERT, CHANNEL, UID);
        token.addPrivilege(AccessToken.Privileges.kJoinChannel, 100);
        String result = token.build();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.startsWith("006"));
        assertTrue(result.contains(APP_ID));
    }

    @Test
    void shouldReturnEmptyStringForInvalidAppId() throws Exception {
        AccessToken token = new AccessToken("invalid", APP_CERT, CHANNEL, UID);
        String result = token.build();
        assertEquals("", result);
    }

    @Test
    void shouldReturnEmptyStringForInvalidAppCertificate() throws Exception {
        AccessToken token = new AccessToken(APP_ID, "invalid", CHANNEL, UID);
        String result = token.build();
        assertEquals("", result);
    }

    @Test
    void shouldAddMultiplePrivileges() throws Exception {
        AccessToken token = new AccessToken(APP_ID, APP_CERT, CHANNEL, UID);
        token.addPrivilege(AccessToken.Privileges.kJoinChannel, 100);
        token.addPrivilege(AccessToken.Privileges.kPublishAudioStream, 200);
        token.addPrivilege(AccessToken.Privileges.kPublishVideoStream, 200);
        token.addPrivilege(AccessToken.Privileges.kPublishDataStream, 200);
        String result = token.build();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void shouldGenerateSignature() throws Exception {
        byte[] sig = AccessToken.generateSignature(APP_CERT, APP_ID, CHANNEL, UID, "message".getBytes());
        assertNotNull(sig);
        assertEquals(32, sig.length);
    }

    @Test
    void shouldParseTokenFromString() throws Exception {
        AccessToken original = new AccessToken(APP_ID, APP_CERT, CHANNEL, UID);
        original.addPrivilege(AccessToken.Privileges.kJoinChannel, 100);
        String tokenStr = original.build();

        AccessToken parsed = new AccessToken(APP_ID, APP_CERT, CHANNEL, UID);
        boolean success = parsed.fromString(tokenStr);
        assertTrue(success);
        assertEquals(APP_ID, parsed.appId);
    }

    @Test
    void shouldReturnFalseForInvalidTokenString() {
        AccessToken token = new AccessToken(APP_ID, APP_CERT, CHANNEL, UID);
        assertFalse(token.fromString("invalid_token"));
    }

    @Test
    void shouldHaveCorrectPrivilegeValues() {
        assertEquals(1, AccessToken.Privileges.kJoinChannel.intValue);
        assertEquals(2, AccessToken.Privileges.kPublishAudioStream.intValue);
        assertEquals(3, AccessToken.Privileges.kPublishVideoStream.intValue);
        assertEquals(4, AccessToken.Privileges.kPublishDataStream.intValue);
        assertEquals(1000, AccessToken.Privileges.kRtmLogin.intValue);
    }

    @Test
    void shouldBuildAndRoundTripToken() throws Exception {
        AccessToken original = new AccessToken(APP_ID, APP_CERT, CHANNEL, UID);
        original.addPrivilege(AccessToken.Privileges.kJoinChannel, 100);
        original.addPrivilege(AccessToken.Privileges.kPublishAudioStream, 200);
        String tokenStr = original.build();

        AccessToken parsed = new AccessToken(APP_ID, APP_CERT, CHANNEL, UID);
        assertTrue(parsed.fromString(tokenStr));
        assertNotNull(parsed.signature);
        assertNotNull(parsed.messageRawContent);
        assertTrue(parsed.crcChannelName != 0);
        assertTrue(parsed.crcUid != 0);
    }
}
