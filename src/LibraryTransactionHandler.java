import java.security.cert.CertificateEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The {@code LibraryTransactionHandler} class handles transactions related to library operations,
 * such as borrowing, returning, buying, and reading items.
 */
public class LibraryTransactionHandler {
    private static ArrayList<Borrow> borrows = new ArrayList<>();
    private String personId;
    private String password;
    private String libraryId;
    private String itemId;
    private String date;
    private String clock;
    private Library library;
    private Person person;
    private Item item;

    /**
     * Constructs a {@code LibraryTransactionHandler} object with the specified details.
     *
     * @param personId   The ID of the person performing the transaction.
     * @param password   The password of the person.
     * @param libraryId  The ID of the library involved in the transaction.
     * @param itemId     The ID of the item involved in the transaction.
     * @param date       The date of the transaction.
     * @param clock      The time of the transaction.
     */
    public LibraryTransactionHandler(String personId, String password, String libraryId, String itemId,
                                     String date, String clock) {
        this.personId = personId;
        this.password = password;
        this.libraryId = libraryId;
        this.itemId = itemId;
        this.date = date;
        this.clock = clock;
    }

    /**
     * Handles borrowing an item from the library.
     *
     * @return A string indicating the result of the borrowing operation.
     */
    public String borrowItem() {
        if(this.notFound()) {
            return "not-found";
        }

        if (!Center.isCorrectPassword(this.personId, this.password)) {
            return "invalid-pass";
        }

        if (this.person.bucketIsFull() || this.person.hasDebt() || this.person.borrowedThisItem(this.itemId, this.libraryId)) {
            return "not-allowed";
        }

        if (!this.item.isBorrowable()) {
            return "not-allowed";
        }

        Borrowable borrowable = (Borrowable) this.item;
        if (!borrowable.canBorrow()) {
            return "not-allowed";
        }
        String fixedDate = Center.formatDate(this.date);
        Borrow borrow = new Borrow(this.personId, this.libraryId, this.itemId, fixedDate, this.clock);
        this.person.borrow(borrow);
        borrowable.borrow();
        return "success";
    }

    /**
     * Handles returning an item to the library.
     *
     * @return A string indicating the result of the return operation.
     */
    public String returnItem() {
        if(this.notFound()) {
            return "not-found";
        }

        if (!this.person.borrowedThisItem(this.itemId, this.libraryId)) {
            return "not-found";
        }

        if (!Center.isCorrectPassword(this.personId, this.password)) {
            return "invalid-pass";
        }

        Borrow borrow = this.person.getBorrow(this.itemId, this.libraryId);
        String fixedDate = Center.formatDate(this.date);
        String fullDateTime = fixedDate + "T" + this.clock;
        LocalDateTime returnDateTime = LocalDateTime.parse(fullDateTime);
        long debt = this.person.debtForReturn(borrow.getBorrowTime(), returnDateTime, this.item);
        this.person.returnItem(borrow);
        Borrowable borrowableItem = (Borrowable) this.item;
        borrowableItem.returnItem();
        if (debt == 0) {
            return "success";
        } else {
            return "" + debt;
        }
    }

    /**
     * Handles purchasing an item from the library.
     *
     * @return A string indicating the result of the purchase operation.
     */
    public String buy() {
        if (this.notFound()) {
            return "not-found";
        }

        if (!Center.isCorrectPassword(this.personId, this.password)) {
            return "invalid-pass";
        }

        if (!this.item.isPurchasable()) {
            return "not-allowed";
        }
        Purchasable purchasableBook = (Purchasable) this.item;
        if (purchasableBook.isSoldOut() || this.person.hasDebt() || this.person.bucketIsFull()) {
            return "not-allowed";
        }

        if (Center.describeRole(this.personId).equals("manager")) {
            return "permission-denied";
        }

        purchasableBook.sell();
        return "success";
    }

    /**
     * Handles reading a treasure book from the library.
     *
     * @return A string indicating the result of the reading operation.
     */
    public String read() {
        if (this.notFound()) {
            return "not-found";
        }

        if (!Center.isCorrectPassword(this.personId, this.password)) {
            return "invalid-pass";
        }

        if (!Center.describeRole(this.personId).equals("professor")) {
            return "permission-denied";
        }

        if (!this.item.isReadable()) {
            return "not-allowed";
        }

        Readable readableBook = (Readable) this.item;
        String fixedDate = Center.formatDate(this.date);
        String newTimeString = fixedDate + "T" + this.clock;
        LocalDateTime newTime = LocalDateTime.parse(newTimeString);
        if (!readableBook.canRead(newTime) || this.person.hasDebt() || this.person.bucketIsFull()) {
            return "not-allowed";
        }

        readableBook.read(newTime);
        return "success";
    }

    /**
     * Checks if the library, person, or item involved in the transaction exists.
     *
     * @return {@code true} if any of the entities is not found, {@code false} otherwise.
     */
    public boolean notFound() {
        if (!Center.libraryExists(this.libraryId) || !Center.personExists(this.personId)) {
            return true;
        }
        this.library = Center.getLibraries().get(this.libraryId);
        this.person = Center.getPersons().get(this.personId);

        if (!Center.bookExists(this.library, this.itemId)) {
            return true;
        }
        this.item = this.library.getItems().get(this.itemId);

        return false;
    }
}
