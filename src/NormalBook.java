/**
 * The {@code NormalBook} class represents a normal book item in the library system.
 * It extends the {@link Item} class, inheriting its attributes and methods.
 */
public class NormalBook extends Item implements Borrowable {
    private String publisher;

    /**
     * Constructs a new NormalBook object with the specified details.
     *
     * @param itemId      The ID of the book.
     * @param title       The title of the book.
     * @param authorName  The name of the author of the book.
     * @param publisher   The publisher of the book.
     * @param year        The year of publication of the book.
     * @param copies      The total number of copies of the book.
     * @param categoryId  The ID of the category to which the book belongs.
     * @param libraryId   The ID of the library where the book is located.
     */
    public NormalBook(String itemId, String title, String authorName, String publisher, String year,
                      int copies, String categoryId, String libraryId) {
        super(itemId, title, authorName, year, copies, categoryId, libraryId);
        this.publisher = publisher;
    }

    @Override
    public boolean isBorrowed() {
        return this.getCopies() > this.getRemainder();
    }

    @Override
    public boolean canBorrow() {
        return this.getRemainder() != 0;
    }
    @Override
    public void borrow() {
        this.decreaseRemainder();
    }

    @Override
    public void returnItem() {

    }
}
