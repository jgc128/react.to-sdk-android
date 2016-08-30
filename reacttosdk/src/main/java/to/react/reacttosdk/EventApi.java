package to.react.reacttosdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.Excluder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import to.react.reacttosdk.Helpers.DeviceHelper;
import to.react.reacttosdk.Models.Event.Event;
import to.react.reacttosdk.Models.Event.EventAdd;
import to.react.reacttosdk.Models.Event.Events;
import to.react.reacttosdk.Models.Event.NewEvent;
import to.react.reacttosdk.Models.Event.Target;
import to.react.reacttosdk.Models.Token;
import to.react.reacttosdk.Models.User.LoginSocial;
import to.react.reacttosdk.Models.User.Profile;
import to.react.reacttosdk.Models.User.User;
import to.react.reacttosdk.Tasks.GetTask;
import to.react.reacttosdk.Tasks.PostTask;

public class EventApi extends ReactApi {

    public interface EventListener extends ReactListener{
        void onResponseEvent(Event event);
        void onResponseEventAdded(int application, int id, String name);
        void onResponseEvents(List<Event> event);
    }

    public EventApi(Context context, EventListener listener){
        super(context, listener);
    }

    //Запрос событий для приложения appId
    public void sendRequestEvents(Integer appId){
        try {
            new GetTask(Urls.Base +
                    Urls.Events.replace("<APPID>", appId.toString()),
                    callbackEvents);
        } catch (final IOException e) {
            e.printStackTrace();
            Error(e.getMessage());
        }
    }

    //Запрос событий для приложения appId категории category
    public void sendRequestEventsForCategory(Integer appId, Integer category){
        try {
            new GetTask(Urls.Base + Urls.EventsCat.replace("<APPID>", appId.toString()) +
                    category, callbackEvents);
        } catch (final IOException e) {
            Error(e.getMessage());
        }
    }

    //Запрос события по идентификатору
    public void sendRequestEvent(Integer id){
        try {
            new GetTask(Urls.Base + Urls.Event + id + "/", callbackEvent);
        } catch (final IOException e) {
           Error(e.getMessage());
        }
    }

    //Добавление события
    public void sendRequestEventAdd(
            Integer id,
            String name,
            String description,
            String image,
            String url,
            Calendar date_start,
            Calendar date_end,
            Boolean negative_reactions,
            Boolean is_two,
            Target target_one,
            Target target_two){
        try {
            EventAdd newEvent = new EventAdd();
            newEvent.name = name;
            newEvent.application = id;
            newEvent.description = description;
            newEvent.image_url = image;
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            newEvent.date_start = format1.format(date_start.getTime());
            newEvent.date_end = format1.format(date_end.getTime());
            newEvent.negative_reactions = negative_reactions ? 1 : 0;
            newEvent.is_two = is_two ? 1 : 0;
            if (target_one != null)
                newEvent.target_one = target_one;
            if (target_two != null)
                newEvent.target_two = target_two;
            String data = gson.toJson(newEvent);
            new PostTask(Urls.Base + Urls.EventAdd, data, callbackEventAdded);
        } catch (final IOException e) {
            Error(e.getMessage());
        }
    }

    //Добавление события
    public void sendRequestEventAdd(
            Integer id,
            EventAdd newEvent){
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            String data = gson.toJson(newEvent);
            new PostTask(Urls.Base + Urls.EventAdd, data, callbackEventAdded);
        } catch (final IOException e) {
            Error(e.getMessage());
        }
    }

    //Добавление события по ссылке
    public void sendRequestEventAddByLink(Integer id,String link){
        try {
            String data = "{\"application\": "+id+",\"link\": \""+link +"\"}";
            Log.e("sddh", data);
            new PostTask(Urls.Base + Urls.EventAdd, data, callbackEventAdded);
        } catch (final IOException e) {
           Error(e.getMessage());
        }
    }

    private Callback callbackEvents = new ReactCallback() {
        public void processResponse() {
            Events eventList = gson.fromJson(result, Events.class);
            final List<Event> events = new ArrayList<>(Arrays.asList(eventList.results));
            ((EventListener) listener).onResponseEvents(events);
        }
    };
    private Callback callbackEvent = new ReactCallback() {
        public void processResponse() {
            final Event event = gson.fromJson(result, Event.class);
            ((EventListener)listener).onResponseEvent(event);
        }
    };
    private Callback callbackEventAdded = new ReactCallback() {
        public void processResponse() {
            final NewEvent event = gson.fromJson(result, NewEvent.class);
            ((EventListener)listener).onResponseEventAdded(event.application, event.id, event.name);
        }
    };



}
