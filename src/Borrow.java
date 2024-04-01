import java.time.LocalDateTime;

public class Borrow {
    private String personId;
    private String libraryId;
    private String itemId;
    private LocalDateTime borrowTime;

    public Borrow(String personId, String libraryId, String itemId, String date, String clock) {
        this.personId = personId;
        this.libraryId = libraryId;
        this.itemId = itemId;
        String fullDate = date + "T" + clock;
        this.borrowTime = LocalDateTime.parse(fullDate);
    }

    public String getLibraryId() {
        return this.libraryId;
    }

    public String getItemId() {
        return this.itemId;
    }
}
