package to.react.sdk.android.Api.Requests;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import to.react.sdk.android.Api.Model.Event;
import to.react.sdk.android.Api.Model.LoginInfo;

public abstract class AccountLoginApiRequest extends BaseApiPostRequest<LoginInfo.UserToken> {
    LoginInfo info;

    public AccountLoginApiRequest(LoginInfo info) {
        this.info = info;
    }

    @Override
    protected String getRequestUrl() {
        return "account/login/";
    }

    @Override
    protected Type getType() {
        return new TypeToken<LoginInfo.UserToken>(){}.getType();
    }

    @Override
    protected JsonElement getData() {
        JsonObject data = (JsonObject)gson.toJsonTree(info);
        return data;
    }

}
