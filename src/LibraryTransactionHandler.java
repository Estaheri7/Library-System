import java.security.cert.CertificateEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
    
    public LibraryTransactionHandler(String personId, String password, String libraryId, String itemId,
                                     String date, String clock) {
        this.personId = personId;
        this.password = password;
        this.libraryId = libraryId;
        this.itemId = itemId;
        this.date = date;
        this.clock = clock;
    }

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
        PurchasableBook purchasableBook = (PurchasableBook) this.item;
        if (purchasableBook.isSoldOut() || this.person.hasDebt() || this.person.bucketIsFull()) {
            return "not-allowed";
        }

        if (Center.describeRole(this.personId).equals("manager")) {
            return "permission-denied";
        }

        purchasableBook.sell();
        return "success";
    }

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

        if (!this.item.isTreasure()) {
            return "not-allowed";
        }

        TreasureBook treasureBook = (TreasureBook) this.item;
        String fixedDate = Center.formatDate(this.date);
        String newTimeString = fixedDate + "T" + this.clock;
        LocalDateTime newTime = LocalDateTime.parse(newTimeString);
        if (!treasureBook.isReadable(newTime) || this.person.hasDebt() || this.person.bucketIsFull()) {
            return "not-allowed";
        }

        treasureBook.read(newTime);
        return "success";
    }

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
