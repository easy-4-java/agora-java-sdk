package io.agora.spring.boot;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.agora.spring.boot.resp.AgoraResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgoraOkHttp3TemplateTest {

    private AgoraProperties properties;
    private AgoraOkHttp3Template template;

    @BeforeEach
    void setUp() {
        properties = new AgoraProperties();
        properties.setAppId("testappid");
        properties.setLoginKey("key");
        properties.setLoginSecret("secret");
        OkHttpClient client = new OkHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        template = new AgoraOkHttp3Template(client, mapper, properties);
    }

    @Test
    void shouldCreateTemplate() {
        assertNotNull(template);
    }

    // --- HttpMethod enum tests ---
    @Test
    void shouldHaveAllHttpMethods() {
        AgoraOkHttp3Template.HttpMethod[] methods = AgoraOkHttp3Template.HttpMethod.values();
        assertEquals(8, methods.length);
    }

    @Test
    void shouldHaveCorrectNames() {
        assertEquals("GET", AgoraOkHttp3Template.HttpMethod.GET.getName());
        assertEquals("HEAD", AgoraOkHttp3Template.HttpMethod.HEAD.getName());
        assertEquals("POST", AgoraOkHttp3Template.HttpMethod.POST.getName());
        assertEquals("PUT", AgoraOkHttp3Template.HttpMethod.PUT.getName());
        assertEquals("PATCH", AgoraOkHttp3Template.HttpMethod.PATCH.getName());
        assertEquals("DELETE", AgoraOkHttp3Template.HttpMethod.DELETE.getName());
        assertEquals("OPTIONS", AgoraOkHttp3Template.HttpMethod.OPTIONS.getName());
        assertEquals("TRACE", AgoraOkHttp3Template.HttpMethod.TRACE.getName());
    }

    @Test
    void shouldApplyGetMethod() {
        Request.Builder builder = new Request.Builder().url("http://localhost");
        Request.Builder result = AgoraOkHttp3Template.HttpMethod.GET.apply(builder);
        assertNotNull(result);
    }

    @Test
    void shouldApplyHeadMethod() {
        Request.Builder builder = new Request.Builder().url("http://localhost");
        Request.Builder result = AgoraOkHttp3Template.HttpMethod.HEAD.apply(builder);
        assertNotNull(result);
    }

    @Test
    void shouldApplyPostMethod() {
        Request.Builder builder = new Request.Builder().url("http://localhost");
        Request.Builder result = AgoraOkHttp3Template.HttpMethod.POST.apply(builder, "{\"key\":\"value\"}");
        assertNotNull(result);
    }

    @Test
    void shouldApplyPutMethod() {
        Request.Builder builder = new Request.Builder().url("http://localhost");
        Request.Builder result = AgoraOkHttp3Template.HttpMethod.PUT.apply(builder, "{\"key\":\"value\"}");
        assertNotNull(result);
    }

    @Test
    void shouldApplyPatchMethod() {
        Request.Builder builder = new Request.Builder().url("http://localhost");
        Request.Builder result = AgoraOkHttp3Template.HttpMethod.PATCH.apply(builder, "{\"key\":\"value\"}");
        assertNotNull(result);
    }

    @Test
    void shouldApplyDeleteMethodWithBody() {
        Request.Builder builder = new Request.Builder().url("http://localhost");
        Request.Builder result = AgoraOkHttp3Template.HttpMethod.DELETE.apply(builder, "{\"key\":\"value\"}");
        assertNotNull(result);
    }

    @Test
    void shouldApplyDeleteMethodWithoutBody() {
        Request.Builder builder = new Request.Builder().url("http://localhost");
        Request.Builder result = AgoraOkHttp3Template.HttpMethod.DELETE.apply(builder);
        assertNotNull(result);
    }

    @Test
    void shouldApplyOptionsMethod() {
        Request.Builder builder = new Request.Builder().url("http://localhost");
        Request.Builder result = AgoraOkHttp3Template.HttpMethod.OPTIONS.apply(builder);
        assertNotNull(result);
    }

    @Test
    void shouldApplyTraceMethod() {
        Request.Builder builder = new Request.Builder().url("http://localhost");
        Request.Builder result = AgoraOkHttp3Template.HttpMethod.TRACE.apply(builder);
        assertNotNull(result);
    }

    @Test
    void shouldGetByNameReturnsNullForInvalidName() {
        assertNull(AgoraOkHttp3Template.HttpMethod.getByName(999));
    }

    // --- getHttpUrl tests ---
    @Test
    void shouldBuildHttpUrlWithoutParams() {
        okhttp3.HttpUrl url = template.getHttpUrl("http://localhost/api", null);
        assertNotNull(url);
        assertEquals("localhost", url.host());
    }

    @Test
    void shouldBuildHttpUrlWithEmptyParams() {
        okhttp3.HttpUrl url = template.getHttpUrl("http://localhost/api", new java.util.HashMap<>());
        assertNotNull(url);
    }

    @Test
    void shouldBuildHttpUrlWithParams() {
        java.util.Map<String, Object> params = new java.util.HashMap<>();
        params.put("key1", "value1");
        params.put("key2", "value2");
        okhttp3.HttpUrl url = template.getHttpUrl("http://localhost/api", params);
        assertNotNull(url);
        assertEquals("value1", url.queryParameter("key1"));
        assertEquals("value2", url.queryParameter("key2"));
    }

    @Test
    void shouldBuildHttpUrlWithNullParamValue() {
        java.util.Map<String, Object> params = new java.util.HashMap<>();
        params.put("key1", null);
        okhttp3.HttpUrl url = template.getHttpUrl("http://localhost/api", params);
        assertNotNull(url);
    }

    // --- readValue tests ---
    @Test
    void shouldReadValidJson() {
        // readValue uses fastjson2 JSONObject.parseObject which may map fields differently
        AgoraResponse response = template.readValue("{\"code\":200,\"success\":true}", AgoraResponse.class);
        assertNotNull(response);
    }

    @Test
    void shouldReadValidJsonWithCodeField() {
        // Test with the actual JSON field name used by fastjson2
        AgoraResponse response = template.readValue("{\"Code\":200,\"success\":true}", AgoraResponse.class);
        assertNotNull(response);
        assertEquals(200, response.getCode());
    }

    // --- createRequestBuilder tests ---
    @Test
    void shouldCreateRequestBuilder() throws Exception {
        okhttp3.HttpUrl url = okhttp3.HttpUrl.parse("http://localhost/api");
        Request.Builder builder = template.createRequestBuilder(url,
                AgoraOkHttp3Template.HttpMethod.GET, null, null);
        assertNotNull(builder);
    }

    @Test
    void shouldCreateRequestBuilderWithHeaders() throws Exception {
        okhttp3.HttpUrl url = okhttp3.HttpUrl.parse("http://localhost/api");
        java.util.Map<String, Object> headers = new java.util.HashMap<>();
        headers.put("X-Custom", "value");
        Request.Builder builder = template.createRequestBuilder(url,
                AgoraOkHttp3Template.HttpMethod.GET, headers, null);
        assertNotNull(builder);
    }

    @Test
    void shouldCreateRequestBuilderWithBody() throws Exception {
        okhttp3.HttpUrl url = okhttp3.HttpUrl.parse("http://localhost/api");
        java.util.Map<String, Object> body = new java.util.HashMap<>();
        body.put("key", "value");
        Request.Builder builder = template.createRequestBuilder(url,
                AgoraOkHttp3Template.HttpMethod.POST, null, body);
        assertNotNull(builder);
    }

    // --- MediaType constants ---
    @Test
    void shouldHaveMediaTypeConstants() {
        assertNotNull(AgoraOkHttp3Template.APPLICATION_JSON);
        assertNotNull(AgoraOkHttp3Template.APPLICATION_JSON_UTF8);
        assertEquals("application/json", AgoraOkHttp3Template.APPLICATION_JSON_VALUE);
        assertEquals("application/json;charset=UTF-8", AgoraOkHttp3Template.APPLICATION_JSON_UTF8_VALUE);
    }
}
