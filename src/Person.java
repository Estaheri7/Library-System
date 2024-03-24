public abstract class Person {
    private String personId;
    private String password;
    private String name;
    private String lastName;
    private String nationalCode;
    private String birthdate;
    private String address;
    private String role;

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

    public String getRole() {
        return this.role;
    }
}
