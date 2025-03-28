import java.util.ArrayList;

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
    private ArrayList<String> comments = new ArrayList<>();

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

    /**
     * Checks if the item is borrowable.
     *
     * @return {@code true} if the item is an instance of a borrowable item, {@code false} otherwise.
     */
    public boolean isBorrowable() {
        return this instanceof Borrowable;
    }

    /**
     * Retrieves the total number of copies of the item.
     *
     * @return The total number of copies of the item.
     */
    public int getCopies() {
        return this.copies;
    }

    /**
     * Retrieves the number of remaining copies of the item.
     *
     * @return The number of remaining copies of the item.
     */
    public int getRemainder() {
        return this.remainder;
    }

    /**
     * Decreases the number of remaining copies of the item by one.
     */
    public void decreaseRemainder() {
        this.remainder--;
    }

    /**
     * Increases the number of remaining copies of the item by one.
     */
    public void increaseRemainder() {
        this.remainder++;
    }

    /**
     * Checks if the item is purchasable.
     *
     * @return {@code true} if the item is an instance of a purchasable book, {@code false} otherwise.
     */
    public boolean isPurchasable() {
        return this instanceof Purchasable;
    }

    /**
     * Checks if the item is a treasure book.
     *
     * @return {@code true} if the item is an instance of a treasure book, {@code false} otherwise.
     */
    public boolean isReadable() {
        return this instanceof Readable;
    }

    /**
     * Adds a comment to the list of comments associated with this object.
     *
     * @param comment The comment to be added.
     */
    public void addComment(String comment) {
        this.comments.add(comment);
    }

    /**
     * Searches for a keyword within the item's title or author name.
     *
     * @param searchKey The keyword to search for.
     * @return {@code true} if the keyword is found in the item's title or author name, {@code false} otherwise.
     */
    public boolean search(String searchKey) {
        return this.title.toLowerCase().contains(searchKey.toLowerCase()) ||
                this.authorName.toLowerCase().contains(searchKey.toLowerCase());
    }
}
