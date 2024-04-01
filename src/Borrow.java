import java.time.LocalDateTime;

public class Borrow {
    private String userId;
    private String libraryId;
    private String itemId;
    private LocalDateTime borrowTime;

    public Borrow(String userId, String libraryId, String itemId, String date, String clock) {
        this.userId = userId;
        this.libraryId = libraryId;
        this.itemId = itemId;
        String fullDate = date + "T" + clock;
        this.borrowTime = LocalDateTime.parse(fullDate);
    }

    public String getLibraryId() {
        return this.libraryId;
    }
}
