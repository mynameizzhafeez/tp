package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptor(DESC_ALEX);
        assertTrue(DESC_ALEX.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ALEX.equals(DESC_ALEX));

        // null -> returns false
        assertFalse(DESC_ALEX.equals(null));

        // different types -> returns false
        assertFalse(DESC_ALEX.equals(5));

        // different values -> returns false
        assertFalse(DESC_ALEX.equals(DESC_BEN));

        // different name -> returns false
        EditPersonDescriptor editedAmy = new EditPersonDescriptorBuilder(DESC_ALEX).withName(NAME_BEN).build();
        assertFalse(DESC_ALEX.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_ALEX).withPhone(PHONE_BEN).build();
        assertFalse(DESC_ALEX.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_ALEX).withEmail(EMAIL_BEN).build();
        assertFalse(DESC_ALEX.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_ALEX).withAddress(ADDRESS_BEN).build();
        assertFalse(DESC_ALEX.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_ALEX).withGroupTags(VALID_GROUP_1).build();
        assertFalse(DESC_ALEX.equals(editedAmy));
    }

    @Test
    public void tostring() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder(DESC_ALEX)
                .withEmail(EMAIL_BEN).build();
        assertTrue(editPersonDescriptor.toString().contains(ADDRESS_ALEX));
        assertTrue(editPersonDescriptor.toString().contains(PHONE_ALEX));
        assertTrue(editPersonDescriptor.toString().contains(TELEGRAM_HANDLE_ALEX));
        assertTrue(editPersonDescriptor.toString().contains(NAME_ALEX));
        assertTrue(editPersonDescriptor.toString().contains(EMAIL_BEN));
    }
}
