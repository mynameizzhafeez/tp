package seedu.address.model.commitment;

import java.util.Objects;

import org.joda.time.LocalTime;

import seedu.address.model.location.Location;
import seedu.address.model.time.Day;
import seedu.address.model.time.TimePeriod;


/**
 * Represents a typical NUS lesson.
 */
public class Lesson extends Commitment {
    /**
     * Constructs a lesson.
     */
    public Lesson(LocalTime startTime, LocalTime endTime, Day day, Location location) {
        super(location, new TimePeriod(startTime, endTime, day));
    }

    public Lesson(LocalTime startTime, LocalTime endTime, Day day) {
        super(Location.NUS, new TimePeriod(startTime, endTime, day));
    }
}
