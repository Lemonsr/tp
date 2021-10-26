package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.PersonType;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;

/**
 * Finds and lists all students or tutors in address book whose name contains the argument keyword.
 * Keyword matching is case-insensitive.
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all the tutor names that contain "
            + "the specified search parameters (case-insensitive) and displays them in the matched tutors list."
            + "Parameters: [n/NAME] [p/PHONE] [g/GENDER] [q/QUALIFICATION]\n"
            + "Example: " + COMMAND_WORD + " t q/3";

    private final Predicate<Person> predicate;

    public static final String MESSAGE_FILTER_FAILED = "Failed to filter as no match command was given prior.";

    /** Creates a FilterCommand to search for the specified {@code Tutor} */
    public FilterCommand(Predicate<Person> predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (lastShownList.isEmpty()) {
            throw new CommandException(String.format(Messages.MESSAGE_EMPTY_LIST, PersonType.STUDENT));
        }

        if (model.getMatchedTutorList().isEmpty()) {
            model.updateMatchedTutor(Model.PREDICATE_SHOW_NO_PERSON, new ArrayList<>());
            throw new CommandException(String.format(MESSAGE_FILTER_FAILED));
        }

        model.filterMatchedTutor(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_TUTORS_LISTED_OVERVIEW,
                model.getMatchedTutorList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }
}
