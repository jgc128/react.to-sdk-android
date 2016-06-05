package to.react.sdk.android.Api.Requests;


import com.android.volley.Request;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import to.react.sdk.android.Api.Model.Event;
import to.react.sdk.android.Api.Model.ProfileUpdate;
import to.react.sdk.android.Api.Model.User;

public abstract class AccountUserProfileUpdateApiRequest extends BaseApiAuthPostRequest<User.Profile> {

    int profileId;
    ProfileUpdate profileUpdate;

    public AccountUserProfileUpdateApiRequest(String accessToken, int profileId, ProfileUpdate profileUpdate) {
        super(accessToken, Request.Method.PUT);

        this.profileId = profileId;
        this.profileUpdate = profileUpdate;
    }

    @Override
    protected String getRequestUrl() {
        return "account/user/profile/" + profileId + "/";
    }

    @Override
    protected Type getType() {
        return new TypeToken<User.Profile>(){}.getType();
    }

    @Override
    protected JsonElement getData() {
        JsonObject data = (JsonObject)gson.toJsonTree(profileUpdate);

        return data;
    }

}
