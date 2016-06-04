package to.react.sdk.android.Api.Model;


import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class NewEvent {

    public NewEvent(){
        Description = "";
        ImageUrl = "";
        IsNegativeReactions = false;
    }

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

    @SerializedName("target_one")
    public String TargetOne;

    @SerializedName("target_two")
    public String TargetTwo;

    @SerializedName("negative_reactions")
    public boolean IsNegativeReactions;

}
