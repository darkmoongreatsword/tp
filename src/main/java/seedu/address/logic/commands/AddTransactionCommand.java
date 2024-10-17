package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OTHER_PARTY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Transaction;

/**
 * Adds a transaction to an existing person in the address book.
 */
public class AddTransactionCommand extends Command {

    public static final String COMMAND_WORD = "addt";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a transaction to selected person. "
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_AMOUNT + "AMOUNT "
            + PREFIX_OTHER_PARTY + "OTHER PARTY "
            + PREFIX_DATE + "DATE\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "buy raw materials "
            + PREFIX_AMOUNT + "-100 "
            + PREFIX_OTHER_PARTY + "Company XYZ "
            + PREFIX_DATE + "10-10-2024";

    public static final String MESSAGE_ADD_TRANSACTION_SUCCESS = "Added new transaction %1$s\nto %2$s";

    private final Index index;
    private final Transaction toAdd;

    /**
     * @param index index of person to add transactions to
     * @param transaction transaction to add
     */
    public AddTransactionCommand(Index index, Transaction transaction) {
        requireAllNonNull(index, transaction);

        this.index = index;
        toAdd = transaction;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        //to be implemented
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        List<Transaction> transactions = personToEdit.getTransactions();
        transactions.add(toAdd);
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getCompany(), personToEdit.getPhone(),
                personToEdit.getEmail(), personToEdit.getAddress(), personToEdit.getTags(), transactions);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_ADD_TRANSACTION_SUCCESS, Messages.format(toAdd),
                Messages.format(editedPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddTransactionCommand)) {
            return false;
        }

        AddTransactionCommand otherCommand = (AddTransactionCommand) other;
        return index.equals(otherCommand.index) && toAdd.equals(otherCommand.toAdd);
    }

}
