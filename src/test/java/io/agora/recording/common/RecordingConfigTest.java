package io.agora.recording.common;

import io.agora.recording.common.Common.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordingConfigTest {

    @Test
    void shouldCreateWithDefaults() {
        RecordingConfig config = new RecordingConfig();
        assertFalse(config.isAudioOnly);
        assertFalse(config.isVideoOnly);
        assertFalse(config.isMixingEnabled);
        assertEquals(MIXED_AV_CODEC_TYPE.MIXED_AV_DEFAULT, config.mixedVideoAudio);
        assertEquals("", config.mixResolution);
        assertEquals("", config.decryptionMode);
        assertEquals("", config.secret);
        assertEquals("", config.appliteDir);
        assertEquals("", config.recordFileRootDir);
        assertEquals("", config.cfgFilePath);
        assertEquals(40000, config.lowUdpPort);
        assertEquals(41000, config.highUdpPort);
        assertEquals(300, config.idleLimitSec);
        assertEquals(5, config.captureInterval);
        assertEquals(0, config.triggerMode);
        assertEquals(0, config.audioIndicationInterval);
        assertEquals(0, config.audioProfile);
        assertEquals(VIDEO_FORMAT_TYPE.VIDEO_FORMAT_DEFAULT_TYPE, config.decodeVideo);
        assertEquals(AUDIO_FORMAT_TYPE.AUDIO_FORMAT_DEFAULT_TYPE, config.decodeAudio);
        assertEquals(CHANNEL_PROFILE_TYPE.CHANNEL_PROFILE_COMMUNICATION, config.channelProfile);
        assertEquals(REMOTE_VIDEO_STREAM_TYPE.REMOTE_VIDEO_STREAM_HIGH, config.streamType);
        assertTrue(config.autoSubscribe);
        assertFalse(config.enableCloudProxy);
        assertEquals("", config.subscribeVideoUids);
        assertEquals("", config.subscribeAudioUids);
        assertTrue(config.enableIntraRequest);
        assertFalse(config.enableH265Support);
    }

    @Test
    void shouldSetAudioOnly() {
        RecordingConfig config = new RecordingConfig();
        config.isAudioOnly = true;
        assertTrue(config.isAudioOnly);
    }

    @Test
    void shouldSetVideoOnly() {
        RecordingConfig config = new RecordingConfig();
        config.isVideoOnly = true;
        assertTrue(config.isVideoOnly);
    }

    @Test
    void shouldSetMixingEnabled() {
        RecordingConfig config = new RecordingConfig();
        config.isMixingEnabled = true;
        assertTrue(config.isMixingEnabled);
    }

    @Test
    void shouldSetDecodeVideo() {
        RecordingConfig config = new RecordingConfig();
        config.decodeVideo = VIDEO_FORMAT_TYPE.VIDEO_FORMAT_YUV_FRAME_TYPE;
        assertEquals(VIDEO_FORMAT_TYPE.VIDEO_FORMAT_YUV_FRAME_TYPE, config.decodeVideo);
    }

    @Test
    void shouldSetDecodeAudio() {
        RecordingConfig config = new RecordingConfig();
        config.decodeAudio = AUDIO_FORMAT_TYPE.AUDIO_FORMAT_PCM_FRAME_TYPE;
        assertEquals(AUDIO_FORMAT_TYPE.AUDIO_FORMAT_PCM_FRAME_TYPE, config.decodeAudio);
    }

    @Test
    void shouldSetChannelProfile() {
        RecordingConfig config = new RecordingConfig();
        config.channelProfile = CHANNEL_PROFILE_TYPE.CHANNEL_PROFILE_LIVE_BROADCASTING;
        assertEquals(CHANNEL_PROFILE_TYPE.CHANNEL_PROFILE_LIVE_BROADCASTING, config.channelProfile);
    }

    @Test
    void shouldSetStreamType() {
        RecordingConfig config = new RecordingConfig();
        config.streamType = REMOTE_VIDEO_STREAM_TYPE.REMOTE_VIDEO_STREAM_LOW;
        assertEquals(REMOTE_VIDEO_STREAM_TYPE.REMOTE_VIDEO_STREAM_LOW, config.streamType);
    }

    @Test
    void shouldSetMixedVideoAudio() {
        RecordingConfig config = new RecordingConfig();
        config.mixedVideoAudio = MIXED_AV_CODEC_TYPE.MIXED_AV_CODEC_V1;
        assertEquals(MIXED_AV_CODEC_TYPE.MIXED_AV_CODEC_V1, config.mixedVideoAudio);
    }
}
