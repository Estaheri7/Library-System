/**
 * The {@code PurchasableBook} class represents a book item available for purchase in the library system.
 * It extends the {@link Item} class, inheriting its attributes and methods.
 */
public class PurchasableBook extends Item {
    private String publisher;
    private String price;
    private String discount;

    /**
     * Constructs a new PurchasableBook object with the specified details.
     *
     * @param itemId      The ID of the book.
     * @param title       The title of the book.
     * @param authorName  The name of the author of the book.
     * @param publisher   The publisher of the book.
     * @param year        The year of publication of the book.
     * @param copies      The total number of copies of the book.
     * @param price       The price of the book.
     * @param discount    The discount on the book.
     * @param categoryId  The ID of the category to which the book belongs.
     * @param libraryId   The ID of the library where the book is located.
     */
    public PurchasableBook(String itemId, String title, String authorName, String publisher, String year,
                           int copies, String price, String discount, String categoryId, String libraryId) {
        super(itemId, title, authorName, year, copies, categoryId, libraryId);
        this.publisher = publisher;
        this.price = price;
        this.discount = discount;
    }

    public boolean isSoldOut() {
        return this.getRemainder() == 0;
    }
}
