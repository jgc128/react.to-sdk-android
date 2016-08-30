package to.react.reacttosdk;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import to.react.reacttosdk.Helpers.DeviceHelper;
import to.react.reacttosdk.Models.Detail;
import to.react.reacttosdk.Models.Token;
import to.react.reacttosdk.Models.User.Gender;
import to.react.reacttosdk.Models.User.Login;
import to.react.reacttosdk.Models.User.LoginSocial;
import to.react.reacttosdk.Models.User.Profile;
import to.react.reacttosdk.Models.User.ProfileUpdate;
import to.react.reacttosdk.Models.User.User;
import to.react.reacttosdk.Tasks.DeleteTask;
import to.react.reacttosdk.Tasks.GetTask;
import to.react.reacttosdk.Tasks.PostTask;
import to.react.reacttosdk.Tasks.PutTask;

public class UserApi extends ReactApi{

    public interface UserListener extends ReactListener {
        void onResponseUser(User user);
        void onResponseProfile(Profile user);
        void onResponseToken(Token message);
    }

    public String SocialFacebook = "facebook";
    public String SocialGoogle = "google";


    public UserApi(Context context, UserListener listener){
        super(context, listener);
    }

    public void sendRequestLogin() {
        try {
            String data = prepareRequestLogin();
            new PostTask(Urls.Base + Urls.Login, data, callbackToken);
        } catch (final IOException e) {
            e.printStackTrace();
           Error(e.getMessage());
        }
    }

    //!!!
    private void sendRequestLogout(String token) {
        try {
            String data = prepareRequestLogin();
            new DeleteTask(Urls.Base + Urls.Login, callbackToken, token);
        } catch (final IOException e) {
            e.printStackTrace();
            Error(e.getMessage());
        }
    }

    public void sendRequestLoginSocial(String token,String social) {
        try {
            String data = prepareRequestLoginSocial(token, social);
            new PostTask(Urls.Base + Urls.Login, data, callbackToken);
        } catch (final IOException e) {
            Error(e.getMessage());
        }
    }

    public void sendRequestUserInfo(String token){
        try {
            new GetTask(Urls.Base + Urls.UserInfo, callbackUser, token);
        } catch (final IOException e) {
            Error(e.getMessage());
        }
    }

    public void sendRequestUpdate(
            String token,
            Integer profileId,
            String email,
            String first_name,
            String last_name,
            Integer age,
            Gender gender,
            String city,
            String profession,
            String education) {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            ProfileUpdate ul = new ProfileUpdate();
            ul.email = email;
            ul.first_name = first_name;
            ul.last_name = last_name;
            ul.age = age;
            ul.gender = gender;
            ul.city = city;
            ul.profession = profession;
            ul.education = education;
            String data = gson.toJson(ul);
            new PutTask(Urls.Base + Urls.UserProfile + profileId + "/", data, callbackProfile, token);

        } catch (final IOException e) {
            e.printStackTrace();
            Error(e.getMessage());
        }
    }

    public void sendRequestUpdate(
            String token,
            Integer profileId,
            ProfileUpdate profile) {
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            String data = gson.toJson(profile);
            new PutTask(Urls.Base + Urls.UserProfile + profileId + "/", data, callbackProfile, token);

        } catch (final IOException e) {
            e.printStackTrace();
            Error(e.getMessage());
        }
    }

    public void sendRequestUserLocation(String token, double lat, double lon){
        try {
            String data = "{\"lat\": "+lat+",\"lon\": "+lon +"}";
            new PostTask(Urls.Base + Urls.UserGeolocation, data, callbackRequest, token);
        } catch (final IOException e) {
            e.printStackTrace();
            Error(e.getMessage());
        }
    }

    private String prepareRequestLogin() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Login ul = new Login();
        ul.device_id = new DeviceHelper().deviceID(context);
        return gson.toJson(ul);
    }

    private String prepareRequestLoginSocial(String token, String social) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        LoginSocial ul = new LoginSocial();
        ul.device_id = new DeviceHelper().deviceID(context);
        ul.access_token = token;
        ul.social_network = social;
        return gson.toJson(ul);
    }

    private okhttp3.Callback callbackToken = new ReactCallback() {
        @Override
        protected void processResponse() {
            final Token token = gson.fromJson(result,Token.class);
            ((UserListener) listener).onResponseToken(token);
        }
    };

    private okhttp3.Callback callbackUser = new ReactCallback() {
        @Override
        protected void processResponse() {
            final User user = gson.fromJson(result,User.class);
            ((UserListener)listener).onResponseUser(user);
        }
    };
    private okhttp3.Callback callbackProfile = new ReactCallback() {
        @Override
        protected void processResponse() {
            final Profile profile = gson.fromJson(result,Profile.class);
            ((UserListener)listener).onResponseProfile(profile);
        }
    };
    private okhttp3.Callback callbackRequest = new ReactCallback() {
        @Override
        protected void processResponse() {
            listener.onSuccess();
        }
    };
}
