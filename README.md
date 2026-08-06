# agora-java-sdk

[English](./README.md) | [简体中文](./README.zh-CN.md)

[![Java](https://img.shields.io/badge/Java-17-orange)] [![License](https://img.shields.io/badge/license-Apache%202.0-green)](https://www.apache.org/licenses/LICENSE-2.0.txt)

> Spring Boot independent Java SDK for Agora: RTC / RTM / signaling token generation,
> Cloud Recording and Channel Management REST APIs, and the on-premise recording bridge.

## Table of Contents

- [1. Project Overview](#1-project-overview)
- [2. Features & Status](#2-features--status)
- [3. Requirements & Compatibility](#3-requirements--compatibility)
- [4. Architecture & Modules](#4-architecture--modules)
- [5. Installation](#5-installation)
- [6. Quick Start](#6-quick-start)
- [7. Configuration](#7-configuration)
- [8. Core Usage / API](#8-core-usage--api)
- [9. Testing & Build](#9-testing--build)
- [10. Versioning & Branches](#10-versioning--branches)
- [11. Contributing & License](#11-contributing--license)

## 1. Project Overview

`agora-java-sdk` is a plain-Java (Spring Boot independent) SDK that wraps the
Agora (声网) platform services commonly needed by server-side applications:

- **Token generation** — RTC tokens (`RtcTokenBuilder`), RTM tokens (`RtmTokenBuilder`),
  signaling tokens (`SignalingToken`) and the low-level `AccessToken` builder.
- **Cloud Recording** — REST client operations: acquire resource, start / update /
  query / stop recording, and layout updates.
- **Channel Management** — REST client operations: query channel user state,
  channel user list and channel list.
- **On-premise Recording** — a JNI bridge (`RecordingSDK`) to the Agora recording
  native library for local / on-premise recording scenarios.

The SDK core (packages `io.agora.media`, `io.agora.recording`, `io.agora.rtm`,
`io.agora.signal`) has no framework dependency. The `io.agora.spring.boot` package
provides a Spring-friendly facade (`AgoraTemplate`, `AgoraProperties`) that stays a
plain POJO layer — no Spring classes are required to compile or run it.

What it is **not**:

- Not a client-side RTC SDK (no audio/video capture, encoding or transport).
- Not a replacement for the Agora Recording SDK native binaries — the
  `io.agora.recording` package requires the native recording library to be
  installed on the host.

Typical scenarios:

| Scenario | What you use |
| :--- | :--- |
| Issue RTC channel tokens to mobile/web clients | `RtcTokenBuilder` / `AgoraTemplate.generateToken(...)` |
| Cloud recording of a live channel (acquire → start → query → stop) | `AgoraCloudRecordingOperations` |
| Query who is in a channel / list channels | `AgoraChannelManagerOperations` |
| On-premise recording server | `RecordingSDK` + `RecordingConfig` + `RecordingEventHandler` |

## 2. Features & Status

| Capability | Status | Notes |
| :--- | :--- | :--- |
| RTC token generation (`RtcTokenBuilder`) | Stable | `buildTokenWithUid`, `buildTokenWithUserAccount`, roles `Role_Publisher` / `Role_Subscriber` / `Role_Admin` |
| RTM token generation (`RtmTokenBuilder`) | Stable | `buildToken(appId, appCertificate, account, role, privilegeTs)` |
| Signaling token (`SignalingToken`) | Stable | `getToken(appId, certificate, account, expiredTsInSeconds)` |
| Cloud Recording REST operations | Stable | acquire, start, update (mix / individual), updateLayout, query, stop |
| Channel management REST operations | Stable | `getChannelUserState`, `getChannelUserList`, `getChannelList` |
| `AgoraTemplate` facade | Stable | `opsForChannel()`, `opsForCloudRecording()`, `generateToken(...)` |
| `AgoraProperties` (prefix `agora`) | Stable | Plain POJO, bind with `@ConfigurationProperties` in Spring Boot |
| On-premise recording bridge (`RecordingSDK`) | Stable | Native methods; requires the Agora recording native library |

## 3. Requirements & Compatibility

| Requirement | Version |
| :--- | :--- |
| JDK | 17+ |
| Maven | 3.x (Maven Wrapper `./mvnw` is included) |
| Agora account | App ID and App Certificate (Dashboard) |
| (Optional) On-premise recording | Agora recording native library on the host |

Version lines:

| Branch | JDK | Version |
| :--- | :--- | :--- |
| `feature/1.0.x` | 8 | `1.0.x.*` |
| `feature/2.0.x` | 17 | `2.0.x.*` |
| `feature/3.0.x` | 21 | `3.0.x.*` |

## 4. Architecture & Modules

```text
+------------------+   +------------------------------------------+
| RTC client app   |   | agora-java-sdk (single jar)              |
| (channel / uid)  |-->|  media   : RtcTokenBuilder, AccessToken  |
| Agora Console    |   |  rtm/sig : RtmTokenBuilder, Signaling    |
| (AppId / Cert)   |   |  spring.boot : AgoraTemplate, Operations |
|                  |   |  recording : RecordingSDK (native)       |
+------------------+   +---------------------+--------------------+
                                            |
                                            v
                     +-------------------------------------------+
                     | Agora REST API / RTC network              |
                     +-------------------------------------------+
```

Single-module Maven project (`packaging: jar`). No child modules.

| Artifact | Responsibility |
| :--- | :--- |
| `io.github.easy4j:agora-java-sdk` | Token generation + Cloud Recording / Channel Management REST clients + on-premise recording bridge |

## 5. Installation

The project is **not yet published to Maven Central**. Snapshots/releases are
distributed through the Aliyun Maven repository and GitHub Releases. Add the
repository to your Maven `settings.xml` / `pom.xml`, then:

Maven:

```xml
<dependency>
    <groupId>io.github.easy4j</groupId>
    <artifactId>agora-java-sdk</artifactId>
    <version>2.0.x.x.20260630-SNAPSHOT</version>
</dependency>
```

Gradle:

```groovy
implementation 'io.github.easy4j:agora-java-sdk:2.0.x.x.20260630-SNAPSHOT'
```

## 6. Quick Start

Generate an RTC token (no configuration required):

```java
import io.agora.media.RtcTokenBuilder;

public class TokenDemo {

    public static void main(String[] args) {
        RtcTokenBuilder builder = new RtcTokenBuilder();
        // token valid for 1 hour
        int expireTs = (int) (System.currentTimeMillis() / 1000) + 3600;
        String token = builder.buildTokenWithUid(
                "YOUR_APP_ID",
                "YOUR_APP_CERTIFICATE",
                "channel-1",
                123456,                                  // numeric uid
                RtcTokenBuilder.Role.Role_Publisher,
                expireTs);
        System.out.println("RTC token: " + token);
    }
}
```

Expected result: a non-empty base64-encoded access token that the Agora server
accepts for the channel until the expiry timestamp.

## 7. Configuration

This library has no runtime configuration file of its own. When used with the
`io.agora.spring.boot` facade, `AgoraProperties` defines the property prefix
`agora` (constant `AgoraProperties.PREFIX`). In Spring Boot, bind it with
`@ConfigurationProperties(prefix = "agora")`.

| Property (prefix `agora`) | Type | Default | Description |
| :--- | :--- | :--- | :--- |
| `app-id` | String | - | Agora App ID |
| `app-certificate` | String | - | App certificate, used for token signing |
| `expiration-time-in-seconds` | int | `3600` | Token validity in seconds |
| `login-key` | String | - | Agora RESTful login key (required by REST APIs) |
| `login-secret` | String | - | Agora RESTful login secret |
| `oss-region` | Integer | - | Recording region, e.g. 7 = Hong Kong, 10 = Singapore |
| `view-width` | Integer | - | Recording video width |
| `view-height` | Integer | - | Recording video height |

Example (`application.yml`):

```yaml
agora:
  app-id: your-app-id
  app-certificate: your-app-certificate
  expiration-time-in-seconds: 3600
  login-key: your-restful-login-key
  login-secret: your-restful-login-secret
```

## 8. Core Usage / API

### 8.1 `AgoraTemplate` facade (Spring-friendly)

`AgoraTemplate` is constructed from three collaborators: an `AgoraUserIdProvider`
(identity mapping between user id and channel name), an `AgoraOkHttp3Template`
(HTTP client) and `AgoraProperties`.

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import io.agora.spring.boot.*;
import okhttp3.OkHttpClient;

AgoraProperties props = new AgoraProperties();
props.setAppId("YOUR_APP_ID");
props.setAppCertificate("YOUR_APP_CERTIFICATE");
props.setLoginKey("YOUR_LOGIN_KEY");
props.setLoginSecret("YOUR_LOGIN_SECRET");

AgoraUserIdProvider userIdProvider = new AgoraUserIdProvider() { /* defaults map user <-> channel 1:1 */ };
AgoraOkHttp3Template http = new AgoraOkHttp3Template(new OkHttpClient(), new ObjectMapper(), props);
AgoraTemplate template = new AgoraTemplate(userIdProvider, http, props);

// 1. Issue a token for a channel
String token = template.generateToken("user-1", "room-1"); // Role_Publisher by default

// 2. Cloud recording: acquire a resource id, then start recording
AcquireResourceResponse acquire = template.opsForCloudRecording().acquireId("user-1", "10001");
CloudRecordingStartResponse start = template.opsForCloudRecording().startRecording(
        "room-1", "10001", token, acquire.getResourceId(), new RecordingStorageConfig());
```

### 8.2 Cloud recording lifecycle

The operations follow the official Agora REST flow and map 1:1 to documented APIs:

`acquireId(userId, uid)` → `startRecording(channelName, uid, token, resourceId, mode, storageConfig, ...)`
→ `updateMixRecording(...)` / `updateIndividualRecording(...)` / `updateLayout(...)`
→ `queryRecording(channelName, uid, resourceId, sid, mode)` → `stopRecording(channelName, uid, resourceId, sid, mode, asyncStop)`.

Request/response models live in `io.agora.spring.boot.req` and `io.agora.spring.boot.resp`
(e.g. `RecordingStorageConfig`, `TranscodingConfig`, `CloudRecordingQueryResponse`).

## 9. Testing & Build

```bash
./mvnw clean verify
```

- The build is configured with the JaCoCo Maven plugin (report + `check` goal with a
  90% line-coverage rule bound to the `verify` phase; `haltOnFailure=false`).
- **Assumption**: the 1.0.x branch currently checks in no test sources under
  `src/test`; coverage thresholds are therefore enforced only when tests exist.
- No CI workflow files are present under `.github/` in this worktree.

## 10. Versioning & Branches

| Branch | JDK | Version | Notes |
| :--- | :--- | :--- | :--- |
| `feature/1.0.x` | 8 | `1.0.x.*` | Current branch, JDK 8 baseline, maintained |
| `feature/2.0.x` | 17 | `2.0.x.*` | JDK 17 line |
| `feature/3.0.x` | 21 | `3.0.x.*` | JDK 21 line |

Maintenance policy: the `1.0.x` line receives bug fixes and compatibility updates
for the JDK 8 baseline. New features targeting newer JDKs land on the `2.0.x` /
`3.0.x` lines. Releases are published to the Aliyun Maven repository and as
GitHub Releases; the project is not yet published to Maven Central.

## 11. Contributing & License

Contributions are welcome — please open issues or pull requests on GitHub.

Licensed under the [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0.txt).
