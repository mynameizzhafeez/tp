package seedu.address.model.time.time;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.time.util.TypicalLesson.FRIDAY_7PM_3HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.FRIDAY_8AM_1HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.MONDAY_10AM_2HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.MONDAY_8AM_2HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.MONDAY_ANOTHER_8AM_2HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.THURSDAY_11AM_2HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.THURSDAY_4PM_2HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.THURSDAY_5PM_4HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.TUESDAY_10AM_1HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.TUESDAY_10AM_2HR_LESSON;
import static seedu.address.model.time.util.TypicalLesson.WEDNESDAY_2PM_2HR_LESSON;
import static seedu.address.model.time.util.TypicalTimetable.TIMETABLE_A;
import static seedu.address.model.time.util.TypicalTimetable.TIMETABLE_B;
import static seedu.address.model.time.util.TypicalTimetable.TIMETABLE_C;
import static seedu.address.model.time.util.TypicalTimetable.TIMETABLE_D;
import static seedu.address.model.time.util.TypicalTimetable.TIMETABLE_E;

import java.util.ArrayList;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import seedu.address.model.time.HourBlock;
import seedu.address.model.recommender.Schedule;
import seedu.address.model.recommender.exceptions.CommitmentClashException;

class ScheduleTest {

    public void testSlotsTrue(int start, int limit, ArrayList<HourBlock> timeSlots) {
        IntStream.iterate(start, x -> x + 1)
                .limit(limit)
                .forEach(x -> assertTrue(timeSlots.get(x).isFree()));
    }

    public void testSlotsFalse(int start, int limit, ArrayList<HourBlock> timeSlots) {
        IntStream.iterate(start, x -> x + 1)
                .limit(limit)
                .forEach(x -> assertFalse(timeSlots.get(x).isFree()));
    }

    @Test
    public void test_typicalTimetables_allValid() {
        assertDoesNotThrow(() -> TIMETABLE_A);
        assertDoesNotThrow(() -> TIMETABLE_B);
        assertDoesNotThrow(() -> TIMETABLE_C);
        assertDoesNotThrow(() -> TIMETABLE_D);
        assertDoesNotThrow(() -> TIMETABLE_E);
    }

    @Test
    public void initialise_oneLesson_success() {
        Schedule timetable = new Schedule();
        timetable.addLesson(MONDAY_8AM_2HR_LESSON);
        // make sure Monday got the lesson
        ArrayList<HourBlock> mondayTime = timetable.getMondayClasses();
        assertFalse(mondayTime.get(0).isFree());
        assertFalse(mondayTime.get(1).isFree());
        for (int i = 2; i < mondayTime.size(); i++) {
            assertTrue(mondayTime.get(i).isFree());
        }
    }

    @Test
    public void initialise_twoLessons_success() {
        Schedule timetable = new Schedule();
        timetable.addLesson(MONDAY_8AM_2HR_LESSON);
        timetable.addLesson(FRIDAY_8AM_1HR_LESSON);
        ArrayList<HourBlock> mondayTime = timetable.getMondayClasses();
        ArrayList<HourBlock> fridayTime = timetable.getFridayClasses();
        assertFalse(mondayTime.get(0).isFree());
        assertFalse(mondayTime.get(1).isFree());
        assertFalse(fridayTime.get(0).isFree());
        for (int i = 2; i < mondayTime.size(); i++) {
            assertTrue(mondayTime.get(i).isFree());
        }
        for (int j = 1; j < fridayTime.size(); j++) {
            assertTrue(fridayTime.get(j).isFree());
        }
    }

    @Test
    public void initialise_clashLessons_throwLessonClashException() {
        Schedule timetable = new Schedule();
        timetable.addLesson(MONDAY_8AM_2HR_LESSON);
        assertThrows(CommitmentClashException.class, () -> timetable.addLesson(MONDAY_ANOTHER_8AM_2HR_LESSON));
        Schedule anotherTimetable = new Schedule();
        anotherTimetable.addLesson(MONDAY_ANOTHER_8AM_2HR_LESSON);
        assertThrows(CommitmentClashException.class, () -> timetable.addLesson(MONDAY_8AM_2HR_LESSON));
    }

    @Test
    public void initialise_consecutiveLessonsSameDay_success() {
        Schedule timetable = new Schedule();
        timetable.addLesson(MONDAY_8AM_2HR_LESSON);
        timetable.addLesson(MONDAY_10AM_2HR_LESSON);
        testSlotsFalse(0, 4, timetable.getMondayClasses());
        testSlotsTrue(4, 10, timetable.getMondayClasses());
    }

    @Test
    public void initialise_lessonsDifferentDaysSameTiming_success() {
        Schedule timetable = new Schedule();
        assertEquals(MONDAY_10AM_2HR_LESSON.getStartTime(), TUESDAY_10AM_2HR_LESSON.getStartTime());
        assertEquals(MONDAY_10AM_2HR_LESSON.getEndTime(), TUESDAY_10AM_2HR_LESSON.getEndTime());
        assertNotEquals(MONDAY_10AM_2HR_LESSON.getDay(), TUESDAY_10AM_2HR_LESSON.getDay());
        assertDoesNotThrow(() -> timetable.addLesson(MONDAY_10AM_2HR_LESSON));
        assertDoesNotThrow(() -> timetable.addLesson(TUESDAY_10AM_2HR_LESSON));
    }

    @Test
    public void initialise_lessonsSameDaySubsetClash_throwsLessonClashException() {
        Schedule timetable = new Schedule();
        assertDoesNotThrow(() -> timetable.addLesson(TUESDAY_10AM_2HR_LESSON));
        assertThrows(CommitmentClashException.class, () -> timetable.addLesson(TUESDAY_10AM_1HR_LESSON));
        ArrayList<HourBlock> tuesdayClasses = timetable.getTuesdayClasses();
        testSlotsFalse(2, 2, tuesdayClasses);
        testSlotsTrue(4, 10, tuesdayClasses);
        testSlotsTrue(0, 2, tuesdayClasses);
    }

    @Test
    public void initialise_lessonsSameDaySupersetClash_throwsLessonClashException() {
        Schedule timetable = new Schedule();
        assertDoesNotThrow(() -> timetable.addLesson(TUESDAY_10AM_1HR_LESSON));
        assertThrows(CommitmentClashException.class, () -> timetable.addLesson(TUESDAY_10AM_2HR_LESSON));
        testSlotsFalse(2, 1, timetable.getTuesdayClasses());
        testSlotsTrue(3, 11, timetable.getTuesdayClasses());
        testSlotsTrue(0, 2, timetable.getTuesdayClasses());
    }

    @Test
    public void initialise_lessonsSameDayIntersectionExistDifferentEndpoints_throwsLessonClashException() {
        Schedule timetable = new Schedule();
        timetable.addLesson(THURSDAY_4PM_2HR_LESSON);
        ArrayList<HourBlock> thursdayClasses = timetable.getThursdayClasses();
        assertThrows(CommitmentClashException.class, () -> timetable.addLesson(THURSDAY_5PM_4HR_LESSON));
        testSlotsTrue(0, 6, thursdayClasses);
        testSlotsFalse(8, 2, thursdayClasses);
        testSlotsTrue(10, 4, thursdayClasses);
    }

    @Test
    public void equalityCheck_sameTimetable_equals() {
        Schedule timetable1 = new Schedule();
        timetable1.addLesson(WEDNESDAY_2PM_2HR_LESSON);
        timetable1.addLesson(FRIDAY_8AM_1HR_LESSON);
        timetable1.addLesson(THURSDAY_11AM_2HR_LESSON);
        timetable1.addLesson(THURSDAY_4PM_2HR_LESSON);

        Schedule timetable2 = new Schedule();
        timetable2.addLesson(THURSDAY_4PM_2HR_LESSON);
        timetable2.addLesson(WEDNESDAY_2PM_2HR_LESSON);
        timetable2.addLesson(THURSDAY_11AM_2HR_LESSON);
        timetable2.addLesson(FRIDAY_8AM_1HR_LESSON);

        assertEquals(timetable1.toString(), timetable2.toString());
        assertEquals(timetable1.getSchedule(), timetable2.getSchedule());
        assertEquals(timetable1.getWednesdayClasses(), timetable2.getWednesdayClasses());
        assertEquals(timetable1.getThursdayClasses(), timetable2.getThursdayClasses());
        assertEquals(timetable1.getFridayClasses(), timetable2.getFridayClasses());
    }

    public boolean arrayListAllEqualsTest(ArrayList<HourBlock> timeSlots1, ArrayList<HourBlock> timeSlots2) {
        for (int i = 0; i < timeSlots1.size(); i++) {
            if (!timeSlots1.get(i).equals(timeSlots2.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void equalityCheck_slightlyDifferentTimetable_notEquals() {
        Schedule timetable1 = new Schedule();
        timetable1.addLesson(WEDNESDAY_2PM_2HR_LESSON);
        timetable1.addLesson(FRIDAY_7PM_3HR_LESSON);
        timetable1.addLesson(THURSDAY_11AM_2HR_LESSON);
        timetable1.addLesson(THURSDAY_4PM_2HR_LESSON);

        Schedule timetable2 = new Schedule();
        timetable2.addLesson(THURSDAY_4PM_2HR_LESSON);
        timetable2.addLesson(WEDNESDAY_2PM_2HR_LESSON);
        timetable2.addLesson(THURSDAY_11AM_2HR_LESSON);
        timetable2.addLesson(FRIDAY_8AM_1HR_LESSON);

        assertNotEquals(timetable1.getSchedule(), timetable2.getSchedule());
        assertTrue(arrayListAllEqualsTest(timetable1.getWednesdayClasses(), timetable2.getWednesdayClasses()));
        assertTrue(arrayListAllEqualsTest(timetable1.getThursdayClasses(), timetable2.getThursdayClasses()));
        assertFalse(arrayListAllEqualsTest(timetable1.getFridayClasses(), timetable2.getFridayClasses()));

    }

}
