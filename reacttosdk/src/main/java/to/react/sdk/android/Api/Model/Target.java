package to.react.sdk.android.Api.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;


public class Target {
    @SerializedName("id")
    public long Id;
    @SerializedName("name")
    public String Name;

    @SerializedName("date_start")
    public Date DateStart;
    @SerializedName("date_end")
    public Date DateEnd;

    @SerializedName("image_url")
    public String ImageUrl;

    @SerializedName("interactions")
    public List<Interaction> Interactions;
}
