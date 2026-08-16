package io.agora.cloud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgoraTemplateTest {

    private AgoraProperties properties;
    private AgoraTemplate template;

    @BeforeEach
    void setUp() {
        properties = new AgoraProperties();
        properties.setAppId("970ca35de60c44645bba356700014e37");
        properties.setAppCertificate("5dfd83dd06fd4c838a640e2b8209bcae");
        properties.setExpirationTimeInSeconds(3600);
        properties.setLoginKey("key");
        properties.setLoginSecret("secret");
        template = new AgoraTemplate(new AgoraUserIdProvider() {}, null, properties);
    }

    @Test
    void shouldCreateTemplate() {
        assertNotNull(template);
    }

    @Test
    void shouldGenerateTokenWithUserId() {
        String token = template.generateToken("user123", "channel1");
        assertNotNull(token);
        assertFalse(token.isEmpty());
        assertTrue(token.startsWith("006"));
    }

    @Test
    void shouldGenerateTokenWithIntUserId() {
        String token = template.generateToken(12345, "channel1",
                io.agora.media.RtcTokenBuilder.Role.Role_Publisher);
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void shouldGenerateTokenWithRole() {
        String token = template.generateToken("user123", "channel1",
                io.agora.media.RtcTokenBuilder.Role.Role_Subscriber);
        assertNotNull(token);
    }

    @Test
    void shouldGetUserIdByChannel() {
        assertEquals("channel1", template.getUserIdByChannel("channel1"));
    }

    @Test
    void shouldGetChannelByUserId() {
        assertEquals("user1", template.getChannelByUserId("user1"));
    }

    @Test
    void shouldGetAgoraProperties() {
        assertEquals(properties, template.getAgoraProperties());
    }

    @Test
    void shouldGetAgoraOkHttp3Template() {
        assertNull(template.getAgoraOkHttp3Template());
    }

    @Test
    void shouldHaveTryMaxConstant() {
        assertEquals(5, AgoraTemplate.TRY_MAX);
    }

    @Test
    void shouldHaveMediaTypeConstants() {
        assertNotNull(AgoraTemplate.APPLICATION_JSON);
        assertNotNull(AgoraTemplate.APPLICATION_JSON_UTF8);
        assertEquals("application/json", AgoraTemplate.APPLICATION_JSON_VALUE);
        assertEquals("application/json;charset=UTF-8", AgoraTemplate.APPLICATION_JSON_UTF8_VALUE);
    }

    @Test
    void shouldOpsForChannel() {
        assertNotNull(template.opsForChannel());
    }

    @Test
    void shouldOpsForCloudRecording() {
        assertNotNull(template.opsForCloudRecording());
    }
}
