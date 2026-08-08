package io.agora.spring.boot;

import io.agora.recording.common.Common.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgoraRecordingPropertiesTest {

    @Test
    void shouldHaveCorrectPrefix() {
        assertEquals("agora.recording", AgoraRecordingProperties.PREFIX);
    }

    @Test
    void shouldCreateWithDefaults() {
        AgoraRecordingProperties props = new AgoraRecordingProperties();
        assertFalse(props.isAudioOnly());
        assertFalse(props.isVideoOnly());
        assertFalse(props.isMixingEnabled());
        assertEquals(MIXED_AV_CODEC_TYPE.MIXED_AV_DEFAULT, props.getMixedVideoAudio());
        assertEquals("", props.getMixResolution());
        assertEquals("", props.getDecryptionMode());
        assertEquals("", props.getSecret());
        assertEquals("", props.getAppliteDir());
        assertEquals("", props.getRecordFileRootDir());
        assertEquals("", props.getCfgFilePath());
        assertEquals(VIDEO_FORMAT_TYPE.VIDEO_FORMAT_DEFAULT_TYPE, props.getDecodeVideo());
        assertEquals(AUDIO_FORMAT_TYPE.AUDIO_FORMAT_DEFAULT_TYPE, props.getDecodeAudio());
        assertEquals(0, props.getLowUdpPort());
        assertEquals(0, props.getHighUdpPort());
        assertEquals(1, props.getLogLevel());
        assertEquals(300, props.getIdleLimitSec());
        assertEquals(5, props.getCaptureInterval());
        assertEquals(0, props.getAudioIndicationInterval());
        assertEquals(CHANNEL_PROFILE_TYPE.CHANNEL_PROFILE_COMMUNICATION, props.getChannelProfile());
        assertEquals(REMOTE_VIDEO_STREAM_TYPE.REMOTE_VIDEO_STREAM_HIGH, props.getStreamType());
        assertEquals(0, props.getTriggerMode());
        assertEquals(1, props.getProxyType());
        assertEquals("", props.getProxyServer());
        assertEquals(0, props.getAudioProfile());
        assertEquals("", props.getDefaultVideoBgPath());
        assertEquals("", props.getDefaultUserBgPath());
        assertTrue(props.isAutoSubscribe());
        assertFalse(props.isEnableCloudProxy());
        assertEquals("", props.getSubscribeVideoUids());
        assertEquals("", props.getSubscribeAudioUids());
        assertTrue(props.isEnableIntraRequest());
        assertFalse(props.isEnableH265Support());
        assertNull(props.getLibPath());
    }

    @Test
    void shouldSetAndGetAudioOnly() {
        AgoraRecordingProperties props = new AgoraRecordingProperties();
        props.setAudioOnly(true);
        assertTrue(props.isAudioOnly());
    }

    @Test
    void shouldSetAndGetVideoOnly() {
        AgoraRecordingProperties props = new AgoraRecordingProperties();
        props.setVideoOnly(true);
        assertTrue(props.isVideoOnly());
    }

    @Test
    void shouldSetAndGetMixingEnabled() {
        AgoraRecordingProperties props = new AgoraRecordingProperties();
        props.setMixingEnabled(true);
        assertTrue(props.isMixingEnabled());
    }

    @Test
    void shouldSetAndGetMixedVideoAudio() {
        AgoraRecordingProperties props = new AgoraRecordingProperties();
        props.setMixedVideoAudio(MIXED_AV_CODEC_TYPE.MIXED_AV_CODEC_V1);
        assertEquals(MIXED_AV_CODEC_TYPE.MIXED_AV_CODEC_V1, props.getMixedVideoAudio());
    }

    @Test
    void shouldSetAndGetMixResolution() {
        AgoraRecordingProperties props = new AgoraRecordingProperties();
        props.setMixResolution("640,360,15,500");
        assertEquals("640,360,15,500", props.getMixResolution());
    }

    @Test
    void shouldSetAndGetDecodeVideo() {
        AgoraRecordingProperties props = new AgoraRecordingProperties();
        props.setDecodeVideo(VIDEO_FORMAT_TYPE.VIDEO_FORMAT_YUV_FRAME_TYPE);
        assertEquals(VIDEO_FORMAT_TYPE.VIDEO_FORMAT_YUV_FRAME_TYPE, props.getDecodeVideo());
    }

    @Test
    void shouldSetAndGetDecodeAudio() {
        AgoraRecordingProperties props = new AgoraRecordingProperties();
        props.setDecodeAudio(AUDIO_FORMAT_TYPE.AUDIO_FORMAT_PCM_FRAME_TYPE);
        assertEquals(AUDIO_FORMAT_TYPE.AUDIO_FORMAT_PCM_FRAME_TYPE, props.getDecodeAudio());
    }

    @Test
    void shouldSetAndGetChannelProfile() {
        AgoraRecordingProperties props = new AgoraRecordingProperties();
        props.setChannelProfile(CHANNEL_PROFILE_TYPE.CHANNEL_PROFILE_LIVE_BROADCASTING);
        assertEquals(CHANNEL_PROFILE_TYPE.CHANNEL_PROFILE_LIVE_BROADCASTING, props.getChannelProfile());
    }

    @Test
    void shouldSetAndGetStreamType() {
        AgoraRecordingProperties props = new AgoraRecordingProperties();
        props.setStreamType(REMOTE_VIDEO_STREAM_TYPE.REMOTE_VIDEO_STREAM_LOW);
        assertEquals(REMOTE_VIDEO_STREAM_TYPE.REMOTE_VIDEO_STREAM_LOW, props.getStreamType());
    }

    @Test
    void shouldSetAndGetIdleLimitSec() {
        AgoraRecordingProperties props = new AgoraRecordingProperties();
        props.setIdleLimitSec(600);
        assertEquals(600, props.getIdleLimitSec());
    }

    @Test
    void shouldSetAndGetLogLevel() {
        AgoraRecordingProperties props = new AgoraRecordingProperties();
        props.setLogLevel(5);
        assertEquals(5, props.getLogLevel());
    }

    @Test
    void shouldSetAndGetLibPath() {
        AgoraRecordingProperties props = new AgoraRecordingProperties();
        props.setLibPath("/usr/lib");
        assertEquals("/usr/lib", props.getLibPath());
    }

    @Test
    void shouldImplementEqualsAndHashCode() {
        AgoraRecordingProperties props1 = new AgoraRecordingProperties();
        props1.setAudioOnly(true);
        AgoraRecordingProperties props2 = new AgoraRecordingProperties();
        props2.setAudioOnly(true);
        assertEquals(props1, props2);
        assertEquals(props1.hashCode(), props2.hashCode());
    }

    @Test
    void shouldImplementToString() {
        AgoraRecordingProperties props = new AgoraRecordingProperties();
        assertNotNull(props.toString());
    }
}
