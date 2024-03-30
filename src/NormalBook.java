public class NormalBook extends Item {
    private String publisher;

    public NormalBook(String itemId, String title, String authorName, String publisher, String year,
                      int copies, String categoryId, String libraryId) {
        super(itemId, title, authorName, year, copies, categoryId, libraryId);
        this.publisher = publisher;
    }
}
