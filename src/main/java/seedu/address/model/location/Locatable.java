package seedu.address.model.location;

/**
 * Interface for objects that have a latitude and longitude.
 */
public interface Locatable {
    /**
     * Returns the latitude of the Locatable object.
     */
    double getLatitude();

    /**
     * Returns the longitude of the Locatable object.
     */
    double getLongitude();
}
