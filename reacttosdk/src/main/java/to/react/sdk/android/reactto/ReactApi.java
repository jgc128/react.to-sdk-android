package to.react.sdk;

import android.content.Context;

import to.react.sdk.Api.BaseApiRequest;

public class ReactApi {

    private Context mCtx;

    public ReactApi(Context context) {
        mCtx = context;
    }

    public void executeRequest(BaseApiRequest request){
        ReactApiSingleton.getInstance(mCtx).addToRequestQueue(request.getRequest());
    }

}
