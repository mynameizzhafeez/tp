package seedu.address.logic.commands.results;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Person;
import seedu.address.testutil.TypicalPersons;

public class ViewCommandResultTest {
    private static final String FEEDBACK = "feedback";
    private static final String DIFFERENT = "different";
    private static final Person PERSON_1 = TypicalPersons.ISAAC;
    private static final Person PERSON_2 = TypicalPersons.EDWARD;
    private static final ViewCommandResult VIEW_COMMAND_RESULT =
            new ViewCommandResult(FEEDBACK, PERSON_1);

    @Test
    public void equals_sameObject_true() {
        assertEquals(VIEW_COMMAND_RESULT, VIEW_COMMAND_RESULT);
    }

    @Test
    public void equals_samePersonSameFeedback_true() {
        assertEquals(VIEW_COMMAND_RESULT,
                new ViewCommandResult(FEEDBACK, PERSON_1));
    }

    @Test
    public void equals_samePersonDifferentFeedback_false() {
        assertNotEquals(VIEW_COMMAND_RESULT,
                new ViewCommandResult(DIFFERENT, PERSON_1));
    }

    @Test
    public void equals_differentPersonSameFeedback_false() {
        assertNotEquals(VIEW_COMMAND_RESULT,
                new ViewCommandResult(FEEDBACK, PERSON_2));
    }

    @Test
    public void equals_differentPersonDifferentFeedback_false() {
        assertNotEquals(VIEW_COMMAND_RESULT,
                new ViewCommandResult(DIFFERENT, PERSON_2));
    }

    @Test
    public void hashCode_sameValues_sameHashCode() {
        assertEquals(VIEW_COMMAND_RESULT.hashCode(),
                new ViewCommandResult(FEEDBACK, PERSON_1).hashCode());
    }

    @Test
    public void hashCode_differentFeedback_differentHashCode() {
        assertNotEquals(VIEW_COMMAND_RESULT.hashCode(),
                new ViewCommandResult(DIFFERENT, PERSON_1).hashCode());
    }
    @Test
    public void hashCode_differentPerson_differentHashCode() {
        assertNotEquals(VIEW_COMMAND_RESULT.hashCode(),
                new ViewCommandResult(FEEDBACK, PERSON_2).hashCode());
    }

    @Test
    public void hashCode_differentValues_differentHashCode() {
        assertNotEquals(VIEW_COMMAND_RESULT.hashCode(),
                new ViewCommandResult(DIFFERENT, PERSON_2).hashCode());
    }
}
