package seedu.address.model.time;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import org.joda.time.Hours;
import org.joda.time.LocalTime;

import seedu.address.model.time.exceptions.InvalidTimeException;
import seedu.address.model.time.util.TimeUtil;


/**
 * Represents a period in time.
 */
public class TimePeriod implements Schedulable {

    private final LocalTime startTime;
    private final LocalTime endTime;
    private final Day day;

    /**
     * Constructs a period in time.
     */
    public TimePeriod(LocalTime startTime, LocalTime endTime, Day day) {
        if (!isValidTimePeriod(startTime, endTime, day)) {
            throw new InvalidTimeException("Start Time Cannot be after End Time!");
        }
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
    }

    public TimePeriod(Schedulable schedulable) {
        this(schedulable.getStartTime(), schedulable.getEndTime(), schedulable.getDay());
    }

    /**
     * Returns a copy of the {@code TimePeriod}
     */
    public TimePeriod copy() {
        return new TimePeriod(startTime, endTime, day);
    }

    private boolean isValidTimePeriod(LocalTime startTime, LocalTime endTime, Day day) {
        requireAllNonNull(startTime, endTime, day);
        return !startTime.isAfter(endTime);
    }

    @Override
    public LocalTime getStartTime() {
        return startTime;
    }

    @Override
    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public Day getDay() {
        return day;
    }

    @Override
    public boolean isFree() {
        return true;
    }

    public Hours getHoursBetween() {
        return Hours.hoursBetween(getStartTime(), getEndTime());
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]",
                TimeUtil.formatLocalTime(getStartTime()),
                TimeUtil.formatLocalTime(getEndTime()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimePeriod that = (TimePeriod) o;
        return getStartTime().isEqual(that.getStartTime())
                && getEndTime().isEqual(that.getEndTime())
                && getDay().equals(that.getDay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime, day);
    }
}
