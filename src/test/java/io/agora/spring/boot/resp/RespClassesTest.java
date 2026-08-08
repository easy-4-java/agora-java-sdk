package io.agora.spring.boot.resp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RespClassesTest {

    @Test
    void shouldCreateCloudRecordingUpdateResponse() {
        CloudRecordingUpdateResponse response = new CloudRecordingUpdateResponse();
        assertNotNull(response);
        response.setCode(200);
        assertEquals(200, response.getCode());
    }

    @Test
    void shouldCreateCloudRecordingUpdateLayoutResponse() {
        CloudRecordingUpdateLayoutResponse response = new CloudRecordingUpdateLayoutResponse();
        assertNotNull(response);
        response.setCode(200);
        assertEquals(200, response.getCode());
    }

    @Test
    void shouldCreateCloudRecordingUpdateLayoutResponseDataBody() {
        CloudRecordingUpdateLayoutResponse.DataBody body = new CloudRecordingUpdateLayoutResponse.DataBody();
        assertNotNull(body);
    }

    @Test
    void shouldSetCloudRecordingUpdateLayoutResponseData() {
        CloudRecordingUpdateLayoutResponse response = new CloudRecordingUpdateLayoutResponse();
        CloudRecordingUpdateLayoutResponse.DataBody body = new CloudRecordingUpdateLayoutResponse.DataBody();
        response.setData(body);
        assertEquals(body, response.getData());
    }

    @Test
    void shouldCreateChannelUserListResponse() {
        ChannelUserListResponse response = new ChannelUserListResponse();
        assertNotNull(response);
        response.setCode(200);
        assertEquals(200, response.getCode());
    }

    @Test
    void shouldCreateChannelUserStateResponse() {
        ChannelUserStateResponse response = new ChannelUserStateResponse();
        assertNotNull(response);
        response.setCode(200);
        assertEquals(200, response.getCode());
    }

    @Test
    void shouldCreateCloudRecordingServiceResponse() {
        CloudRecordingServiceResponse response = new CloudRecordingServiceResponse();
        assertNotNull(response);
    }

    @Test
    void shouldCreateCloudRecordingServiceFile() {
        CloudRecordingServiceFile file = new CloudRecordingServiceFile();
        assertNotNull(file);
    }

    @Test
    void shouldCreateCloudRecordingExtensionServiceState() {
        CloudRecordingExtensionServiceState state = new CloudRecordingExtensionServiceState();
        assertNotNull(state);
    }

    @Test
    void shouldCreateCloudRecordingSubServiceStatus() {
        CloudRecordingSubServiceStatus status = new CloudRecordingSubServiceStatus();
        assertNotNull(status);
    }

    @Test
    void shouldSetAndGetCloudRecordingStartResponseFields() {
        CloudRecordingStartResponse response = new CloudRecordingStartResponse();
        response.setSid("sid-123");
        response.setResourceId("res-456");
        assertEquals("sid-123", response.getSid());
        assertEquals("res-456", response.getResourceId());
    }

    @Test
    void shouldSetAndGetAcquireResourceResponseFields() {
        AcquireResourceResponse response = new AcquireResourceResponse();
        response.setResourceId("res-789");
        response.setCname("channel-1");
        assertEquals("res-789", response.getResourceId());
        assertEquals("channel-1", response.getCname());
    }

    @Test
    void shouldImplementEqualsForCloudRecordingStartResponse() {
        CloudRecordingStartResponse r1 = new CloudRecordingStartResponse();
        r1.setSid("s1");
        r1.setResourceId("r1");
        r1.setCode(200);
        CloudRecordingStartResponse r2 = new CloudRecordingStartResponse();
        r2.setSid("s1");
        r2.setResourceId("r1");
        r2.setCode(200);
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void shouldImplementToStringForCloudRecordingStartResponse() {
        CloudRecordingStartResponse response = new CloudRecordingStartResponse();
        response.setSid("s1");
        assertNotNull(response.toString());
    }

    @Test
    void shouldImplementEqualsForAcquireResourceResponse() {
        AcquireResourceResponse r1 = new AcquireResourceResponse();
        r1.setResourceId("r1");
        r1.setCode(200);
        AcquireResourceResponse r2 = new AcquireResourceResponse();
        r2.setResourceId("r1");
        r2.setCode(200);
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void shouldImplementToStringForAcquireResourceResponse() {
        AcquireResourceResponse response = new AcquireResourceResponse();
        assertNotNull(response.toString());
    }

    @Test
    void shouldImplementEqualsForCloudRecordingStopResponse() {
        CloudRecordingStopResponse r1 = new CloudRecordingStopResponse();
        r1.setCode(200);
        CloudRecordingStopResponse r2 = new CloudRecordingStopResponse();
        r2.setCode(200);
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void shouldImplementToStringForCloudRecordingStopResponse() {
        CloudRecordingStopResponse response = new CloudRecordingStopResponse();
        assertNotNull(response.toString());
    }

    @Test
    void shouldImplementEqualsForCloudRecordingQueryResponse() {
        CloudRecordingQueryResponse r1 = new CloudRecordingQueryResponse();
        r1.setCode(200);
        CloudRecordingQueryResponse r2 = new CloudRecordingQueryResponse();
        r2.setCode(200);
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void shouldImplementToStringForCloudRecordingQueryResponse() {
        CloudRecordingQueryResponse response = new CloudRecordingQueryResponse();
        assertNotNull(response.toString());
    }

    @Test
    void shouldImplementEqualsForCloudRecordingUpdateResponse() {
        CloudRecordingUpdateResponse r1 = new CloudRecordingUpdateResponse();
        r1.setCode(200);
        CloudRecordingUpdateResponse r2 = new CloudRecordingUpdateResponse();
        r2.setCode(200);
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void shouldImplementToStringForCloudRecordingUpdateResponse() {
        CloudRecordingUpdateResponse response = new CloudRecordingUpdateResponse();
        assertNotNull(response.toString());
    }

    @Test
    void shouldImplementEqualsForCloudRecordingUpdateLayoutResponse() {
        CloudRecordingUpdateLayoutResponse r1 = new CloudRecordingUpdateLayoutResponse();
        r1.setCode(200);
        CloudRecordingUpdateLayoutResponse r2 = new CloudRecordingUpdateLayoutResponse();
        r2.setCode(200);
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void shouldImplementToStringForCloudRecordingUpdateLayoutResponse() {
        CloudRecordingUpdateLayoutResponse response = new CloudRecordingUpdateLayoutResponse();
        assertNotNull(response.toString());
    }

    @Test
    void shouldImplementEqualsForChannelUserListResponse() {
        ChannelUserListResponse r1 = new ChannelUserListResponse();
        r1.setCode(200);
        ChannelUserListResponse r2 = new ChannelUserListResponse();
        r2.setCode(200);
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void shouldImplementToStringForChannelUserListResponse() {
        ChannelUserListResponse response = new ChannelUserListResponse();
        assertNotNull(response.toString());
    }

    @Test
    void shouldImplementEqualsForChannelUserStateResponse() {
        ChannelUserStateResponse r1 = new ChannelUserStateResponse();
        r1.setCode(200);
        ChannelUserStateResponse r2 = new ChannelUserStateResponse();
        r2.setCode(200);
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void shouldImplementToStringForChannelUserStateResponse() {
        ChannelUserStateResponse response = new ChannelUserStateResponse();
        assertNotNull(response.toString());
    }
}
