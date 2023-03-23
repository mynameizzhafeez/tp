package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.joda.time.LocalTime;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commitment.Lesson;
import seedu.address.model.person.Address;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;
import seedu.address.model.time.Day;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    private static final Pattern MODULE_TAG_ARGUMENTS_VALIDATION_REGEX = Pattern.compile(
            "(?<moduleCode>\\S+)\\s+(?<day>\\S+)\\s+(?<startTime>\\S+)\\s+(?<endTime>\\S+).*");

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Index>}.
     */
    public static Set<ContactIndex> parseIndices(Collection<String> indices) throws ParseException {
        requireNonNull(indices);
        final Set<ContactIndex> indexSet = new HashSet<>();
        for (String index : indices) {
            indexSet.add(parseContactIndex(index));
        }
        return indexSet;
    }

    /**
     * Returns a ContactIndex from the string index.
     */
    public static ContactIndex parseContactIndex(String contactIndex) throws ParseException {
        String trimmedIndex = contactIndex.trim();
        if (trimmedIndex.isEmpty()) {
            return null;
        }
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return new ContactIndex(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses an {@code intString} into an {@code Integer} and returns it.
     * @throws ParseException if the string cannot be converted into an integer.
     */
    public static int parseInt(String intString) throws ParseException {
        String trimmedInt = intString.trim();
        if (trimmedInt.isEmpty() || !StringUtil.isNonZeroUnsignedInteger(trimmedInt)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Integer.parseInt(trimmedInt);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String handle} into an {@code TelegramHandle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code handle} is invalid.
     */
    public static TelegramHandle parseTelegramHandle(String handle) throws ParseException {
        requireNonNull(handle);
        String trimmedTelegramHandle = handle.trim();
        if (!TelegramHandle.isValidTelegramHandle(trimmedTelegramHandle)) {
            throw new ParseException(TelegramHandle.MESSAGE_CONSTRAINTS);
        }
        return new TelegramHandle(trimmedTelegramHandle);
    }

    /**
     * Parses a {@code String tag} into a {@code GroupTag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static GroupTag parseGroupTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!GroupTag.isValidTagName(trimmedTag)) {
            throw new ParseException(GroupTag.MESSAGE_CONSTRAINTS);
        }
        return new GroupTag(trimmedTag);
    }

    /**
     * Parses a {@code String tag} into a {@code ModuleTag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static ModuleTag parseModuleTag(String tag) throws ParseException {
        requireNonNull(tag);

        String trimmedTag = tag.trim();
        final Matcher matcher = MODULE_TAG_ARGUMENTS_VALIDATION_REGEX.matcher(trimmedTag);

        final Matcher backupMatcher = ModuleTag.VALIDATION_REGEX.matcher(trimmedTag);

        if (!matcher.matches() && !backupMatcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        if (!matcher.matches()) {
            return new ModuleTag(trimmedTag);
        }

        final String moduleCode = matcher.group("moduleCode");
        final String dayAsStr = matcher.group("day");
        final String startTime = matcher.group("startTime");
        final String endTime = matcher.group("endTime");

        Lesson lesson = new Lesson(parseLocalTime(startTime), parseLocalTime(endTime), parseDay(dayAsStr));

        return new ModuleTag(moduleCode, lesson);
    }

    public static Day parseDay(String dayAsStr) throws ParseException {
        String upperDayAsStr = dayAsStr.toUpperCase();
        for (Day day : Day.values()) {
            if (day.toString().contains(upperDayAsStr)) {
                return day;
            }
        }
        throw new ParseException("Day is invalid");
    }

    public static LocalTime parseLocalTime(String timeAsStr) throws ParseException {
        try {
            int hour = Integer.parseInt(timeAsStr);
            if (hour >= 24 || hour < 0) {
                throw new ParseException("Invalid time");
            }
            return new LocalTime(hour, 0);
        } catch (NumberFormatException nfe) {
            throw new ParseException("Invalid time");
        }
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<GroupTag>}.
     */
    public static Set<GroupTag> parseGroupTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        List<String> filteredTags = tags.stream()
                .filter(t -> !t.isBlank()).collect(Collectors.toList());
        final Set<GroupTag> groupTagSet = new HashSet<>();
        for (String tagName : filteredTags) {
            groupTagSet.add(parseGroupTag(tagName));
        }
        return groupTagSet;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<ModuleTag>}.
     */
    public static Set<ModuleTag> parseModuleTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        List<String> filteredTags = tags.stream()
                .filter(t -> !t.isBlank()).collect(Collectors.toList());
        final Set<ModuleTag> moduleTagSet = new HashSet<>();
        for (String tagName : filteredTags) {
            moduleTagSet.add(parseModuleTag(tagName));
        }
        return moduleTagSet;
    }
}

