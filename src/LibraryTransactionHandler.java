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

        Library library = Center.getLibraries().get(this.libraryId);
        Person person = Center.getPersons().get(this.personId);
        if (!Center.isCorrectPassword(this.personId, this.password)) {
            return "invalid-pass";
        }

        if (person.bucketIsFull() || person.hasDebt() || person.borrowedThisItem(this.itemId, this.libraryId)) {
            return "not-allowed";
        }

        Item item = library.getItems().get(this.itemId);
        if (!item.isBorrowable()) {
            return "not-allowed";
        }

        Borrowable borrowable = (Borrowable) item;
        if (!borrowable.canBorrow()) {
            return "not-allowed";
        }
        String fixedDate = Center.formatDate(this.date);
        Borrow borrow = new Borrow(this.personId, this.libraryId, this.itemId, fixedDate, this.clock);
        person.borrow(borrow);
        borrowable.borrow();
        return "success";
    }

    public String returnItem() {
        if(this.notFound()) {
            return "not-found";
        }

        Library library = Center.getLibraries().get(this.libraryId);
        Person person = Center.getPersons().get(this.personId);
        if (!person.borrowedThisItem(this.itemId, this.libraryId)) {
            return "not-found";
        }

        if (!Center.isCorrectPassword(this.personId, this.password)) {
            return "invalid-pass";
        }

        Item item = library.getItems().get(this.itemId);
        Borrow borrow = person.getBorrow(this.itemId, this.libraryId);
        String fixedDate = Center.formatDate(this.date);
        String fullDateTime = fixedDate + "T" + this.clock;
        LocalDateTime returnDateTime = LocalDateTime.parse(fullDateTime);
        long debt = person.debtForReturn(borrow.getBorrowTime(), returnDateTime, item);
        person.returnItem(borrow);
        Borrowable borrowableItem = (Borrowable) item;
        borrowableItem.returnItem();
        if (debt == 0) {
            return "success";
        } else {
            return "" + debt;
        }
    }

    public boolean notFound() {
        if (!Center.libraryExists(this.libraryId) || !Center.personExists(this.personId)) {
            return true;
        }

        Library library = Center.getLibraries().get(this.libraryId);
        if (!Center.bookExists(library, this.itemId)) {
            return true;
        }

        return false;
    }
}
