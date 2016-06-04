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

import to.react.sdk.android.Api.Model.BaseReactMessage;
import to.react.sdk.android.Api.Model.InteractionUpdateMessage;
import to.react.sdk.android.Api.Model.ReactionMessage;
import to.react.sdk.android.Api.Model.User;
import to.react.sdk.android.Api.Requests.AccountLogoutApiRequest;
import to.react.sdk.android.Api.Requests.AccountUserApiRequest;
import to.react.sdk.android.Api.Requests.AccountUserDemographicsApiRequest;
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

        testTextView = (TextView)findViewById(R.id.test);
        testTextView.setText("zzzz");



        api = new ReactApi(this) {
            @Override
            public void onReactMessage(BaseReactMessage message) {
                if (message instanceof InteractionUpdateMessage) {
                    InteractionUpdateMessage msg = (InteractionUpdateMessage)message;
                    log(String.valueOf(msg.Value));
                }
            }
            @Override
            public void onReactConnected() {
                api.sendMessage(new ReactionMessage("zzz", 5));
            }
        };


//        api.executeRequest(new AppsApiRequest() {
//            @Override
//            public void onApiResponse(List<App> result) {
//                testTextView.setText(result.get(0).Name);
//            }
//        });

//        api.executeRequest(new AccountUserApiRequest("9a4b10e44d2f000e060e599d41fe3e0e26652d45") {
//            @Override
//            public void onApiResponse(User result) {
//                testTextView.setText(Long.toString(result.Id));
//            }
//        });

//        App targetApp = new App();
//        targetApp.Id = 1;
//        api.executeRequest(new AppDemographicApiRequest(targetApp) {
//            @Override
//            public void onApiResponse(List<DemographicCategory> result) {
//                testTextView.setText(result.get(0).Name);
//            }
//        });


//        App targetApp = new App();
//        targetApp.Id = 1;
//        api.executeRequest(new AppEventsApiRequest(targetApp) {
//            @Override
//            public void onApiResponse(List<Event> result) {
//                testTextView.setText(result.get(0).Name);
//            }
//        });

//        Event targetEvent = new Event();
//        targetEvent.Id = 31;
//        api.executeRequest(new EventDetailsApiRequest(targetEvent) {
//            @Override
//            public void onApiResponse(Event result) {
//                testTextView.setText(result.Name);
//            }
//        });


//        App testApp = new App();
//        testApp.Id = 1;
//        NewEvent testEvent = new NewEvent();
//        testEvent.Name = "zzzz - sdk android test 2";
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
//            public void onApiResponse(Event result) {
//                testTextView.setText(result.Name);
//            }
//        });


//        App testApp = new App();
//        testApp.Id = 1;
//        String testLink = "https://www.twitch.tv/nyxeira";
//        api.executeRequest(new AddEventByLinkApiRequest(testApp, testLink) {
//            @Override
//            public void onApiResponse(Event result) {
//                testTextView.setText(result.Name);
//            }
//        });



//        LoginInfo testLogin = new LoginInfo();
//        testLogin.AccessToken = "EAAIItymR5iwBALFZBtCIKojnuRf06JbWCOSMiLSITO7R8cAHq1C7FFZCzhI240V3I45eS53ZAWvpw9ft6iYxomlN6KX4Wn9PiG1Tp75WPohU6RV25SIAt0jvU1IiaoKUneH5wglqzbaR32lH452OgssA9YUo6p8MsWCvZBTyCFuMZBK5tqzoC";
//        testLogin.DeviceId = "test_device";
//        testLogin.SocialNetwork = LoginInfo.SocialNetworks.Facebook;
//
//        api.executeRequest(new AccountLoginApiRequest(testLogin) {
//            @Override
//            public void onApiResponse(LoginInfo.UserToken result) {
//                testTextView.setText(result.Token);
//            }
//        });


//        api.executeRequest(new AccountLogoutApiRequest("166a5394b8127f1266a8e67e49a0cd25af063957") {
//            @Override
//            public void onApiResponse(String result) {
//                testTextView.setText(result);
//            }
//        });

//        api.executeRequest(new AccountUserDemographicsApiRequest("50177533757d231b7045ab23ce388b50a754e461", 1) {
//            @Override
//            public void onApiResponse(User.UserDemographicChoice result) {
//                testTextView.setText(String.valueOf(result.ChoiceId));
//            }
//        });



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
