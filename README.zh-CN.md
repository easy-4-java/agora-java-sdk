# agora-java-sdk

[English](./README.md) | [简体中文](./README.zh-CN.md)

[![Java](https://img.shields.io/badge/Java-21-orange)](https://github.com/easy-4-java/agora-java-sdk) [![License](https://img.shields.io/badge/license-Apache%202.0-green)](https://www.apache.org/licenses/LICENSE-2.0.txt)

> 与 Spring Boot 解耦的声网（Agora）Java SDK：RTC / RTM / Signaling Token 生成、
> 云端录制与频道管理 REST API、以及本地服务端录制（on-premise recording）桥接。

## 目录

- [1. 项目概述](#1-项目概述)
- [2. 功能与状态](#2-功能与状态)
- [3. 环境要求与兼容性](#3-环境要求与兼容性)
- [4. 架构与模块](#4-架构与模块)
- [5. 安装](#5-安装)
- [6. 快速开始](#6-快速开始)
- [7. 配置](#7-配置)
- [8. 核心用法 / API](#8-核心用法--api)
- [9. 测试与构建](#9-测试与构建)
- [10. 版本与分支](#10-版本与分支)
- [11. 贡献与许可](#11-贡献与许可)

## 1. 项目概述

`agora-java-sdk` 是一个纯 Java（与 Spring Boot 解耦）的声网平台服务端 SDK，封装了
服务端应用常用的声网能力：

- **Token 生成** — RTC Token（`RtcTokenBuilder`）、RTM Token（`RtmTokenBuilder`）、
  Signaling Token（`SignalingToken`）以及底层 `AccessToken` 构造器。
- **云端录制** — REST 客户端操作：获取资源、开始 / 更新 / 查询 / 停止录制、更新布局。
- **频道管理** — REST 客户端操作：查询频道用户状态、频道用户列表、频道列表。
- **本地录制** — `RecordingSDK`（JNI 桥接），对接声网录制端原生库，用于本地 / 私有化录制场景。

整个 SDK（包括 `io.agora.cloud` 包的 REST 门面 `AgoraTemplate`、`AgoraProperties`）
都是纯 POJO 层——编译和运行都不依赖任何框架类。Spring Boot 集成由独立的
`agora-spring-boot-starter` 项目提供。

它不是：

- 客户端 RTC SDK（不含音视频采集、编码与传输）。
- 声网录制端原生库的替代品——`io.agora.recording` 包需要宿主机上安装录制端原生库。

典型场景：

| 场景 | 使用内容 |
| :--- | :--- |
| 为移动 / Web 客户端签发 RTC 频道 Token | `RtcTokenBuilder` / `AgoraTemplate.generateToken(...)` |
| 频道云端录制（获取 → 开始 → 查询 → 停止） | `AgoraCloudRecordingOperations` |
| 查询频道内用户 / 频道列表 | `AgoraChannelManagerOperations` |
| 本地录制服务器 | `RecordingSDK` + `RecordingConfig` + `RecordingEventHandler` |

## 2. 功能与状态

| 能力 | 状态 | 说明 |
| :--- | :--- | :--- |
| RTC Token 生成（`RtcTokenBuilder`） | 稳定 | `buildTokenWithUid`、`buildTokenWithUserAccount`，角色 `Role_Publisher` / `Role_Subscriber` / `Role_Admin` |
| RTM Token 生成（`RtmTokenBuilder`） | 稳定 | `buildToken(appId, appCertificate, account, role, privilegeTs)` |
| Signaling Token（`SignalingToken`） | 稳定 | `getToken(appId, certificate, account, expiredTsInSeconds)` |
| 云端录制 REST 操作 | 稳定 | 获取、开始、更新（合流 / 单流）、更新布局、查询、停止 |
| 频道管理 REST 操作 | 稳定 | `getChannelUserState`、`getChannelUserList`、`getChannelList` |
| `AgoraTemplate` 门面 | 稳定 | `opsForChannel()`、`opsForCloudRecording()`、`generateToken(...)` |
| `AgoraProperties`（前缀 `agora`） | 稳定 | 纯 POJO，Spring Boot 中可用 `@ConfigurationProperties` 绑定 |
| 本地录制桥接（`RecordingSDK`） | 稳定 | native 方法；需要声网录制端原生库 |

## 3. 环境要求与兼容性

| 要求 | 版本 |
| :--- | :--- |
| JDK | 21+ |
| Maven | 3.x（项目内置 Maven Wrapper `./mvnw`） |
| 声网账号 | App ID 与 App Certificate（声网控制台） |
| （可选）本地录制 | 宿主机安装声网录制端原生库 |

版本线：

| 分支 | JDK | 版本 |
| :--- | :--- | :--- |
| `feature/1.0.x` | 8 | `1.0.x.*` |
| `feature/2.0.x` | 17 | `2.0.x.*` |
| `feature/3.0.x` | 21 | `3.0.x.*` |

## 4. 架构与模块

```text
+------------------+   +------------------------------------------+
| RTC client app   |   | agora-java-sdk (single jar)              |
| (channel / uid)  |-->|  media   : RtcTokenBuilder, AccessToken  |
| Agora Console    |   |  rtm/sig : RtmTokenBuilder, Signaling    |
| (AppId / Cert)   |   |  cloud : AgoraTemplate, Operations |
|                  |   |  recording : RecordingSDK (native)       |
+------------------+   +---------------------+--------------------+
                                            |
                                            v
                     +-------------------------------------------+
                     | Agora REST API / RTC network              |
                     +-------------------------------------------+
```

单模块 Maven 工程（`packaging: jar`），无子模块。

| 构件 | 职责 |
| :--- | :--- |
| `io.github.easy4j:agora-java-sdk` | Token 生成 + 云端录制 / 频道管理 REST 客户端 + 本地录制桥接 |

## 5. 安装

项目**尚未发布到 Maven Central**。快照 / 发布版本通过阿里云 Maven 仓库与 GitHub
Releases 分发。请在 Maven `settings.xml` / `pom.xml` 中配置对应仓库后使用：

Maven：

```xml
<dependency>
    <groupId>io.github.easy4j</groupId>
    <artifactId>agora-java-sdk</artifactId>
    <version>3.0.x.x.20260630-SNAPSHOT</version>
</dependency>
```

Gradle：

```groovy
implementation 'io.github.easy4j:agora-java-sdk:3.0.x.x.20260630-SNAPSHOT'
```

## 6. 快速开始

生成 RTC Token（无需任何配置）：

```java
import io.agora.media.RtcTokenBuilder;

public class TokenDemo {

    public static void main(String[] args) {
        RtcTokenBuilder builder = new RtcTokenBuilder();
        // Token 有效期 1 小时
        int expireTs = (int) (System.currentTimeMillis() / 1000) + 3600;
        String token = builder.buildTokenWithUid(
                "YOUR_APP_ID",
                "YOUR_APP_CERTIFICATE",
                "channel-1",
                123456,                                  // 数字 uid
                RtcTokenBuilder.Role.Role_Publisher,
                expireTs);
        System.out.println("RTC token: " + token);
    }
}
```

预期结果：得到一个非空、base64 编码的 Access Token，声网服务端在过期时间前
可正常校验该 Token。

## 7. 配置

本库自身没有运行时配置文件。使用 `io.agora.cloud` 门面时，
`AgoraProperties` 定义了属性前缀 `agora`（常量 `AgoraProperties.PREFIX`）。
Spring Boot 中通过 `@ConfigurationProperties(prefix = "agora")` 绑定。

| 属性（前缀 `agora`） | 类型 | 默认值 | 说明 |
| :--- | :--- | :--- | :--- |
| `app-id` | String | - | 声网 App ID |
| `app-certificate` | String | - | App 证书，用于 Token 签名 |
| `expiration-time-in-seconds` | int | `3600` | Token 有效期（秒） |
| `login-key` | String | - | 声网 RESTful 登录 key（REST API 必填） |
| `login-secret` | String | - | 声网 RESTful 登录密钥 |
| `oss-region` | Integer | - | 录制区域，如 7 = 中国香港，10 = 新加坡 |
| `view-width` | Integer | - | 录制视频宽度 |
| `view-height` | Integer | - | 录制视频高度 |

示例（`application.yml`）：

```yaml
agora:
  app-id: your-app-id
  app-certificate: your-app-certificate
  expiration-time-in-seconds: 3600
  login-key: your-restful-login-key
  login-secret: your-restful-login-secret
```

## 8. 核心用法 / API

### 8.1 `AgoraTemplate` 门面（对 Spring 友好）

`AgoraTemplate` 由三个协作者构造：`AgoraUserIdProvider`（用户 ID 与频道名的映射）、
`AgoraOkHttp3Template`（HTTP 客户端）与 `AgoraProperties`。

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import io.agora.cloud.*;
import okhttp3.OkHttpClient;

AgoraProperties props = new AgoraProperties();
props.setAppId("YOUR_APP_ID");
props.setAppCertificate("YOUR_APP_CERTIFICATE");
props.setLoginKey("YOUR_LOGIN_KEY");
props.setLoginSecret("YOUR_LOGIN_SECRET");

AgoraUserIdProvider userIdProvider = new AgoraUserIdProvider() { /* 默认用户 <-> 频道 1:1 映射 */ };
AgoraOkHttp3Template http = new AgoraOkHttp3Template(new OkHttpClient(), new ObjectMapper(), props);
AgoraTemplate template = new AgoraTemplate(userIdProvider, http, props);

// 1. 为频道签发 Token
String token = template.generateToken("user-1", "room-1"); // 默认 Role_Publisher

// 2. 云端录制：先获取资源 ID，再开始录制
AcquireResourceResponse acquire = template.opsForCloudRecording().acquireId("user-1", "10001");
CloudRecordingStartResponse start = template.opsForCloudRecording().startRecording(
        "room-1", "10001", token, acquire.getResourceId(), new RecordingStorageConfig());
```

### 8.2 云端录制生命周期

操作遵循声网官方 REST 流程，与文档化 API 一一对应：

`acquireId(userId, uid)` → `startRecording(channelName, uid, token, resourceId, mode, storageConfig, ...)`
→ `updateMixRecording(...)` / `updateIndividualRecording(...)` / `updateLayout(...)`
→ `queryRecording(channelName, uid, resourceId, sid, mode)` → `stopRecording(channelName, uid, resourceId, sid, mode, asyncStop)`。

请求 / 响应模型位于 `io.agora.cloud.req` 与 `io.agora.cloud.resp`
（如 `RecordingStorageConfig`、`TranscodingConfig`、`CloudRecordingQueryResponse`）。

## 9. 测试与构建

```bash
./mvnw clean verify
```

- 构建配置了 JaCoCo Maven 插件（报告 + 绑定在 `verify` 阶段的 `check` 目标，
  行覆盖率规则为 90%；`haltOnFailure=false`）。
- **假设**：1.0.x 分支当前 `src/test` 下未提交测试源码；覆盖率门禁仅在存在测试时生效。
- 本 worktree 的 `.github/` 下无 CI 工作流文件。

## 10. 版本与分支

| 分支 | JDK | 版本 | 说明 |
| :--- | :--- | :--- | :--- |
| `feature/1.0.x` | 8 | `1.0.x.*` | 当前分支，JDK 8 基线，维护中 |
| `feature/2.0.x` | 17 | `2.0.x.*` | JDK 17 版本线 |
| `feature/3.0.x` | 21 | `3.0.x.*` | JDK 21 版本线 |

维护策略：`1.0.x` 版本线接收针对 JDK 8 基线的缺陷修复与兼容性更新；面向新 JDK 的
新特性在 `2.0.x` / `3.0.x` 版本线开发。发布物通过阿里云 Maven 仓库与 GitHub
Releases 分发；项目尚未发布到 Maven Central。

## 11. 贡献与许可

欢迎通过 GitHub Issue 或 Pull Request 参与贡献。

本项目基于 [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0.txt) 许可。
