import java.util.HashMap;

public class Library {
    private String libraryId;
    private String name;
    private String establishedYear;
    private int numberOfTables;
    private String address;

    private HashMap<String, Item> items = new HashMap<>();

    public Library(String libraryId, String name, String establishedYear, int numberOfTables, String address) {
        this.libraryId = libraryId;
        this.name = name;
        this.establishedYear = establishedYear;
        this.numberOfTables = numberOfTables;
        this.address = address;
    }

    public void addItem(Item newItem) {
        this.items.put(newItem.getItemId(), newItem);
    }

    public void removeItem(String itemId) {
        this.items.remove(itemId);
    }

    public HashMap<String, Item> getItems() {
        return this.items;
    }

    public String getLibraryId() {
        return this.libraryId;
    }
}
