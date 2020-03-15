package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.good.Good;
import seedu.address.model.offer.Price;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.offer.Offer;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getOfferList("banana 4.5")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getOfferList("toilet paper 2", "tissue 70.50")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getOfferList("hand sanitiser 3.25")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getOfferList("instant noodle 0.45")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getOfferList("facial mask 5.75")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getOfferList("apple 50.3"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns an offer list containing the list of strings given.
     */
    public static List<Offer> getOfferList(String... strings) {
        return Arrays.stream(strings)
                .map(ParserUtil::splitOnLastWhitespace)
                .map(SampleDataUtil::getGoodPricePair)
                .map(x -> new Offer((Good) x[0], (Price) x[1]))
                .collect(Collectors.toList());
    }

    /**
     * Returns an Object array containing a Good and a Price in indices 0 and 1 respectively. 
     * It is still subject to the same validation as the class constructors, but assumes that all input is valid.
     * The {@code Good} and {@code Price} corresponds to the one specified in the string.
     * 
     * @param goodAndPrice the string representation of the good and price pair
     * @return an {@code Object} array containing the {@code Good} and {@code Price}
     */
    private static Object[] getGoodPricePair(String[] goodAndPrice) {
        Object result[] = new Object[2];
        result[0] = new Good(goodAndPrice[0]);
        result[1] = new Price(goodAndPrice[1]);
        return result;
    }

}
