package to.react.sdk.android.reactto;


import ibt.ortc.api.Ortc;
import ibt.ortc.extensibility.OnConnected;
import ibt.ortc.extensibility.OnException;
import ibt.ortc.extensibility.OnMessage;
import ibt.ortc.extensibility.OnSubscribed;
import ibt.ortc.extensibility.OrtcClient;
import ibt.ortc.extensibility.OrtcFactory;

public class ReactBaseOrtc {

    protected String apiKey;
    protected String sendChannel;
    protected String receiveChannel;

    protected OrtcClient client;

    public ReactBaseOrtc(String apiKey, String sendChannel, String receiveChannel) {
        this(apiKey, sendChannel);
        this.receiveChannel = receiveChannel;
    }

    public ReactBaseOrtc(String apiKey, String sendChannel) {
        this.apiKey = apiKey;
        this.sendChannel = sendChannel;

        this.receiveChannel = null;
    }

    protected void createOrtcClient() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        OrtcFactory factory;

        Ortc ortc = new Ortc();
        factory = ortc.loadOrtcFactory("IbtRealtimeSJ");
        client = factory.createClient();

        client.setClusterUrl("http://ortc-developers.realtime.co/server/2.1/");
        client.setConnectionMetadata("AndroidApp");

        client.onConnected = new OnConnected() {
            @Override
            public void run(final OrtcClient sender) {
                if(receiveChannel != null) {
                    client.subscribe(receiveChannel, true,
                            new OnMessage() {
                                public void run(OrtcClient sender, String channel, String message) {
                                    onMessage(message);
                                };
                            }
                    );
                }
            }
        };

        client.onSubscribed = new OnSubscribed() {
            @Override
            public void run(OrtcClient sender, String channel) {
                onSubscribed();
            }
        };

        client.onException = new OnException() {
            @Override
            public void run(OrtcClient send, Exception ex) {
                ex.printStackTrace();
            }
        };

    }

    protected void onMessage(String message) {

    }
    protected void sendMessage(String message) {
        client.send(this.sendChannel, message);
    }

    public void init() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        if (client == null)
            createOrtcClient();
    }

    public void connect() {
        if(!client.getIsConnected())
            client.connect(this.apiKey, "myAuthenticationToken");
    }

    public void disconnect() {
        if(client.getIsConnected())
            client.disconnect();
    }

    public void onSubscribed() {

    }

    public boolean isReceivingOrtc() {
        return receiveChannel != null;
    }

}
