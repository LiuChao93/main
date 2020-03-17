package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.good.Good;
import seedu.address.model.supplier.Supplier;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final Inventory inventory;
    private final UserPrefs userPrefs;
    private final FilteredList<Supplier> filteredSuppliers;
    private final FilteredList<Good> filteredGoods;

    /**
     * Initializes a ModelManager with the given addressBook, inventory and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyInventory inventory,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, inventory, userPrefs);

        logger.fine("Initializing with address book: " + addressBook
                + ", inventory: " + inventory + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.inventory = new Inventory(inventory);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredSuppliers = new FilteredList<>(this.addressBook.getSupplierList());
        filteredGoods = new FilteredList<>(this.inventory.getGoodList());
    }

    public ModelManager() {
        this(new AddressBook(), new Inventory(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public Path getInventoryFilePath() {
        return userPrefs.getInventoryFilePath();
    }

    @Override
    public void setInventoryFilePath(Path inventoryFilePath) {
        requireNonNull(inventoryFilePath);
        userPrefs.setInventoryFilePath(inventoryFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasSupplier(Supplier supplier) {
        requireNonNull(supplier);
        return addressBook.hasSupplier(supplier);
    }

    @Override
    public void deleteSupplier(Supplier target) {
        addressBook.removeSupplier(target);
    }

    @Override
    public void addSupplier(Supplier supplier) {
        addressBook.addSupplier(supplier);
        updateFilteredSupplierList(PREDICATE_SHOW_ALL_SUPPLIERS);
    }

    @Override
    public void setSupplier(Supplier target, Supplier editedSupplier) {
        requireAllNonNull(target, editedSupplier);

        addressBook.setSupplier(target, editedSupplier);
    }

    //=========== Inventory ================================================================================

    @Override
    public void setInventory(ReadOnlyInventory inventory) {
        this.inventory.resetData(inventory);
    }

    @Override
    public ReadOnlyInventory getInventory() {
        return inventory;
    }

    @Override
    public boolean hasGood(Good good) {
        requireNonNull(good);
        return inventory.hasGood(good);
    }

    @Override
    public void deleteGood(Good target) {
        inventory.removeGood(target);
    }

    @Override
    public void addGood(Good good) {
        inventory.addGood(good);
        updateFilteredSupplierList(PREDICATE_SHOW_ALL_SUPPLIERS);
    }

    @Override
    public int indexOfGood(Good good) {
        return inventory.index(good);
    }

    @Override
    public void setGood(Good target, Good editedGood) {
        requireAllNonNull(target, editedGood);

        inventory.setGood(target, editedGood);
    }

    //=========== Filtered Supplier List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Supplier} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Supplier> getFilteredSupplierList() {
        return filteredSuppliers;
    }

    @Override
    public void updateFilteredSupplierList(Predicate<Supplier> predicate) {
        requireNonNull(predicate);
        filteredSuppliers.setPredicate(predicate);
    }

    //=========== Filtered Good List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Good} backed by the internal list of
     * {@code versionedInventory}
     */
    @Override
    public ObservableList<Good> getFilteredGoodList() {
        return filteredGoods;
    }

    @Override
    public void updateFilteredGoodList(Predicate<Good> predicate) {
        requireNonNull(predicate);
        filteredGoods.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && inventory.equals(other.inventory)
                && userPrefs.equals(other.userPrefs)
                && filteredSuppliers.equals(other.filteredSuppliers)
                && filteredGoods.equals(other.filteredGoods);
    }

}
