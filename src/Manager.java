/**
 * The {@code Manager} class represents a manager in the library system.
 * It extends the {@link Person} class, inheriting its attributes and methods.
 */
public class Manager extends Person {
    private String libraryId;

    /**
     * Constructs a new Manager object with the specified details.
     *
     * @param personId     The ID of the manager.
     * @param password     The password of the manager.
     * @param name         The first name of the manager.
     * @param lastName     The last name of the manager.
     * @param nationalCode The national code of the manager.
     * @param birthdate    The birthdate of the manager.
     * @param address      The address of the manager.
     * @param libraryId    The ID of the library managed by the manager.
     */
    public Manager(String personId, String password, String name, String lastName, String nationalCode,
                     String birthdate, String address, String libraryId) {
        super(personId, password, name, lastName, nationalCode, birthdate, address, "manager");
        this.libraryId = libraryId;
    }

    /**
     * Retrieves the ID of the library managed by the manager.
     *
     * @return The ID of the library.
     */
    public String getLibraryId() {
        return this.libraryId;
    }
}
