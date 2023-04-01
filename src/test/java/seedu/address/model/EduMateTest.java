package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.STATION_BEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_1;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALBERT;
import static seedu.address.testutil.TypicalPersons.BART;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.meetup.MeetUp;
import seedu.address.model.meetup.Participants;
import seedu.address.model.person.Person;
import seedu.address.model.person.User;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.recommendation.Recommendation;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalUser;

public class EduMateTest {

    private final EduMate eduMate = new EduMate();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), eduMate.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eduMate.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyEduMate_replacesData() {
        EduMate newData = getTypicalEduMate();
        eduMate.resetData(newData);
        assertEquals(newData, eduMate);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlbert = new PersonBuilder(ALBERT).withStation(STATION_BEN).withGroupTags(VALID_GROUP_1)
                .build();
        List<Person> newPersons = Arrays.asList(ALBERT, editedAlbert);
        User validUser = TypicalUser.getTypicalUser();
        EduMateStub newData = new EduMateStub(newPersons, validUser);

        assertThrows(DuplicatePersonException.class, () -> eduMate.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eduMate.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInEduMate_returnsFalse() {
        assertFalse(eduMate.hasPerson(ALBERT));
    }

    @Test
    public void hasPerson_personInEduMate_returnsTrue() {
        eduMate.addPerson(ALBERT);
        assertTrue(eduMate.hasPerson(ALBERT));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInEduMate_returnsTrue() {
        eduMate.addPerson(ALBERT);
        Person editedAlbert = new PersonBuilder(ALBERT).withStation(STATION_BEN).withGroupTags(VALID_GROUP_1)
                .build();
        assertTrue(eduMate.hasPerson(editedAlbert));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> eduMate.getPersonList().remove(0));
    }

    @Test
    public void hashCode_validEduMate_success() {
        assertEquals(eduMate.hashCode(), Objects.hash(eduMate.getPersonList(), eduMate.getUser()));
    }

    @Test
    public void removePerson_validIndex_success() {
        if (!eduMate.hasPerson(BART)) {
            eduMate.addPerson(BART);
        }
        eduMate.removePerson(BART);
        assertFalse(eduMate.hasPerson(BART));
    }

    /**
     * A stub ReadOnlyEduMate whose persons list or user can violate interface constraints.
     */
    private static class EduMateStub implements ReadOnlyEduMate {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private User user;
        private final ObservableList<Recommendation> recommendations = FXCollections.observableArrayList();

        EduMateStub(Collection<Person> persons, User user, Collection<Recommendation> recommendations) {
            this.persons.setAll(persons);
            this.user = user;
            this.recommendations.setAll(recommendations);
        }

        EduMateStub(Collection<Person> persons, User user) {
            this.persons.setAll(persons);
            this.user = user;
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Recommendation> getRecommendationList() {
            return null;
        }

        @Override
        public User getUser() {
            return user;
        }

        @Override
        public ObservableList<MeetUp> getMeetUpList() {
            return null;
        }

        @Override
        public Participants getParticipantList() {
            return null;
        }
    }

}
