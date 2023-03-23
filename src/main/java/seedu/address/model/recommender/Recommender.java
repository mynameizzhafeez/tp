package seedu.address.model.recommender;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.logic.parser.IndexHandler;
import seedu.address.model.Model;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;
import seedu.address.model.time.*;
import seedu.address.model.recommender.util.ScheduleUtil;

/**
 * Represents an automatic scheduler.
 */
public class Recommender {
    private final List<Schedule> schedules;
    private final Model model;
    private final List<Person> participants;

    /**
     * Constructs a scheduler.
     */
    public Recommender(Model model, Collection<ContactIndex> participantIndices) {
        this.model = model;
        this.participants = getParticipantsFromIndices(participantIndices);
        this.schedules = getSchedulesFromParticipants(participants);
    }

    private List<Person> getParticipantsFromIndices(Collection<ContactIndex> participantIndices) {
        requireAllNonNull(model);
        IndexHandler indexHandler = new IndexHandler(model);
        return participantIndices.stream().map(indexHandler::getPersonByIndex)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<Schedule> getSchedulesFromParticipants(Collection<Person> participants) {
        return participants.stream()
                .map(Person::getSchedule)
                .collect(Collectors.toList());
    }

    /**
     * Adds a schedule to collate all schedules.
     */
    public void addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
    }

    /**
     * Recommends the longest common timing that is available.
     */
    public Optional<TimePeriod> giveLongestTimingRecommendation() {
        List<TimePeriod> periods = getAllTimings();
        return periods.stream()
                .max(Comparator.comparing(TimePeriod::getHoursBetween));
    }

    /**
     * Recommends a specified limit number of common available timings.
     * @param limit number of recommendations.
     */
    public List<TimePeriod> giveLongestTimingRecommendations(int limit) {
        List<TimePeriod> periods = getAllTimings();
        return periods.stream()
            .sorted(Comparator.comparing(TimePeriod::getHoursBetween).reversed())
            .limit(limit)
            .collect(Collectors.toList());
    }

    /**
     * Get all Time Periods that everyone is free.
     */
    public List<TimePeriod> getAllTimings() {
        List<TimePeriod> periods = new ArrayList<>();
        for (Day day : Day.values()) {
            List<Schedulable> availableTimeSlots = ScheduleUtil.getFreeCommonIntervals(day, schedules);
            periods.addAll(ScheduleUtil.mergeSchedulables(availableTimeSlots)
                    .stream().map(TimePeriod::new).collect(Collectors.toList()));
        }
        return periods;
    }

    /**
     * Get all Time Periods that everyone is free on that school day.
     */
    public List<TimePeriod> getAllTimings(Day day) {
        List<Schedulable> availableTimeSlots = ScheduleUtil.getFreeCommonIntervals(day, schedules);
        return ScheduleUtil.mergeSchedulables(availableTimeSlots)
                .stream().map(TimePeriod::new).collect(Collectors.toList());
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public Model getModel() {
        return model;
    }

    public List<Person> getParticipants() {
        return participants;
    }
}
