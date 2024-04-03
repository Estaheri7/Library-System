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

    /**
     * Checks if the book is currently borrowed.
     *
     * @return {@code true} if the book is currently borrowed, {@code false} otherwise.
     */
    @Override
    public boolean isBorrowed() {
        return this.getCopies() > this.getRemainder();
    }

    /**
     * Checks if the book can be borrowed.
     *
     * @return {@code true} if the book can be borrowed, {@code false} otherwise.
     */
    @Override
    public boolean canBorrow() {
        return this.getRemainder() != 0;
    }

    /**
     * Marks the book as borrowed by decreasing the remaining copies count.
     */
    @Override
    public void borrow() {
        this.decreaseRemainder();
    }

    /**
     * Marks the book as returned by increasing the remaining copies count.
     */
    @Override
    public void returnItem() {
        this.increaseRemainder();
    }
}
