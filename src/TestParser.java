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
        } else if (command[0].equals("add-comment")) {
            System.out.println(Center.addComment(command));
        } else if (command[0].equals("search")) {
            SearchHandler searchHandler = new SearchHandler(command[1]);
            System.out.println(searchHandler.searchBook());
        } else if (command[0].equals("search-user")) {
            SearchHandler searchHandler = new SearchHandler(command[3]);
            System.out.println(searchHandler.searchUser(command[1], command[2]));
        } else if (command[0].equals("category-report")) {
            ReportHandler reportHandler = new ReportHandler(command[1], command[2]);
            System.out.println(reportHandler.categoryReport(command[3], command[4]));
        } else if (command[0].equals("library-report")) {
            ReportHandler reportHandler = new ReportHandler(command[1], command[2]);
            System.out.println(reportHandler.libraryReport(command[3]));
        } else if (command[0].equals("report-passed-deadline")) {
            ReportHandler reportHandler = new ReportHandler(command[1], command[2]);
            System.out.println(reportHandler.reportPassedDeadline(command[3], command[4], command[5]));
        } else if (command[0].equals("report-penalties-sum")) {
            ReportHandler reportHandler = new ReportHandler(command[1], command[2]);
            System.out.println(reportHandler.reportPenaltiesSum());
        }
    }
}
