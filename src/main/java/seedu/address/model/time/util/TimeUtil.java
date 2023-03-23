package seedu.address.model.time.util;

import org.joda.time.LocalTime;
import seedu.address.model.time.Schedulable;

public class TimeUtil {
    public static boolean areSameDay(Schedulable s1, Schedulable s2) {
        return s1.getDay().equals(s2.getDay());
    }

    public static boolean areConsecutive(Schedulable s1, Schedulable s2) {
        return areSameDay(s1, s2)
                && (s1.getEndTime().isEqual(s2.getStartTime())
                || s1.getStartTime().isEqual(s2.getEndTime()));
    }

    public static LocalTime getNewStartTime(Schedulable s1, Schedulable s2) {
        LocalTime startTime1 = s1.getStartTime();
        LocalTime startTime2 = s2.getStartTime();
        if (startTime1.isBefore(startTime2)) {
            return startTime1;
        }
        return startTime2;
    }

    public static LocalTime getNewEndTime(Schedulable s1, Schedulable s2) {
        LocalTime endTime1 = s1.getEndTime();
        LocalTime endTime2 = s2.getEndTime();
        if (endTime1.isAfter(endTime2)) {
            return endTime1;
        }
        return endTime2;
    }

    /**
     * Formats the LocalTime to a more reader-friendly format.
     * @param time LocalTime
     * @return Reader-friendly string format.
     */
    public static String formatLocalTime(LocalTime time) {
        int hourOfDay = time.getHourOfDay();
        if (hourOfDay < 12) {
            return String.format("%d AM", hourOfDay);
        } else if (hourOfDay == 12) {
            return "12 PM";
        } else {
            return String.format("%d PM", hourOfDay % 12);
        }
    }
}
