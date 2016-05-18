package to.react.sdk.android.Helpers;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeHelper {

    protected static String defaultDateFormat = "yyyy-MM-dd'T'HH:mm:ss";

    public static long DateToUnixTimeStamp(Date date) {
        long unixTime = System.currentTimeMillis() / 1000L;
        return unixTime;
    }

    public static String formatDateString(Date date, String format) {
        return new SimpleDateFormat(format, Locale.US).format(date);
    }
    public static String formatDateString(Date date) {
        return formatDateString(date, defaultDateFormat);
    }

    public static Date parseDateString(String dateString, String format) throws ParseException {
        return new SimpleDateFormat(format, Locale.US).parse(dateString);
    }
    public static Date parseDateString(String dateString) throws ParseException {
        return parseDateString(dateString, defaultDateFormat);
    }


}
