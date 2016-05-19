package to.react.sdk.android.Api.Requests;

//public class AccountGeoApiRequest extends BaseApiPostRequest<ApiRequestStatusResult> {
//
//    Account account;
//
//    double lat;
//    double lon;
//
//    public AccountGeoApiRequest(Account account, double lat, double lon) {
//        this.account = account;
//        this.lat = lat;
//        this.lon = lon;
//    }
//
//    @Override
//    protected String getUrl() {
//        return baseUrl + "react/account/geo/";
//    }
//
//    @Override
//    protected Map<String, String> getParams() {
//
//        Map<String, String> params = new HashMap<>();
//
//        params.put("device_id", account.device_id);
//        params.put("device_token", account.social_network);
//        params.put("lat", String.valueOf(lat));
//        params.put("lon", String.valueOf(lon));
//
//        return params;
//    }
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
