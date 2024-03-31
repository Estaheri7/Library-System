/**
 * The {@code Staff} class represents a staff member in the library system.
 * It extends the {@link Person} class, inheriting its attributes and methods.
 */
public class Staff extends Person {

    /**
     * Constructs a new Staff object with the specified details.
     *
     * @param personId     The ID of the staff member.
     * @param password     The password of the staff member.
     * @param name         The first name of the staff member.
     * @param lastName     The last name of the staff member.
     * @param nationalCode The national code of the staff member.
     * @param birthdate    The birthdate of the staff member.
     * @param address      The address of the staff member.
     * @param role         The role of the staff member (e.g., staff, professor, etc.).
     */
    public Staff(String personId, String password, String name, String lastName, String nationalCode,
                   String birthdate, String address, String role) {
        super(personId, password, name, lastName, nationalCode, birthdate, address, role);
    }
}
