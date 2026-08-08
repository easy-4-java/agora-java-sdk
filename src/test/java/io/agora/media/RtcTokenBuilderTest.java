package io.agora.media;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RtcTokenBuilderTest {

    private static final String APP_ID = "970ca35de60c44645bba356700014e37";
    private static final String APP_CERT = "5dfd83dd06fd4c838a640e2b8209bcae";
    private static final String CHANNEL = "test_channel";

    @Test
    void shouldBuildTokenWithUid() {
        RtcTokenBuilder builder = new RtcTokenBuilder();
        String token = builder.buildTokenWithUid(APP_ID, APP_CERT, CHANNEL, 12345,
                RtcTokenBuilder.Role.Role_Publisher, 100);
        assertNotNull(token);
        assertFalse(token.isEmpty());
        assertTrue(token.startsWith("006"));
    }

    @Test
    void shouldBuildTokenWithZeroUid() {
        RtcTokenBuilder builder = new RtcTokenBuilder();
        String token = builder.buildTokenWithUid(APP_ID, APP_CERT, CHANNEL, 0,
                RtcTokenBuilder.Role.Role_Publisher, 100);
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void shouldBuildTokenWithUserAccount() {
        RtcTokenBuilder builder = new RtcTokenBuilder();
        String token = builder.buildTokenWithUserAccount(APP_ID, APP_CERT, CHANNEL, "user123",
                RtcTokenBuilder.Role.Role_Publisher, 100);
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void shouldBuildTokenWithSubscriberRole() {
        RtcTokenBuilder builder = new RtcTokenBuilder();
        String token = builder.buildTokenWithUid(APP_ID, APP_CERT, CHANNEL, 12345,
                RtcTokenBuilder.Role.Role_Subscriber, 100);
        assertNotNull(token);
    }

    @Test
    void shouldBuildTokenWithAdminRole() {
        RtcTokenBuilder builder = new RtcTokenBuilder();
        String token = builder.buildTokenWithUid(APP_ID, APP_CERT, CHANNEL, 12345,
                RtcTokenBuilder.Role.Role_Admin, 100);
        assertNotNull(token);
    }

    @Test
    void shouldBuildTokenWithAttendeeRole() {
        RtcTokenBuilder builder = new RtcTokenBuilder();
        String token = builder.buildTokenWithUid(APP_ID, APP_CERT, CHANNEL, 12345,
                RtcTokenBuilder.Role.Role_Attendee, 100);
        assertNotNull(token);
    }

    @Test
    void shouldHaveCorrectRoleValues() {
        assertEquals(0, RtcTokenBuilder.Role.Role_Attendee.initValue);
        assertEquals(1, RtcTokenBuilder.Role.Role_Publisher.initValue);
        assertEquals(2, RtcTokenBuilder.Role.Role_Subscriber.initValue);
        assertEquals(101, RtcTokenBuilder.Role.Role_Admin.initValue);
    }

    @Test
    void shouldGenerateDifferentTokensForDifferentRoles() {
        RtcTokenBuilder builder = new RtcTokenBuilder();
        String pubToken = builder.buildTokenWithUid(APP_ID, APP_CERT, CHANNEL, 12345,
                RtcTokenBuilder.Role.Role_Publisher, 100);
        String subToken = builder.buildTokenWithUid(APP_ID, APP_CERT, CHANNEL, 12345,
                RtcTokenBuilder.Role.Role_Subscriber, 100);
        assertNotEquals(pubToken, subToken);
    }
}
