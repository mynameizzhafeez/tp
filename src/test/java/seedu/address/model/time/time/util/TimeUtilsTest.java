package seedu.address.model.time.time.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import static seedu.address.model.time.util.TypicalTimetable.FULL_CONFLICT_TIMETABLE_A;
import static seedu.address.model.time.util.TypicalTimetable.FULL_CONFLICT_TIMETABLE_B;
import static seedu.address.model.time.util.TypicalTimetable.TIMETABLE_A;
import static seedu.address.model.time.util.TypicalTimetable.TIMETABLE_B;
import static seedu.address.model.time.util.TypicalTimetable.TIMETABLE_C;
import static seedu.address.model.time.util.TypicalTimetable.TIMETABLE_D;
import static seedu.address.model.time.util.TypicalTimetable.TIMETABLE_E;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.recommender.Schedule;
import seedu.address.model.time.Day;
import seedu.address.model.time.TimePeriod;
import seedu.address.model.time.HourBlock;
import seedu.address.model.recommender.util.ScheduleUtil;


class TimeUtilsTest {

    @Test
    public void getCommonIntervals_twoTimetablesMonday_oneTimePeriod() {
        List<Schedule> timetables = List.of(TIMETABLE_A, TIMETABLE_B);
        List<HourBlock> intervals = ScheduleUtil.getFreeCommonIntervals(Day.MONDAY, timetables);
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(1, mergedIntervals.size());
        // expecting 12pm - 10pm
        assertEquals(new TimePeriod(TWELVE_PM, TEN_PM, Day.MONDAY), mergedIntervals.get(0));
    }

    @Test
    public void getCommonIntervals_twoTimetablesTuesday_success() {
        List<Schedule> timetables = List.of(TIMETABLE_A, TIMETABLE_B);
        List<HourBlock> intervals = ScheduleUtil.getFreeCommonIntervals(Day.TUESDAY, timetables);
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(intervals);
        //expecting 8-10am, 12-4pm, 5pm - 10pm
        assertEquals(3, mergedIntervals.size());
        assertArrayEquals(new TimePeriod[]{new TimePeriod(EIGHT_AM, TEN_AM, Day.TUESDAY),
            new TimePeriod(TWELVE_PM, FOUR_PM, Day.TUESDAY),
            new TimePeriod(FIVE_PM, TEN_PM, Day.TUESDAY)}, mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_twoTimetablesWednesday_success() {
        List<Schedule> timetables = List.of(TIMETABLE_A, TIMETABLE_B);
        List<HourBlock> intervals = ScheduleUtil.getFreeCommonIntervals(Day.WEDNESDAY, timetables);
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(intervals);
        //expecting 8am-6pm, 7-10pm
        assertEquals(2, mergedIntervals.size());
        assertArrayEquals(new TimePeriod[] { new TimePeriod(EIGHT_AM, SIX_PM, Day.WEDNESDAY),
            new TimePeriod(SEVEN_PM, TEN_PM, Day.WEDNESDAY)}, mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_twoTimetablesThursday_success() {
        List<Schedule> timetables = List.of(TIMETABLE_A, TIMETABLE_B);
        List<HourBlock> intervals = ScheduleUtil.getFreeCommonIntervals(Day.THURSDAY, timetables);
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(intervals);
        //expecting 8-4pm, 9-10pm
        assertEquals(2, mergedIntervals.size());
        assertArrayEquals(new TimePeriod[] { new TimePeriod(EIGHT_AM, FOUR_PM, Day.THURSDAY),
            new TimePeriod(NINE_PM, TEN_PM, Day.THURSDAY)}, mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_twoTimetablesFriday_success() {
        List<Schedule> timetables = List.of(TIMETABLE_A, TIMETABLE_B);
        List<HourBlock> intervals = ScheduleUtil.getFreeCommonIntervals(Day.FRIDAY, timetables);
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(intervals);
        //expecting 11am-7pm
        assertEquals(1, mergedIntervals.size());
        assertArrayEquals(new TimePeriod[] { new TimePeriod(ELEVEN_AM, SEVEN_PM, Day.FRIDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_noCommonSlotAvailable_success() {
        List<Schedule> timetables = List.of(FULL_CONFLICT_TIMETABLE_A, FULL_CONFLICT_TIMETABLE_B);
        List<HourBlock> intervals = ScheduleUtil.getFreeCommonIntervals(Day.THURSDAY, timetables);
        assertEquals(0, intervals.size());
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(intervals);
        //expecting 11am-7pm
        assertEquals(0, mergedIntervals.size());
        assertArrayEquals(new TimePeriod[]{}, mergedIntervals.toArray());

    }

    @Test
    public void getCommonIntervals_emptyList_success() {
        List<Schedule> timetables = List.of();
        List<HourBlock> intervals = ScheduleUtil.getFreeCommonIntervals(Day.THURSDAY, timetables);
        assertEquals(0, intervals.size());
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(intervals);
        //expecting 11am-7pm
        assertEquals(0, mergedIntervals.size());
        assertArrayEquals(new TimePeriod[]{}, mergedIntervals.toArray());

    }

    @Test
    public void getCommonIntervals_threeTimetablesMonday_oneTimePeriod() {
        List<Schedule> timetables = List.of(TIMETABLE_A, TIMETABLE_B, TIMETABLE_C);
        List<HourBlock> intervals = ScheduleUtil.getFreeCommonIntervals(Day.MONDAY, timetables);
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(1, mergedIntervals.size());
        // expecting 12pm - 10pm
        assertEquals(new TimePeriod(TWELVE_PM, TEN_PM, Day.MONDAY), mergedIntervals.get(0));
    }

    @Test
    public void getCommonIntervals_threeTimetablesWednesday_oneTimePeriod() {
        List<Schedule> timetables = List.of(TIMETABLE_A, TIMETABLE_B, TIMETABLE_C);
        List<HourBlock> intervals = ScheduleUtil.getFreeCommonIntervals(Day.WEDNESDAY, timetables);
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(3, mergedIntervals.size());
        // expecting 8am-10am, 1pm-6pm, 7-10pm
        assertArrayEquals(new TimePeriod[]{new TimePeriod(EIGHT_AM, TEN_AM, Day.WEDNESDAY),
            new TimePeriod(ONE_PM, SIX_PM, Day.WEDNESDAY),
            new TimePeriod(SEVEN_PM, TEN_PM, Day.WEDNESDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_timetablesVariant2Monday_oneTimePeriod() {
        List<Schedule> timetables = List.of(TIMETABLE_C, TIMETABLE_D, TIMETABLE_E);
        List<HourBlock> intervals = ScheduleUtil.getFreeCommonIntervals(Day.MONDAY, timetables);
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(1, mergedIntervals.size());
        // expecting 12noon-2pm
        assertArrayEquals(new TimePeriod[]{
            new TimePeriod(TWELVE_PM, TEN_PM, Day.MONDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_timetablesVariant2Tuesday_twoTimePeriod() {
        List<Schedule> timetables = List.of(TIMETABLE_D, TIMETABLE_C, TIMETABLE_E);
        List<HourBlock> intervals = ScheduleUtil.getFreeCommonIntervals(Day.TUESDAY, timetables);
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(2, mergedIntervals.size());
        // expecting 12noon-2am, 5-10pm
        assertArrayEquals(new TimePeriod[]{
            new TimePeriod(TWELVE_PM, TWO_PM, Day.TUESDAY),
            new TimePeriod(FIVE_PM, TEN_PM, Day.TUESDAY)},
                mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_timetablesVariant2Wednesday_oneTimePeriod() {
        List<Schedule> timetables = List.of(TIMETABLE_C, TIMETABLE_D, TIMETABLE_E);
        List<HourBlock> intervals = ScheduleUtil.getFreeCommonIntervals(Day.WEDNESDAY, timetables);
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(3, mergedIntervals.size());
        // expecting 8am-10am, 1pm-4pm, 5pm-10pm
        assertArrayEquals(new TimePeriod[]{
            new TimePeriod(EIGHT_AM, TEN_AM, Day.WEDNESDAY),
            new TimePeriod(ONE_PM, FOUR_PM, Day.WEDNESDAY),
            new TimePeriod(FIVE_PM, TEN_PM, Day.WEDNESDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_timetablesVariant2Thursday_twoTimePeriod() {
        List<Schedule> timetables = List.of(TIMETABLE_C, TIMETABLE_D, TIMETABLE_E);
        List<HourBlock> intervals = ScheduleUtil.getFreeCommonIntervals(Day.THURSDAY, timetables);
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(2, mergedIntervals.size());
        // expecting 8am-11am, 1pm-10pm
        assertArrayEquals(new TimePeriod[]{
            new TimePeriod(EIGHT_AM, ELEVEN_AM, Day.THURSDAY),
            new TimePeriod(ONE_PM, TEN_PM, Day.THURSDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_timetablesVariant2Friday_twoTimePeriod() {
        List<Schedule> timetables = List.of(TIMETABLE_C, TIMETABLE_E, TIMETABLE_D);
        List<HourBlock> intervals = ScheduleUtil.getFreeCommonIntervals(Day.FRIDAY, timetables);
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(2, mergedIntervals.size());
        // expecting 8am-9am, 11am-7pm
        assertArrayEquals(new TimePeriod[]{
            new TimePeriod(EIGHT_AM, NINE_AM, Day.FRIDAY),
            new TimeBlock(ELEVEN_AM, SEVEN_PM, Day.FRIDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_timetablesVariant3Monday_twoTimeBlock() {
        List<Schedule> timetables = List.of(TIMETABLE_B, TIMETABLE_D, TIMETABLE_E);
        List<HourBlock> intervals = ScheduleUtil.getFreeCommonIntervals(Day.MONDAY, timetables);
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(2, mergedIntervals.size());
        // expecting 8-10am, 12-10pm
        assertArrayEquals(new TimeBlock[]{
            new TimeBlock(EIGHT_AM, TEN_AM, Day.MONDAY),
            new TimeBlock(TWELVE_PM, TEN_PM, Day.MONDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_timetablesVariant3Tuesday_threeTimeBlock() {
        List<Schedule> timetables = List.of(TIMETABLE_D, TIMETABLE_B, TIMETABLE_E);
        List<HourBlock> intervals = ScheduleUtil.getFreeCommonIntervals(Day.TUESDAY, timetables);
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(3, mergedIntervals.size());
        // expecting 8-10am, 12-2pm, 5-10pm
        assertArrayEquals(new TimeBlock[]{
            new TimeBlock(EIGHT_AM, TEN_AM, Day.TUESDAY),
            new TimeBlock(TWELVE_PM, TWO_PM, Day.TUESDAY),
            new TimeBlock(FIVE_PM, TEN_PM, Day.TUESDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_timetablesVariant3Wednesday_threeTimeBlock() {
        List<Schedule> timetables = List.of(TIMETABLE_B, TIMETABLE_D, TIMETABLE_E);
        List<HourBlock> intervals = ScheduleUtil.getFreeCommonIntervals(Day.WEDNESDAY, timetables);
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(3, mergedIntervals.size());
        // expecting 8am-4pm, 5pm-6pm, 7pm-10pm
        assertArrayEquals(new TimeBlock[]{
            new TimeBlock(EIGHT_AM, FOUR_PM, Day.WEDNESDAY),
            new TimeBlock(FIVE_PM, SIX_PM, Day.WEDNESDAY),
            new TimeBlock(SEVEN_PM, TEN_PM, Day.WEDNESDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_timetablesVariant3Thursday_threeTimeBlock() {
        List<Schedule> timetables = List.of(TIMETABLE_B, TIMETABLE_D, TIMETABLE_E);
        List<HourBlock> intervals = ScheduleUtil.getFreeCommonIntervals(Day.THURSDAY, timetables);
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(3, mergedIntervals.size());
        // expecting 8am-11am, 1pm-5pm, 9-10pm
        assertArrayEquals(new TimeBlock[]{
            new TimeBlock(EIGHT_AM, ELEVEN_AM, Day.THURSDAY),
            new TimeBlock(ONE_PM, FIVE_PM, Day.THURSDAY),
            new TimeBlock(NINE_PM, TEN_PM, Day.THURSDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_timetablesVariant3Friday_twoTimeBlock() {
        List<Schedule> timetables = List.of(TIMETABLE_B, TIMETABLE_E, TIMETABLE_D);
        List<HourBlock> intervals = ScheduleUtil.getFreeCommonIntervals(Day.FRIDAY, timetables);
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(intervals);
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(2, mergedIntervals.size());
        // expecting 8am-9am, 11am-7pm
        assertArrayEquals(new TimeBlock[]{
            new TimeBlock(EIGHT_AM, NINE_AM, Day.FRIDAY),
            new TimeBlock(ELEVEN_AM, SEVEN_PM, Day.FRIDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getCommonIntervals_fiveTimeTablesTuesday_success() {
        List<Schedule> timetables = List.of(TIMETABLE_A, TIMETABLE_B, TIMETABLE_C, TIMETABLE_D, TIMETABLE_E);
        List<HourBlock> intervals = ScheduleUtil.getFreeCommonIntervals(Day.TUESDAY, timetables);
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(intervals);
        assertFalse(mergedIntervals.isEmpty());
        //expecting 12-2pm, 5pm - 10pm
        assertEquals(2, mergedIntervals.size());
        assertArrayEquals(new TimeBlock[]{
            new TimeBlock(TWELVE_PM, TWO_PM, Day.TUESDAY),
            new TimeBlock(FIVE_PM, TEN_PM, Day.TUESDAY)},
            mergedIntervals.toArray());
    }

    @Test
    public void getIntervals_nineDisjointIntervalsNonIntersecting_threeTimeBlocks() {
        Day day = Day.MONDAY;
        List<HourBlock> timeSlots = List.of(new HourBlock(EIGHT_AM, day),
            new HourBlock(NINE_AM, day), new HourBlock(ONE_PM, day), new HourBlock(TWO_PM, day),
            new HourBlock(THREE_PM, day), new HourBlock(FIVE_PM, day), new HourBlock(SIX_PM, day),
            new HourBlock(SEVEN_PM, day), new HourBlock(EIGHT_PM, day));
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(timeSlots);
        //expecting 8am-10am, 1pm-4pm, 5pm-9pm
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(3, mergedIntervals.size());
        assertArrayEquals(new TimeBlock[]{new TimeBlock(EIGHT_AM, TEN_AM, day),
            new TimeBlock(ONE_PM, FOUR_PM, day), new TimeBlock(FIVE_PM, NINE_PM, day)},
            mergedIntervals.toArray());
    }

    @Test
    public void getIntervals_thirteenDisjointIntervalIntersecting_oneTimeBlock() {
        Day day = Day.MONDAY;
        List<HourBlock> timeSlots = List.of(new HourBlock(EIGHT_AM, day),
            new HourBlock(NINE_AM, day), new HourBlock(TEN_AM, day),
            new HourBlock(ELEVEN_AM, day), new HourBlock(TWELVE_PM, day),
            new HourBlock(ONE_PM, day), new HourBlock(TWO_PM, day),
            new HourBlock(THREE_PM, day), new HourBlock(FOUR_PM, day),
            new HourBlock(FIVE_PM, day), new HourBlock(SIX_PM, day),
            new HourBlock(SEVEN_PM, day), new HourBlock(EIGHT_PM, day));
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(timeSlots);
        //expecting 8am-9pm
        assertFalse(mergedIntervals.isEmpty());
        assertEquals(1, mergedIntervals.size());
        assertArrayEquals(new TimeBlock[]{new TimeBlock(EIGHT_AM, NINE_PM, day)},
                mergedIntervals.toArray());
    }

    @Test
    public void testIntervals_emptyTimeSlots_emptyMerge() {
        List<HourBlock> timeSlots = List.of();
        List<TimePeriod> mergedIntervals = ScheduleUtil.mergeSchedulables(timeSlots);
        assertTrue(mergedIntervals.isEmpty());
    }

    @Test
    public void testFormating_success() {
        assertEquals("8 AM", ScheduleUtil.formatLocalTime(EIGHT_AM));
        assertEquals("9 AM", ScheduleUtil.formatLocalTime(NINE_AM));
        assertEquals("10 AM", ScheduleUtil.formatLocalTime(TEN_AM));
        assertEquals("11 AM", ScheduleUtil.formatLocalTime(ELEVEN_AM));
        assertEquals("12 PM", ScheduleUtil.formatLocalTime(TWELVE_PM));
        assertEquals("1 PM", ScheduleUtil.formatLocalTime(ONE_PM));
        assertEquals("2 PM", ScheduleUtil.formatLocalTime(TWO_PM));
        assertEquals("3 PM", ScheduleUtil.formatLocalTime(THREE_PM));
        assertEquals("4 PM", ScheduleUtil.formatLocalTime(FOUR_PM));
        assertEquals("5 PM", ScheduleUtil.formatLocalTime(FIVE_PM));
        assertEquals("6 PM", ScheduleUtil.formatLocalTime(SIX_PM));
        assertEquals("7 PM", ScheduleUtil.formatLocalTime(SEVEN_PM));
        assertEquals("8 PM", ScheduleUtil.formatLocalTime(EIGHT_PM));
        assertEquals("9 PM", ScheduleUtil.formatLocalTime(NINE_PM));
        assertEquals("10 PM", ScheduleUtil.formatLocalTime(TEN_PM));
    }
}
