public abstract class Item {
    private String itemId;
    private String title;
    private String authorName;
    private String year;
    private int copies;
    private int remainder;
    private String categoryId;
    private String libraryId;

    public Item(String itemId, String title, String authorName, String year, int copies,
                String categoryId, String libraryId) {
        this.itemId = itemId;
        this.title = title;
        this.authorName = authorName;
        this.year = year;
        this.copies = copies;
        this.remainder = copies;
        this.categoryId = categoryId;
        this.libraryId = libraryId;
    }

    public String getItemId() {
        return this.itemId;
    }
}
