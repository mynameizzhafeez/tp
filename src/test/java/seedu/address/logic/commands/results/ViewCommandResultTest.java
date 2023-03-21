package seedu.address.logic.commands.results;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.TypicalUser.LINUS;

import org.junit.jupiter.api.Test;

public class ViewCommandResultTest {
    private static final String FEEDBACK = "feedback";
    private static final String DIFFERENT = "different";
    private static final ViewCommandResult VIEW_COMMAND_RESULT = new ViewCommandResult(FEEDBACK, LINUS);

    @Test
    public void equals() {
        // same values -> returns true
        assertEquals(VIEW_COMMAND_RESULT, new ViewCommandResult(FEEDBACK, LINUS));

        // same object -> returns true
        assertEquals(VIEW_COMMAND_RESULT, VIEW_COMMAND_RESULT);

        // null -> returns false
        assertNotEquals(null, VIEW_COMMAND_RESULT);

        // different types -> returns false
        assertNotEquals(0.5f, VIEW_COMMAND_RESULT);

        // different feedbackToUser value -> returns false
        assertNotEquals(VIEW_COMMAND_RESULT, new ViewCommandResult(DIFFERENT, LINUS));
    }

    @Test
    public void hashcode() {
        // same values -> returns same hashcode
        assertEquals(VIEW_COMMAND_RESULT.hashCode(), new ViewCommandResult(FEEDBACK, LINUS).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(VIEW_COMMAND_RESULT.hashCode(), new ViewCommandResult(DIFFERENT, LINUS).hashCode());
    }
}
