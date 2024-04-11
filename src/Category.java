/**
 * The {@code Category} class represents a category in the library system.
 * Categories are used to classify items within the library.
 */
public class Category {
    private String categoryId;
    private String name;
    private String superCategory;

    /**
     * Constructs a new Category object with the specified details.
     *
     * @param categoryId    The unique ID of the category.
     * @param name          The name of the category.
     * @param superCategory The ID of the super category if this category is a subcategory,
     *                      otherwise set to {@code null}.
     */
    public Category(String categoryId, String name, String superCategory) {
        this.categoryId = categoryId;
        this.name = name;
        this.superCategory = superCategory;
    }

    /**
     * Retrieves the unique ID of the category.
     *
     * @return The ID of the category.
     */
    public String getCategoryId() {
        return this.categoryId;
    }

    /**
     * Retrieves the ID of the super category if this category is a subcategory.
     * If the category is not a subcategory, returns {@code null}.
     *
     * @return The ID of the super category, or {@code null} if the category is not a subcategory.
     */
    public String getSuperCategory() {
        return this.superCategory;
    }
}
