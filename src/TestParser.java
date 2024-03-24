public class TestParser {
    public void parse(String[] command) {
        Center.initialAdmin();
        if (command[0].equals("add-library")) {
            System.out.println(Center.addLibrary(command));
        } else if (command[0].equals("add-category")) {
            System.out.println(Center.addCategory(command));
        } else if (command[0].equals("add-student") || command[0].equals("add-staff") || command[0].equals("add-manager")) {
            System.out.println(Center.addPerson(command));
        } else if (command[0].equals("remove-user")) {
            System.out.println(Center.removePerson(command));
        }
    }
}
