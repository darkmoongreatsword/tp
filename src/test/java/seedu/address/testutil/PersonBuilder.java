package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Transaction;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_COMPANY = "ABC Corp";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final Transaction DEFAULT_TRANSACTION_1 = new Transaction(
            "Buy raw materials", -100, "ABC Company", "10/10/2023");
    public static final Transaction DEFAULT_TRANSACTION_2 = new Transaction(
            "Invest", -1000, "Nice Company", "!st August 2024");

    private Name name;
    private Company company;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private List<Transaction> transactions;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        company = new Company(DEFAULT_COMPANY);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        transactions = new ArrayList<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        company = personToCopy.getCompany();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        transactions = new ArrayList<>(personToCopy.getTransactions());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Company} of the {@code Person} that we are building.
     */
    public PersonBuilder withCompany(String company) {
        this.company = new Company(company);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Transaction} of the {@code Person} that we are building.
     */
    public PersonBuilder withTransactions(Transaction ... transactions) {
        this.transactions.addAll(List.of(transactions));
        return this;
    }

    public Person build() {
        return new Person(name, company, phone, email, address, tags, transactions);
    }
}
