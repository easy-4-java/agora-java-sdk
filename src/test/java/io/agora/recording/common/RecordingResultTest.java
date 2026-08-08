package io.agora.recording.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordingResultTest {

    @Test
    void shouldBuildRecordingResult() {
        RecordingResult result = RecordingResult.builder()
                .channelId("test-channel")
                .leaveState(true)
                .width(640)
                .height(360)
                .fps(15)
                .kbps(500)
                .count(1)
                .firstReceiveAudioTime(1000L)
                .firstReceiveAudioElapsed(500L)
                .firstReceiveVideoTime(1200L)
                .firstReceiveVideoElapsed(600L)
                .storageDir("/tmp/recordings")
                .build();

        assertEquals("test-channel", result.getChannelId());
        assertTrue(result.isLeaveState());
        assertEquals(640, result.getWidth());
        assertEquals(360, result.getHeight());
        assertEquals(15, result.getFps());
        assertEquals(500, result.getKbps());
        assertEquals(1, result.getCount());
        assertEquals(1000L, result.getFirstReceiveAudioTime());
        assertEquals(500L, result.getFirstReceiveAudioElapsed());
        assertEquals(1200L, result.getFirstReceiveVideoTime());
        assertEquals(600L, result.getFirstReceiveVideoElapsed());
        assertEquals("/tmp/recordings", result.getStorageDir());
    }

    @Test
    void shouldBuildWithDefaults() {
        RecordingResult result = RecordingResult.builder().build();
        assertNull(result.getChannelId());
        assertFalse(result.isLeaveState());
        assertEquals(0, result.getWidth());
        assertEquals(0, result.getHeight());
        assertEquals(0, result.getFps());
        assertEquals(0, result.getKbps());
        assertEquals(0, result.getCount());
        assertEquals(0L, result.getFirstReceiveAudioTime());
        assertEquals(0L, result.getFirstReceiveAudioElapsed());
        assertEquals(0L, result.getFirstReceiveVideoTime());
        assertEquals(0L, result.getFirstReceiveVideoElapsed());
        assertNull(result.getStorageDir());
    }

    @Test
    void shouldSetAndGetChannelId() {
        RecordingResult result = RecordingResult.builder().build();
        result.setChannelId("new-channel");
        assertEquals("new-channel", result.getChannelId());
    }

    @Test
    void shouldSetAndGetLeaveState() {
        RecordingResult result = RecordingResult.builder().build();
        result.setLeaveState(true);
        assertTrue(result.isLeaveState());
    }

    @Test
    void shouldImplementEqualsAndHashCode() {
        RecordingResult r1 = RecordingResult.builder().channelId("ch1").width(640).build();
        RecordingResult r2 = RecordingResult.builder().channelId("ch1").width(640).build();
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void shouldImplementToString() {
        RecordingResult result = RecordingResult.builder().channelId("ch1").build();
        assertNotNull(result.toString());
        assertTrue(result.toString().contains("ch1"));
    }
}
