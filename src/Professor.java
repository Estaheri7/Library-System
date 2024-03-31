/**
 * The {@code Professor} class represents a professor in the library system.
 * It extends the {@link Person} class, inheriting its attributes and methods.
 */
public class Professor extends Person {

    /**
     * Constructs a new Professor object with the specified details.
     *
     * @param personId     The ID of the professor.
     * @param password     The password of the professor.
     * @param name         The first name of the professor.
     * @param lastName     The last name of the professor.
     * @param nationalCode The national code of the professor.
     * @param birthdate    The birthdate of the professor.
     * @param address      The address of the professor.
     * @param role         The role of the professor (e.g., admin, manager, staff, etc.).
     */
    public Professor(String personId, String password, String name, String lastName, String nationalCode,
                 String birthdate, String address, String role) {
        super(personId, password, name, lastName, nationalCode, birthdate, address, role);
    }
}
