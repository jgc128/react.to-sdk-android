package to.react.sdk.android.Api.Model;

import com.google.gson.annotations.SerializedName;

public class LoginInfo {
    public class UserToken {
        @SerializedName("token")
        public String Token;
    }

    public enum SocialNetworks {
        Facebook,
        Google,
    }

    @SerializedName("device_id")
    public String DeviceId;

    @SerializedName("access_token")
    public String AccessToken;

    @SerializedName("social_network")
    public SocialNetworks SocialNetwork;


}
