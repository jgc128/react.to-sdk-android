package to.react.sdk.android.Api.Requests;


//public class AccountLogoutApiRequest extends BaseApiPostRequest<ApiRequestStatusResult> {
//
//    Account account;
//
//    public AccountLogoutApiRequest(Account account) {
//        this.account = account;
//    }
//
//    @Override
//    protected String getUrl() {
//        return baseUrl + "react/account/logout/";
//    }
//
//    @Override
//    protected Map<String, String> getParams() {
//
//        Map<String, String> params = new HashMap<>();
//
//        params.put("access_token", account.access_token);
//        params.put("device_id", account.device_id);
//
//        return params;
//    }
//
//
//    @Override
//    protected ApiRequestStatusResult getFromJson(JSONObject json) throws Exception {
//        String strStatus = StringHelper.toTitleCase(json.getJSONObject("response").getString("status"));
//
//        ApiRequestStatusResult result = new ApiRequestStatusResult();
//        result.Status = ApiRequestStatusResult.StatusResult.valueOf(strStatus);
//
//        return result;
//    }
//
//    @Override
//    public void onApiResponse(ApiRequestStatusResult result) {
//
//    }
//
//
//    @Override
//    public void onApiError(String error) {
//
//    }
//}
