package to.react.sdk.android.reactto.Api.Model;


public class InteractionUpdateMessage {
    public enum InteractionMode
    {
        Negative,
        Positive,
        Neutral
    }

    public String Action;
    public double Value;
    public InteractionMode Mode;
    public long InteractionId;
    public String InteractionType;
}
