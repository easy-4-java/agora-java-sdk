package io.agora.recording.common;

import io.agora.recording.common.Common.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonTest {

    // --- ERROR_CODE_TYPE ---
    @Test
    void shouldHaveCorrectErrorCodes() {
        assertEquals(0, ERROR_CODE_TYPE.ERR_OK.getValue());
        assertEquals(1, ERROR_CODE_TYPE.ERR_FAILED.getValue());
        assertEquals(2, ERROR_CODE_TYPE.ERR_INVALID_ARGUMENT.getValue());
        assertEquals(3, ERROR_CODE_TYPE.ERR_INTERNAL_FAILED.getValue());
    }

    @Test
    void shouldGetErrorCodeByCode() {
        assertEquals(ERROR_CODE_TYPE.ERR_OK, ERROR_CODE_TYPE.getByCode(0));
        assertEquals(ERROR_CODE_TYPE.ERR_FAILED, ERROR_CODE_TYPE.getByCode(1));
        assertEquals(ERROR_CODE_TYPE.ERR_INVALID_ARGUMENT, ERROR_CODE_TYPE.getByCode(2));
        assertEquals(ERROR_CODE_TYPE.ERR_INTERNAL_FAILED, ERROR_CODE_TYPE.getByCode(3));
    }

    @Test
    void shouldReturnNullForUnknownErrorCode() {
        assertNull(ERROR_CODE_TYPE.getByCode(999));
    }

    // --- STAT_CODE_TYPE ---
    @Test
    void shouldHaveCorrectStatCodes() {
        assertEquals(0, STAT_CODE_TYPE.STAT_OK.getValue());
        assertEquals(1, STAT_CODE_TYPE.STAT_ERR_FROM_ENGINE.getValue());
        assertEquals(2, STAT_CODE_TYPE.STAT_ERR_ARS_JOIN_CHANNEL.getValue());
        assertEquals(3, STAT_CODE_TYPE.STAT_ERR_CREATE_PROCESS.getValue());
        assertEquals(4, STAT_CODE_TYPE.STAT_ERR_MIXED_INVALID_VIDEO_PARAM.getValue());
        assertEquals(5, STAT_CODE_TYPE.STAT_ERR_NULL_POINTER.getValue());
        assertEquals(6, STAT_CODE_TYPE.STAT_ERR_PROXY_SERVER_INVALID_PARAM.getValue());
        assertEquals(8, STAT_CODE_TYPE.STAT_POLL_ERR.getValue());
        assertEquals(16, STAT_CODE_TYPE.STAT_POLL_HANG_UP.getValue());
        assertEquals(32, STAT_CODE_TYPE.STAT_POLL_NVAL.getValue());
    }

    @Test
    void shouldGetStatCodeByCode() {
        assertEquals(STAT_CODE_TYPE.STAT_OK, STAT_CODE_TYPE.getByCode(0));
        assertEquals(STAT_CODE_TYPE.STAT_ERR_FROM_ENGINE, STAT_CODE_TYPE.getByCode(1));
    }

    @Test
    void shouldReturnNullForUnknownStatCode() {
        assertNull(STAT_CODE_TYPE.getByCode(999));
    }

    // --- LEAVE_PATH_CODE ---
    @Test
    void shouldHaveCorrectLeaveCodes() {
        assertEquals(0, LEAVE_PATH_CODE.LEAVE_CODE_INIT.getValue());
        assertEquals(2, LEAVE_PATH_CODE.LEAVE_CODE_SIG.getValue());
        assertEquals(4, LEAVE_PATH_CODE.LEAVE_CODE_NO_USERS.getValue());
        assertEquals(8, LEAVE_PATH_CODE.LEAVE_CODE_TIMER_CATCH.getValue());
        assertEquals(16, LEAVE_PATH_CODE.LEAVE_CODE_CLIENT_LEAVE.getValue());
    }

    @Test
    void shouldGetLeaveCodeByCode() {
        assertEquals(LEAVE_PATH_CODE.LEAVE_CODE_INIT, LEAVE_PATH_CODE.getByCode(0));
        assertEquals(LEAVE_PATH_CODE.LEAVE_CODE_SIG, LEAVE_PATH_CODE.getByCode(2));
        assertEquals(LEAVE_PATH_CODE.LEAVE_CODE_CLIENT_LEAVE, LEAVE_PATH_CODE.getByCode(16));
    }

    @Test
    void shouldReturnNullForUnknownLeaveCode() {
        assertNull(LEAVE_PATH_CODE.getByCode(999));
    }

    // --- REMOTE_STREAM_STATE ---
    @Test
    void shouldHaveCorrectRemoteStreamStates() {
        assertEquals(0, REMOTE_STREAM_STATE.REMOTE_STREAM_STATE_RUNNING.getValue());
        assertEquals(1, REMOTE_STREAM_STATE.REMOTE_STREAM_STATE_STOPPED.getValue());
    }

    @Test
    void shouldGetRemoteStreamStateByCode() {
        assertEquals(REMOTE_STREAM_STATE.REMOTE_STREAM_STATE_RUNNING, REMOTE_STREAM_STATE.getByCode(0));
        assertEquals(REMOTE_STREAM_STATE.REMOTE_STREAM_STATE_STOPPED, REMOTE_STREAM_STATE.getByCode(1));
    }

    @Test
    void shouldReturnNullForUnknownRemoteStreamState() {
        assertNull(REMOTE_STREAM_STATE.getByCode(999));
    }

    // --- REMOTE_STREAM_STATE_CHANGED_REASON ---
    @Test
    void shouldHaveCorrectStreamStateChangeReasons() {
        assertEquals(0, REMOTE_STREAM_STATE_CHANGED_REASON.REASON_REMOTE_STREAM_STARTED.getValue());
        assertEquals(1, REMOTE_STREAM_STATE_CHANGED_REASON.REASON_REMOTE_STREAM_STOPPED.getValue());
    }

    @Test
    void shouldGetStreamStateChangeReasonByCode() {
        assertEquals(REMOTE_STREAM_STATE_CHANGED_REASON.REASON_REMOTE_STREAM_STARTED,
                REMOTE_STREAM_STATE_CHANGED_REASON.getByCode(0));
        assertEquals(REMOTE_STREAM_STATE_CHANGED_REASON.REASON_REMOTE_STREAM_STOPPED,
                REMOTE_STREAM_STATE_CHANGED_REASON.getByCode(1));
    }

    @Test
    void shouldReturnNullForUnknownStreamStateChangeReason() {
        assertNull(REMOTE_STREAM_STATE_CHANGED_REASON.getByCode(999));
    }

    // --- WARN_CODE_TYPE ---
    @Test
    void shouldHaveCorrectWarnCodes() {
        assertEquals(103, WARN_CODE_TYPE.WARN_NO_AVAILABLE_CHANNEL.getValue());
        assertEquals(104, WARN_CODE_TYPE.WARN_LOOKUP_CHANNEL_TIMEOUT.getValue());
        assertEquals(105, WARN_CODE_TYPE.WARN_LOOKUP_CHANNEL_REJECTED.getValue());
        assertEquals(106, WARN_CODE_TYPE.WARN_OPEN_CHANNEL_TIMEOUT.getValue());
        assertEquals(107, WARN_CODE_TYPE.WARN_OPEN_CHANNEL_REJECTED.getValue());
        assertEquals(108, WARN_CODE_TYPE.WARN_RECOVERY_CORE_SERVICE_FAILURE.getValue());
    }

    @Test
    void shouldGetWarnCodeByCode() {
        assertEquals(WARN_CODE_TYPE.WARN_NO_AVAILABLE_CHANNEL, WARN_CODE_TYPE.getByCode(103));
        assertEquals(WARN_CODE_TYPE.WARN_RECOVERY_CORE_SERVICE_FAILURE, WARN_CODE_TYPE.getByCode(108));
    }

    @Test
    void shouldReturnNullForUnknownWarnCode() {
        assertNull(WARN_CODE_TYPE.getByCode(999));
    }

    // --- CHANNEL_PROFILE_TYPE ---
    @Test
    void shouldHaveCorrectChannelProfiles() {
        assertEquals(0, CHANNEL_PROFILE_TYPE.CHANNEL_PROFILE_COMMUNICATION.getValue());
        assertEquals(1, CHANNEL_PROFILE_TYPE.CHANNEL_PROFILE_LIVE_BROADCASTING.getValue());
    }

    @Test
    void shouldGetChannelProfileByCode() {
        assertEquals(CHANNEL_PROFILE_TYPE.CHANNEL_PROFILE_COMMUNICATION, CHANNEL_PROFILE_TYPE.getByCode(0));
        assertEquals(CHANNEL_PROFILE_TYPE.CHANNEL_PROFILE_LIVE_BROADCASTING, CHANNEL_PROFILE_TYPE.getByCode(1));
    }

    @Test
    void shouldReturnNullForUnknownChannelProfile() {
        assertNull(CHANNEL_PROFILE_TYPE.getByCode(999));
    }

    // --- CONNECTION_STATE_TYPE ---
    @Test
    void shouldHaveCorrectConnectionStates() {
        assertEquals(1, CONNECTION_STATE_TYPE.CONNECTION_STATE_DISCONNECTED.getValue());
        assertEquals(2, CONNECTION_STATE_TYPE.CONNECTION_STATE_CONNECTING.getValue());
        assertEquals(3, CONNECTION_STATE_TYPE.CONNECTION_STATE_CONNECTED.getValue());
        assertEquals(4, CONNECTION_STATE_TYPE.CONNECTION_STATE_RECONNECTING.getValue());
        assertEquals(5, CONNECTION_STATE_TYPE.CONNECTION_STATE_FAILED.getValue());
    }

    @Test
    void shouldGetConnectionStateByCode() {
        assertEquals(CONNECTION_STATE_TYPE.CONNECTION_STATE_DISCONNECTED, CONNECTION_STATE_TYPE.getByCode(1));
        assertEquals(CONNECTION_STATE_TYPE.CONNECTION_STATE_CONNECTED, CONNECTION_STATE_TYPE.getByCode(3));
    }

    @Test
    void shouldReturnNullForUnknownConnectionState() {
        assertNull(CONNECTION_STATE_TYPE.getByCode(999));
    }

    // --- CONNECTION_CHANGED_REASON_TYPE ---
    @Test
    void shouldHaveCorrectConnectionChangedReasons() {
        assertEquals(0, CONNECTION_CHANGED_REASON_TYPE.CONNECTION_CHANGED_CONNECTING.getValue());
        assertEquals(1, CONNECTION_CHANGED_REASON_TYPE.CONNECTION_CHANGED_JOIN_SUCCESS.getValue());
        assertEquals(2, CONNECTION_CHANGED_REASON_TYPE.CONNECTION_CHANGED_INTERRUPTED.getValue());
        assertEquals(3, CONNECTION_CHANGED_REASON_TYPE.CONNECTION_CHANGED_BANNED_BY_SERVER.getValue());
        assertEquals(4, CONNECTION_CHANGED_REASON_TYPE.CONNECTION_CHANGED_JOIN_FAILED.getValue());
        assertEquals(5, CONNECTION_CHANGED_REASON_TYPE.CONNECTION_CHANGED_LEAVE_CHANNEL.getValue());
    }

    // --- REMOTE_VIDEO_STREAM_TYPE ---
    @Test
    void shouldHaveCorrectRemoteVideoStreamTypes() {
        assertEquals(0, REMOTE_VIDEO_STREAM_TYPE.REMOTE_VIDEO_STREAM_HIGH.getValue());
        assertEquals(1, REMOTE_VIDEO_STREAM_TYPE.REMOTE_VIDEO_STREAM_LOW.getValue());
    }

    // --- TRIGGER_MODE_TYPE ---
    @Test
    void shouldHaveCorrectTriggerModes() {
        assertEquals(0, TRIGGER_MODE_TYPE.AUTOMATICALLY_MODE.getValue());
        assertEquals(1, TRIGGER_MODE_TYPE.MANUALLY_MODE.getValue());
    }

    // --- AUDIO_PROFILE_TYPE ---
    @Test
    void shouldHaveCorrectAudioProfiles() {
        assertEquals(0, AUDIO_PROFILE_TYPE.AUDIO_PROFILE_DEFAULT.getValue());
        assertEquals(1, AUDIO_PROFILE_TYPE.AUDIO_PROFILE_HIGH_QUALITY.getValue());
        assertEquals(2, AUDIO_PROFILE_TYPE.AUDIO_PROFILE_HIGH_QUALITY_STEREO.getValue());
    }

    // --- agora_log_level ---
    @Test
    void shouldHaveCorrectLogLevels() {
        assertEquals(1, agora_log_level.AGORA_LOG_LEVEL_FATAL.getValue());
        assertEquals(2, agora_log_level.AGORA_LOG_LEVEL_ERROR.getValue());
        assertEquals(3, agora_log_level.AGORA_LOG_LEVEL_WARN.getValue());
        assertEquals(4, agora_log_level.AGORA_LOG_LEVEL_NOTICE.getValue());
        assertEquals(5, agora_log_level.AGORA_LOG_LEVEL_INFO.getValue());
        assertEquals(6, agora_log_level.AGORA_LOG_LEVEL_DEBUG.getValue());
    }

    // --- Inner classes ---
    @Test
    void shouldCreateAudioFrame() {
        Common common = new Common();
        AudioFrame frame = common.new AudioFrame();
        assertNotNull(frame);
    }

    @Test
    void shouldCreateAudioPcmFrame() {
        Common common = new Common();
        AudioPcmFrame frame = common.new AudioPcmFrame(1000L, 44100L, 1024L);
        assertNotNull(frame);
    }

    @Test
    void shouldCreateAudioAacFrame() {
        Common common = new Common();
        AudioAacFrame frame = common.new AudioAacFrame(1000L);
        assertNotNull(frame);
        assertEquals(1000L, frame.frame_ms);
        assertEquals(0L, frame.aacBufSize);
    }

    @Test
    void shouldCreateRemoteVideoStats() {
        Common common = new Common();
        RemoteVideoStats stats = common.new RemoteVideoStats();
        assertNotNull(stats);
        assertEquals(0, stats.delay);
        assertEquals(0, stats.width);
        assertEquals(0, stats.height);
    }

    @Test
    void shouldCreateRemoteAudioStats() {
        Common common = new Common();
        RemoteAudioStats stats = common.new RemoteAudioStats();
        assertNotNull(stats);
        assertEquals(0, stats.quality);
        assertEquals(0, stats.networkTransportDelay);
    }

    @Test
    void shouldCreateRecordingStats() {
        Common common = new Common();
        RecordingStats stats = common.new RecordingStats();
        assertNotNull(stats);
        assertEquals(0, stats.duration);
        assertEquals(0, stats.rxBytes);
    }

    @Test
    void shouldCreateAudioVolumeInfo() {
        Common common = new Common();
        AudioVolumeInfo info = common.new AudioVolumeInfo();
        assertNotNull(info);
        assertEquals(0L, info.uid);
        assertEquals(0, info.volume);
    }

    @Test
    void shouldCreateVideoMixingLayout() {
        Common common = new Common();
        VideoMixingLayout layout = common.new VideoMixingLayout();
        assertNotNull(layout);
        assertEquals(0, layout.canvasWidth);
        assertEquals(0, layout.canvasHeight);
    }

    @Test
    void shouldCreateVideoMixingLayoutRegion() {
        Common common = new Common();
        VideoMixingLayout layout = common.new VideoMixingLayout();
        VideoMixingLayout.Region region = layout.new Region();
        assertNotNull(region);
        assertEquals(0L, region.uid);
        assertEquals(0.0, region.x);
        assertEquals(0.0, region.y);
    }

    @Test
    void shouldCreateLiteraWatermarkConfig() {
        Common common = new Common();
        LiteraWatermarkConfig config = common.new LiteraWatermarkConfig();
        assertNotNull(config);
        assertEquals("", config.wmLitera);
        assertEquals(10, config.fontSize);
    }

    @Test
    void shouldCreateTimestampWatermarkConfig() {
        Common common = new Common();
        TimestampWatermarkConfig config = common.new TimestampWatermarkConfig();
        assertNotNull(config);
        assertEquals(10, config.fontSize);
    }

    @Test
    void shouldCreateImageWatermarkConfig() {
        Common common = new Common();
        ImageWatermarkConfig config = common.new ImageWatermarkConfig();
        assertNotNull(config);
        assertEquals("", config.imagePath);
    }

    @Test
    void shouldCreateVideoFrame() {
        Common common = new Common();
        VideoFrame frame = common.new VideoFrame();
        assertNotNull(frame);
    }

    @Test
    void shouldCreateVideoJpgFrame() {
        Common common = new Common();
        VideoJpgFrame frame = common.new VideoJpgFrame();
        assertNotNull(frame);
        assertEquals(0L, frame.frame_ms);
        assertEquals(0L, frame.bufSize);
    }

    @Test
    void shouldCreateVideoJpgFile() {
        Common common = new Common();
        VideoJpgFile file = common.new VideoJpgFile();
        assertNotNull(file);
        assertEquals("", file.file_name);
    }

    @Test
    void shouldCreateVideoFrameType() {
        Common common = new Common();
        VIDEO_FRAME_TYPE type = common.new VIDEO_FRAME_TYPE();
        assertNotNull(type);
        assertEquals(0, type.type);
        assertEquals(0, type.getValue());
    }
}
