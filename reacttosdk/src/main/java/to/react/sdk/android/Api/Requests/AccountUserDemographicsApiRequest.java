package to.react.sdk.android.Api.Requests;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import to.react.sdk.android.Api.Model.Event;
import to.react.sdk.android.Api.Model.LoginInfo;
import to.react.sdk.android.Api.Model.User;

abstract public class AccountUserDemographicsApiRequest extends BaseApiAuthPostRequest<User.UserDemographicChoice> {

    long choiceId;

    public AccountUserDemographicsApiRequest(String accessToken, long ChoicId) {
        super(accessToken);

        choiceId = ChoicId;
    }

    @Override
    protected String getRequestUrl() {
        return "account/user/demographics/";
    }

    @Override
    protected Type getType() {
        return new TypeToken<User.UserDemographicChoice>(){}.getType();
    }

    @Override
    protected JsonElement getData() {
        JsonObject data = new JsonObject();
        data.addProperty("choice", choiceId);

        return data;
    }
}
