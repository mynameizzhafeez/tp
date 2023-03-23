package seedu.address.model.location.util;

import seedu.address.model.location.Locatable;
import seedu.address.model.location.Location;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Handles the computations between locations.
 * Different from {@code LocationUtil} which handles parses and processes the Locations instead.
 */
public class LocationUtil {

    private static final double DISTANCE_CONSTANT = 111.33;

    /**
     * Calculates the distance between locations.
     */
    public static double getDistance(Locatable firstLocation, Locatable secondLocation) {
        return DISTANCE_CONSTANT * Math.pow(
                Math.pow(firstLocation.getLatitude() - secondLocation.getLatitude(), 2)
                        + Math.pow(firstLocation.getLongitude() - secondLocation.getLongitude(), 2), 0.5);
    }

    /**
     * Gets the midpoint location based on a list of locations.
     * By default, an invalid calculation returns the coordinates of NUS.
     */
    public static Location getMidpoint(List<? extends Locatable> locations) {
        double midLat = locations.stream()
                .mapToDouble(Locatable::getLatitude)
                .average()
                .orElse(Location.NUS.getLatitude());
        double midLon = locations.stream()
                .mapToDouble(Locatable::getLongitude)
                .average()
                .orElse(Location.NUS.getLongitude());
        return new Location(midLat, midLon);
    }

    public static Location getMidpoint(Locatable... locations) {
        return getMidpoint(Arrays.asList(locations));
    }

    /**
     * Returns the closest point to a particular location.
     * For example, "the closest restaurant to home" would be {@code getClosestPoint(home, restaurants)}.
     */
    public static Optional<Location> getClosestPoint(
            Location location, List<Location> locations) {
        return locations.stream()
                .min(Comparator.comparingDouble((Location location1) -> getDistance(location1, location)));
    }

    /**
     * Returns the closest points to a particular location.
     * For example, "the 5 closest restaurants to home" would be {@code getClosestPoint(home, 5, restaurants)}.
     */
    public static List<Location> getClosestPoints(
            Locatable location, int limit, Collection<Location> locations) {
        return locations.stream()
                .sorted(Comparator.comparingDouble((Locatable other) -> getDistance(other, location)))
                .limit(limit)
                .collect(Collectors.toList());
    }
}
