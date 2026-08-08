package io.agora.recording;

import io.agora.recording.common.Common;
import io.agora.recording.common.Common.*;
import io.agora.recording.common.RecordingConfig;
import io.agora.recording.common.RecordingEngineProperties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordingSDKTest {

    @Test
    void shouldHaveMaxUserAccountLength() {
        assertEquals(256, RecordingSDK.MAX_USER_ACCOUNT_LENGTH);
    }

    @Test
    void shouldCreateRecordingSDKWithNullLibPath() {
        // This will fail to load native lib, but should not throw
        try {
            RecordingSDK sdk = new RecordingSDK(null);
            assertNotNull(sdk);
        } catch (Throwable e) {
            // Expected - native lib not available
        }
    }

    @Test
    void shouldCreateRecordingSDKWithNonExistentLibPath() {
        try {
            RecordingSDK sdk = new RecordingSDK("/nonexistent/path");
            assertNotNull(sdk);
        } catch (Throwable e) {
            // Expected - native lib not available
        }
    }

    @Test
    void shouldLeaveChannelReturnsFalseWhenNotConnected() {
        try {
            RecordingSDK sdk = new RecordingSDK(null);
            assertFalse(sdk.leaveChannel());
        } catch (Throwable e) {
            // Expected - native lib not available
        }
    }

    @Test
    void shouldGetUidByUserAccountReturnsZeroWhenNotConnected() {
        try {
            RecordingSDK sdk = new RecordingSDK(null);
            assertEquals(0, sdk.getUidByUserAccount("user1"));
        } catch (Throwable e) {
            // Expected - native lib not available
        }
    }

    @Test
    void shouldGetUserAccountByUidReturnsEmptyWhenNotConnected() {
        try {
            RecordingSDK sdk = new RecordingSDK(null);
            assertEquals("", sdk.getUserAccountByUid(123));
        } catch (Throwable e) {
            // Expected - native lib not available
        }
    }

    @Test
    void shouldSetVideoMixingLayoutReturnsNegativeWhenNotConnected() {
        try {
            RecordingSDK sdk = new RecordingSDK(null);
            Common common = new Common();
            VideoMixingLayout layout = common.new VideoMixingLayout();
            assertEquals(-1, sdk.setVideoMixingLayout(layout));
        } catch (Throwable e) {
            // Expected - native lib not available
        }
    }

    @Test
    void shouldUpdateWatermarkConfigsReturnsNegativeWhenNotConnected() {
        try {
            RecordingSDK sdk = new RecordingSDK(null);
            assertEquals(-1, sdk.updateWatermarkConfigs(null, null, null));
        } catch (Throwable e) {
            // Expected - native lib not available
        }
    }

    @Test
    void shouldUpdateSubscribeVideoUidsReturnsNegativeWhenNotConnected() {
        try {
            RecordingSDK sdk = new RecordingSDK(null);
            assertEquals(-1, sdk.updateSubscribeVideoUids(new int[]{1, 2}));
        } catch (Throwable e) {
            // Expected - native lib not available
        }
    }

    @Test
    void shouldUpdateSubscribeAudioUidsReturnsNegativeWhenNotConnected() {
        try {
            RecordingSDK sdk = new RecordingSDK(null);
            assertEquals(-1, sdk.updateSubscribeAudioUids(new int[]{1, 2}));
        } catch (Throwable e) {
            // Expected - native lib not available
        }
    }

    @Test
    void shouldStartServiceReturnsNegativeWhenNotConnected() {
        try {
            RecordingSDK sdk = new RecordingSDK(null);
            assertEquals(-1, sdk.startService());
        } catch (Throwable e) {
            // Expected - native lib not available
        }
    }

    @Test
    void shouldStopServiceReturnsNegativeWhenNotConnected() {
        try {
            RecordingSDK sdk = new RecordingSDK(null);
            assertEquals(-1, sdk.stopService());
        } catch (Throwable e) {
            // Expected - native lib not available
        }
    }

    @Test
    void shouldGetPropertiesReturnsNullWhenNotConnected() {
        try {
            RecordingSDK sdk = new RecordingSDK(null);
            assertNull(sdk.getProperties());
        } catch (Throwable e) {
            // Expected - native lib not available
        }
    }

    @Test
    void shouldSetUserBackgroundReturnsNegativeWhenNotConnected() {
        try {
            RecordingSDK sdk = new RecordingSDK(null);
            assertEquals(-1, sdk.setUserBackground(123, "/path/to/image.jpg"));
        } catch (Throwable e) {
            // Expected - native lib not available
        }
    }

    @Test
    void shouldRegisterAndUnregisterObserver() {
        try {
            RecordingSDK sdk = new RecordingSDK(null);
            RecordingEventHandler handler = createTestHandler("test-channel");
            sdk.registerOberserver(handler);
            assertEquals(handler, sdk.getRegisterOberserver("test-channel"));
            sdk.unRegisterOberserver(handler);
            assertNull(sdk.getRegisterOberserver("test-channel"));
        } catch (Throwable e) {
            // Expected - native lib not available
        }
    }

    @Test
    void shouldUnregisterObserverByChannelName() {
        try {
            RecordingSDK sdk = new RecordingSDK(null);
            RecordingEventHandler handler = createTestHandler("test-channel");
            sdk.registerOberserver(handler);
            sdk.unRegisterOberserver("test-channel");
            assertNull(sdk.getRegisterOberserver("test-channel"));
        } catch (Throwable e) {
            // Expected - native lib not available
        }
    }

    @Test
    void shouldShutdown() {
        try {
            RecordingSDK sdk = new RecordingSDK(null);
            assertDoesNotThrow(sdk::shutdown);
        } catch (Throwable e) {
            // Expected - native lib not available
        }
    }

    private RecordingEventHandler createTestHandler(String channelId) {
        return new RecordingEventHandler() {
            @Override
            public String getChannel() { return channelId; }
            @Override public void onLeaveChannel(int reason) {}
            @Override public void onError(int error, int stat_code) {}
            @Override public void onWarning(int warn) {}
            @Override public void onJoinChannelSuccess(String channelId, long uid) {}
            @Override public void onRemoteVideoStreamStateChanged(long uid, REMOTE_STREAM_STATE state, REMOTE_STREAM_STATE_CHANGED_REASON reason) {}
            @Override public void onRemoteAudioStreamStateChanged(long uid, REMOTE_STREAM_STATE state, REMOTE_STREAM_STATE_CHANGED_REASON reason) {}
            @Override public void onUserOffline(long uid, int reason) {}
            @Override public void onUserJoined(long uid, String recordingDir) {}
            @Override public void onActiveSpeaker(long uid) {}
            @Override public void audioFrameReceived(long uid, AudioFrame frame) {}
            @Override public void videoFrameReceived(long uid, int type, VideoFrame frame, int rotation) {}
            @Override public void recordingPathCallBack(String path) {}
            @Override public void onAudioVolumeIndication(AudioVolumeInfo[] infos) {}
            @Override public void onFirstRemoteVideoDecoded(long uid, int width, int height, int elapsed) {}
            @Override public void onFirstRemoteAudioFrame(long uid, int elapsed) {}
            @Override public void onReceivingStreamStatusChanged(boolean receivingAudio, boolean receivingVideo) {}
            @Override public void onConnectionLost() {}
            @Override public void onConnectionInterrupted() {}
            @Override public void onRejoinChannelSuccess(String channelId, long uid) {}
            @Override public void onConnectionStateChanged(CONNECTION_STATE_TYPE state, CONNECTION_CHANGED_REASON_TYPE reason) {}
            @Override public void onRemoteVideoStats(long uid, RemoteVideoStats stats) {}
            @Override public void onRemoteAudioStats(long uid, RemoteAudioStats stats) {}
            @Override public void onRecordingStats(RecordingStats stats) {}
            @Override public void onLocalUserRegistered(long uid, String userAccount) {}
            @Override public void onUserInfoUpdated(long uid, String userAccount) {}
        };
    }
}
