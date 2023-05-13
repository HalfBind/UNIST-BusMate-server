package halfbind.UNISTBusMate.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.util.Pair;

public class TimeManager {
    private static final Pattern TIME_PATTERN = Pattern.compile("^([01]?[0-9]|2[0-3]):([0-5][0-9])$");
    private static final Long HOUR_IN_MINUTES = 60L;

    private static Pair<Long, Long> convertStringToLong(String time) {
        Matcher matcher = TIME_PATTERN.matcher(time);
        Long hour;
        Long minute;
        if (matcher.matches()) {
            hour = Long.parseLong(matcher.group(1));
            minute = Long.parseLong(matcher.group(2));
            return Pair.of(hour, minute);
        }
        return null;
    }

    private static String convertLongToString(Long hour, Long minute) {
        if (minute >= 60) {
            minute -= 60;
            hour++;
        }

        String result = "";
        if (hour < 10) {
            result += "0";
        }
        result += hour.toString();

        result += ":";
        if (minute < 10) {
            result += "0";
        }
        result += minute.toString();

        return result;
    }

    public static String addMinutes(String targetTime, Long minutes) {
        Pair<Long, Long> targetTimeInLong = convertStringToLong(targetTime);
        Long targetHour = targetTimeInLong.getFirst();
        Long targetMinute = targetTimeInLong.getSecond();

        return convertLongToString(targetHour, targetMinute);
    }

    public static Boolean isAfter(String targetTime, String standardTime) {
        Pair<Long, Long> targetTimeInLong = convertStringToLong(targetTime);
        Long targetHour = targetTimeInLong.getFirst();
        Long targetMinute = targetTimeInLong.getSecond();
        Pair<Long, Long> standardTimeInLong = convertStringToLong(standardTime);
        Long standardHour = standardTimeInLong.getFirst();
        Long standardMinute = standardTimeInLong.getSecond();

        return HOUR_IN_MINUTES * targetHour + targetMinute >= HOUR_IN_MINUTES * standardHour + standardMinute;
    }

    public static Boolean isBefore(String targetTime, String standardTime) {
        return isAfter(standardTime, targetTime);
    }
}
