public class Admin extends Person {
    public Admin(String personId, String password, String name, String lastName, String nationalCode,
                     String birthdate, String address) {
        super(personId, password, name, lastName, nationalCode, birthdate, address, "admin");
    }
}
