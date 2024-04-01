import java.util.ArrayList;

public class LibraryTransactionHandler {
    private static ArrayList<Borrow> borrows = new ArrayList<>();

    public static String borrowItem(String[] args) {
        String personId = args[1];
        String password = args[2];
        String libraryId = args[3];
        String itemId = args[4];
        String date = args[5];
        String clock = args[6];

        if (!Center.libraryExists(libraryId) || !Center.personExists(personId)) {
            return "not-found";
        }

        Library library = Center.getLibraries().get(libraryId);
        if (!Center.bookExists(library, itemId)) {
            return "not-found";
        }

        Person person = Center.getPersons().get(personId);
        if (!Center.isCorrectPassword(personId, password)) {
            return "invalid-pass";
        }

        if (person.bucketIsFull() || person.hasDebt() || person.borrowedThisItem(itemId, libraryId)) {
            return "not-allowed";
        }

        Item item = library.getItems().get(itemId);
        if (!item.isBorrowable()) {
            return "not-allowed";
        }

        Borrowable borrowable = (Borrowable) item;
        if (!borrowable.canBorrow()) {
            return "not-allowed";
        }
        String fixedDate = Center.formatDate(date);
        Borrow borrow = new Borrow(personId, libraryId, itemId, fixedDate, clock);
        person.borrow(itemId, borrow);
        borrowable.borrow();
        return "success";
    }
}
