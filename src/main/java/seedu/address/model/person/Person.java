package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.commitment.Commitment;
import seedu.address.model.commitment.Lesson;
import seedu.address.model.recommender.Schedule;
import seedu.address.model.recommender.exceptions.CommitmentClashException;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final TelegramHandle telegramHandle;

    // Indexing fields
    private final ContactIndex contactIndex;

    // Data fields
    private final Address address;
    private final GroupTagSet groupTagSet = new GroupTagSet();
    private final ModuleTagSet moduleTagSet = new ModuleTagSet();
    private final Schedule schedule = new Schedule();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, TelegramHandle telegramHandle,
                  ContactIndex contactIndex, Set<GroupTag> groupTags, Set<ModuleTag> moduleTags) {
        requireAllNonNull(name, phone, email, address, telegramHandle, contactIndex, groupTags, moduleTags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.telegramHandle = telegramHandle;
        this.contactIndex = contactIndex;
        this.groupTagSet.addAll(groupTags);
        addModuleTags(moduleTags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public TelegramHandle getTelegramHandle() {
        return telegramHandle;
    }

    public ContactIndex getContactIndex() {
        return contactIndex;
    }

    /**
     * Returns a copy of the person's group tags.
     */
    public GroupTagSet getGroupTagSet() {
        return groupTagSet;
    }

    /**
     * Returns an immutable group tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<GroupTag> getImmutableGroupTags() {
        return groupTagSet.getImmutableGroups();
    }

    /**
     * Returns a copy of the person's module tags.
     */
    public ModuleTagSet getModuleTagSet() {
        return moduleTagSet;
    }

    /**
     * Returns an immutable modules, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<String> getImmutableModuleCodes() {
        return moduleTagSet.getImmutableModuleCodes();
    }

    /**
     * Returns an immutable module tags, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<ModuleTag> getImmutableModuleTags() {
        return Collections.unmodifiableSet(moduleTagSet.getImmutableModuleTags());
    }

    /**
     * Returns an immutable common modules, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<String> getImmutableCommonModuleCodes() {
        return Collections.unmodifiableSet(moduleTagSet.getImmutableCommonModuleCodes());
    }

    /**
     * Returns an immutable common modules, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<ModuleTag> getImmutableCommonModuleTags() {
        return Collections.unmodifiableSet(moduleTagSet.getImmutableCommonModuleTags());
    }

    /**
     * Sets the common modules that the person has with the user.
     */
    public void setCommonModules(Set<ModuleTag> userModules) {
        moduleTagSet.setCommonModules(userModules);
    }

    /**
     * Gets the lessons of the person.
     */
    public Set<? extends Lesson> getLessons() {
        Set<Lesson> lessons = new HashSet<>();
        for (ModuleTag moduleTag : getImmutableModuleTags()) {
            lessons.addAll(moduleTag.getLessons());
        }
        return lessons;
    }

    /**
     * Gets the commitments of the person.
     * We put this here as futureproofing.
     */
    public Set<? extends Commitment> getCommitments() {
        return getLessons();
    }

    /**
     * Returns the schedule of the person.
     */
    public Schedule getSchedule() {
        return schedule;
    }

    public void addCommitment(Commitment commitment) throws CommitmentClashException {
        requireAllNonNull(commitment);
        schedule.addCommitment(commitment);
    }

    public void addModuleTags(Collection<ModuleTag> moduleTags) {
        for (ModuleTag moduleTag : moduleTags) {
            // Here we keep the lessons that successfully
            // can be added to the Schedule.
            List<Lesson> insertableLessons = new ArrayList<>();
            for (Lesson lesson : moduleTag.getLessons()) {
                try {
                    addCommitment(lesson);
                    insertableLessons.add(lesson);
                } catch (CommitmentClashException cce) {
                    continue;
                }
            }
            if (!insertableLessons.isEmpty()) {
                // we update the module tag to only include
                // lessons that have successfully been added
                moduleTagSet.add(new ModuleTag(moduleTag.tagName, insertableLessons));
            }
        }
    }

    public void addModuleTags(ModuleTag... moduleTags) {
        addModuleTags(List.of(moduleTags));
    }

    public void removeModuleTags(ModuleTag... moduleTags) {
        moduleTagSet.removeAll(Set.of(moduleTags));
    }

    /**
     * Creates a new person and sets the contact index.
     */
    public Person setContactIndex(ContactIndex contactIndex) {
        return new Person(name, phone, email, address, telegramHandle,
                contactIndex, groupTagSet.getImmutableGroups(),
                new HashSet<>(moduleTagSet.getImmutableModuleTags()));
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getImmutableGroupTags().equals(getImmutableGroupTags())
                && otherPerson.getTelegramHandle().equals(getTelegramHandle())
                && otherPerson.getImmutableModuleCodes().equals(getImmutableModuleCodes());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, telegramHandle, groupTagSet, moduleTagSet);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" [Index : " + String.valueOf(contactIndex) + "]")
                .append("\nPhone: ")
                .append(getPhone())
                .append("\nEmail: ")
                .append(getEmail())
                .append("\nAddress: ")
                .append(getAddress())
                .append("\nTelegram: ")
                .append(getTelegramHandle())
                .append("\nGroups: ")
                .append(getImmutableGroupTags())
                .append("\nModules: ")
                .append(getImmutableModuleCodes())
                .append("\nCommitments: ")
                .append(getCommitmentsAsStr());

        return builder.toString();
    }

    public String getCommitmentsAsStr() {
        return getCommitments().stream()
                .map(Commitment::toString)
                .collect(Collectors.joining(", "));
    }

}
