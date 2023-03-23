package seedu.address.model.time;

import org.joda.time.LocalTime;
import seedu.address.model.location.Location;

import java.util.Optional;

/**
 * Interface for objects that have a day, start time and end time.
 */
public interface Schedulable {
    /**
     * Returns the day of the Schedulable object.
     */
    Day getDay();

    /**
     * Returns the start time of the Schedulable object.
     */
    LocalTime getStartTime();

    /**
     * Returns the end time of the Schedulable object.
     */
    LocalTime getEndTime();

    /**
     * Checks whether the person is free.
     */
    boolean isFree();
}
