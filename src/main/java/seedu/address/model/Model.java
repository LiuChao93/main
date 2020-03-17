package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.good.Good;
import seedu.address.model.supplier.Supplier;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Supplier> PREDICATE_SHOW_ALL_SUPPLIERS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Good> PREDICATE_SHOW_ALL_GOODS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' inventory file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a supplier with the same identity as {@code supplier} exists in the address book.
     */
    boolean hasSupplier(Supplier supplier);

    /**
     * Deletes the given supplier.
     * The supplier must exist in the address book.
     */
    void deleteSupplier(Supplier target);

    /**
     * Adds the given supplier.
     * {@code supplier} must not already exist in the address book.
     */
    void addSupplier(Supplier supplier);

    /**
     * Replaces the given supplier {@code target} with {@code editedSupplier}.
     * {@code target} must exist in the address book.
     * The supplier identity of {@code editedSupplier} must not be the same as another existing supplier
     * in the address book.
     */
    void setSupplier(Supplier target, Supplier editedSupplier);

    /** Returns an unmodifiable view of the filtered supplier list */
    ObservableList<Supplier> getFilteredSupplierList();

    /**
     * Updates the filter of the filtered supplier list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredSupplierList(Predicate<Supplier> predicate);

    /**
     * Returns the user prefs' inventory file path.
     */
    Path getInventoryFilePath();

    /**
     * Sets the user prefs' inventory file path.
     */
    void setInventoryFilePath(Path inventoryFilePath);

    /**
     * Replaces inventory data with the data in {@code inventory}.
     */
    void setInventory(ReadOnlyInventory inventory);

    /** Returns the Inventory */
    ReadOnlyInventory getInventory();

    /**
     * Returns true if a good with the same identity as {@code good} exists in the inventory.
     */
    boolean hasGood(Good good);

    /**
     * Deletes the given good.
     * The good must exist in the inventory.
     */
    void deleteGood(Good target);

    /**
     * Adds the given good.
     * {@code good} must not already exist in the inventory.
     */
    void addGood(Good good);

    /**
     * Returns the zero-based index of the first occurance of good if found, -1 otherwise.
     * @param good to be found
     * @return zero based index of good to be found
     */
    int indexOfGood(Good good);

    /**
     * Replaces the given good {@code target} with {@code editedGood}.
     * {@code target} must exist in the inventory.
     * The good identity of {@code editedGood} must not be the same as another existing good in the inventory.
     */
    void setGood(Good target, Good editedGood);

    /** Returns an unmodifiable view of the filtered good list */
    ObservableList<Good> getFilteredGoodList();

    /**
     * Updates the filter of the filtered good list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredGoodList(Predicate<Good> predicate);
}
