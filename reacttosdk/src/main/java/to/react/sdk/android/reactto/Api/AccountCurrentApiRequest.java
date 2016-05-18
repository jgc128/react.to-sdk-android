package to.react.sdk.Api;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import to.react.sdk.Api.Model.Account;
import to.react.sdk.Api.Model.BaseApiPostRequest;

public class AccountCurrentApiRequest extends BaseApiPostRequest<Integer> {

    Account account;

    public AccountCurrentApiRequest(Account account) {
        this.account = account;
    }

    @Override
    protected String getUrl() {
        return baseUrl + "react/account/current/";
    }

    @Override
    protected Map<String, String> getParams() {

        Map<String, String> params = new HashMap<>();

        params.put("access_token", account.access_token);
        params.put("device_id", account.device_id);

        return params;
    }


    @Override
    protected Integer getFromJson(JSONObject json) throws Exception {
        return json.getJSONObject("response").getInt("id");
    }

    @Override
    public void onApiResponse(Integer result) {

    }


    @Override
    public void onApiError(String error) {

    }
}
