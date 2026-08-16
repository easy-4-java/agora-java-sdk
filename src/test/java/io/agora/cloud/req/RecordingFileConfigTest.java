package io.agora.cloud.req;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecordingFileConfigTest {

    @Test
    void shouldCreateWithDefaults() {
        RecordingFileConfig config = new RecordingFileConfig();
        assertNotNull(config.getFileTypes());
        assertTrue(config.getFileTypes().contains("hls"));
        assertTrue(config.getFileTypes().contains("mp4"));
    }

    @Test
    void shouldSetAndGetFileTypes() {
        RecordingFileConfig config = new RecordingFileConfig();
        List<String> types = Arrays.asList("hls");
        config.setFileTypes(types);
        assertEquals(types, config.getFileTypes());
    }

    @Test
    void shouldImplementEqualsAndHashCode() {
        RecordingFileConfig c1 = new RecordingFileConfig();
        RecordingFileConfig c2 = new RecordingFileConfig();
        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void shouldImplementToString() {
        RecordingFileConfig config = new RecordingFileConfig();
        assertNotNull(config.toString());
    }
}
