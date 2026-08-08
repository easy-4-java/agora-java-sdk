package io.agora.spring.boot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgoraConstantTest {

    @Test
    void shouldHaveUrlChannelUser() {
        assertNotNull(AgoraConstant.URL_CHANNEL_USER);
        assertTrue(AgoraConstant.URL_CHANNEL_USER.contains("{0}"));
        assertTrue(AgoraConstant.URL_CHANNEL_USER.contains("{1}"));
    }

    @Test
    void shouldHaveUrlRule() {
        assertNotNull(AgoraConstant.URL_RULE);
        assertTrue(AgoraConstant.URL_RULE.contains("kicking-rule"));
    }

    @Test
    void shouldHaveRecordingUid() {
        assertEquals("10", AgoraConstant.RECORDING_UID);
    }

    @Test
    void shouldHaveVideoPath() {
        assertEquals("video", AgoraConstant.VEIDO_PAHT);
    }
}
