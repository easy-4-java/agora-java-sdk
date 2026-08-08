package io.agora.media;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.TreeMap;

import static io.agora.media.Utils.crc32;

/**
 * Builds and parses Agora Access Tokens for authenticating users in RTC and RTM channels.
 *
 * <p>An access token is a dynamic key generated using the App ID, App Certificate,
 * channel name, user ID, and privilege settings. It is used to authenticate
 * users joining Agora channels.</p>
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see RtcTokenBuilder
 * @see io.agora.rtm.RtmTokenBuilder
 */
public class AccessToken {
    /**
     * Privilege types that can be granted to a token.
     */
    public enum Privileges {
        kJoinChannel(1),
        kPublishAudioStream(2),
        kPublishVideoStream(3),
        kPublishDataStream(4),
        
        // For RTM only
        kRtmLogin(1000);
    	
        public short intValue;

        Privileges(int value) {
            intValue = (short) value;
        }
    }

    private static final String VER = "006";
    
    public String appId;
    public String appCertificate;
    public String channelName;
    public String uid;
    public byte[] signature;
    public byte[] messageRawContent;
    public int crcChannelName;
    public int crcUid;
    public PrivilegeMessage message;
    public int expireTimestamp;

    /**
     * Constructs an AccessToken with the specified parameters.
     *
     * @param appId          the Agora App ID
     * @param appCertificate the Agora App Certificate
     * @param channelName    the channel name
     * @param uid            the user ID as a string
     */
    public AccessToken(String appId, String appCertificate, String channelName, String uid) {
        this.appId = appId;
        this.appCertificate = appCertificate;
        this.channelName = channelName;
        this.uid = uid;
        this.crcChannelName = 0;
        this.crcUid = 0;
        this.message = new PrivilegeMessage();
    }

    /**
     * Builds the access token string.
     *
     * @return the encoded token string, or an empty string if the App ID or App Certificate is invalid
     * @throws Exception if an error occurs during signature generation or packing
     */
    public String build() throws Exception {
        if (! Utils.isUUID(appId)) {
            return "";
        }

        if (!Utils.isUUID(appCertificate)) {
            return "";
        }

        messageRawContent = Utils.pack(message);
        signature = generateSignature(appCertificate, 
        		appId, channelName, uid, messageRawContent);
        crcChannelName = crc32(channelName);
        crcUid = crc32(uid);

        PackContent packContent = new PackContent(signature, crcChannelName, crcUid, messageRawContent);
        byte[] content = Utils.pack(packContent);
        return getVersion() + this.appId + Utils.base64Encode(content);
    }

    /**
     * Adds a privilege with an expiration timestamp to the token.
     *
     * @param privilege      the privilege type to add
     * @param expireTimestamp the expiration timestamp in seconds since epoch
     */
    public void addPrivilege(Privileges privilege, int expireTimestamp) {
        message.messages.put(privilege.intValue, expireTimestamp);
    }

    /**
     * Returns the token version string.
     *
     * @return the version string ("006")
     */
    public static String getVersion() {
        return VER;
    }

    /**
     * Generates an HMAC-SHA256 signature for the token.
     *
     * @param appCertificate the App Certificate used as the HMAC key
     * @param appID          the App ID
     * @param channelName    the channel name
     * @param uid            the user ID
     * @param message        the raw message bytes to sign
     * @return the HMAC-SHA256 signature bytes
     * @throws Exception if an error occurs during signing
     */
    public static byte[] generateSignature(String appCertificate, 
    		String appID, String channelName, String uid, byte[] message) throws Exception {
    	
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            baos.write(appID.getBytes());
            baos.write(channelName.getBytes());
            baos.write(uid.getBytes());
            baos.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Utils.hmacSign(appCertificate, baos.toByteArray());
    }

    /**
     * Parses an access token string and populates this object's fields.
     *
     * @param token the token string to parse
     * @return {@code true} if the token was parsed successfully, {@code false} otherwise
     */
    public boolean fromString(String token) {
        if (!getVersion().equals(token.substring(0, Utils.VERSION_LENGTH))) {
            return false;
        }
        
        try {
            appId = token.substring(Utils.VERSION_LENGTH, Utils.VERSION_LENGTH + Utils.APP_ID_LENGTH);
            PackContent packContent = new PackContent();
            Utils.unpack(Utils.base64Decode(token.substring(Utils.VERSION_LENGTH + Utils.APP_ID_LENGTH, token.length())), packContent);
            signature = packContent.signature;
            crcChannelName = packContent.crcChannelName;
            crcUid = packContent.crcUid;
            messageRawContent = packContent.rawMessage;
            Utils.unpack(messageRawContent, message);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

    public class PrivilegeMessage implements PackableEx {
        public int salt;
        public int ts;
        public TreeMap<Short, Integer> messages;

        public PrivilegeMessage() {
            salt = Utils.randomInt();
            ts = Utils.getTimestamp() + 24 * 3600;
            messages = new TreeMap<>();
        }

        @Override
        public ByteBuf marshal(ByteBuf out) {
            return out.put(salt).put(ts).putIntMap(messages);
        }

        @Override
        public void unmarshal(ByteBuf in) {
            salt = in.readInt();
            ts = in.readInt();
            messages = in.readIntMap();
        }
    }

    public class PackContent implements PackableEx {
        public byte[] signature;
        public int crcChannelName;
        public int crcUid;
        public byte[] rawMessage;

        public PackContent() {
        	// Nothing done
        }
        
        public PackContent(byte[] signature, int crcChannelName, int crcUid, byte[] rawMessage) {
            this.signature = signature;
            this.crcChannelName = crcChannelName;
            this.crcUid = crcUid;
            this.rawMessage = rawMessage;
        }

        @Override
        public ByteBuf marshal(ByteBuf out) {
            return out.put(signature).put(crcChannelName).put(crcUid).put(rawMessage);
        }

        @Override
        public void unmarshal(ByteBuf in) {
            signature = in.readBytes();
            crcChannelName = in.readInt();
            crcUid = in.readInt();
            rawMessage = in.readBytes();
        }
    }
}
