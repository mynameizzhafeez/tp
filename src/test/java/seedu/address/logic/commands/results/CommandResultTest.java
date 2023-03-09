package seedu.address.logic.commands.results;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    private static final CommandResult COMMAND_RESULT = new CommandResult("feedback");

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertEquals(commandResult, new CommandResult("feedback"));

        // same object -> returns true
        assertEquals(commandResult, commandResult);

        // null -> returns false
        assertNotEquals(null, commandResult);

        // different types -> returns false
        assertNotEquals(commandResult, 0.5f);

        // different feedbackToUser value -> returns false
        assertNotEquals(commandResult, new CommandResult("different"));

        // different showHelp value -> returns false
        assertNotEquals(commandResult, new ExitCommandResult("feedback"));

        // different exit value -> returns false
        assertNotEquals(commandResult, new HelpCommandResult("feedback"));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new HelpCommandResult("feedback").hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new ExitCommandResult("feedback").hashCode());
    }

    @Test
    public void getFeedbackToUser_validCommandResult_success() {
        String feedbackToUser = "feedback";
        CommandResult commandResult = new CommandResult(feedbackToUser);
        assertEquals(feedbackToUser, commandResult.getFeedbackToUser());
    }

    @Test
    public void isShowHelp_true_true() {
        assertTrue(new HelpCommandResult("feedback").isShowHelp());
    }

    @Test
    public void isExit_true_true() {
        assertTrue(new ExitCommandResult("feedback").isExit());
    }
}
