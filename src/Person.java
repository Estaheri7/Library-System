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
    private long debt = 0;

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

    public boolean bucketIsFull() {
        return this.borrowBucket >= 5;
    }

    public int getBorrowBucket() {
        return this.borrowBucket;
    }

    public boolean hasDebt() {
        return this.debt != 0;
    }

    public boolean borrowedThisItem(String itemId, String libraryId) {
        for (Borrow borrow : this.borrows) {
            if (borrow.getItemId().equals(itemId) && borrow.getLibraryId().equals(libraryId)) {
                return true;
            }
        }
        return false;
    }

    public void borrow(Borrow borrow) {
        this.borrows.add(borrow);
        this.borrowBucket++;
    }

    public boolean hasBorrowed() {
        return this.borrows.size() > 0;
    }

    public long debtForReturn(LocalDateTime borrowDateTime, LocalDateTime returnDateTime, Item item) {
        long hourBetween = ChronoUnit.HOURS.between(borrowDateTime, returnDateTime);

        if (item instanceof NormalBook) {
            if (hourBetween > 14 * 24) {
                this.debt += (hourBetween - 14 * 24) * 100;
            }
        } else if (item instanceof Thesis) {
            if (hourBetween > 10 * 24) {
                this.debt += (hourBetween - 10 * 14) * 100;
            }
        }

        return debt;
    }

    public void returnItem(Borrow borrow) {
        this.borrows.remove(borrow);
        this.borrowBucket--;
    }
}
