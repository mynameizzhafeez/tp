package seedu.address.model.time.time;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.time.util.TypicalTime.EIGHT_AM;
import static seedu.address.model.time.util.TypicalTime.EIGHT_PM;
import static seedu.address.model.time.util.TypicalTime.ELEVEN_AM;
import static seedu.address.model.time.util.TypicalTime.FIVE_PM;
import static seedu.address.model.time.util.TypicalTime.FOUR_PM;
import static seedu.address.model.time.util.TypicalTime.NINE_AM;
import static seedu.address.model.time.util.TypicalTime.NINE_PM;
import static seedu.address.model.time.util.TypicalTime.ONE_PM;
import static seedu.address.model.time.util.TypicalTime.SEVEN_PM;
import static seedu.address.model.time.util.TypicalTime.SIX_PM;
import static seedu.address.model.time.util.TypicalTime.TEN_AM;
import static seedu.address.model.time.util.TypicalTime.TEN_PM;
import static seedu.address.model.time.util.TypicalTime.THREE_PM;
import static seedu.address.model.time.util.TypicalTime.TWELVE_PM;
import static seedu.address.model.time.util.TypicalTime.TWO_PM;

import org.joda.time.Hours;
import org.junit.jupiter.api.Test;

import seedu.address.model.time.Day;
import seedu.address.model.time.TimePeriod;
import seedu.address.model.time.HourBlock;
import seedu.address.model.time.exceptions.InvalidTimeException;

class TimePeriodTest {

    @Test
    public void initialise_twoHours_success() {
        TimePeriod timePeriod = new TimePeriod(EIGHT_AM, TEN_AM, Day.MONDAY);
        assertEquals(Hours.TWO, timePeriod.getHoursBetween());
    }

    @Test
    public void initialise_eightHours_success() {
        TimePeriod timePeriod = new TimePeriod(TEN_AM, SIX_PM, Day.TUESDAY);
        assertEquals(Hours.EIGHT, timePeriod.getHoursBetween());
    }

    @Test
    public void initialise_tenHours_success() {
        TimePeriod timePeriod = new TimePeriod(ELEVEN_AM, NINE_PM, Day.TUESDAY);
        assertEquals(Hours.hours(10), timePeriod.getHoursBetween());
    }

    @Test
    public void initialise_twelveHours_success() {
        TimePeriod timePeriod = new TimePeriod(TEN_AM, TEN_PM, Day.TUESDAY);
        assertEquals(Hours.hours(12), timePeriod.getHoursBetween());
    }

    @Test
    public void initialise_endTimeBeforeStartTime_throwsException() {
        assertThrows(InvalidTimeException.class, () ->new TimePeriod(ONE_PM, ELEVEN_AM, Day.WEDNESDAY));
    }

    @Test
    public void mergeTimePeriod_withAnotherConsecutiveTimePeriod_success() {
        TimePeriod timePeriod1 = new TimePeriod(EIGHT_AM, TEN_AM, Day.MONDAY);
        TimePeriod timePeriod2 = new TimePeriod(TEN_AM, SIX_PM, Day.MONDAY);
        TimePeriod mergedTimePeriod = timePeriod1.merge(timePeriod2);
        assertEquals(mergedTimePeriod, new TimePeriod(EIGHT_AM, SIX_PM, Day.MONDAY));
        assertEquals(Hours.hours(10), mergedTimePeriod.getHoursBetween());
    }

    @Test
    public void mergeTimePeriod_withNonConsecutiveTimePeriodSameDay_throwsException() {
        TimePeriod timePeriod1 = new TimePeriod(EIGHT_AM, TEN_AM, Day.MONDAY);
        TimePeriod timePeriod2 = new TimePeriod(ELEVEN_AM, SIX_PM, Day.MONDAY);
        assertTrue(timePeriod1.isSameDay(timePeriod2));
        assertFalse(timePeriod1.isConsecutiveWith(timePeriod2));
        assertFalse(timePeriod2.isConsecutiveWith(timePeriod1));
    }

    @Test
    public void mergeTimePeriod_withNonConsecutiveTimePeriodDifferentDay_throwsException() {
        TimePeriod timePeriod1 = new TimePeriod(EIGHT_AM, TEN_AM, Day.MONDAY);
        TimePeriod timePeriod2 = new TimePeriod(ELEVEN_AM, SIX_PM, Day.FRIDAY);
        assertFalse(timePeriod1.isConsecutiveWith(timePeriod2));
        assertFalse(timePeriod2.isConsecutiveWith(timePeriod1));
        assertFalse(timePeriod1.isSameDay(timePeriod2));
        assertThrows(InvalidTimeException.class, () -> timePeriod1.merge(timePeriod2));
        assertThrows(InvalidTimeException.class, () -> timePeriod2.merge(timePeriod1));
    }

    @Test
    public void mergeTimePeriod_withConsecutiveTimeBlockDifferentDay_throwsException() {
        TimeBlock timePeriod1 = new TimeBlock(EIGHT_AM, TEN_AM, Day.MONDAY);
        TimeBlock timePeriod2 = new TimeBlock(TEN_AM, SIX_PM, Day.FRIDAY);
        assertFalse(timePeriod1.isConsecutiveWith(timePeriod2));
        assertFalse(timePeriod2.isConsecutiveWith(timePeriod1));
        assertFalse(timePeriod1.isSameDay(timePeriod2));
        assertThrows(InvalidTimeException.class, () -> timePeriod1.merge(timePeriod2));
        assertThrows(InvalidTimeException.class, () -> timePeriod2.merge(timePeriod1));

    }

    @Test
    public void testConsecutiveTimeBlocks_commutativityTest_success() {
        TimeBlock timePeriod1 = new TimeBlock(EIGHT_AM, TEN_AM, Day.THURSDAY);
        TimeBlock timePeriod2 = new TimeBlock(TEN_AM, SIX_PM, Day.THURSDAY);
        assertDoesNotThrow(() -> timePeriod1.merge(timePeriod2));
        assertDoesNotThrow(() -> timePeriod2.merge(timePeriod1));
        TimeBlock timePeriodnew1 = timePeriod1.merge(timePeriod2);
        TimeBlock timePeriodnew2 = timePeriod2.merge(timePeriod1);
        assertEquals(timePeriodnew1, timePeriodnew2);
        assertEquals(Hours.hours(10), timePeriodnew1.getHoursBetween());
        assertEquals(Hours.hours(10), timePeriodnew2.getHoursBetween());
        assertEquals(timePeriodnew1.getStartTime(), EIGHT_AM);
        assertEquals(timePeriodnew2.getStartTime(), EIGHT_AM);
        assertEquals(timePeriodnew1.getEndTime(), SIX_PM);
        assertEquals(timePeriodnew2.getEndTime(), SIX_PM);
    }

    // merge with timeslots
    @Test
    public void mergeTimeSlot_withAnotherConsecutiveTimeBlock_success() {
        HourBlock timeSlot = new HourBlock(EIGHT_AM, Day.MONDAY);
        TimeBlock timePeriod = new TimeBlock(NINE_AM, SIX_PM, Day.MONDAY);
        TimeBlock mergedTimeBlock = timeSlot.merge(timePeriod);
        assertEquals(mergedTimeBlock, new TimeBlock(EIGHT_AM, SIX_PM, Day.MONDAY));
        assertEquals(Hours.hours(10), mergedTimeBlock.getHoursBetween());
    }

    @Test
    public void mergeTimeSlot_withNonConsecutiveTimeBlockSameDay_throwsException() {
        HourBlock timeSlot = new HourBlock(EIGHT_AM, Day.MONDAY);
        TimeBlock timePeriod = new TimeBlock(ELEVEN_AM, SIX_PM, Day.MONDAY);
        assertTrue(timeSlot.isSameDay(timePeriod));
        assertFalse(timeSlot.isConsecutiveWith(timePeriod));
        assertFalse(timePeriod.isConsecutiveWith(timeSlot));
    }

    @Test
    public void mergeTimeSlot_withNonConsecutiveTimeBlockDifferentDay_throwsException() {
        HourBlock timeSlot = new HourBlock(FOUR_PM, Day.MONDAY);
        TimeBlock timePeriod = new TimeBlock(ELEVEN_AM, SIX_PM, Day.FRIDAY);
        assertFalse(timeSlot.isConsecutiveWith(timePeriod));
        assertFalse(timePeriod.isConsecutiveWith(timeSlot));
        assertFalse(timeSlot.isSameDay(timePeriod));
        assertThrows(InvalidTimeException.class, () -> timeSlot.merge(timePeriod));
        assertThrows(InvalidTimeException.class, () -> timePeriod.merge(timeSlot));
    }

    @Test
    public void mergeTimeSlot_withConsecutiveTimeBlockDifferentDay_throwsException() {
        HourBlock timeSlot = new HourBlock(EIGHT_AM, Day.MONDAY);
        TimeBlock timePeriod = new TimeBlock(TWO_PM, SIX_PM, Day.FRIDAY);
        assertFalse(timeSlot.isConsecutiveWith(timePeriod));
        assertFalse(timePeriod.isConsecutiveWith(timeSlot));
        assertFalse(timeSlot.isSameDay(timePeriod));
        assertThrows(InvalidTimeException.class, () -> timeSlot.merge(timePeriod));
        assertThrows(InvalidTimeException.class, () -> timePeriod.merge(timeSlot));

    }

    @Test
    public void testConsecutiveTimeSlots_commutativityTest_success() {
        HourBlock timeSlot = new HourBlock(TWELVE_PM, Day.THURSDAY);
        TimeBlock timePeriod = new TimeBlock(ONE_PM, SIX_PM, Day.THURSDAY);
        assertDoesNotThrow(() -> timeSlot.merge(timePeriod));
        assertDoesNotThrow(() -> timePeriod.merge(timeSlot));
        TimeBlock timePeriodnew1 = timeSlot.merge(timePeriod);
        TimeBlock timePeriodnew2 = timePeriod.merge(timeSlot);
        assertEquals(timePeriodnew1, timePeriodnew2);
        assertEquals(Hours.SIX, timePeriodnew1.getHoursBetween());
        assertEquals(Hours.SIX, timePeriodnew2.getHoursBetween());
        assertEquals(timePeriodnew1.getStartTime(), TWELVE_PM);
        assertEquals(timePeriodnew2.getStartTime(), TWELVE_PM);
        assertEquals(timePeriodnew1.getEndTime(), SIX_PM);
        assertEquals(timePeriodnew2.getEndTime(), SIX_PM);
    }

    @Test
    public void chain_consecutive3TimeBlocks_success() {
        TimeBlock timePeriod1 = new TimeBlock(ONE_PM, THREE_PM, Day.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(THREE_PM, EIGHT_PM, Day.MONDAY);
        TimeBlock timeBlock3 = new TimeBlock(EIGHT_PM, TEN_PM, Day.MONDAY);
        TimeBlock mergedBlock1 = timeBlock1.merge(timeBlock2).merge(timeBlock3);
        TimeBlock mergedBlock2 = timeBlock2.merge(timeBlock1).merge(timeBlock3);
        TimeBlock mergedBlock3 = timeBlock3.merge(timeBlock2).merge(timeBlock1);
        assertEquals(mergedBlock1, mergedBlock2);
        assertEquals(mergedBlock2, mergedBlock3);
        assertEquals(mergedBlock1, mergedBlock3);
        assertEquals(Hours.hours(9), mergedBlock1.getHoursBetween());
        assertEquals(Hours.hours(9), mergedBlock2.getHoursBetween());
        assertEquals(Hours.hours(9), mergedBlock3.getHoursBetween());
    }

    @Test
    public void chainWrongOrder_consecutive3TimeBlocks_success() {
        TimeBlock timeBlock1 = new TimeBlock(ONE_PM, THREE_PM, Day.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(THREE_PM, EIGHT_PM, Day.MONDAY);
        TimeBlock timeBlock3 = new TimeBlock(EIGHT_PM, TEN_PM, Day.MONDAY);
        assertThrows(InvalidTimeException.class, () -> timeBlock1.merge(timeBlock3).merge(timeBlock2));
        assertThrows(InvalidTimeException.class, () ->timeBlock3.merge(timeBlock1).merge(timeBlock2));
    }

    @Test
    public void chain_nonConsecutive3TimeBlocksSameDay_throwsWrongTimingException() {
        TimeBlock timeBlock1 = new TimeBlock(NINE_AM, THREE_PM, Day.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(EIGHT_AM, TWELVE_PM, Day.MONDAY);
        TimeBlock timeBlock3 = new TimeBlock(EIGHT_PM, TEN_PM, Day.MONDAY);
        assertThrows(InvalidTimeException.class, () -> timeBlock1.merge(timeBlock3).merge(timeBlock2));
        assertThrows(InvalidTimeException.class, () ->timeBlock3.merge(timeBlock1).merge(timeBlock2));
        assertThrows(InvalidTimeException.class, () -> timeBlock2.merge(timeBlock1).merge(timeBlock2));
        assertThrows(InvalidTimeException.class, () ->timeBlock2.merge(timeBlock1).merge(timeBlock3));

    }

    @Test
    public void chain_nonConsecutive3TimeBlocksDifferentDay_throwsWrongTimingException() {
        TimeBlock timeBlock1 = new TimeBlock(NINE_AM, THREE_PM, Day.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(EIGHT_AM, TWELVE_PM, Day.TUESDAY);
        TimeBlock timeBlock3 = new TimeBlock(EIGHT_PM, TEN_PM, Day.WEDNESDAY);
        assertThrows(InvalidTimeException.class, () -> timeBlock1.merge(timeBlock3).merge(timeBlock2));
        assertThrows(InvalidTimeException.class, () ->timeBlock3.merge(timeBlock1).merge(timeBlock2));
        assertThrows(InvalidTimeException.class, () -> timeBlock2.merge(timeBlock1).merge(timeBlock2));
        assertThrows(InvalidTimeException.class, () ->timeBlock2.merge(timeBlock1).merge(timeBlock3));

    }

    @Test
    public void chain_consecutive4TimeBlocks_success() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TWELVE_PM, Day.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(TWELVE_PM, TWO_PM, Day.MONDAY);
        TimeBlock timeBlock3 = new TimeBlock(TWO_PM, FIVE_PM, Day.MONDAY);
        TimeBlock timeBlock4 = new TimeBlock(FIVE_PM, TEN_PM, Day.MONDAY);
        TimeBlock mergedBlock1 = timeBlock1.merge(timeBlock2).merge(timeBlock3).merge(timeBlock4);
        TimeBlock mergedBlock2 = timeBlock2.merge(timeBlock1).merge(timeBlock3).merge(timeBlock4);
        TimeBlock mergedBlock3 = timeBlock3.merge(timeBlock2).merge(timeBlock1).merge(timeBlock4);
        TimeBlock mergedBlock4 = timeBlock4.merge(timeBlock3).merge(timeBlock2).merge(timeBlock1);
        TimeBlock mergedBlock5 = timeBlock2.merge(timeBlock3).merge(timeBlock4).merge(timeBlock1);
        TimeBlock mergedBlock6 = timeBlock3.merge(timeBlock2).merge(timeBlock4).merge(timeBlock1);
        assertEquals(mergedBlock1, mergedBlock2);
        assertEquals(mergedBlock2, mergedBlock3);
        assertEquals(mergedBlock1, mergedBlock3);
        assertEquals(mergedBlock4, mergedBlock2);
        assertEquals(mergedBlock5, mergedBlock1);
        assertEquals(mergedBlock6, mergedBlock3);
        assertEquals(Hours.hours(14), mergedBlock1.getHoursBetween());
        assertEquals(Hours.hours(14), mergedBlock2.getHoursBetween());
        assertEquals(Hours.hours(14), mergedBlock3.getHoursBetween());
        assertEquals(Hours.hours(14), mergedBlock4.getHoursBetween());
        assertEquals(Hours.hours(14), mergedBlock5.getHoursBetween());
        assertEquals(Hours.hours(14), mergedBlock6.getHoursBetween());

    }

    @Test
    public void chain_nonConsecutive4TimeBlocks_throwsWrongTimingException() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TWELVE_PM, Day.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(THREE_PM, SEVEN_PM, Day.MONDAY);
        TimeBlock timeBlock3 = new TimeBlock(TWO_PM, SIX_PM, Day.TUESDAY);
        TimeBlock timeBlock4 = new TimeBlock(SIX_PM, EIGHT_PM, Day.WEDNESDAY);
        //wrong order
        assertThrows(InvalidTimeException.class, () -> timeBlock1.merge(timeBlock3).merge(timeBlock2).merge(timeBlock4));
        assertThrows(InvalidTimeException.class, () ->timeBlock3.merge(timeBlock1).merge(timeBlock4).merge(timeBlock2));
        assertThrows(InvalidTimeException.class, () ->timeBlock4.merge(timeBlock1).merge(timeBlock3).merge(timeBlock3));
        //correct order
        assertThrows(InvalidTimeException.class, () -> timeBlock2.merge(timeBlock3).merge(timeBlock1).merge(timeBlock4));
        assertThrows(InvalidTimeException.class, () -> timeBlock2.merge(timeBlock1).merge(timeBlock3).merge(timeBlock4));
        assertThrows(InvalidTimeException.class, () -> timeBlock4.merge(timeBlock3).merge(timeBlock2).merge(timeBlock1));
        assertThrows(InvalidTimeException.class, () -> timeBlock3.merge(timeBlock4).merge(timeBlock2).merge(timeBlock1));

    }

    @Test
    public void chain_consecutiveAlternateTimeBlocksAndTimeSlots_success() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TWELVE_PM, Day.MONDAY);
        HourBlock timeSlot1 = new HourBlock(TWELVE_PM, Day.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(ONE_PM, FIVE_PM, Day.MONDAY);
        HourBlock timeSlot2 = new HourBlock(FIVE_PM, Day.MONDAY);
        TimeBlock mergedBlock1 = timeBlock1.merge(timeSlot1).merge(timeBlock2).merge(timeSlot2);
        TimeBlock mergedBlock2 = timeSlot1.merge(timeBlock1).merge(timeBlock2).merge(timeSlot2);
        TimeBlock mergedBlock3 = timeBlock2.merge(timeSlot2).merge(timeSlot1).merge(timeBlock1);
        TimeBlock mergedBlock4 = timeSlot2.merge(timeBlock2).merge(timeSlot1).merge(timeBlock1);
        TimeBlock mergedBlock5 = timeSlot1.merge(timeBlock2).merge(timeSlot2).merge(timeBlock1);
        TimeBlock mergedBlock6 = timeBlock2.merge(timeSlot1).merge(timeSlot2).merge(timeBlock1);
        assertEquals(mergedBlock1, mergedBlock2);
        assertEquals(mergedBlock2, mergedBlock3);
        assertEquals(mergedBlock1, mergedBlock3);
        assertEquals(mergedBlock4, mergedBlock2);
        assertEquals(mergedBlock5, mergedBlock1);
        assertEquals(mergedBlock6, mergedBlock3);
        assertEquals(Hours.hours(10), mergedBlock1.getHoursBetween());
        assertEquals(Hours.hours(10), mergedBlock2.getHoursBetween());
        assertEquals(Hours.hours(10), mergedBlock3.getHoursBetween());
        assertEquals(Hours.hours(10), mergedBlock4.getHoursBetween());
        assertEquals(Hours.hours(10), mergedBlock5.getHoursBetween());
        assertEquals(Hours.hours(10), mergedBlock6.getHoursBetween());
    }

    @Test
    public void chain_nonConsecutiveAlternateTimeBlocksAndTimeSlots_throwsWrongTimingException() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TWELVE_PM, Day.MONDAY);
        HourBlock timeSlot1 = new HourBlock(TWELVE_PM, Day.FRIDAY);
        TimeBlock timeBlock2 = new TimeBlock(ONE_PM, FIVE_PM, Day.WEDNESDAY);
        HourBlock timeSlot2 = new HourBlock(FIVE_PM, Day.TUESDAY);
        // wrong order
        assertThrows(InvalidTimeException.class, () -> timeBlock1.merge(timeSlot1).merge(timeBlock2).merge(timeSlot2));
        assertThrows(InvalidTimeException.class, () -> timeSlot1.merge(timeBlock1).merge(timeBlock2).merge(timeSlot2));
        assertThrows(InvalidTimeException.class, () -> timeBlock2.merge(timeSlot2).merge(timeBlock1).merge(timeSlot2));
        assertThrows(InvalidTimeException.class, () -> timeSlot1.merge(timeBlock2).merge(timeSlot2).merge(timeBlock1));
        // correct order
        assertThrows(InvalidTimeException.class, () -> timeSlot2.merge(timeSlot1).merge(timeBlock1).merge(timeBlock2));
        assertThrows(InvalidTimeException.class, () -> timeSlot1.merge(timeSlot2).merge(timeBlock1).merge(timeBlock2));
        assertThrows(InvalidTimeException.class, () -> timeBlock1.merge(timeBlock2).merge(timeSlot2).merge(timeSlot1));
        assertThrows(InvalidTimeException.class, () -> timeBlock2.merge(timeSlot2).merge(timeBlock1).merge(timeSlot1));
    }

    @Test
    public void checkSequence_consecutive2TimeBlocksSameDay_success() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TWELVE_PM, Day.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(TWELVE_PM, FIVE_PM, Day.MONDAY);
        assertTrue(timeBlock1.isConsecutiveWith(timeBlock2));
        assertTrue(timeBlock2.isConsecutiveWith(timeBlock1));
        assertTrue(timeBlock1.isStraightBefore(timeBlock2));
        assertTrue(timeBlock2.isStraightAfter(timeBlock1));
    }

    @Test
    public void checkSequence_consecutive1TimeBlock1TimeSlotSameDay_success() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TWELVE_PM, Day.MONDAY);
        HourBlock timeSlot1 = new HourBlock(TWELVE_PM, Day.MONDAY);
        assertTrue(timeBlock1.isConsecutiveWith(timeSlot1));
        assertTrue(timeSlot1.isConsecutiveWith(timeBlock1));
        assertTrue(timeBlock1.isStraightBefore(timeSlot1));
        assertTrue(timeSlot1.isStraightAfter(timeBlock1));
    }

    @Test
    public void checkSequence_consecutive2TimeBlocksDifferentDay_failure() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TWELVE_PM, Day.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(TWELVE_PM, FIVE_PM, Day.WEDNESDAY);
        assertFalse(timeBlock1.isConsecutiveWith(timeBlock2));
        assertFalse(timeBlock2.isConsecutiveWith(timeBlock1));
        assertFalse(timeBlock1.isStraightBefore(timeBlock2));
        assertFalse(timeBlock2.isStraightAfter(timeBlock1));
    }

    @Test
    public void checkSequence_consecutive1TimeBlock1TimeSlotDifferentDay_failure() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TWELVE_PM, Day.MONDAY);
        HourBlock timeSlot1 = new HourBlock(TWELVE_PM, Day.FRIDAY);
        assertFalse(timeBlock1.isConsecutiveWith(timeSlot1));
        assertFalse(timeSlot1.isConsecutiveWith(timeBlock1));
        assertFalse(timeBlock1.isStraightBefore(timeSlot1));
        assertFalse(timeSlot1.isStraightAfter(timeBlock1));
    }

    @Test
    public void checkSequence_sameBlock_failure() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TWELVE_PM, Day.MONDAY);
        TimeBlock timeBlock2 = new TimeBlock(EIGHT_AM, TWELVE_PM, Day.MONDAY);
        assertFalse(timeBlock1.isConsecutiveWith(timeBlock2));
        assertFalse(timeBlock2.isConsecutiveWith(timeBlock1));
        assertFalse(timeBlock1.isStraightBefore(timeBlock2));
        assertFalse(timeBlock2.isStraightAfter(timeBlock1));
    }

    @Test
    public void equalityCheck_null_notEquals() {
        assertNotEquals(null, new TimeBlock(EIGHT_AM, TWELVE_PM, Day.MONDAY));
    }

    @Test
    public void equalityCheck_notTimeBlock_notEquals() {
        assertNotEquals(new HourBlock(EIGHT_AM, Day.MONDAY), new TimeBlock(EIGHT_AM, TWELVE_PM, Day.MONDAY));
    }

    @Test
    public void equalityCheck_sameObjectReference_equals() {
        TimeBlock timeBlock1 = new TimeBlock(EIGHT_AM, TWELVE_PM, Day.MONDAY);
        assertEquals(timeBlock1, timeBlock1);
        assertEquals(timeBlock1.toString(), timeBlock1.toString());
    }
}
