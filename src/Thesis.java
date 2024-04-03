/**
 * The {@code Thesis} class represents a thesis item in the library system.
 * It extends the {@link Item} class, inheriting its attributes and methods.
 */
public class Thesis extends Item implements Borrowable {
    private String advisorName;
    private boolean isBorrowed = false;

    /**
     * Constructs a new Thesis object with the specified details.
     *
     * @param itemId      The ID of the thesis.
     * @param title       The title of the thesis.
     * @param authorName  The name of the author of the thesis.
     * @param advisorName The name of the advisor for the thesis.
     * @param year        The year of submission of the thesis.
     * @param categoryId  The ID of the category to which the thesis belongs.
     * @param libraryId   The ID of the library where the thesis is located.
     */
    public Thesis(String itemId, String title, String authorName, String advisorName, String year,
                  String categoryId, String libraryId) {
        super(itemId, title, authorName, year, 1, categoryId, libraryId);
        this.advisorName = advisorName;
    }

    /**
     * Checks if the item is currently borrowed.
     *
     * @return {@code true} if the item is currently borrowed, {@code false} otherwise.
     */
    @Override
    public boolean isBorrowed() {
        return this.isBorrowed;
    }

    /**
     * Checks if the item can be borrowed.
     *
     * @return {@code true} if the item can be borrowed, {@code false} if it is already borrowed.
     */
    @Override
    public boolean canBorrow() {
        return !this.isBorrowed;
    }

    /**
     * Marks the item as borrowed.
     */
    @Override
    public void borrow() {
        this.isBorrowed = true;
    }

    /**
     * Marks the item as returned.
     */
    @Override
    public void returnItem() {
        this.isBorrowed = false;
    }
}
