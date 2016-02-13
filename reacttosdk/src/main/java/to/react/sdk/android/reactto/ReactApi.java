package to.react.sdk.android.reactto;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.List;

import to.react.sdk.android.reactto.Api.App;
import to.react.sdk.android.reactto.Api.AppsApiRequest;
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
