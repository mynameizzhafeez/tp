package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.location.Location;
import seedu.address.model.recommender.Recommendation;
import seedu.address.model.scheduler.time.TimePeriod;

public class JsonAdaptedRecommendation {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Recommendation's %s field is missing!";

    protected final JsonAdaptedLocation location;
    protected final JsonAdaptedTimePeriod timePeriod;
    protected final boolean isSaved;

    /**
     * Constructs a {@code JsonAdaptedRecommendation} with the given recommendation details.
     */
    @JsonCreator
    public JsonAdaptedRecommendation(
            @JsonProperty("location") JsonAdaptedLocation location,
            @JsonProperty("timePeriod") JsonAdaptedTimePeriod timePeriod,
            @JsonProperty("isSaved") boolean isSaved) {
        this.location = location;
        this.timePeriod = timePeriod;
        this.isSaved = isSaved;
    }

    public JsonAdaptedRecommendation(Recommendation recommendation) {
        location = new JsonAdaptedLocation(recommendation.getLocation());
        timePeriod = new JsonAdaptedTimePeriod(recommendation.getTimePeriod());
        isSaved = recommendation.getIsSaved();
    }

    public Recommendation toModelType() throws IllegalValueException {
        if (location == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName()));
        }
        final Location modelLocation = location.toModelType();

        if (timePeriod == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, TimePeriod.class.getSimpleName()));
        }
        final TimePeriod modelTimePeriod = timePeriod.toModelType();

        return new Recommendation(modelLocation, modelTimePeriod, isSaved);
    }
}
