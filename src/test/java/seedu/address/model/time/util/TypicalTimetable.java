package seedu.address.model.time.util;

import static seedu.address.model.time.util.TypicalLesson.FRIDAY_7PM_3HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.FRIDAY_8AM_1HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.FRIDAY_9AM_1HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.FRIDAY_9AM_2HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.MONDAY_10AM_2HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.MONDAY_8AM_2HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.MONDAY_ANOTHER_8AM_2HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.THURSDAY_10AM_3HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.THURSDAY_11AM_2HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.THURSDAY_1PM_3HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.THURSDAY_4PM_2HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.THURSDAY_5PM_4HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.THURSDAY_8AM_2HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.THURSDAY_9PM_1HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.TUESDAY_10AM_2HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.TUESDAY_2PM_2HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.TUESDAY_4PM_1HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.TUESDAY_8AM_2HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.WEDNESDAY_10AM_3HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.WEDNESDAY_4PM_1HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.WEDNESDAY_6PM_1HR_LESSON;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.commitment.Lesson;
import seedu.address.model.recommender.Schedule;

/**
 * Composes of all the Typical Timetables
 */
public class TypicalTimetable {

    public static final Schedule TIMETABLE_A = new TimetableBuilder()
            .withLesson(MONDAY_8AM_2HR_LESSON)
            .withLesson(MONDAY_10AM_2HR_LESSON)
            .withLesson(TUESDAY_10AM_2HR_LESSON)
            .withLesson(TUESDAY_4PM_1HR_LESSON)
            .withLesson(THURSDAY_4PM_2HR_LESSON)
            .withLesson(FRIDAY_8AM_1HR_LESSON)
            .withLesson(FRIDAY_7PM_3HR_LESSON)
            .build();

    public static final Schedule TIMETABLE_B = new TimetableBuilder()
            .withLesson(MONDAY_10AM_2HR_LESSON)
            .withLesson(TUESDAY_4PM_1HR_LESSON)
            .withLesson(WEDNESDAY_6PM_1HR_LESSON)
            .withLesson(THURSDAY_5PM_4HR_LESSON)
            .withLesson(FRIDAY_9AM_2HR_LESSON)
            .build();

    public static final Schedule TIMETABLE_C = new TimetableBuilder()
            .withLesson(MONDAY_ANOTHER_8AM_2HR_LESSON)
            .withLesson(TUESDAY_8AM_2HR_LESSON)
            .withLesson(TUESDAY_2PM_2HR_LESSON)
            .withLesson(WEDNESDAY_10AM_3HR_LESSON)
            .withLesson(THURSDAY_11AM_2HR_LESSON)
            .withLesson(FRIDAY_9AM_1HR_LESSON)
            .build();

    public static final Schedule TIMETABLE_D = new TimetableBuilder()
            .withLesson(TUESDAY_2PM_2HR_LESSON)
            .withLesson(TUESDAY_4PM_1HR_LESSON)
            .withLesson(WEDNESDAY_4PM_1HR_LESSON)
            .withLesson(FRIDAY_9AM_2HR_LESSON)
            .build();

    public static final Schedule TIMETABLE_E = new TimetableBuilder()
            .withLesson(MONDAY_10AM_2HR_LESSON)
            .withLesson(TUESDAY_10AM_2HR_LESSON)
            .withLesson(THURSDAY_11AM_2HR_LESSON)
            .withLesson(FRIDAY_7PM_3HR_LESSON)
            .build();

    public static final Schedule FULL_CONFLICT_TIMETABLE_A = new TimetableBuilder()
            .withLesson(THURSDAY_8AM_2HR_LESSON)
            .withLesson(THURSDAY_1PM_3HR_LESSON)
            .withLesson(THURSDAY_5PM_4HR_LESSON)
            .build();

    public static final Schedule FULL_CONFLICT_TIMETABLE_B = new TimetableBuilder()
            .withLesson(THURSDAY_8AM_2HR_LESSON)
            .withLesson(THURSDAY_10AM_3HR_LESSON)
            .withLesson(THURSDAY_1PM_3HR_LESSON)
            .withLesson(THURSDAY_4PM_2HR_LESSON)
            .withLesson(THURSDAY_9PM_1HR_LESSON)
            .build();

}

/**
 * Creates a new timetable with lessons.
 */
class TimetableBuilder {

    private List<Lesson> lessons;

    public TimetableBuilder() {
        lessons = new ArrayList<>();
    }

    public TimetableBuilder withLesson(Lesson lesson) {
        lessons.add(lesson);
        return this;
    }

    public Schedule build() {
        Schedule timetable = new Schedule();
        lessons.stream().forEach(timetable::addLesson);
        return timetable;
    }

}
