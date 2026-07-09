package io.agora.spring.boot;

import lombok.Data;

/**
 * Agora configuration properties (plain POJO, no Spring dependency).
 * In a Spring Boot application, use @ConfigurationProperties to bind to these properties.
 */
@Data
public class AgoraProperties {

	/**
	 * The prefix of the property of { AgoraProperties}.
	 */
	public static final String PREFIX = "agora";

	/** appId */
	private String appId;
	/** 证书 */
	private String appCertificate;
	/** token过期时间 */
	private int expirationTimeInSeconds = 3600;
	/** 声网restful登录key: 必填 */
	private String loginKey;
	/** 声网restful登录密钥: 必填 */
	private String loginSecret;

	/** 录制区域选择 7-香港 10 -新加坡 */
	private Integer ossRegion;

	/** 声网视频宽度 */
	private Integer viewWidth;

	/** 声网视频高度 */
	private Integer viewHeight;

}
