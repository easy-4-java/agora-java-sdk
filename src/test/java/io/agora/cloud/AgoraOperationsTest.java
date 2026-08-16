package io.agora.cloud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgoraOperationsTest {

    private AgoraProperties properties;
    private AgoraTemplate template;
    private AgoraChannelManagerOperations operations;

    @BeforeEach
    void setUp() {
        properties = new AgoraProperties();
        properties.setAppId("testappid");
        properties.setLoginKey("key");
        properties.setLoginSecret("secret");
        template = new AgoraTemplate(new AgoraUserIdProvider() {}, null, properties);
        operations = new AgoraChannelManagerOperations(template);
    }

    @Test
    void shouldCreateOperations() {
        assertNotNull(operations);
    }

    @Test
    void shouldGetAgoraTemplate() {
        assertEquals(template, operations.getAgoraTemplate());
    }

    @Test
    void shouldHavePrefixConstant() {
        assertNotNull(AgoraOperations.PREFIX);
        assertTrue(AgoraOperations.PREFIX.contains("tim.qq.com"));
    }

    @Test
    void shouldHaveMediaTypeConstants() {
        assertEquals("application/json", AgoraOperations.APPLICATION_JSON_VALUE);
        assertEquals("application/json;charset=UTF-8", AgoraOperations.APPLICATION_JSON_UTF8_VALUE);
    }

    @Test
    void shouldCreateAsyncOperations() {
        AgoraChannelManagerAsyncOperations asyncOps = new AgoraChannelManagerAsyncOperations(template);
        assertNotNull(asyncOps);
        assertEquals(template, asyncOps.getAgoraTemplate());
    }
}
