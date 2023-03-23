package seedu.address.model.time;

import org.joda.time.Hours;
import org.joda.time.LocalTime;


/**
 * Represents an hour timeslot in a Timetable.
 */
public class HourBlock extends TimePeriod {

    public HourBlock(LocalTime startTime, Day day) {
        super(startTime, startTime.plusHours(1), day);
    }

    /**
     * Copies an HourBlock to another HourBlock.
     */
    public HourBlock(HourBlock hourBlock) {
        this(hourBlock.getStartTime(), hourBlock.getDay());
    }

    public LocalTime getStartTime() {
        return super.getStartTime();
    }

    public LocalTime getEndTime() {
        return super.getEndTime();
    }
}
