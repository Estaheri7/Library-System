import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * The {@code Person} class represents a person in the library system.
 * It is an abstract class that serves as the superclass for various types of persons,
 * such as administrators, managers, staff, and students.
 */
public abstract class Person {
    private String personId;
    private String password;
    private String name;
    private String lastName;
    private String nationalCode;
    private String birthdate;
    private String address;
    private String role;
    private ArrayList<Borrow> borrows = new ArrayList<>();
    private int borrowBucket = 0;
    protected long debt = 0;
    protected long totalDebt = 0;

    /**
     * Constructs a new Person object with the specified details.
     *
     * @param personId     The ID of the person.
     * @param password     The password of the person.
     * @param name         The first name of the person.
     * @param lastName     The last name of the person.
     * @param nationalCode The national code of the person.
     * @param birthdate    The birthdate of the person.
     * @param address      The address of the person.
     * @param role         The role of the person (e.g., admin, manager, staff, etc.).
     */
    public Person(String personId, String password, String name, String lastName, String nationalCode,
                  String birthdate, String address, String role) {
        this.personId = personId;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.birthdate = birthdate;
        this.address = address;
        this.role = role;
    }

    /**
     * Retrieves the ID of the person.
     *
     * @return The ID of the person.
     */
    public String getPersonId() {
        return this.personId;
    }

    /**
     * Retrieves the password of the person.
     *
     * @return The password of the person.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Retrieves the role of the person.
     *
     * @return The role of the person.
     */
    public String getRole() {
        return this.role;
    }

    /**
     * Checks if the borrow bucket is full.
     *
     * @return {@code true} if the borrow bucket is full, {@code false} otherwise.
     */
    public boolean bucketIsFull() {
        return this.borrowBucket >= 5;
    }

    /**
     * Retrieves the current borrow bucket count.
     *
     * @return The number of items currently borrowed by the person.
     */
    public int getBorrowBucket() {
        return this.borrowBucket;
    }

    /**
     * Checks if the person has any debt.
     *
     * @return {@code true} if the person has debt, {@code false} otherwise.
     */
    public boolean hasDebt() {
        return this.totalDebt != 0;
    }

    /**
     * Checks if the person has borrowed the specified item from the given library.
     *
     * @param itemId     The ID of the item.
     * @param libraryId  The ID of the library.
     * @return {@code true} if the person has borrowed the item, {@code false} otherwise.
     */
    public boolean borrowedThisItem(String itemId, String libraryId) {
        for (Borrow borrow : this.borrows) {
            if (borrow.getItemId().equals(itemId) && borrow.getLibraryId().equals(libraryId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the borrow object associated with the specified item and library, if any.
     *
     * @param itemId     The ID of the item.
     * @param libraryId  The ID of the library.
     * @return The borrow object associated with the item and library, or {@code null} if not found.
     */
    public Borrow getBorrow(String itemId, String libraryId) {
        for (Borrow borrow : this.borrows) {
            if (borrow.getItemId().equals(itemId) && borrow.getLibraryId().equals(libraryId)) {
                return borrow;
            }
        }
        return null;
    }

    public ArrayList<Borrow> getBorrows() {
        return this.borrows;
    }

    /**
     * Borrows an item and adds it to the person's borrow history.
     *
     * @param borrow The borrow object representing the item to be borrowed.
     */
    public void borrow(Borrow borrow) {
        this.borrows.add(borrow);
        this.borrowBucket++;
    }

    /**
     * Checks if the person has borrowed any items.
     *
     * @return {@code true} if the person has borrowed any items, {@code false} otherwise.
     */
    public boolean hasBorrowed() {
        return this.borrows.size() > 0;
    }

    /**
     * Checks if the person's borrowing includes debt incurred for returning a borrowed item after a specified duration.
     * The debt calculation depends on the type of item and the duration it was overdue.
     *
     * @param hourBetween The duration between the borrowing and returning of the item in hours.
     * @param item        The item for which the debt is being checked.
     * @return {@code true} if the person's borrowing includes debt, {@code false} otherwise.
     */
    public boolean includesDebt(long hourBetween, Item item) {
        if (item == null) {
            return false;
        }

        boolean isBook = item instanceof NormalBook;
        boolean isThesis = item instanceof Thesis;
        return (isBook && hourBetween > 14 * 24) || (isThesis && hourBetween > 10 * 24);
    }

    /**
     * Calculates the debt incurred for returning a borrowed item after a specified duration.
     *
     * @param borrowDateTime The date and time when the item was borrowed.
     * @param returnDateTime The date and time when the item was returned.
     * @param item           The item for which the debt is calculated.
     * @return The amount of debt incurred for the late return of the item.
     */
    public long debtForReturn(LocalDateTime borrowDateTime, LocalDateTime returnDateTime, Item item) {
        long hourBetween = ChronoUnit.HOURS.between(borrowDateTime, returnDateTime);

        if (item instanceof NormalBook) {
            if (hourBetween > 14 * 24) {
                this.debt += (hourBetween - 14 * 24) * 100;
                this.totalDebt += (hourBetween - 14 * 24) * 100;
            }
        } else if (item instanceof Thesis) {
            if (hourBetween > 10 * 24) {
                this.debt += (hourBetween - 10 * 14) * 100;
                this.totalDebt += (hourBetween - 10 * 14) * 100;
            }
        }

        return debt;
    }

    /**
     * Returns a borrowed item and removes it from the person's borrow history.
     *
     * @param borrow The borrow object representing the item to be returned.
     */
    public void returnItem(Borrow borrow) {
        this.borrows.remove(borrow);
        this.debt = 0;
        this.borrowBucket--;
    }

    /**
     * Searches for a keyword within the person's name or last name.
     *
     * @param searchKey The keyword to search for.
     * @return {@code true} if the keyword is found in the person's name or last name, {@code false} otherwise.
     */
    public boolean search(String searchKey) {
        return this.name.toLowerCase().contains(searchKey.toLowerCase()) ||
                this.lastName.toLowerCase().contains(searchKey.toLowerCase());
    }
}
