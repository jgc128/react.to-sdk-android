package to.react.sdk.android.Api.Model;


import java.util.Date;
import java.util.List;

public class Event {
    public long Id;
    public String Name;
    public String Description;
    public long ParentEventId;
    public Date DateStart;
    public Date DateEnd;
    public String ImageUrl;
    public String Link;
    public boolean IsNegativeReactions;
    public List<EventTarget> Targets;
}
