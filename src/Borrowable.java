/**
 * The {@code Borrowable} interface represents an item that can be borrowed from a library.
 * Classes implementing this interface should provide methods for managing the borrowing
 * and returning of the item.
 */
public interface Borrowable {
    boolean isBorrowed();
    boolean canBorrow();
    void borrow();
    void returnItem();
}
