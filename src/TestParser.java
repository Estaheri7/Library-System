public class TestParser {
    public void parse(String[] command) {
        Center.initialAdmin();
        if (command[0].equals("add-library")) {
            Center.addLibrary(command);
        } else if (command[0].equals("add-category")) {
            Center.addCategory(command);
        } else if (command[0].equals("add-student") || command[0].equals("add-staff") || command[0].equals("add-manager")) {
            Center.addPerson(command);
        } else if (command[0].equals("remove-user")) {
            Center.removePerson(command);
        }
    }
}
