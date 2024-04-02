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
        } else if (command[0].equals("add-book")) {
            System.out.println(Center.addBook(command));
        } else if (command[0].equals("add-thesis")) {
            System.out.println(Center.addThesis(command));
        } else if (command[0].equals("add-ganjineh-book")) {
            System.out.println(Center.addGanjineh(command));
        } else if (command[0].equals("add-selling-book")) {
            System.out.println(Center.addSellingBook(command));
        } else if (command[0].equals("remove-resource")) {
            System.out.println(Center.removeResource(command));
        } else if (command[0].equals("borrow")) {
            LibraryTransactionHandler libraryTransactionHandler = new LibraryTransactionHandler(command[1],
                    command[2], command[3], command[4], command[5], command[6]);
            System.out.println(libraryTransactionHandler.borrowItem());
        } else if (command[0].equals("return")) {
            LibraryTransactionHandler libraryTransactionHandler = new LibraryTransactionHandler(command[1],
                    command[2], command[3], command[4], command[5], command[6]);
            System.out.println(libraryTransactionHandler.returnItem());
        } else if (command[0].equals("buy")) {
            LibraryTransactionHandler libraryTransactionHandler = new LibraryTransactionHandler(command[1],
                    command[2], command[3], command[4], "-", "-");
            System.out.println(libraryTransactionHandler.buy());
        } else if (command[0].equals("read")) {
            LibraryTransactionHandler libraryTransactionHandler = new LibraryTransactionHandler(command[1],
                    command[2], command[3], command[4], command[5], command[6]);
            System.out.println(libraryTransactionHandler.read());
        }
    }
}
