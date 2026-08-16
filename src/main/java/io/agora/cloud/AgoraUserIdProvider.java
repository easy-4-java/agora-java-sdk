/*
 * Copyright (c) 2018, vindell (https://github.com/vindell).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package io.agora.cloud;

/**
 * Strategy interface for mapping between Agora channel names and user IDs.
 * Implementations can provide custom channel naming conventions.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 3.0.0
 * @see AgoraTemplate
 */
public interface AgoraUserIdProvider {

	default String getUserIdByChannel(String appid, String channel)  {
		return channel;
	}
	
	default String getChannelByUserId(String appid, String userId) {
		return userId;
	}

}
