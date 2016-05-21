package to.react.sdk.android.Api.Requests;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

import to.react.sdk.android.Api.Model.Event;

public abstract class BaseApiListRequest<T> extends BaseApiRequest<T> {
    @Override
    protected T getObject(JsonElement json) throws Exception {
        JsonElement jsonRes = ((JsonObject)json).getAsJsonArray("results");

        return super.getObject(jsonRes);
    }
}
