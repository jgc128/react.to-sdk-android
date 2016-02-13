package to.react.sdk.android.reactto;


import android.content.Context;

import to.react.sdk.android.reactto.Api.BaseApiRequest;

/**
 * ReactTo REST API
 */
public class ReactApi {

    private Context mCtx;

    public ReactApi(Context context) {
        mCtx = context;
    }

    public void executeRequest(BaseApiRequest request){
        ReactApiSingleton.getInstance(mCtx).addToRequestQueue(request.getRequest());
    }

}
