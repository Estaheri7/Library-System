import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * The {@code Student} class represents a student in the library system.
 * It extends the {@link Person} class, inheriting its attributes and methods.
 */
public class Student extends Person {

    /**
     * Constructs a new Student object with the specified details.
     *
     * @param personId     The ID of the student.
     * @param password     The password of the student.
     * @param name         The first name of the student.
     * @param lastName     The last name of the student.
     * @param nationalCode The national code of the student.
     * @param birthdate    The birthdate of the student.
     * @param address      The address of the student.
     */
    public Student(String personId, String password, String name, String lastName, String nationalCode,
                  String birthdate, String address) {
        super(personId, password, name, lastName, nationalCode, birthdate, address, "student");
    }

    /**
     * Checks if the borrow bucket is full.
     *
     * @return {@code true} if the borrow bucket is full, {@code false} otherwise.
     */
    @Override
    public boolean bucketIsFull() {
        return this.getBorrowBucket() >= 3;
    }

    /**
     * Checks if the student's borrow includes debt based on the elapsed time and the item type.
     *
     * @param hourBetween The number of hours between the borrow time and the return time.
     * @param item        The item being borrowed.
     * @return {@code true} if the borrow includes debt, {@code false} otherwise.
     */
    @Override
    public boolean includesDebt(long hourBetween, Item item) {
        if (item == null) {
            return false;
        }

        boolean isBook = item instanceof NormalBook;
        boolean isThesis = item instanceof Thesis;
        return (isBook && hourBetween > 10 * 24) || (isThesis && hourBetween > 7 * 24);
    }

    /**
     * Calculates the debt incurred for returning an item after borrowing.
     *
     * @param borrowDateTime  The date and time when the item was borrowed.
     * @param returnDateTime  The date and time when the item was returned.
     * @param item            The item being returned.
     * @return                The calculated debt for returning the item.
     */
    @Override
    public long debtForReturn(LocalDateTime borrowDateTime, LocalDateTime returnDateTime, Item item) {
        long hourBetween = ChronoUnit.HOURS.between(borrowDateTime, returnDateTime);

        if (item instanceof NormalBook) {
            if (hourBetween > 10 * 24) {
                this.debt += (hourBetween - 10 * 24) * 50;
                this.totalDebt += (hourBetween - 10 * 24) * 50;
            }
        } else if (item instanceof Thesis) {
            if (hourBetween > 7 * 24) {
                this.debt += (hourBetween - 7 * 14) * 50;
                this.totalDebt += (hourBetween - 7 * 14) * 50;
            }
        }

        return debt;
    }
}
