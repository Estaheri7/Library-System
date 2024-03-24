public class Manager extends Person {
    private String libraryId;
    public Manager(String personId, String password, String name, String lastName, String nationalCode,
                     String birthdate, String address, String libraryId) {
        super(personId, password, name, lastName, nationalCode, birthdate, address, "manager");
        this.libraryId = libraryId;
    }
}
