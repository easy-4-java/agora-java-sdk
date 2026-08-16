package io.agora.cloud.req;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordingModeTest {

    @Test
    void shouldHaveMixMode() {
        assertNotNull(RecordingMode.MIX);
        assertEquals("mix", RecordingMode.MIX.getName());
    }

    @Test
    void shouldHaveIndividualMode() {
        assertNotNull(RecordingMode.INDIVIDUAL);
        assertEquals("individual", RecordingMode.INDIVIDUAL.getName());
    }

    @Test
    void shouldHaveWebMode() {
        assertNotNull(RecordingMode.WEB);
        assertEquals("web", RecordingMode.WEB.getName());
    }

    @Test
    void shouldHaveThreeModes() {
        assertEquals(3, RecordingMode.values().length);
    }

    @Test
    void shouldParseFromName() {
        assertEquals(RecordingMode.MIX, RecordingMode.valueOf("MIX"));
        assertEquals(RecordingMode.INDIVIDUAL, RecordingMode.valueOf("INDIVIDUAL"));
        assertEquals(RecordingMode.WEB, RecordingMode.valueOf("WEB"));
    }
}
