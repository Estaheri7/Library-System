public class Student extends Person {
    public Student(String personId, String password, String name, String lastName, String nationalCode,
                  String birthdate, String address) {
        super(personId, password, name, lastName, nationalCode, birthdate, address, "student");
    }
}
