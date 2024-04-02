import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TreasureBook extends Item {
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

    public boolean isReadable(LocalDateTime newTime) {
        long hourBetween = ChronoUnit.HOURS.between(this.currentTime, newTime);
        return Math.abs(hourBetween) >= 2;
    }
}
