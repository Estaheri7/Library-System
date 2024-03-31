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
}
