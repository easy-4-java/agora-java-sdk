package io.agora.recording.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordingEnginePropertiesTest {

    @Test
    void shouldReturnNullStorageDirByDefault() {
        RecordingEngineProperties props = new RecordingEngineProperties();
        assertNull(props.getStorageDir());
    }
}
