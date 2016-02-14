package to.react.sdk.android.reactto.Api.Model;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import to.react.sdk.android.reactto.Api.BaseApiRequest;

public abstract class BaseApiPostRequest<T> extends BaseApiRequest<T> {

    @Override
    protected Response.Listener createListener() {
        Response.Listener<String> listener =  new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject  jsonResponse = new JSONObject(response);

                    onJsonResponse(jsonResponse);
                } catch (JSONException e) {
                    e.printStackTrace();

                    onApiError(e.getMessage());
                }
            }
        };

        return listener;
    }

    @Override
    protected Request createRequest(String url, Response.Listener listener, Response.ErrorListener errorListener) {
        final Map<String, String> requestParams = getParams();

        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                return requestParams;
            };
        };

        return request;
    }

    abstract protected Map<String, String> getParams();

}
