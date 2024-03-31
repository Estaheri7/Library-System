/**
 * The {@code Admin} class represents an administrator in the library system.
 * It extends the {@link Person} class, inheriting its attributes and methods.
 */
public class Admin extends Person {

    /**
     * Constructs a new Admin object with the specified details.
     *
     * @param personId     The ID of the administrator.
     * @param password     The password of the administrator.
     * @param name         The first name of the administrator.
     * @param lastName     The last name of the administrator.
     * @param nationalCode The national code of the administrator.
     * @param birthdate    The birthdate of the administrator.
     * @param address      The address of the administrator.
     */
    public Admin(String personId, String password, String name, String lastName, String nationalCode,
                     String birthdate, String address) {
        super(personId, password, name, lastName, nationalCode, birthdate, address, "admin");
    }
}
