package seedu.address.model.commitment;

import org.joda.time.LocalTime;

import seedu.address.model.location.Locatable;
import seedu.address.model.location.Location;
import seedu.address.model.time.Day;
import seedu.address.model.time.Schedulable;
import seedu.address.model.time.TimePeriod;

import java.util.Objects;

/**
 * A commitment that causes a person to be unavailable.
 */
public class Commitment implements Locatable, Schedulable {
    private final Location location;
    private final TimePeriod timePeriod;

    public Commitment(Location location, TimePeriod timePeriod) {
        this.location = location;
        this.timePeriod = timePeriod;
    }

    @Override
    public double getLatitude() {
        return location.getLatitude();
    }

    @Override
    public double getLongitude() {
        return location.getLongitude();
    }

    @Override
    public Day getDay() {
        return timePeriod.getDay();
    }

    @Override
    public LocalTime getStartTime() {
        return timePeriod.getStartTime();
    }

    @Override
    public LocalTime getEndTime() {
        return timePeriod.getEndTime();
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("Commitment[%s, %s]", location, timePeriod);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Commitment)) {
            return false;
        }

        Commitment otherCommitment = (Commitment) other;
        return timePeriod.equals(otherCommitment.timePeriod)
                && location.equals(otherCommitment.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timePeriod, location);
    }
}
