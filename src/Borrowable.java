public interface Borrowable {
    boolean isBorrowed();
    boolean canBorrow();
    void borrow();
    void returnItem();
}
