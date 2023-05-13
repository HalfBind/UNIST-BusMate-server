package halfbind.UNISTBusMate.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeManager {
    public static String addMinutes(String targetTime, Long minutes) {
        Pattern pattern = Pattern.compile("^([01]?[0-9]|2[0-3]):([0-5][0-9])$");
        Matcher matcher = pattern.matcher(targetTime);
        Long targetMinute;
        Long targetHour;
        if (matcher.matches()) {
            targetHour = Long.parseLong(matcher.group(1));
            targetMinute = Long.parseLong(matcher.group(2));
        } else {
            return null;
        }
        targetMinute += minutes;
        if (targetMinute >= 60) {
            targetMinute -= 60;
            targetHour += 1;
        }
        String result = "";
        if (targetHour < 10) {
            result += "0";
        }
        result += targetHour.toString();
        result += ":";
        if (targetMinute < 10) {
            result += "0";
        }
        result += targetMinute.toString();
        return result;
    }
}
