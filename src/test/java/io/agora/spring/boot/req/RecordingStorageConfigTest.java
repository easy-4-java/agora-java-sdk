package io.agora.spring.boot.req;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordingStorageConfigTest {

    @Test
    void shouldCreateWithDefaults() {
        RecordingStorageConfig config = new RecordingStorageConfig();
        assertNotNull(config);
    }

    @Test
    void shouldSetAndGetVendor() {
        RecordingStorageConfig config = new RecordingStorageConfig();
        config.setVendor(2);
        assertEquals(2, config.getVendor());
    }

    @Test
    void shouldSetAndGetRegion() {
        RecordingStorageConfig config = new RecordingStorageConfig();
        config.setRegion(0);
        assertEquals(0, config.getRegion());
    }

    @Test
    void shouldSetAndGetBucket() {
        RecordingStorageConfig config = new RecordingStorageConfig();
        config.setBucket("my-bucket");
        assertEquals("my-bucket", config.getBucket());
    }

    @Test
    void shouldSetAndGetAccessKey() {
        RecordingStorageConfig config = new RecordingStorageConfig();
        config.setAccessKey("ak");
        assertEquals("ak", config.getAccessKey());
    }

    @Test
    void shouldSetAndGetSecretKey() {
        RecordingStorageConfig config = new RecordingStorageConfig();
        config.setSecretKey("sk");
        assertEquals("sk", config.getSecretKey());
    }

    @Test
    void shouldSetAndGetFileNamePrefix() {
        RecordingStorageConfig config = new RecordingStorageConfig();
        java.util.List<String> prefix = java.util.Arrays.asList("dir1", "dir2");
        config.setFileNamePrefix(prefix);
        assertEquals(prefix, config.getFileNamePrefix());
    }

    @Test
    void shouldImplementEqualsAndHashCode() {
        RecordingStorageConfig c1 = new RecordingStorageConfig();
        c1.setVendor(2);
        c1.setBucket("b1");
        RecordingStorageConfig c2 = new RecordingStorageConfig();
        c2.setVendor(2);
        c2.setBucket("b1");
        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void shouldImplementToString() {
        RecordingStorageConfig config = new RecordingStorageConfig();
        assertNotNull(config.toString());
    }
}
