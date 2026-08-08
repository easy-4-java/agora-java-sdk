package io.agora.spring.boot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgoraApiAddressTest {

    @Test
    void shouldHaveCloudRecordingEndpoints() {
        assertNotNull(AgoraApiAddress.ACQUIRE_RESOURCE_ID);
        assertNotNull(AgoraApiAddress.START_CLOUD_RECORDING);
        assertNotNull(AgoraApiAddress.UPDATE_CLOUD_RECORDING);
        assertNotNull(AgoraApiAddress.UPDATE_CLOUD_RECORDING_LAYOUT);
        assertNotNull(AgoraApiAddress.QUERY_CLOUD_RECORDING);
        assertNotNull(AgoraApiAddress.STOP_CLOUD_RECORDING);
    }

    @Test
    void shouldHaveProjectEndpoints() {
        assertNotNull(AgoraApiAddress.PROJECT_POST);
        assertNotNull(AgoraApiAddress.PROJECT_GET);
        assertNotNull(AgoraApiAddress.PROJECTS_GET);
        assertNotNull(AgoraApiAddress.PROJECT_STATUS_POST);
        assertNotNull(AgoraApiAddress.PROJECT_USAGE_GET);
    }

    @Test
    void shouldHaveChannelEndpoints() {
        assertNotNull(AgoraApiAddress.CHANNEL_USER_STATE);
        assertNotNull(AgoraApiAddress.CHANNEL_USER_LIST);
        assertNotNull(AgoraApiAddress.CHANNEL_LIST);
    }

    @Test
    void shouldHaveKickingRuleEndpoints() {
        assertNotNull(AgoraApiAddress.KICKING_RULE_POST);
        assertNotNull(AgoraApiAddress.KICKING_RULE_GET);
        assertNotNull(AgoraApiAddress.KICKING_RULE_PUT);
        assertNotNull(AgoraApiAddress.KICKING_RULE_DELETE);
    }

    @Test
    void shouldGetOpt() {
        assertFalse(AgoraApiAddress.ACQUIRE_RESOURCE_ID.getOpt().isEmpty());
    }

    @Test
    void shouldGetMethod() {
        assertEquals(RequestMethod.POST, AgoraApiAddress.ACQUIRE_RESOURCE_ID.getMethod());
        assertEquals(RequestMethod.GET, AgoraApiAddress.CHANNEL_USER_STATE.getMethod());
    }

    @Test
    void shouldGetUrl() {
        String url = AgoraApiAddress.ACQUIRE_RESOURCE_ID.getUrl();
        assertNotNull(url);
        assertTrue(url.contains("cloud_recording"));
    }

    @Test
    void shouldGetUrlWithArgs() {
        String url = AgoraApiAddress.ACQUIRE_RESOURCE_ID.getUrl("testappid");
        assertNotNull(url);
        assertTrue(url.contains("testappid"));
    }

    @Test
    void shouldGetUrlWithMultipleArgs() {
        String url = AgoraApiAddress.START_CLOUD_RECORDING.getUrl("appid", "resourceid", "mix");
        assertNotNull(url);
        assertTrue(url.contains("appid"));
        assertTrue(url.contains("resourceid"));
        assertTrue(url.contains("mix"));
    }

    @Test
    void shouldHaveSignKeyEndpoints() {
        assertNotNull(AgoraApiAddress.SIGNKEY_POST);
        assertNotNull(AgoraApiAddress.SIGNKEY_RESET_POST);
    }

    @Test
    void shouldHaveRecordingConfigEndpoint() {
        assertNotNull(AgoraApiAddress.RECORDING_CONFIG_POST);
    }
}
