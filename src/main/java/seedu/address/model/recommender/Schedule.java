package seedu.address.model.recommender;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.joda.time.LocalTime;

import seedu.address.model.commitment.Commitment;
import seedu.address.model.time.Day;
import seedu.address.model.time.Schedulable;
import seedu.address.model.time.HourBlock;
import seedu.address.model.recommender.exceptions.CommitmentClashException;

/**
 * Represents a timetable for a person.
 */
public class Schedule {

    private final HashMap<Day, List<Schedulable>> schedule;

    /**
     * Constructs an empty Schedule.
     */
    public Schedule() {
        this.schedule = new HashMap<>();
        Arrays.stream(Day.values()).forEach(this::initialiseEmptyScheduleInDay);
    }

    private void initialiseEmptyScheduleInDay(Day day) {
        List<Schedulable> hourBlocks = IntStream.range(0, 24)
                .mapToObj(hour -> getTimeSlotFromInt(hour, day))
                .collect(Collectors.toList());
        schedule.put(day, hourBlocks);
    }

    private HourBlock getTimeSlotFromInt(int hour, Day day) {
        return new HourBlock(new LocalTime(hour, 0), day);
    }

    /**
     * Adds a commitment to the schedule.
     */
    public void addCommitment(Commitment commitment) throws CommitmentClashException {
        Day day = commitment.getDay();
        List<Schedulable> availableSlots = schedule.get(day);

        if (!canFitCommitment(commitment, day)) {
            throw new CommitmentClashException("There is commitment clash!");
        }

        int startHour = commitment.getStartTime().getHourOfDay();
        int endHour = commitment.getEndTime().getHourOfDay();

        for (int i = startHour; i < endHour; i++) {
            availableSlots.set(i, commitment);
        }
    }

    private boolean canFitCommitment(Commitment commitment, Day day) {
        boolean canFit = true;
        int startHour = commitment.getStartTime().getHourOfDay();
        int endHour = commitment.getEndTime().getHourOfDay();

        for (int i = startHour; i < endHour; i++) {
            canFit &= schedule.get(day).get(i).isFree();
        }
        return canFit;
    }

    @Override
    public String toString() {
        return "Commitments: \n"
                + Arrays.stream(Day.values())
                        .map(this::getDayScheduleAsString)
                        .collect(Collectors.joining("\n"))
                + "\n";
    }

    private String getDayScheduleAsString(Day day) {
        return schedule.get(day).stream()
                .filter(s -> !s.isFree())
                .map(Schedulable::toString)
                .collect(Collectors.joining("\n"));
    }

    public HashMap<Day, List<Schedulable>> getSchedule() {
        return schedule;
    }

    public List<Schedulable> getMondaySchedule() {
        return schedule.get(Day.MONDAY);
    }

    public List<Schedulable> getTuesdaySchedule() {
        return schedule.get(Day.TUESDAY);
    }

    public List<Schedulable> getWednesdaySchedule() {
        return schedule.get(Day.WEDNESDAY);
    }

    public List<Schedulable> getThursdaySchedule() {
        return schedule.get(Day.THURSDAY);
    }

    public List<Schedulable> getFridaySchedule() {
        return schedule.get(Day.FRIDAY);
    }

    public List<Schedulable> getSaturdaySchedule() {
        return schedule.get(Day.SATURDAY);
    }

    public List<Schedulable> getSundaySchedule() {
        return schedule.get(Day.SUNDAY);
    }

}
