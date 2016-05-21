package to.react.sdk.android.Api.Requests;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import to.react.sdk.android.Api.Model.App;
import to.react.sdk.android.Api.Model.DemographicCategory;

public abstract class AppDemographicApiRequest extends BaseApiRequest<List<DemographicCategory>> {
    App targetApp;

    public AppDemographicApiRequest(App targetApp) {
        this.targetApp = targetApp;
    }

    @Override
    protected String getRequestUrl() {
        return "universal_event/apps/" + targetApp.Id + "/demographics/";
    }

    @Override
    protected List<DemographicCategory> getObject(JsonElement json) throws Exception {
        JsonElement jsonRes = ((JsonObject)json).getAsJsonArray("results");

        return super.getObject(jsonRes);
    }

    @Override
    protected Type getType() {
        return new TypeToken<List<DemographicCategory>>(){}.getType();
    }

}
