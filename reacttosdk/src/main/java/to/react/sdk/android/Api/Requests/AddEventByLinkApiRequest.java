package to.react.sdk.android.Api.Requests;


//public class AddEventByLinkApiRequest extends BaseApiPostRequest<ApiRequestStatusResult> {
//    App targetApp;
//    String eventLink;
//
//    public AddEventByLinkApiRequest(App app, String eventLink) {
//        targetApp = app;
//        this.eventLink = eventLink;
//    }
//
//    @Override
//    protected String getUrl() {
//        return baseUrl + "universal_event/apps/" + targetApp.Id + "/events/add_by_link/";
//    }
//
//    @Override
//    protected Map<String, String> getParams() {
//        Map<String, String> params = new HashMap<>();
//
//        params.put("link", eventLink);
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
//    @Override
//    public void onApiError(String error) {
//
//    }
//}
