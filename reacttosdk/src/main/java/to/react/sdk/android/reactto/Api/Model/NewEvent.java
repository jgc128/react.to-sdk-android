package to.react.sdk.android.reactto.Api.Model;


import java.util.Date;

public class NewEvent {

    public NewEvent(){
        IsTwoTarget = false;
        IsNegativeReactions = false;
        Description = "";
        ImageUrl = "";
    }

    public String Name;
    public String Description;
    public Date DateStart;
    public Date DateEnd;
    public String ImageUrl;
    public boolean IsTwoTarget;
    public String TargetOne;
    public String TargetTwo;
    public boolean IsNegativeReactions;

}
