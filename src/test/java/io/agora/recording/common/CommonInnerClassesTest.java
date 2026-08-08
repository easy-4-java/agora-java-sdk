package io.agora.recording.common;

import io.agora.recording.common.Common.*;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

import static org.junit.jupiter.api.Assertions.*;

class CommonInnerClassesTest {

    private final Common common = new Common();

    // --- VideoYuvFrame ---
    @Test
    void shouldCreateVideoYuvFrame() {
        VideoYuvFrame frame = common.new VideoYuvFrame(1000L, 640, 360, 640, 320, 320);
        assertNotNull(frame);
        assertEquals(1000L, frame.frame_ms);
        assertEquals(640, frame.width);
        assertEquals(360, frame.height);
        assertEquals(640, frame.ystride);
        assertEquals(320, frame.ustride);
        assertEquals(320, frame.vstride);
    }

    @Test
    void shouldSetVideoYuvFrameFields() {
        VideoYuvFrame frame = common.new VideoYuvFrame(0L, 0, 0, 0, 0, 0);
        frame.frame_ms = 2000L;
        frame.width = 1920;
        frame.height = 1080;
        frame.ystride = 1920;
        frame.ustride = 960;
        frame.vstride = 960;
        frame.buf = new byte[100];
        frame.bufSize = 100L;
        frame.ybuf = ByteBuffer.allocate(100);
        frame.ubuf = ByteBuffer.allocate(50);
        frame.vbuf = ByteBuffer.allocate(50);
        assertEquals(2000L, frame.frame_ms);
        assertEquals(1920, frame.width);
        assertEquals(1080, frame.height);
        assertNotNull(frame.buf);
        assertEquals(100L, frame.bufSize);
    }

    // --- VideoH264Frame ---
    @Test
    void shouldCreateVideoH264Frame() {
        VideoH264Frame frame = common.new VideoH264Frame();
        assertNotNull(frame);
        assertEquals(0L, frame.frame_ms);
        assertEquals(0L, frame.frame_num);
        assertEquals(0L, frame.bufSize);
    }

    @Test
    void shouldSetVideoH264FrameFields() {
        VideoH264Frame frame = common.new VideoH264Frame();
        frame.frame_ms = 1000L;
        frame.frame_num = 42L;
        frame.buf = new byte[]{1, 2, 3};
        frame.bufSize = 3L;
        assertEquals(1000L, frame.frame_ms);
        assertEquals(42L, frame.frame_num);
        assertArrayEquals(new byte[]{1, 2, 3}, frame.buf);
        assertEquals(3L, frame.bufSize);
    }

    // --- VideoH265Frame ---
    @Test
    void shouldCreateVideoH265Frame() {
        VideoH265Frame frame = common.new VideoH265Frame();
        assertNotNull(frame);
        assertEquals(0L, frame.frame_ms);
        assertEquals(0L, frame.frame_num);
        assertEquals(0L, frame.bufSize);
    }

    @Test
    void shouldSetVideoH265FrameFields() {
        VideoH265Frame frame = common.new VideoH265Frame();
        frame.frame_ms = 1000L;
        frame.frame_num = 42L;
        frame.buf = new byte[]{4, 5, 6};
        frame.bufSize = 3L;
        assertEquals(1000L, frame.frame_ms);
        assertEquals(42L, frame.frame_num);
        assertArrayEquals(new byte[]{4, 5, 6}, frame.buf);
        assertEquals(3L, frame.bufSize);
    }

    // --- UserJoinInfos ---
    @Test
    void shouldCreateUserJoinInfos() {
        UserJoinInfos info = common.new UserJoinInfos();
        assertNotNull(info);
        assertEquals("", info.storageDir);
    }

    // --- AUDIO_FRAME_TYPE ---
    @Test
    void shouldHaveAudioFrameTypes() {
        assertEquals(2, AUDIO_FRAME_TYPE.values().length);
        assertNotNull(AUDIO_FRAME_TYPE.AUDIO_FRAME_RAW_PCM);
        assertNotNull(AUDIO_FRAME_TYPE.AUDIO_FRAME_AAC);
    }

    // --- SERVICE_MODE ---
    @Test
    void shouldHaveServiceModes() {
        assertEquals(3, SERVICE_MODE.values().length);
        assertNotNull(SERVICE_MODE.RECORDING_MODE);
        assertNotNull(SERVICE_MODE.SERVER_MODE);
        assertNotNull(SERVICE_MODE.IOT_MODE);
    }

    // --- USER_OFFLINE_REASON_TYPE ---
    @Test
    void shouldHaveUserOfflineReasons() {
        assertEquals(3, USER_OFFLINE_REASON_TYPE.values().length);
        assertNotNull(USER_OFFLINE_REASON_TYPE.USER_OFFLINE_QUIT);
        assertNotNull(USER_OFFLINE_REASON_TYPE.USER_OFFLINE_DROPPED);
        assertNotNull(USER_OFFLINE_REASON_TYPE.USER_OFFLINE_BECOME_AUDIENCE);
    }

    // --- AudioPcmFrame ---
    @Test
    void shouldCreateAudioPcmFrame() {
        AudioPcmFrame frame = common.new AudioPcmFrame(1000L, 44100L, 1024L);
        assertNotNull(frame);
    }

    @Test
    void shouldSetAudioPcmFrameFields() {
        AudioPcmFrame frame = common.new AudioPcmFrame(0L, 0L, 0L);
        frame.frame_ms = 2000L;
        frame.channels = 2;
        frame.sample_bits = 16;
        frame.sample_rates = 44100L;
        frame.samples = 1024L;
        frame.pcmBuf = new byte[]{1, 2, 3};
        frame.pcmBufSize = 3L;
        assertEquals(2000L, frame.frame_ms);
        assertEquals(2, frame.channels);
        assertEquals(16, frame.sample_bits);
        assertEquals(44100L, frame.sample_rates);
        assertEquals(1024L, frame.samples);
        assertNotNull(frame.pcmBuf);
        assertEquals(3L, frame.pcmBufSize);
    }

    // --- AudioAacFrame ---
    @Test
    void shouldCreateAudioAacFrame() {
        AudioAacFrame frame = common.new AudioAacFrame(1000L);
        assertNotNull(frame);
        assertEquals(1000L, frame.frame_ms);
        assertEquals(0L, frame.aacBufSize);
    }

    @Test
    void shouldSetAudioAacFrameFields() {
        AudioAacFrame frame = common.new AudioAacFrame(0L);
        frame.frame_ms = 2000L;
        frame.aacBuf = new byte[]{1, 2, 3};
        frame.aacBufSize = 3L;
        frame.channels = 2;
        frame.bitrate = 128000;
        assertEquals(2000L, frame.frame_ms);
        assertNotNull(frame.aacBuf);
        assertEquals(3L, frame.aacBufSize);
        assertEquals(2, frame.channels);
        assertEquals(128000, frame.bitrate);
    }

    // --- RemoteVideoStats ---
    @Test
    void shouldSetRemoteVideoStatsFields() {
        RemoteVideoStats stats = common.new RemoteVideoStats();
        stats.delay = 100;
        stats.width = 640;
        stats.height = 360;
        stats.receivedBitrate = 500;
        stats.decoderOutputFrameRate = 30;
        stats.rxStreamType = 0;
        assertEquals(100, stats.delay);
        assertEquals(640, stats.width);
        assertEquals(360, stats.height);
        assertEquals(500, stats.receivedBitrate);
        assertEquals(30, stats.decoderOutputFrameRate);
        assertEquals(0, stats.rxStreamType);
    }

    // --- RemoteAudioStats ---
    @Test
    void shouldSetRemoteAudioStatsFields() {
        RemoteAudioStats stats = common.new RemoteAudioStats();
        stats.quality = 1;
        stats.networkTransportDelay = 50;
        stats.jitterBufferDelay = 30;
        stats.audioLossRate = 2;
        assertEquals(1, stats.quality);
        assertEquals(50, stats.networkTransportDelay);
        assertEquals(30, stats.jitterBufferDelay);
        assertEquals(2, stats.audioLossRate);
    }

    // --- RecordingStats ---
    @Test
    void shouldSetRecordingStatsFields() {
        RecordingStats stats = common.new RecordingStats();
        stats.duration = 60;
        stats.rxBytes = 1024;
        stats.rxKBitRate = 500;
        stats.rxAudioKBitRate = 64;
        stats.rxVideoKBitRate = 436;
        stats.lastmileDelay = 100;
        stats.userCount = 3;
        stats.cpuAppUsage = 10;
        stats.cpuTotalUsage = 30;
        assertEquals(60, stats.duration);
        assertEquals(1024, stats.rxBytes);
        assertEquals(500, stats.rxKBitRate);
        assertEquals(64, stats.rxAudioKBitRate);
        assertEquals(436, stats.rxVideoKBitRate);
        assertEquals(100, stats.lastmileDelay);
        assertEquals(3, stats.userCount);
        assertEquals(10, stats.cpuAppUsage);
        assertEquals(30, stats.cpuTotalUsage);
    }

    // --- AudioVolumeInfo ---
    @Test
    void shouldSetAudioVolumeInfoFields() {
        AudioVolumeInfo info = common.new AudioVolumeInfo();
        info.uid = 12345L;
        info.volume = 200;
        assertEquals(12345L, info.uid);
        assertEquals(200, info.volume);
    }

    // --- LiteraWatermarkConfig ---
    @Test
    void shouldSetLiteraWatermarkConfigFields() {
        LiteraWatermarkConfig config = common.new LiteraWatermarkConfig();
        config.wmLitera = "Test Watermark";
        config.fontFilePath = "/path/to/font.ttf";
        config.fontSize = 20;
        config.offsetX = 10;
        config.offsetY = 20;
        config.wmWidth = 200;
        config.wmHeight = 50;
        assertEquals("Test Watermark", config.wmLitera);
        assertEquals("/path/to/font.ttf", config.fontFilePath);
        assertEquals(20, config.fontSize);
        assertEquals(10, config.offsetX);
        assertEquals(20, config.offsetY);
        assertEquals(200, config.wmWidth);
        assertEquals(50, config.wmHeight);
    }

    // --- TimestampWatermarkConfig ---
    @Test
    void shouldSetTimestampWatermarkConfigFields() {
        TimestampWatermarkConfig config = common.new TimestampWatermarkConfig();
        config.fontSize = 15;
        config.offsetX = 5;
        config.offsetY = 10;
        config.wmWidth = 150;
        config.wmHeight = 30;
        assertEquals(15, config.fontSize);
        assertEquals(5, config.offsetX);
        assertEquals(10, config.offsetY);
        assertEquals(150, config.wmWidth);
        assertEquals(30, config.wmHeight);
    }

    // --- ImageWatermarkConfig ---
    @Test
    void shouldSetImageWatermarkConfigFields() {
        ImageWatermarkConfig config = common.new ImageWatermarkConfig();
        config.imagePath = "/path/to/image.png";
        config.offsetX = 10;
        config.offsetY = 20;
        config.wmWidth = 100;
        config.wmHeight = 100;
        assertEquals("/path/to/image.png", config.imagePath);
        assertEquals(10, config.offsetX);
        assertEquals(20, config.offsetY);
        assertEquals(100, config.wmWidth);
        assertEquals(100, config.wmHeight);
    }

    // --- VideoMixingLayout ---
    @Test
    void shouldSetVideoMixingLayoutFields() {
        VideoMixingLayout layout = common.new VideoMixingLayout();
        layout.canvasWidth = 640;
        layout.canvasHeight = 360;
        layout.backgroundColor = "#C0C0C0";
        layout.regionCount = 2;
        layout.appData = "test";
        layout.appDataLength = 4;
        layout.keepLastFrame = 1;
        assertEquals(640, layout.canvasWidth);
        assertEquals(360, layout.canvasHeight);
        assertEquals("#C0C0C0", layout.backgroundColor);
        assertEquals(2, layout.regionCount);
        assertEquals("test", layout.appData);
        assertEquals(4, layout.appDataLength);
        assertEquals(1, layout.keepLastFrame);
    }

    @Test
    void shouldSetVideoMixingLayoutRegionFields() {
        VideoMixingLayout layout = common.new VideoMixingLayout();
        VideoMixingLayout.Region region = layout.new Region();
        region.uid = 12345L;
        region.x = 0.1;
        region.y = 0.2;
        region.width = 0.5;
        region.height = 0.5;
        region.alpha = 0.8;
        region.renderMode = 1;
        assertEquals(12345L, region.uid);
        assertEquals(0.1, region.x);
        assertEquals(0.2, region.y);
        assertEquals(0.5, region.width);
        assertEquals(0.5, region.height);
        assertEquals(0.8, region.alpha);
        assertEquals(1, region.renderMode);
    }

    @Test
    void shouldSetVideoMixingLayoutWatermarks() {
        VideoMixingLayout layout = common.new VideoMixingLayout();
        LiteraWatermarkConfig[] literalWms = new LiteraWatermarkConfig[]{common.new LiteraWatermarkConfig()};
        TimestampWatermarkConfig[] timestampWms = new TimestampWatermarkConfig[]{common.new TimestampWatermarkConfig()};
        ImageWatermarkConfig[] imageWms = new ImageWatermarkConfig[]{common.new ImageWatermarkConfig()};
        layout.literalWms = literalWms;
        layout.timestampWms = timestampWms;
        layout.imageWms = imageWms;
        assertEquals(1, layout.literalWms.length);
        assertEquals(1, layout.timestampWms.length);
        assertEquals(1, layout.imageWms.length);
    }

    @Test
    void shouldSetVideoMixingLayoutRegions() {
        VideoMixingLayout layout = common.new VideoMixingLayout();
        VideoMixingLayout.Region[] regions = new VideoMixingLayout.Region[2];
        regions[0] = layout.new Region();
        regions[0].uid = 1L;
        regions[1] = layout.new Region();
        regions[1].uid = 2L;
        layout.regions = regions;
        assertEquals(2, layout.regions.length);
        assertEquals(1L, layout.regions[0].uid);
        assertEquals(2L, layout.regions[1].uid);
    }

    // --- VideoFrame ---
    @Test
    void shouldSetVideoFrameFields() {
        VideoFrame frame = common.new VideoFrame();
        frame.yuv = common.new VideoYuvFrame(0L, 0, 0, 0, 0, 0);
        frame.h264 = common.new VideoH264Frame();
        frame.h265 = common.new VideoH265Frame();
        frame.jpg = common.new VideoJpgFrame();
        frame.jpg_file = common.new VideoJpgFile();
        frame.rotation = 90;
        assertNotNull(frame.yuv);
        assertNotNull(frame.h264);
        assertNotNull(frame.h265);
        assertNotNull(frame.jpg);
        assertNotNull(frame.jpg_file);
        assertEquals(90, frame.rotation);
    }

    // --- AudioFrame ---
    @Test
    void shouldSetAudioFrameFields() {
        AudioFrame frame = common.new AudioFrame();
        frame.type = AUDIO_FRAME_TYPE.AUDIO_FRAME_RAW_PCM;
        frame.pcm = common.new AudioPcmFrame(0L, 0L, 0L);
        frame.aac = common.new AudioAacFrame(0L);
        assertEquals(AUDIO_FRAME_TYPE.AUDIO_FRAME_RAW_PCM, frame.type);
        assertNotNull(frame.pcm);
        assertNotNull(frame.aac);
    }

    // --- VIDEO_FRAME_TYPE ---
    @Test
    void shouldSetVideoFrameTypeFields() {
        VIDEO_FRAME_TYPE type = common.new VIDEO_FRAME_TYPE();
        type.type = 2;
        assertEquals(2, type.type);
        assertEquals(2, type.getValue());
    }

    @Test
    void shouldHaveVideoFrameTypeConstants() {
        VIDEO_FRAME_TYPE type = common.new VIDEO_FRAME_TYPE();
        assertEquals(0, type.VIDEO_FRAME_RAW_YUV);
        assertEquals(1, type.VIDEO_FRAME_H264);
        assertEquals(2, type.VIDEO_FRAME_JPG);
        assertEquals(3, type.VIDEO_FRAME_H265);
        assertEquals(4, type.VIDEO_JPG_FILE);
    }
}
