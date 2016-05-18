package to.react.sdk.android.Api;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import to.react.sdk.android.Api.Model.ApiRequestStatusResult;
import to.react.sdk.android.Api.Model.BaseApiPostRequest;
import to.react.sdk.android.Api.Model.FacebookProfile;
import to.react.sdk.android.Helpers.DateTimeHelper;
import to.react.sdk.android.Helpers.StringHelper;

public class UpdateFacebookProfileApiRequest  extends BaseApiPostRequest<ApiRequestStatusResult> {

    String deviceId;
    String accessToken;
    FacebookProfile facebookProfile;

    public UpdateFacebookProfileApiRequest(String deviceId, String accessToken, FacebookProfile facebookProfile) {
        this.deviceId = deviceId;
        this.accessToken = accessToken;
        this.facebookProfile = facebookProfile;
    }

    @Override
    protected String getUrl() {
        return baseUrl + "universal_event/facebook_profile/";
    }

    @Override
    protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();

        params.put("id", Long.toString(facebookProfile.Id));
        params.put("name", facebookProfile.Name);
        params.put("first_name", facebookProfile.FirstName);
        params.put("last_name", facebookProfile.LastName);

        params.put("access_token", accessToken);
        params.put("device_id", deviceId);

        return params;
    }

    @Override
    protected ApiRequestStatusResult getFromJson(JSONObject json) throws Exception {
        String strStatus = StringHelper.toTitleCase(json.getJSONObject("response").getString("status"));

        ApiRequestStatusResult result = new ApiRequestStatusResult();
        result.Status = ApiRequestStatusResult.StatusResult.valueOf(strStatus);

        return result;
    }

    @Override
    public void onApiResponse(ApiRequestStatusResult result) {

    }

    @Override
    public void onApiError(String error) {

    }
}
