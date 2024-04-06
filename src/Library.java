import java.util.HashMap;
import java.util.HashSet;

/**
 * The {@code Library} class represents a library in the system.
 * It contains information about the library and manages its items.
 */
public class Library {
    private String libraryId;
    private String name;
    private String establishedYear;
    private int numberOfTables;
    private String address;

    private HashMap<String, Item> items = new HashMap<>();

    /**
     * Constructs a new Library object with the specified details.
     *
     * @param libraryId        The unique ID of the library.
     * @param name             The name of the library.
     * @param establishedYear  The year the library was established.
     * @param numberOfTables   The number of tables available in the library.
     * @param address          The address of the library.
     */
    public Library(String libraryId, String name, String establishedYear, int numberOfTables, String address) {
        this.libraryId = libraryId;
        this.name = name;
        this.establishedYear = establishedYear;
        this.numberOfTables = numberOfTables;
        this.address = address;
    }

    /**
     * Adds a new item to the library.
     *
     * @param newItem The item to be added.
     */
    public void addItem(Item newItem) {
        this.items.put(newItem.getItemId(), newItem);
    }

    /**
     * Removes an item from the library.
     *
     * @param itemId The ID of the item to be removed.
     */
    public void removeItem(String itemId) {
        this.items.remove(itemId);
    }

    public String categoryReport(HashSet<String> categories) {
        int normalBooks = 0;
        int theses = 0;
        int treasureBooks = 0;
        int purchasableBooks = 0;
        for (String category : categories) {
            for (Item item : this.items.values()) {
                if (item.getCategoryId().equals(category)) {
                    if (item instanceof NormalBook) {
                        normalBooks += item.getCopies();
                    } else if (item instanceof Thesis) {
                        theses += item.getCopies();
                    } else if (item instanceof TreasureBook) {
                        treasureBooks += item.getCopies();
                    } else if (item instanceof PurchasableBook) {
                        purchasableBooks += item.getCopies();
                    }
                }
            }
        }

        return normalBooks + " " + theses + " " + treasureBooks + " " + purchasableBooks;
    }

    /**
     * Retrieves a map containing all items in the library.
     *
     * @return A map containing item IDs as keys and corresponding items as values.
     */
    public HashMap<String, Item> getItems() {
        return this.items;
    }

    /**
     * Retrieves the ID of the library.
     *
     * @return The ID of the library.
     */
    public String getLibraryId() {
        return this.libraryId;
    }
}
