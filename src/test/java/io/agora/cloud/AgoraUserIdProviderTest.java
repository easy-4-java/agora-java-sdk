package io.agora.cloud;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgoraUserIdProviderTest {

    @Test
    void shouldReturnChannelAsDefaultUserId() {
        AgoraUserIdProvider provider = new AgoraUserIdProvider() {};
        assertEquals("myChannel", provider.getUserIdByChannel("appid", "myChannel"));
    }

    @Test
    void shouldReturnUserIdAsDefaultChannel() {
        AgoraUserIdProvider provider = new AgoraUserIdProvider() {};
        assertEquals("myUser", provider.getChannelByUserId("appid", "myUser"));
    }

    @Test
    void shouldSupportCustomImplementation() {
        AgoraUserIdProvider provider = new AgoraUserIdProvider() {
            @Override
            public String getUserIdByChannel(String appid, String channel) {
                return "custom_" + channel;
            }

            @Override
            public String getChannelByUserId(String appid, String userId) {
                return "ch_" + userId;
            }
        };
        assertEquals("custom_myChannel", provider.getUserIdByChannel("appid", "myChannel"));
        assertEquals("ch_myUser", provider.getChannelByUserId("appid", "myUser"));
    }
}
