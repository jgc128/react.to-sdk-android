package to.react.sdk.android.Api;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.ArrayMap;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Map;

public abstract class BaseApiRequest<T> {

    protected String baseUrl = "http://react.flowmaster.org/";

    public Request getRequest() {
        String url = getUrl();
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String errorMsg = error.getMessage();
                onApiError(errorMsg);
                Log.e("---", "error" + errorMsg);
            }
        };
        Response.Listener listener = createListener();
        Request request = createRequest(url, listener, errorListener);
        return request;
    }

    protected Response.Listener createListener() {
        Response.Listener<JSONObject> listener =  new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                onJsonResponse(response);
            }
        };
        return listener;
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected Request createRequest(String url, Response.Listener listener, Response.ErrorListener errorListener) {
        final Map<String, String> mHeaders = new ArrayMap<String, String>();
        //mHeaders.put("token","3b2e49931e19699b955ed2cc9723fb7ebcc0211f");
        JsonObjectRequest request = new JsonObjectRequest(url, listener, errorListener){
            public Map<String, String> getHeaders() {
                return mHeaders;
            }
        };
        return  request;
    }

    protected void onJsonResponse(JSONObject response) {
        try {
            T result = getFromJson(response);
            Log.e("____1", String.valueOf(response));
            Log.e("____2", String.valueOf(result));
            onApiResponse(result);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("___3", "error)-" + e.getMessage());
            onApiError(e.getMessage());
        }
    }

    abstract protected String getUrl();
    abstract protected T getFromJson(JSONObject json) throws Exception;
    abstract public void onApiResponse(T result);
    abstract public void onApiError(String error);
}
