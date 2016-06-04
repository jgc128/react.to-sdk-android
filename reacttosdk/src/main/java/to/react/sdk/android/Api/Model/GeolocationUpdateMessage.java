package to.react.sdk.android.Api.Model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeolocationUpdateMessage extends BaseReactMessage {
    public class Geolocation {
        public double Latitude;
        public double Longitude;
    }

    @SerializedName("interaction_id")
    public long InteractionId;

    @SerializedName("locations")
    public List<Geolocation> Locations;
}
