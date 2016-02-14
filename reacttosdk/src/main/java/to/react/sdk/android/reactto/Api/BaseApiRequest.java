package to.react.sdk.android.reactto.Api;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseApiRequest<T> {

    protected String baseUrl = "http://vseto-site-eu.cloudapp.net:8181/";

    public Request getRequest() {
        String url = getUrl();

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String errorMsg = error.getMessage();
                onApiError(errorMsg);
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
    protected Request createRequest(String url, Response.Listener listener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(url, listener, errorListener);

        return  request;
    }

    protected void onJsonResponse(JSONObject response) {
        try {
            T result = getFromJson(response);

            onApiResponse(result);

        } catch (Exception e) {
            e.printStackTrace();

            onApiError(e.getMessage());
        }
    }

    abstract protected String getUrl();
    abstract protected T getFromJson(JSONObject json) throws Exception;
    abstract public void onApiResponse(T result);
    abstract public void onApiError(String error);
}
