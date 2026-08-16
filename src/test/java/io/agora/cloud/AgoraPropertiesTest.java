package io.agora.cloud;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgoraPropertiesTest {

    @Test
    void shouldHaveCorrectPrefix() {
        assertEquals("agora", AgoraProperties.PREFIX);
    }

    @Test
    void shouldCreateWithDefaults() {
        AgoraProperties props = new AgoraProperties();
        assertEquals(3600, props.getExpirationTimeInSeconds());
        assertNull(props.getAppId());
        assertNull(props.getAppCertificate());
        assertNull(props.getLoginKey());
        assertNull(props.getLoginSecret());
    }

    @Test
    void shouldSetAndGetAppId() {
        AgoraProperties props = new AgoraProperties();
        props.setAppId("test-app-id");
        assertEquals("test-app-id", props.getAppId());
    }

    @Test
    void shouldSetAndGetAppCertificate() {
        AgoraProperties props = new AgoraProperties();
        props.setAppCertificate("test-cert");
        assertEquals("test-cert", props.getAppCertificate());
    }

    @Test
    void shouldSetAndGetLoginKey() {
        AgoraProperties props = new AgoraProperties();
        props.setLoginKey("key");
        assertEquals("key", props.getLoginKey());
    }

    @Test
    void shouldSetAndGetLoginSecret() {
        AgoraProperties props = new AgoraProperties();
        props.setLoginSecret("secret");
        assertEquals("secret", props.getLoginSecret());
    }

    @Test
    void shouldSetAndGetExpirationTime() {
        AgoraProperties props = new AgoraProperties();
        props.setExpirationTimeInSeconds(7200);
        assertEquals(7200, props.getExpirationTimeInSeconds());
    }

    @Test
    void shouldSetAndGetOssRegion() {
        AgoraProperties props = new AgoraProperties();
        props.setOssRegion(7);
        assertEquals(7, props.getOssRegion());
    }

    @Test
    void shouldSetAndViewDimensions() {
        AgoraProperties props = new AgoraProperties();
        props.setViewWidth(1920);
        props.setViewHeight(1080);
        assertEquals(1920, props.getViewWidth());
        assertEquals(1080, props.getViewHeight());
    }

    @Test
    void shouldImplementEqualsAndHashCode() {
        AgoraProperties props1 = new AgoraProperties();
        props1.setAppId("id1");
        AgoraProperties props2 = new AgoraProperties();
        props2.setAppId("id1");
        assertEquals(props1, props2);
        assertEquals(props1.hashCode(), props2.hashCode());
    }

    @Test
    void shouldImplementToString() {
        AgoraProperties props = new AgoraProperties();
        assertNotNull(props.toString());
    }
}
