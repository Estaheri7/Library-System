import java.time.LocalDateTime;

/**
 * The {@code Borrow} class represents a borrowing transaction in the library system.
 * It contains information about the person who borrowed an item, the library from which
 * the item was borrowed, the ID of the borrowed item, and the time of the borrowing.
 */
public class Borrow {
    private String personId;
    private String libraryId;
    private String itemId;
    private LocalDateTime borrowTime;

    /**
     * Constructs a new Borrow object with the specified details.
     *
     * @param personId   The ID of the person who borrowed the item.
     * @param libraryId  The ID of the library from which the item was borrowed.
     * @param itemId     The ID of the borrowed item.
     * @param date       The date of the borrowing in the format "yyyy-MM-dd".
     * @param clock      The time of the borrowing in the format "HH:mm".
     */
    public Borrow(String personId, String libraryId, String itemId, String date, String clock) {
        this.personId = personId;
        this.libraryId = libraryId;
        this.itemId = itemId;
        String fullDate = date + "T" + clock;
        this.borrowTime = LocalDateTime.parse(fullDate);
    }

    /**
     * Retrieves the ID of the library from which the item was borrowed.
     *
     * @return The library ID.
     */
    public String getLibraryId() {
        return this.libraryId;
    }

    /**
     * Retrieves the ID of the borrowed item.
     *
     * @return The item ID.
     */
    public String getItemId() {
        return this.itemId;
    }

    /**
     * Retrieves the date and time of the borrowing.
     *
     * @return The borrowing date and time.
     */
    public LocalDateTime getBorrowTime() {
        return this.borrowTime;
    }
}
