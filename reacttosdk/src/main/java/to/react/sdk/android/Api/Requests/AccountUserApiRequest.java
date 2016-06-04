package to.react.sdk.android.Api.Requests;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import to.react.sdk.android.Api.Model.App;
import to.react.sdk.android.Api.Model.User;

public abstract class AccountUserApiRequest extends BaseApiAuthRequest<User> {
    public AccountUserApiRequest(String accessToken) {
        super(accessToken);
    }

    @Override
    protected String getRequestUrl() {
        return "account/user/";
    }

    @Override
    protected Type getType() {
        return new TypeToken<User>(){}.getType();
    }


}
