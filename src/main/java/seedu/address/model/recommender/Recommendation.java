package seedu.address.model.recommender;

import java.util.Objects;

import seedu.address.model.location.Location;
import seedu.address.model.time.TimePeriod;

public class Recommendation {
    private final Location location;
    private final TimePeriod timePeriod;

    public Recommendation(Location location, TimePeriod timePeriod) {
        this.location = location;
        this.timePeriod = timePeriod;
    }

    public Location getLocation() {
        return location;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", location, timePeriod);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Recommendation)) {
            return false;
        }

        Recommendation otherRecommendation = (Recommendation) other;
        return location.equals(otherRecommendation.location)
                && timePeriod.equals(otherRecommendation.timePeriod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, timePeriod);
    }
}
