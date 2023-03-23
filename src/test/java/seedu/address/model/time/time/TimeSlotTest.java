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

import org.joda.time.Hours;
import org.joda.time.LocalTime;
import org.junit.jupiter.api.Test;

import seedu.address.model.time.Day;
import seedu.address.model.time.TimePeriod;
import seedu.address.model.time.HourBlock;
import seedu.address.model.recommender.exceptions.CommitmentClashException;
import seedu.address.model.time.exceptions.InvalidTimeException;

class TimeSlotTest {

    private static final LocalTime MORNING_START = new LocalTime(8, 0);
    private static final LocalTime MORNING_SECOND = new LocalTime(9, 0);
    private static final LocalTime AFTERNOON_FIRST = new LocalTime(12, 0);
    private static final LocalTime NIGHT_FIRST = new LocalTime(20, 0);

    @Test
    public void initialise_morningSlotNoLesson_success() {
        HourBlock timeSlot = new HourBlock(MORNING_START, Day.MONDAY);
        assertTrue(timeSlot.isFree());
        assertEquals(1, timeSlot.getHoursBetween().getHours());
        assertEquals(MORNING_START, timeSlot.getStartTime());
        assertEquals(MORNING_START.plusHours(1), timeSlot.getEndTime());
    }

    @Test
    public void initialise_morningSlotWithLesson_success() {
        HourBlock timeSlot = new HourBlock(MORNING_START, Day.MONDAY);
        assertTrue(timeSlot.canFitLesson(MONDAY_8AM_2HR_LESSON));
        assertDoesNotThrow(() -> timeSlot.setLesson(MONDAY_8AM_2HR_LESSON));
        assertFalse(timeSlot.isFree());
    }

    @Test
    public void initialise_dayMismatchTimeCorrect_failure() {
        HourBlock timeSlot = new HourBlock(MORNING_START, Day.MONDAY);
        assertFalse(timeSlot.canFitLesson(FRIDAY_8AM_1HR_LESSON));
        assertThrows(InvalidTimeException.class, () -> timeSlot.setLesson(FRIDAY_8AM_1HR_LESSON));
    }

    @Test
    public void initialise_dayMatchTimeWrong_throwsWrongTimeException() {
        HourBlock timeSlot = new HourBlock(MORNING_START, Day.MONDAY);
        assertFalse(timeSlot.canFitLesson(MONDAY_10AM_2HR_LESSON));
        assertThrows(InvalidTimeException.class, () -> timeSlot.setLesson(MONDAY_10AM_2HR_LESSON));
    }

    @Test
    public void initialise_dayMatchLaterHalfLesson_success() {
        HourBlock timeSlot = new HourBlock(MORNING_SECOND, Day.MONDAY);
        assertTrue(timeSlot.isFree());
        assertTrue(timeSlot.canFitLesson(MONDAY_8AM_2HR_LESSON));
        assertDoesNotThrow(() -> timeSlot.setLesson(MONDAY_8AM_2HR_LESSON));
        assertFalse(timeSlot.isFree());
    }

    @Test
    public void initialise_dayMatchMiddleSlot_success() {
        HourBlock timeSlot = new HourBlock(new LocalTime(20, 0), Day.FRIDAY);
        assertTrue(timeSlot.isFree());
        assertTrue(timeSlot.canFitLesson(FRIDAY_7PM_3HR_LESSON));
        assertDoesNotThrow(() -> timeSlot.setLesson(FRIDAY_7PM_3HR_LESSON));
        assertFalse(timeSlot.isFree());
        assertEquals(FRIDAY_7PM_3HR_LESSON, timeSlot.getLesson().get());
    }

    @Test
    public void addLesson_emptyTimeSlot_success() {
        HourBlock timeSlot = new HourBlock(MORNING_START, Day.MONDAY);
        assertTrue(timeSlot.isFree());
        timeSlot.setLesson(MONDAY_8AM_2HR_LESSON);
        assertFalse(timeSlot.isFree());
    }

    @Test
    public void addLesson_occupiedTimeSlot_throwsLessonClashException() {
        HourBlock timeSlot = new HourBlock(MORNING_START, Day.MONDAY);
        assertTrue(timeSlot.isFree());
        timeSlot.setLesson(MONDAY_8AM_2HR_LESSON);
        assertFalse(timeSlot.isFree());
        // check to see if lesson can even fit inside the slot.
        assertTrue(timeSlot.canFitLesson(MONDAY_ANOTHER_8AM_2HR_LESSON));
        assertThrows(CommitmentClashException.class, () -> timeSlot.setLesson(MONDAY_ANOTHER_8AM_2HR_LESSON));
    }

    @Test
    public void mergeTimeSlot_consecutiveTimeSlotsABeforeB_success() {
        HourBlock timeSlotA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock timeSlotB = new HourBlock(MORNING_SECOND, Day.MONDAY);
        TimePeriod mergedTimeSlots = timeSlotA.merge(timeSlotB);
        assertEquals(new LocalTime(8, 0), mergedTimeSlots.getStartTime());
        assertEquals(new LocalTime(10, 0), mergedTimeSlots.getEndTime());
        assertEquals(Hours.TWO, mergedTimeSlots.getHoursBetween());
    }

    @Test
    public void mergeTimeSlot_consecutiveTimeSlotsCommutativeTest_success() {
        HourBlock timeSlotA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock timeSlotB = new HourBlock(MORNING_SECOND, Day.MONDAY);
        TimePeriod mergedTimeSlots1 = timeSlotB.merge(timeSlotA);
        TimePeriod mergedTimeSlots2 = timeSlotA.merge(timeSlotB);
        assertEquals(new LocalTime(8, 0), mergedTimeSlots1.getStartTime());
        assertEquals(new LocalTime(10, 0), mergedTimeSlots1.getEndTime());
        assertEquals(Hours.TWO, mergedTimeSlots1.getHoursBetween());
        assertEquals(new LocalTime(8, 0), mergedTimeSlots2.getStartTime());
        assertEquals(new LocalTime(10, 0), mergedTimeSlots2.getEndTime());
        assertEquals(Hours.TWO, mergedTimeSlots2.getHoursBetween());

        assertEquals(mergedTimeSlots1, mergedTimeSlots2);

    }

    @Test
    public void mergeTimeSlot_nonConsecutiveTimeSlots_throwsWrongTimingException() {
        HourBlock timeSlotA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock timeSlotB = new HourBlock(AFTERNOON_FIRST, Day.MONDAY);
        assertThrows(InvalidTimeException.class, () -> timeSlotA.merge(timeSlotB));
        assertThrows(InvalidTimeException.class, () -> timeSlotB.merge(timeSlotA));
    }

    @Test
    public void mergeTimeSlot_consecutiveTimeSlotsWrongDay_throwsWrongTimingException() {
        HourBlock timeSlotA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock timeSlotB = new HourBlock(AFTERNOON_FIRST, Day.TUESDAY);
        assertThrows(InvalidTimeException.class, () -> timeSlotA.merge(timeSlotB));
        assertThrows(InvalidTimeException.class, () -> timeSlotB.merge(timeSlotA));
    }

    @Test
    public void mergeTimeSlot_sameTimeSlot_throwsWrongTimingException() {
        HourBlock timeSlotA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock timeSlotB = new HourBlock(MORNING_START, Day.MONDAY);
        assertThrows(InvalidTimeException.class, () -> timeSlotA.merge(timeSlotB));
        assertThrows(InvalidTimeException.class, () -> timeSlotB.merge(timeSlotA));
    }

    @Test
    public void testSequence_morningBeforeNight_success() {
        HourBlock timeSlotA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock timeSlotB = new HourBlock(MORNING_SECOND, Day.MONDAY);
        assertTrue(timeSlotA.isStraightBefore(timeSlotB));
        assertTrue(timeSlotB.isStraightAfter(timeSlotA));
        assertTrue(timeSlotA.isConsecutiveWith(timeSlotB));
        assertTrue(timeSlotB.isConsecutiveWith(timeSlotA));
    }

    @Test
    public void testSequence_nonConsecutiveTimeSlots_failure() {
        HourBlock timeSlotA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock timeSlotB = new HourBlock(AFTERNOON_FIRST, Day.MONDAY);
        assertFalse(timeSlotA.isStraightBefore(timeSlotB));
        assertFalse(timeSlotB.isStraightAfter(timeSlotA));
        assertFalse(timeSlotA.isConsecutiveWith(timeSlotB));
        assertFalse(timeSlotB.isConsecutiveWith(timeSlotA));
    }

    @Test
    public void testSequence_consecutiveTimeDifferentDays_failure() {
        HourBlock timeSlotA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock timeSlotB = new HourBlock(MORNING_SECOND, Day.TUESDAY);
        assertFalse(timeSlotA.isStraightBefore(timeSlotB));
        assertFalse(timeSlotB.isStraightAfter(timeSlotA));
        assertFalse(timeSlotA.isConsecutiveWith(timeSlotB));
        assertFalse(timeSlotB.isConsecutiveWith(timeSlotA));
    }

    @Test
    public void copyTimeSlot_checkEquality_sameTimeSlot() {
        HourBlock timeSlotA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock timeSlotB = new HourBlock(timeSlotA);
        assertEquals(timeSlotB, timeSlotA);
    }

    @Test
    public void equalityCheck_null_notEqual() {
        HourBlock timeSlotA = new HourBlock(MORNING_START, Day.MONDAY);
        assertNotEquals(null, timeSlotA);
    }

    @Test
    public void equalityCheck_notSameTimeSlot_notEqual() {
        HourBlock timeSlotA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock timeSlotB = new HourBlock(AFTERNOON_FIRST, Day.MONDAY);
        assertNotEquals(timeSlotA, timeSlotB);
    }

    @Test
    public void equalityCheck_sameTimeDifferentLesson_notEqual() {
        HourBlock timeSlotA = new HourBlock(MORNING_START, Day.MONDAY);
        HourBlock timeSlotB = new HourBlock(MORNING_START, Day.MONDAY);
        timeSlotA.setLesson(MONDAY_8AM_2HR_LESSON);
        assertNotEquals(timeSlotA, timeSlotB);
    }

    @Test
    public void equalityCheck_sameTimeSlotReference_equal() {
        HourBlock timeSlotA = new HourBlock(MORNING_START, Day.MONDAY);
        assertEquals(timeSlotA, timeSlotA);
    }

    @Test
    public void equalityCheck_differentObjects_notEqual() {
        HourBlock timeSlotA = new HourBlock(MORNING_START, Day.MONDAY);
        TimePeriod timePeriodA = new TimePeriod(MORNING_START, MORNING_SECOND, Day.MONDAY);
        assertNotEquals(timePeriodA, timeSlotA);
    }

}
