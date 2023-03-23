package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import seedu.address.model.commitment.Lesson;

/**
 * Represents a ModuleTag in the address book.
 * Guarantees: immutable; name is valid
 */
public class ModuleTag extends Tag implements Comparable<ModuleTag> {
    public static final String MESSAGE_CONSTRAINTS =
            "NUS Modules should have 2 - 4 letter prefix, followed by 4 digits and optional 1 - 3 alphabets";

    public static final Pattern VALIDATION_REGEX = Pattern.compile("[A-Z]{2,4}[0-9]{4}[A-Z]{0,3}");

    private final Set<Lesson> lessons;

    /**
     * Constructs a {@code ModuleTag}.
     *
     * @param moduleCode A valid moduleCode.
     */
    public ModuleTag(String moduleCode) {
        super(moduleCode);
        requireNonNull(moduleCode);
        System.out.println(moduleCode);
        checkArgument(isValidTagName(moduleCode), MESSAGE_CONSTRAINTS);
        this.lessons = new HashSet<>();
    }

    /**
     * Overloaded constructor for a {@code ModuleTag}
     */
    public ModuleTag(String moduleCode, Lesson lesson) {
        super(moduleCode);
        requireAllNonNull(moduleCode, lesson);
        this.lessons = Set.of(lesson);
    }

    /**
     * Overloaded constructor for a {@code ModuleTag}
     */
    public ModuleTag(String moduleCode, Collection<Lesson> lessons) {
        super(moduleCode);
        requireAllNonNull(moduleCode, lessons);
        this.lessons = new HashSet<>(lessons);
    }

    public String getTagName() {
        return tagName;
    }

    public ModuleTag mergeWith(ModuleTag otherModuleTag) {
        Set<Lesson> newLessons = new HashSet<>(lessons);
        newLessons.addAll(otherModuleTag.lessons);
        return new ModuleTag(tagName, newLessons);
    }

    public ModuleTag copy() {
        return new ModuleTag(tagName, new HashSet<>(lessons));
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public String getLessonsAsStr() {
        return lessons.stream()
                .map(Lesson::toString)
                .collect(Collectors.joining(", "));
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void addLessons(Collection<Lesson> lessons) {
        for (Lesson lesson : lessons) {
            addLesson(lesson);
        }
    }

    public void removeLesson(Lesson lesson) {
        lessons.remove(lesson);
    }

    public void removeLessons(Collection<Lesson> lessons) {
        for (Lesson lesson : lessons) {
            removeLesson(lesson);
        }
    }

    @Override
    boolean isValidTagName(String test, String regex) {
        return VALIDATION_REGEX.matcher(test).matches();
    }

    public static boolean isValidTagName(String test) {
        return VALIDATION_REGEX.matcher(test).matches();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleTag // instanceof handles nulls
                && tagName.equals(((ModuleTag) other).tagName)
                && lessons.equals(((ModuleTag) other).lessons)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName, lessons);
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return tagName;
    }

    @Override
    public int compareTo(ModuleTag otherModuleTag) {
        return tagName.compareTo(otherModuleTag.tagName);
    }
}
