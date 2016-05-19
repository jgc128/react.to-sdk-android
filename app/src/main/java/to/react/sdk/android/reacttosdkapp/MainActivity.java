package to.react.sdk.android.reacttosdkapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import to.react.sdk.android.Api.Model.App;
import to.react.sdk.android.Api.Model.BaseReactMessage;
import to.react.sdk.android.Api.Model.Event;
import to.react.sdk.android.Api.Model.InteractionUpdateMessage;
import to.react.sdk.android.Api.Requests.AppsApiRequest;
import to.react.sdk.android.ReactApi;

public class MainActivity extends AppCompatActivity {

    TextView testTextView;

    ReactApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        api = new ReactApi(this);

        testTextView = (TextView)findViewById(R.id.test);
        testTextView.setText("zzzz");

        api.executeRequest(new AppsApiRequest() {
            @Override
            public void onApiResponse(List<App> result) {
                testTextView.setText(result.get(0).Name);
            }
        });




//        api.executeRequest(new AppsApiRequest() {
//            @Override
//            public void onApiResponse(List<App> result) {
//                testTextView.setText(result.get(0).Name);
//            }
//        });


//        App testApp = new App();
//        testApp.Id = 2;
//        api.executeRequest(new AppEventsApiRequest(testApp) {
//            @Override
//            public void onApiResponse(List<Event> result) {
//                testTextView.setText(result.get(0).Name + "\n" + result.get(1).Name + "\n" + result.get(2).Name);
//            }
//        });


//        Event testEvent = new Event();
//        testEvent.Id = 10;
//        api.executeRequest(new EventTargetsApiRequest(testEvent) {
//            @Override
//            public void onApiResponse(List<EventTarget> result) {
//                testTextView.setText(
//                        result.get(0).Name + "\n" + result.get(1).Name
//                        + "\n\n" + result.get(0).Interactions.get(0).Id
//                        + " - " + result.get(0).Interactions.get(0).MinValue + ":" + result.get(0).Interactions.get(0).MaxValue
//                );
//            }
//        });


//        App testApp = new App();
//        testApp.Id = 2;
//        api.executeRequest(new AddEventByLinkApiRequest(testApp, "http://www.twitch.tv/low4n") {
//            @Override
//            public void onApiResponse(ApiRequestStatusResult result) {
//                if(result.Status == ApiRequestStatusResult.StatusResult.Ok) {
//                    log("Added!");
//                }
//                if(result.Status == ApiRequestStatusResult.StatusResult.Error) {
//                    log("Error!");
//                }
//            }
//        });



//        App testApp = new App();
//        testApp.Id = 2;
//        NewEvent testEvent = new NewEvent();
//        testEvent.Name = "zzzz - sdk android";
//        testEvent.Description = "zzzzzz";
//
//        try {
//            testEvent.DateStart = DateTimeHelper.parseDateString("2015-07-22T15:00:00");
//            testEvent.DateEnd = DateTimeHelper.parseDateString("2015-08-22T15:00:00");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        api.executeRequest(new AddEventApiRequest(testApp, testEvent) {
//            @Override
//            public void onApiResponse(ApiRequestStatusResult result) {
//                if(result.Status == ApiRequestStatusResult.StatusResult.Ok) {
//                    log("Added!");
//                }
//                if(result.Status == ApiRequestStatusResult.StatusResult.Error) {
//                    log("Error!");
//                }
//            }
//        });



//        ortc = new ReactOrtc("cacRFz", "universal_event_channel_to_receive_reaction", "universal_event_channel_to_send_aggregate") {
//            @Override
//            public void onInteractionUpdate(final InteractionUpdateMessage message) {
//                log("Interaction: " + message.Value);
//            }
//
//            @Override
//            public void onUsersCounterUpdate(UsersCounterUpdateMessage message) {
//                log("Users: " + message.Users);
//            }
//            @Override
//            public void onSubscribed() {
//
//                Interaction testInteraction = new Interaction();
//                testInteraction.Id = 11;
//                testInteraction.Type = "FloatValueInteraction";
//                ortc.sendReaction("zzz", testInteraction, 0.3);
//
////                Event testEvent = new Event();
////                testEvent.Id = 10;
////                ortc.sendUsersCounter("zzz", testEvent, UsersCounterType.Connect);
////                ortc.sendUsersCounter("zzz", testEvent, UsersCounterType.Disconnect);
//            }
//        };
//
//        try {
//            ortc.init();
//            ortc.connect();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }


//
//        React r = new React(this, "cacRFz", "universal_event_channel_to_receive_reaction") {
//            @Override
//            public void onReactMessage(BaseReactMessage message) {
//                if (message instanceof InteractionUpdateMessage) {
//                    InteractionUpdateMessage ium = (InteractionUpdateMessage)message;
//
//                    log("Interaction: " + ium.InteractionId + " - " + ium.Value);
//                } else {
//                    log("Message: " + message.Action);
//                }
//            }
//        };
//
//        try {
//            r.connect();
//
//            Event testEvent = new Event();
//            testEvent.Id = 10;
//
//            r.subscribe(testEvent);
//
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void log(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String currentText = testTextView.getText().toString();
                String newText = currentText + "\n" + text;
                testTextView.setText(newText);
            }
        });

    }
}
