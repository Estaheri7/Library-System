/**
 * The {@code Item} class represents an item in the library system.
 * It is an abstract class that provides a blueprint for various types of library items.
 */
public abstract class Item {
    private String itemId;
    private String title;
    private String authorName;
    private String year;
    private int copies;
    private int remainder;
    private String categoryId;
    private String libraryId;

    /**
     * Constructs a new Item object with the specified details.
     *
     * @param itemId      The unique ID of the item.
     * @param title       The title of the item.
     * @param authorName  The name of the author of the item.
     * @param year        The year of publication or release of the item.
     * @param copies      The total number of copies of the item available in the library.
     * @param categoryId  The ID of the category to which the item belongs.
     * @param libraryId   The ID of the library where the item is located.
     */
    public Item(String itemId, String title, String authorName, String year, int copies,
                String categoryId, String libraryId) {
        this.itemId = itemId;
        this.title = title;
        this.authorName = authorName;
        this.year = year;
        this.copies = copies;
        this.remainder = copies;
        this.categoryId = categoryId;
        this.libraryId = libraryId;
    }

    /**
     * Retrieves the ID of the item.
     *
     * @return The ID of the item.
     */
    public String getItemId() {
        return this.itemId;
    }

    /**
     * Retrieves the ID of the category to which the item belongs.
     *
     * @return The ID of the category.
     */
    public String getCategoryId() {
        return this.categoryId;
    }

    /**
     * Retrieves the ID of the library where the item is located.
     *
     * @return The ID of the library.
     */
    public String getLibraryId() {
        return this.libraryId;
    }

    public boolean isBorrowable() {
        return this instanceof Borrowable;
    }

    public int getCopies() {
        return this.copies;
    }

    public int getRemainder() {
        return this.remainder;
    }

    public void decreaseRemainder() {
        this.remainder--;
    }

    public void increaseRemainder() {
        this.remainder++;
    }

    public boolean isPurchasable() {
        return this instanceof PurchasableBook;
    }

    public boolean isTreasure() {
        return this instanceof TreasureBook;
    }
}
