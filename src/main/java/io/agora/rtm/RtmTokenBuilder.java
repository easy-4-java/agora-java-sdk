package io.agora.rtm;

import io.agora.media.AccessToken;

/**
 * Builds RTM (Real-Time Messaging) tokens for authenticating users in Agora RTM services.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see io.agora.media.AccessToken
 */
public class RtmTokenBuilder {
    public enum Role {
        Rtm_User(1);

        int value;
        Role(int value) {
            this.value = value;
        }
    }

    public AccessToken mTokenCreator;

    public String buildToken(String appId, String appCertificate,
            String uid, Role role, int privilegeTs) throws Exception {
        mTokenCreator = new AccessToken(appId, appCertificate, uid, "");
        mTokenCreator.addPrivilege(AccessToken.Privileges.kRtmLogin, privilegeTs);
        return mTokenCreator.build();
    }

    public void setPrivilege(AccessToken.Privileges privilege, int expireTs) {
        mTokenCreator.addPrivilege(privilege, expireTs);
    }

    public boolean initTokenBuilder(String originToken) {
        mTokenCreator.fromString(originToken);
        return true;
    }
}
