public class Library {
    private String libraryId;
    private String name;
    private String establishedYear;
    private int numberOfTables;
    private String address;

    public Library(String libraryId, String name, String establishedYear, int numberOfTables, String address) {
        this.libraryId = libraryId;
        this.name = name;
        this.establishedYear = establishedYear;
        this.numberOfTables = numberOfTables;
        this.address = address;
    }
}
