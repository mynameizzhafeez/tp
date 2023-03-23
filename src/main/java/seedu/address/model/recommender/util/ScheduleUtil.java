package seedu.address.model.recommender.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.joda.time.LocalTime;
import seedu.address.model.commitment.Commitment;
import seedu.address.model.location.Locatable;
import seedu.address.model.location.Location;
import seedu.address.model.location.util.LocationUtil;
import seedu.address.model.recommender.Schedule;
import seedu.address.model.time.Day;
import seedu.address.model.time.Schedulable;
import seedu.address.model.time.TimePeriod;
import seedu.address.model.time.util.TimeUtil;


/**
 * Contains utils functions for Timetable related jobs.
 */
public class ScheduleUtil {
    /**
     * Gets a list of free intervals for which a participant is free for the specific school day.
     */
    public static List<Schedulable> getFreeCommonIntervals(Day day, List<Schedule> schedules) {
        List<List<Schedulable>> daySchedules = obtainAllSchedulesForDay(day, schedules);
        if (daySchedules.isEmpty()) {
            return new ArrayList<>();
        }
        List<Schedulable> commonFreeTimeSlots = new ArrayList<>();
        int numberOfTimeSlots = daySchedules.get(0).size();
        for (int i = 0; i < numberOfTimeSlots; i++) {
            boolean isFreeForAll = true;
            for (List<Schedulable> daySchedule : daySchedules) {
                isFreeForAll &= daySchedule.get(i).isFree();
            }
            if (isFreeForAll) {
                Schedulable sampleSlot = daySchedules.get(daySchedules.size() - 1).get(i);
                commonFreeTimeSlots.add(sampleSlot);
            }
        }
        return commonFreeTimeSlots;
    }

    /**
     * Retrieves all row schedules from all timetables for a specific day
     */
    public static List<List<Schedulable>> obtainAllSchedulesForDay(Day day, List<Schedule> schedules) {
        return schedules.stream()
                .map(timetable -> timetable.getSchedule().get(day))
                .collect(Collectors.toList());
    }

    /**
     * Reduces the number of TimePeriods by merging consecutive time slots.
     */
    public static List<Schedulable> mergeSchedulables(List<? extends Schedulable> schedulables) {
        if (schedulables.isEmpty()) {
            return new ArrayList<>();
        }
        Schedulable s1 = schedulables.get(0);
        List<Schedulable> mergedBlocks = new ArrayList<>();
        for (Schedulable s2 : schedulables) {
            if (areMergeable(s1, s2)) {
                s1 = merge(s1, s2);
            } else {
                mergedBlocks.add(s1);
                s1 = s2;
            }
        }

        mergedBlocks.add(s1);

        return mergedBlocks;
    }

    private static boolean areMergeable(Schedulable s1, Schedulable s2) {
        Optional<Location> firstLocation = Optional.empty();
        Optional<Location> secondLocation = Optional.empty();

        if (s1 instanceof Locatable) {
            Locatable l1 = (Locatable) s1;
            firstLocation = Optional.of(new Location(l1));
        }

        if (s2 instanceof Locatable) {
            Locatable l2 = (Locatable) s2;
            secondLocation = Optional.of(new Location(l2));
        }

        return TimeUtil.areConsecutive(s1, s2)
                && (firstLocation.isEmpty() || secondLocation.isEmpty()
                || firstLocation.equals(secondLocation));
    }

    public static Schedulable merge(Schedulable s1, Schedulable s2) {
        assert areMergeable(s1, s2);

        LocalTime newStartTime = TimeUtil.getNewStartTime(s1, s2);
        LocalTime newEndTime = TimeUtil.getNewEndTime(s1, s2);

        TimePeriod newTimePeriod = new TimePeriod(newStartTime, newEndTime, s1.getDay());

        Optional<Location> firstLocation = Optional.empty();
        Optional<Location> secondLocation = Optional.empty();

        if (s1 instanceof Locatable) {
            Locatable l1 = (Locatable) s1;
            firstLocation = Optional.of(new Location(l1));
        }

        if (s2 instanceof Locatable) {
            Locatable l2 = (Locatable) s2;
            secondLocation = Optional.of(new Location(l2));
        }

        if (firstLocation.isEmpty()) {
            if (secondLocation.isEmpty()) {
                return newTimePeriod;
            }
            return new Commitment(secondLocation.get(), newTimePeriod);
        }

        if (secondLocation.isEmpty()) {
            return new Commitment(firstLocation.get(), newTimePeriod);
        }

        Location newLocation = LocationUtil.getMidpoint(
                firstLocation.get(), secondLocation.get());
        return new Commitment(newLocation, newTimePeriod);
    }
}
