package io.agora.spring.boot.req;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecordingConfigReqTest {

    @Test
    void shouldCreateRecordingConfig() {
        RecordingConfig config = new RecordingConfig(new TranscodingConfig());
        assertNotNull(config);
    }

    @Test
    void shouldCreateTranscodingConfig() {
        TranscodingConfig config = new TranscodingConfig();
        assertNotNull(config);
    }

    @Test
    void shouldSetAndGetTranscodingConfigFields() {
        TranscodingConfig config = new TranscodingConfig();
        config.setHeight(360);
        config.setWidth(640);
        config.setFps(15);
        config.setBitrate(500);
        config.setMaxResolutionUid("12345");
        config.setBackgroundColor("#000000");
        config.setMixedVideoLayout(1);
        config.setBackgroundImage("http://example.com/bg.jpg");
        config.setDefaultUserBackgroundImage("http://example.com/user-bg.jpg");
        assertEquals(Integer.valueOf(360), config.getHeight());
        assertEquals(Integer.valueOf(640), config.getWidth());
        assertEquals(Integer.valueOf(15), config.getFps());
        assertEquals(Integer.valueOf(500), config.getBitrate());
        assertEquals("12345", config.getMaxResolutionUid());
        assertEquals("#000000", config.getBackgroundColor());
        assertEquals(Integer.valueOf(1), config.getMixedVideoLayout());
        assertEquals("http://example.com/bg.jpg", config.getBackgroundImage());
        assertEquals("http://example.com/user-bg.jpg", config.getDefaultUserBackgroundImage());
    }

    @Test
    void shouldSetTranscodingConfigLayoutConfig() {
        TranscodingConfig config = new TranscodingConfig();
        TranscodingLayoutConfig layout = new TranscodingLayoutConfig();
        layout.setUid("12345");
        List<TranscodingLayoutConfig> layouts = Arrays.asList(layout);
        config.setLayoutConfig(layouts);
        assertEquals(1, config.getLayoutConfig().size());
        assertEquals("12345", config.getLayoutConfig().get(0).getUid());
    }

    @Test
    void shouldSetTranscodingConfigBackgroundConfig() {
        TranscodingConfig config = new TranscodingConfig();
        TranscodingBackgroundConfig bg = new TranscodingBackgroundConfig();
        bg.setUid("user1");
        List<TranscodingBackgroundConfig> bgs = Arrays.asList(bg);
        config.setBackgroundConfig(bgs);
        assertEquals(1, config.getBackgroundConfig().size());
    }

    @Test
    void shouldCreateTranscodingLayoutConfig() {
        TranscodingLayoutConfig config = new TranscodingLayoutConfig();
        assertNotNull(config);
    }

    @Test
    void shouldSetAndGetTranscodingLayoutConfigFields() {
        TranscodingLayoutConfig config = new TranscodingLayoutConfig();
        config.setUid("12345");
        config.setXAxis(0.0f);
        config.setYAxis(0.0f);
        config.setWidth(0.5f);
        config.setHeight(0.5f);
        config.setAlpha(1.0f);
        config.setRenderMode(0);
        assertEquals("12345", config.getUid());
        assertEquals(Float.valueOf(0.0f), config.getXAxis());
        assertEquals(Float.valueOf(0.0f), config.getYAxis());
        assertEquals(Float.valueOf(0.5f), config.getWidth());
        assertEquals(Float.valueOf(0.5f), config.getHeight());
        assertEquals(Float.valueOf(1.0f), config.getAlpha());
        assertEquals(Integer.valueOf(0), config.getRenderMode());
    }

    @Test
    void shouldCreateTranscodingBackgroundConfig() {
        TranscodingBackgroundConfig config = new TranscodingBackgroundConfig();
        assertNotNull(config);
    }

    @Test
    void shouldSetAndGetTranscodingBackgroundConfigFields() {
        TranscodingBackgroundConfig config = new TranscodingBackgroundConfig();
        config.setUid("user1");
        config.setImageUrl("http://example.com/bg.jpg");
        config.setRenderMode(1);
        assertEquals("user1", config.getUid());
        assertEquals("http://example.com/bg.jpg", config.getImageUrl());
        assertEquals(Integer.valueOf(1), config.getRenderMode());
    }

    @Test
    void shouldCreateRecordingSnapshotConfig() {
        RecordingSnapshotConfig config = new RecordingSnapshotConfig();
        assertNotNull(config);
    }

    @Test
    void shouldCreateRecordingExtensionServiceConfig() {
        RecordingExtensionServiceConfig config = new RecordingExtensionServiceConfig();
        assertNotNull(config);
    }

    @Test
    void shouldCreateRecordingUpdateStreamSubscribe() {
        RecordingUpdateStreamSubscribe config = new RecordingUpdateStreamSubscribe();
        assertNotNull(config);
    }

    @Test
    void shouldCreateRecordingUpdateWebConfig() {
        RecordingUpdateWebConfig config = new RecordingUpdateWebConfig();
        assertNotNull(config);
    }

    @Test
    void shouldCreateRecordingUpdateRtmpPublishConfig() {
        RecordingUpdateRtmpPublishConfig config = new RecordingUpdateRtmpPublishConfig();
        assertNotNull(config);
    }

    @Test
    void shouldCreateRecordingUpdateTranscodingConfig() {
        RecordingUpdateTranscodingConfig config = new RecordingUpdateTranscodingConfig();
        assertNotNull(config);
    }

    @Test
    void shouldCreateRecordingAppsCollectionConfig() {
        RecordingAppsCollectionConfig config = new RecordingAppsCollectionConfig();
        assertNotNull(config);
    }

    @Test
    void shouldHaveRecordingAppCombinationPolicyValues() {
        assertNotNull(RecordingAppCombinationPolicy.values());
        assertTrue(RecordingAppCombinationPolicy.values().length > 0);
    }

    @Test
    void shouldCreateAgoraRequest() {
        AgoraRequest<String> request = new AgoraRequest<>("channel1", "user1");
        assertNotNull(request);
        assertEquals("channel1", request.getCname());
        assertEquals("user1", request.getUid());
    }

    @Test
    void shouldSetAgoraRequestClientRequest() {
        AgoraRequest<String> request = new AgoraRequest<>("ch1", "u1");
        request.setClientRequest("test");
        assertEquals("test", request.getClientRequest());
    }

    @Test
    void shouldCreateAgoraStartParam() {
        AgoraStartParam param = new AgoraStartParam();
        assertNotNull(param);
    }

    @Test
    void shouldSetAgoraStartParamFields() {
        AgoraStartParam param = new AgoraStartParam();
        RecordingConfig rc = new RecordingConfig(new TranscodingConfig());
        RecordingFileConfig fc = new RecordingFileConfig();
        RecordingStorageConfig sc = new RecordingStorageConfig();
        param.setRecordingConfig(rc);
        param.setRecordingFileConfig(fc);
        param.setStorageConfig(sc);
        assertEquals(rc, param.getRecordingConfig());
        assertEquals(fc, param.getRecordingFileConfig());
        assertEquals(sc, param.getStorageConfig());
    }

    @Test
    void shouldCreateAgoraStopParam() {
        AgoraStopParam param = new AgoraStopParam();
        assertNotNull(param);
        assertTrue(param.getAsync_stop());
    }

    @Test
    void shouldSetAgoraStopParamAsyncStop() {
        AgoraStopParam param = new AgoraStopParam();
        param.setAsync_stop(false);
        assertFalse(param.getAsync_stop());
    }

    @Test
    void shouldGetRecordingModeName() {
        assertEquals("mix", RecordingMode.MIX.getName());
        assertEquals("individual", RecordingMode.INDIVIDUAL.getName());
        assertEquals("web", RecordingMode.WEB.getName());
    }

    @Test
    void shouldGetRecordingModeDesc() {
        assertNotNull(RecordingMode.MIX.getDesc());
        assertNotNull(RecordingMode.INDIVIDUAL.getDesc());
        assertNotNull(RecordingMode.WEB.getDesc());
    }

    @Test
    void shouldCreateRecordingStorageConfigWithAllFields() {
        RecordingStorageConfig config = new RecordingStorageConfig();
        config.setVendor(2);
        config.setRegion(0);
        config.setBucket("bucket");
        config.setAccessKey("ak");
        config.setSecretKey("sk");
        config.setFileNamePrefix(Arrays.asList("dir1", "dir2"));
        assertEquals(Integer.valueOf(2), config.getVendor());
        assertEquals(Integer.valueOf(0), config.getRegion());
        assertEquals("bucket", config.getBucket());
        assertEquals("ak", config.getAccessKey());
        assertEquals("sk", config.getSecretKey());
        assertEquals(Arrays.asList("dir1", "dir2"), config.getFileNamePrefix());
    }

    @Test
    void shouldCreateRecordingFileConfigWithCustomTypes() {
        RecordingFileConfig config = new RecordingFileConfig();
        config.setFileTypes(Arrays.asList("hls"));
        assertEquals(1, config.getFileTypes().size());
        assertTrue(config.getFileTypes().contains("hls"));
    }

    @Test
    void shouldImplementEqualsAndHashCodeForTranscodingConfig() {
        TranscodingConfig c1 = new TranscodingConfig();
        c1.setWidth(640);
        TranscodingConfig c2 = new TranscodingConfig();
        c2.setWidth(640);
        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void shouldImplementToStringForTranscodingConfig() {
        TranscodingConfig config = new TranscodingConfig();
        assertNotNull(config.toString());
    }

    @Test
    void shouldImplementEqualsAndHashCodeForTranscodingLayoutConfig() {
        TranscodingLayoutConfig c1 = new TranscodingLayoutConfig();
        c1.setUid("u1");
        TranscodingLayoutConfig c2 = new TranscodingLayoutConfig();
        c2.setUid("u1");
        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void shouldImplementToStringForTranscodingLayoutConfig() {
        TranscodingLayoutConfig config = new TranscodingLayoutConfig();
        assertNotNull(config.toString());
    }

    @Test
    void shouldImplementEqualsAndHashCodeForTranscodingBackgroundConfig() {
        TranscodingBackgroundConfig c1 = new TranscodingBackgroundConfig();
        c1.setUid("u1");
        TranscodingBackgroundConfig c2 = new TranscodingBackgroundConfig();
        c2.setUid("u1");
        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void shouldImplementToStringForTranscodingBackgroundConfig() {
        TranscodingBackgroundConfig config = new TranscodingBackgroundConfig();
        assertNotNull(config.toString());
    }

    @Test
    void shouldImplementEqualsForAgoraRequest() {
        AgoraRequest<String> r1 = new AgoraRequest<>("ch1", "u1");
        AgoraRequest<String> r2 = new AgoraRequest<>("ch1", "u1");
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void shouldImplementToStringForAgoraRequest() {
        AgoraRequest<String> request = new AgoraRequest<>("ch1", "u1");
        assertNotNull(request.toString());
    }

    @Test
    void shouldImplementEqualsForAgoraStartParam() {
        AgoraStartParam p1 = new AgoraStartParam();
        AgoraStartParam p2 = new AgoraStartParam();
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void shouldImplementToStringForAgoraStartParam() {
        AgoraStartParam param = new AgoraStartParam();
        assertNotNull(param.toString());
    }

    @Test
    void shouldImplementEqualsForAgoraStopParam() {
        AgoraStopParam p1 = new AgoraStopParam();
        AgoraStopParam p2 = new AgoraStopParam();
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void shouldImplementToStringForAgoraStopParam() {
        AgoraStopParam param = new AgoraStopParam();
        assertNotNull(param.toString());
    }
}
