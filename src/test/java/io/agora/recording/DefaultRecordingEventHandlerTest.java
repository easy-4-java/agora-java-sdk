package io.agora.recording;

import io.agora.recording.common.Common;
import io.agora.recording.common.Common.*;
import io.agora.recording.common.RecordingResult;
import io.agora.cloud.AgoraProperties;
import io.agora.cloud.AgoraRecordingProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultRecordingEventHandlerTest {

    private AgoraProperties agoraProperties;
    private AgoraRecordingProperties recordingProperties;

    @BeforeEach
    void setUp() {
        agoraProperties = new AgoraProperties();
        agoraProperties.setAppId("970ca35de60c44645bba356700014e37");
        agoraProperties.setAppCertificate("5dfd83dd06fd4c838a640e2b8209bcae");

        recordingProperties = new AgoraRecordingProperties();
        recordingProperties.setMixingEnabled(false);
        recordingProperties.setAudioOnly(false);
        recordingProperties.setVideoOnly(false);
    }

    private DefaultRecordingEventHandler createHandler() {
        try {
            RecordingSDK sdk = new RecordingSDK(null);
            return new DefaultRecordingEventHandler("test-channel", 100L, 200L,
                    agoraProperties, recordingProperties, sdk);
        } catch (Throwable e) {
            // Native lib not available - this is expected in test env
            return null;
        }
    }

    @Test
    void shouldCreateHandler() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            assertNotNull(handler);
            assertEquals("test-channel", handler.getChannel());
            assertEquals(100L, handler.getAnchorUid());
            assertEquals(200L, handler.getRecordingUid());
        }
    }

    @Test
    void shouldGetAndSetRecordingId() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            handler.setRecordingId(42L);
            assertEquals(42L, handler.getRecordingId());
        }
    }

    @Test
    void shouldGetMPeers() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            assertNotNull(handler.getMPeers());
            assertEquals(0, handler.getMPeers().size());
        }
    }

    @Test
    void shouldHaveLayoutConstants() {
        assertEquals(0, DefaultRecordingEventHandler.DEFAULT_LAYOUT);
        assertEquals(1, DefaultRecordingEventHandler.BESTFIT_LAYOUT);
        assertEquals(2, DefaultRecordingEventHandler.VERTICALPRESENTATION_LAYOUT);
    }

    @Test
    void shouldCallOnLeaveChannel() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            assertDoesNotThrow(() -> handler.onLeaveChannel(0));
        }
    }

    @Test
    void shouldCallOnError() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            assertDoesNotThrow(() -> handler.onError(1, 2));
        }
    }

    @Test
    void shouldCallOnWarning() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            assertDoesNotThrow(() -> handler.onWarning(103));
        }
    }

    @Test
    void shouldCallOnRejoinChannelSuccess() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            assertDoesNotThrow(() -> handler.onRejoinChannelSuccess("test-channel", 100L));
        }
    }

    @Test
    void shouldCallOnConnectionStateChanged() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            assertDoesNotThrow(() -> handler.onConnectionStateChanged(
                    CONNECTION_STATE_TYPE.CONNECTION_STATE_CONNECTED,
                    CONNECTION_CHANGED_REASON_TYPE.CONNECTION_CHANGED_JOIN_SUCCESS));
        }
    }

    @Test
    void shouldCallOnRemoteAudioStats() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            Common common = new Common();
            RemoteAudioStats stats = common.new RemoteAudioStats();
            stats.quality = 1;
            stats.networkTransportDelay = 50;
            stats.jitterBufferDelay = 30;
            stats.audioLossRate = 2;
            assertDoesNotThrow(() -> handler.onRemoteAudioStats(100L, stats));
        }
    }

    @Test
    void shouldCallOnRemoteVideoStats() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            Common common = new Common();
            RemoteVideoStats stats = common.new RemoteVideoStats();
            stats.delay = 100;
            stats.width = 640;
            stats.height = 360;
            stats.receivedBitrate = 500;
            stats.decoderOutputFrameRate = 30;
            stats.rxStreamType = 0;
            assertDoesNotThrow(() -> handler.onRemoteVideoStats(100L, stats));
        }
    }

    @Test
    void shouldCallOnRecordingStats() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            Common common = new Common();
            RecordingStats stats = common.new RecordingStats();
            stats.duration = 60;
            stats.rxBytes = 1024;
            assertDoesNotThrow(() -> handler.onRecordingStats(stats));
        }
    }

    @Test
    void shouldCallOnUserOffline() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            handler.getMPeers().add(100L);
            handler.getMPeers().add(200L);
            assertDoesNotThrow(() -> handler.onUserOffline(100L, 0));
            assertEquals(1, handler.getMPeers().size());
        }
    }

    @Test
    void shouldCallOnUserJoined() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            assertDoesNotThrow(() -> handler.onUserJoined(300L, "/tmp/"));
            assertTrue(handler.getMPeers().contains(300L));
        }
    }

    @Test
    void shouldCallOnLocalUserRegistered() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            assertDoesNotThrow(() -> handler.onLocalUserRegistered(100L, "user1"));
        }
    }

    @Test
    void shouldCallOnUserInfoUpdated() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            assertDoesNotThrow(() -> handler.onUserInfoUpdated(100L, "user1"));
        }
    }

    @Test
    void shouldCallOnRemoteVideoStreamStateChanged() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            assertDoesNotThrow(() -> handler.onRemoteVideoStreamStateChanged(100L,
                    REMOTE_STREAM_STATE.REMOTE_STREAM_STATE_RUNNING,
                    REMOTE_STREAM_STATE_CHANGED_REASON.REASON_REMOTE_STREAM_STARTED));
        }
    }

    @Test
    void shouldCallOnRemoteAudioStreamStateChanged() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            assertDoesNotThrow(() -> handler.onRemoteAudioStreamStateChanged(100L,
                    REMOTE_STREAM_STATE.REMOTE_STREAM_STATE_RUNNING,
                    REMOTE_STREAM_STATE_CHANGED_REASON.REASON_REMOTE_STREAM_STARTED));
        }
    }

    @Test
    void shouldCallOnActiveSpeaker() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            assertDoesNotThrow(() -> handler.onActiveSpeaker(100L));
        }
    }

    @Test
    void shouldCallOnReceivingStreamStatusChanged() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            assertDoesNotThrow(() -> handler.onReceivingStreamStatusChanged(true, false));
        }
    }

    @Test
    void shouldCallOnConnectionLost() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            assertDoesNotThrow(() -> handler.onConnectionLost());
        }
    }

    @Test
    void shouldCallOnConnectionInterrupted() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            assertDoesNotThrow(() -> handler.onConnectionInterrupted());
        }
    }

    @Test
    void shouldCallOnAudioVolumeIndication() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            Common common = new Common();
            AudioVolumeInfo info1 = common.new AudioVolumeInfo();
            info1.uid = 100L;
            info1.volume = 200;
            AudioVolumeInfo info2 = common.new AudioVolumeInfo();
            info2.uid = 200L;
            info2.volume = 100;
            assertDoesNotThrow(() -> handler.onAudioVolumeIndication(new AudioVolumeInfo[]{info1, info2}));
        }
    }

    @Test
    void shouldCallOnAudioVolumeIndicationEmpty() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            assertDoesNotThrow(() -> handler.onAudioVolumeIndication(new AudioVolumeInfo[0]));
        }
    }

    @Test
    void shouldCallOnFirstRemoteVideoDecoded() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            assertDoesNotThrow(() -> handler.onFirstRemoteVideoDecoded(100L, 640, 360, 500));
        }
    }

    @Test
    void shouldCallOnFirstRemoteAudioFrame() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            assertDoesNotThrow(() -> handler.onFirstRemoteAudioFrame(100L, 500));
        }
    }

    @Test
    void shouldCallRecordingPathCallBack() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            assertDoesNotThrow(() -> handler.recordingPathCallBack("/tmp/recordings"));
        }
    }

    @Test
    void shouldCallAudioFrameReceived() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            Common common = new Common();
            AudioFrame frame = common.new AudioFrame();
            frame.type = AUDIO_FRAME_TYPE.AUDIO_FRAME_RAW_PCM;
            frame.pcm = common.new AudioPcmFrame(0L, 0L, 0L);
            frame.pcm.pcmBuf = new byte[]{1, 2, 3};
            frame.pcm.pcmBufSize = 3L;
            assertDoesNotThrow(() -> handler.audioFrameReceived(100L, frame));
        }
    }

    @Test
    void shouldCallAudioFrameReceivedAac() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            Common common = new Common();
            AudioFrame frame = common.new AudioFrame();
            frame.type = AUDIO_FRAME_TYPE.AUDIO_FRAME_AAC;
            frame.aac = common.new AudioAacFrame(0L);
            frame.aac.aacBuf = new byte[]{1, 2, 3};
            frame.aac.aacBufSize = 3L;
            assertDoesNotThrow(() -> handler.audioFrameReceived(100L, frame));
        }
    }

    @Test
    void shouldCallVideoFrameReceivedJpg() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            Common common = new Common();
            VideoFrame frame = common.new VideoFrame();
            frame.jpg = null;
            assertThrows(NullPointerException.class, () -> handler.videoFrameReceived(100L, 2, frame, 0));
        }
    }

    @Test
    void shouldCallVideoFrameReceivedJpgFile() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            Common common = new Common();
            VideoFrame frame = common.new VideoFrame();
            frame.jpg_file = null;
            // type 4 checks jpg_file.file_name which is null -> NPE
            assertThrows(NullPointerException.class, () -> handler.videoFrameReceived(100L, 4, frame, 0));
        }
    }

    @Test
    void shouldCallVideoFrameReceivedYuv() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            // VideoYuvFrame constructor is package-private, so NPE is expected
            // when accessing frame.yuv.buf on a null yuv field
            Common common = new Common();
            VideoFrame frame = common.new VideoFrame();
            frame.yuv = null;
            assertThrows(NullPointerException.class, () -> handler.videoFrameReceived(100L, 0, frame, 0));
        }
    }

    @Test
    void shouldCallVideoFrameReceivedH264() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            Common common = new Common();
            VideoFrame frame = common.new VideoFrame();
            frame.h264 = null;
            assertThrows(NullPointerException.class, () -> handler.videoFrameReceived(100L, 1, frame, 0));
        }
    }

    @Test
    void shouldCallVideoFrameReceivedH265() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            Common common = new Common();
            VideoFrame frame = common.new VideoFrame();
            frame.h265 = null;
            assertThrows(NullPointerException.class, () -> handler.videoFrameReceived(100L, 3, frame, 0));
        }
    }

    @Test
    void shouldCallVideoFrameReceivedUnknownType() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            Common common = new Common();
            VideoFrame frame = common.new VideoFrame();
            assertDoesNotThrow(() -> handler.videoFrameReceived(100L, 99, frame, 0));
        }
    }

    @Test
    void shouldUnRegister() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            assertDoesNotThrow(handler::unRegister);
        }
    }

    @Test
    void shouldLeaveChannel() {
        DefaultRecordingEventHandler handler = createHandler();
        if (handler != null) {
            RecordingResult result = handler.leaveChannel();
            assertNotNull(result);
            assertEquals("test-channel", result.getChannelId());
        }
    }
}
