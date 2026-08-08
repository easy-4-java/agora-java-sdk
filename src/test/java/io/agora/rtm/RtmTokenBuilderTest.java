package io.agora.rtm;

import io.agora.media.AccessToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RtmTokenBuilderTest {

    private static final String APP_ID = "970ca35de60c44645bba356700014e37";
    private static final String APP_CERT = "5dfd83dd06fd4c838a640e2b8209bcae";

    @Test
    void shouldBuildRtmToken() throws Exception {
        RtmTokenBuilder builder = new RtmTokenBuilder();
        String token = builder.buildToken(APP_ID, APP_CERT, "user123",
                RtmTokenBuilder.Role.Rtm_User, 100);
        assertNotNull(token);
        assertFalse(token.isEmpty());
        assertTrue(token.startsWith("006"));
    }

    @Test
    void shouldHaveCorrectRoleValue() {
        assertEquals(1, RtmTokenBuilder.Role.Rtm_User.value);
    }

    @Test
    void shouldSetPrivilege() throws Exception {
        RtmTokenBuilder builder = new RtmTokenBuilder();
        builder.buildToken(APP_ID, APP_CERT, "user123",
                RtmTokenBuilder.Role.Rtm_User, 100);
        assertDoesNotThrow(() -> builder.setPrivilege(AccessToken.Privileges.kRtmLogin, 200));
    }

    @Test
    void shouldInitTokenBuilder() throws Exception {
        RtmTokenBuilder builder = new RtmTokenBuilder();
        String token = builder.buildToken(APP_ID, APP_CERT, "user123",
                RtmTokenBuilder.Role.Rtm_User, 100);
        assertTrue(builder.initTokenBuilder(token));
    }

    @Test
    void shouldStoreTokenCreator() throws Exception {
        RtmTokenBuilder builder = new RtmTokenBuilder();
        builder.buildToken(APP_ID, APP_CERT, "user123",
                RtmTokenBuilder.Role.Rtm_User, 100);
        assertNotNull(builder.mTokenCreator);
    }

    @Test
    void shouldGenerateDifferentTokensForDifferentUsers() throws Exception {
        RtmTokenBuilder builder1 = new RtmTokenBuilder();
        RtmTokenBuilder builder2 = new RtmTokenBuilder();
        String token1 = builder1.buildToken(APP_ID, APP_CERT, "user1",
                RtmTokenBuilder.Role.Rtm_User, 100);
        String token2 = builder2.buildToken(APP_ID, APP_CERT, "user2",
                RtmTokenBuilder.Role.Rtm_User, 100);
        assertNotEquals(token1, token2);
    }
}
