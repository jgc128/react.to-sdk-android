package to.react.sdk.android.reacttosdkapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import to.react.sdk.android.reactto.Api.Model.Event;
import to.react.sdk.android.reactto.Api.Model.EventTarget;
import to.react.sdk.android.reactto.Api.EventTargetsApiRequest;
import to.react.sdk.android.reactto.Api.Model.Interaction;
import to.react.sdk.android.reactto.Api.Model.InteractionUpdateMessage;
import to.react.sdk.android.reactto.Api.Model.UsersCounterUpdateMessage;
import to.react.sdk.android.reactto.ReactApi;
import to.react.sdk.android.reactto.ReactOrtc;

public class MainActivity extends AppCompatActivity {

    TextView testTextView;

    ReactApi api;
    ReactOrtc ortc;

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


        ortc = new ReactOrtc("cacRFz", "universal_event_channel_to_receive_reaction", "universal_event_channel_to_send_aggregate") {
            @Override
            public void onInteractionUpdate(final InteractionUpdateMessage message) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        testTextView.setText("Interaction:" + message.Value);
                    }
                });
            }

            @Override
            public void onUsersCounterUpdate(UsersCounterUpdateMessage message) {

            }
            @Override
            public void onSubscribed() {
                Interaction testInteraction = new Interaction();
                testInteraction.Id = 11;
                testInteraction.Type = "FloatValueInteraction";
                ortc.sendReaction("zzz", testInteraction, 0.3);
            }
        };

        try {
            ortc.init();
            ortc.connect();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



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
}
