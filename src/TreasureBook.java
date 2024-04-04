import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * The {@code TreasureBook} class represents a treasure book item in the library system.
 * It extends the {@link Item} class, inheriting its attributes and methods.
 */
public class TreasureBook extends Item implements Readable {
    private String publisher;
    private String donateName;
    private LocalDateTime currentTime;

    /**
     * Constructs a new TreasureBook object with the specified details.
     *
     * @param itemId     The ID of the treasure book.
     * @param title      The title of the treasure book.
     * @param authorName The name of the author of the treasure book.
     * @param publisher  The publisher of the treasure book.
     * @param year       The year of publication of the treasure book.
     * @param donateName The name of the person who donated the treasure book.
     * @param categoryId The ID of the category to which the treasure book belongs.
     * @param libraryId  The ID of the library where the treasure book is located.
     */
    public TreasureBook(String itemId, String title, String authorName, String publisher, String year,
                        String donateName, String categoryId, String libraryId) {
        super(itemId, title, authorName, year, 1, categoryId, libraryId);
        this.publisher = publisher;
        this.donateName = donateName;
    }

    /**
     * Checks if the treasure book is readable based on the time since it was last read.
     *
     * @param newTime The current time.
     * @return {@code true} if the treasure book is readable, {@code false} otherwise.
     */
    @Override
    public boolean canRead(LocalDateTime newTime) {
        long hourBetween = 0;
        if (this.currentTime != null) {
            hourBetween = ChronoUnit.HOURS.between(this.currentTime, newTime);
        }
        return Math.abs(hourBetween) >= 2 || (this.currentTime == null);
    }

    /**
     * Marks the treasure book as read at the specified time.
     *
     * @param readTime The time at which the treasure book is read.
     */
    @Override
    public void read(LocalDateTime readTime) {
        this.currentTime = readTime;
    }
}
