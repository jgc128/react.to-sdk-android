package to.react.sdk.android.reactto.Helpers;


import android.content.Context;
import android.os.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import to.react.sdk.android.reactto.Api.BaseApiRequest;
import to.react.sdk.android.reactto.Api.EventTargetsApiRequest;
import to.react.sdk.android.reactto.Api.InteractionUpdateApiRequest;
import to.react.sdk.android.reactto.Api.Model.ApiRequestStatusResult;
import to.react.sdk.android.reactto.Api.Model.BaseReactMessage;
import to.react.sdk.android.reactto.Api.Model.Event;
import to.react.sdk.android.reactto.Api.Model.EventTarget;
import to.react.sdk.android.reactto.Api.Model.Interaction;
import to.react.sdk.android.reactto.Api.Model.InteractionUpdateMessage;
import to.react.sdk.android.reactto.ReactApi;
import to.react.sdk.android.reactto.ReactOrtc;

public class React {

    ReactApi api;
    ReactOrtc ortc;

    // get reactions stuff
    protected final static int SUBSCRIBE_INTERVAL = 1000;
    protected Event subscribedEvent;
    protected List<InteractionUpdateApiRequest> subscribedEventUpdateRequests = new ArrayList<>();
    protected Map<Long, Double> subscribedEventPreviousReactions = new HashMap<>();
    protected Handler subscribedEventHandler = new Handler();
    protected Runnable subscribedEventHandlerTask;

    public React(Context context, String apiKey, String sendChannel) {
        api = new ReactApi(context);
        ortc = new ReactOrtc(apiKey, sendChannel);

        subscribedEventHandlerTask = new Runnable() {
            @Override
            public void run() {
                updateInteraction();
                subscribedEventHandler.postDelayed(subscribedEventHandlerTask, SUBSCRIBE_INTERVAL);
            }
        };

    }
    public React(Context context, String apiKey, String sendChannel, String receiveChannel) {

        api = new ReactApi(context);
        ortc = new ReactOrtc(apiKey, sendChannel, receiveChannel) {
            @Override
            public void onReactMessage(BaseReactMessage message) {
                React.this.onReactMessage(message);
            }
        };

        subscribedEventHandlerTask = new Runnable() {
            @Override
            public void run() {
                updateInteraction();
                subscribedEventHandler.postDelayed(subscribedEventHandlerTask, SUBSCRIBE_INTERVAL);
            }
        };


    }

    protected void updateInteraction() {
        if(subscribedEventUpdateRequests != null)
            for (InteractionUpdateApiRequest r : subscribedEventUpdateRequests) {
                api.executeRequest(r);
            }
    }

    public void connect() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        ortc.init();
        ortc.connect();
    }

    public void executeRequest(BaseApiRequest request) {
        api.executeRequest(request);
    }
    public void sendReaction(String deviceId, Interaction interaction, double value) {
        ortc.sendReaction(deviceId, interaction, value);

    }
    public void sendUsersCounter(String deviceId, Event event, ReactOrtc.UsersCounterType usersCounterType)
    {
        ortc.sendUsersCounter(deviceId, event, usersCounterType);
    }

    public void onReactMessage(BaseReactMessage message) {

    }

    public void subscribe(Event event) {
        unsubscribe();

        subscribedEvent = event;

        api.executeRequest(new EventTargetsApiRequest(subscribedEvent) {
            @Override
            public void onApiResponse(List<EventTarget> result) {

                // create update requests
                subscribedEventUpdateRequests.clear();
                subscribedEventPreviousReactions.clear();

                for (EventTarget target : result) {
                    for (Interaction inter : target.Interactions) {
                        subscribedEventPreviousReactions.put(inter.Id, -99999.0);

                        InteractionUpdateApiRequest r = new InteractionUpdateApiRequest(inter) {
                            @Override
                            public void onApiResponse(InteractionUpdateMessage result) {
                                if (subscribedEventPreviousReactions.get(result.InteractionId) != result.Value) {
                                    subscribedEventPreviousReactions.put(result.InteractionId, result.Value);
                                    React.this.onReactMessage(result);

                                }
                            }
                        };
                        subscribedEventUpdateRequests.add(r);
                    }
                }
            }
        });

        subscribedEventHandlerTask.run();
    }

    public void unsubscribe() {
        subscribedEventHandler.removeCallbacks(subscribedEventHandlerTask);
    }


}
