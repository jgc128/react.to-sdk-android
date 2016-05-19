package to.react.sdk.android;

import android.content.Context;

import to.react.sdk.android.Api.Requests.BaseApiRequest;

public class ReactApi {

    private Context mCtx;

    private String serverAddress = "http://react.flowmaster.org/";
    private String wsAddress = "ws://react.flowmaster.org/realtime/clients/universal_event";

    public ReactApi(Context context) {
        mCtx = context;
    }

    public void executeRequest(BaseApiRequest request){
        ReactApiSingleton.getInstance(mCtx).addToRequestQueue(request.getRequest());
    }

}
