package to.react.sdk.android.Api.Model;


import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Event {
    @SerializedName("id")
    public long Id;
    @SerializedName("name")
    public String Name;

    @SerializedName("description")
    public String Description;

    @SerializedName("date_start")
    public Date DateStart;
    @SerializedName("date_end")
    public Date DateEnd;

    @SerializedName("image_url")
    public String ImageUrl;
    @SerializedName("link")
    public String Link;

    @SerializedName("negative_reactions")
    public boolean IsNegativeReactions;

    @SerializedName("targets")
    public List<Target> Targets;
}
