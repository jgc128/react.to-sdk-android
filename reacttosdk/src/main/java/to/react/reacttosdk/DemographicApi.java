package to.react.reacttosdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import to.react.reacttosdk.Models.Demographic.Category;
import to.react.reacttosdk.Models.Demographic.Demographics;
import to.react.reacttosdk.Models.Event.Event;
import to.react.reacttosdk.Models.Event.Events;
import to.react.reacttosdk.Tasks.GetTask;
import to.react.reacttosdk.Tasks.PostTask;

public class DemographicApi extends ReactApi{

    public interface DemographicListener extends ReactApi.ReactListener{
        void onResponseCategories(List<Category> listCategories);
    }

    public DemographicApi(Context context, DemographicListener listener){
        super(context, listener);
    }

    public void sendRequestDemographicsUser(String token, Integer choice){
        try {
            String data = "{\"choice\":" + choice + "}";
            new PostTask(Urls.Base +
                    Urls.UserDemographic, data,
                    callbackRequest, token);
        } catch (final IOException e) {
            Error(e.getMessage());
        }
    }
    public void sendRequestDemographics(Integer appId){
        try {
            new GetTask(Urls.Base +
                    Urls.Demographics.replace("<APPID>", appId.toString()),
                    callbackDemographics);
        } catch (final IOException e) {
            Error(e.getMessage());
        }
    }

    private okhttp3.Callback callbackRequest = new ReactCallback() {
        @Override
        protected void processResponse() {
            listener.onSuccess();
        }
    };

    private okhttp3.Callback callbackDemographics = new ReactCallback() {
        @Override
        protected void processResponse() {
            Demographics demographicList = gson.fromJson(
                    result,
                    Demographics.class);
            final List<Category> listCategories = new ArrayList<>(Arrays.asList(demographicList.results));
            ((DemographicListener) listener).onResponseCategories(listCategories);
        }
    };
}
