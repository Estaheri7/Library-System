import java.util.ArrayList;
import java.util.HashMap;

/**
 * The {@code Center} class represents the central management system of the library.
 * It manages persons (users), libraries, categories, and resources (items).
 */
public class Center {
    private final static HashMap<String, Person> persons = new HashMap<>();
    private final static HashMap<String, Library> libraries = new HashMap<>();
    private final static HashMap<String, Category> categories = new HashMap<>();

    /**
     * Initializes the administrator account.
     */
    public static void initialAdmin() {
        Admin admin = new Admin("admin", "AdminPass", "admin", "admin", "123", "10000", "test");
        persons.put("admin", admin);
    }

    /**
     * Adds a new library to the system.
     * @param args An array of arguments containing details of the library to be added.
     * @return A string indicating the result of the operation.
     */
    public static String addLibrary(String[] args) {
        String personId = args[1];
        String personPassword = args[2];
        String libraryId = args[3];
        String name = args[4];
        String year = args[5];
        int numberOfTables = Integer.parseInt(args[6]);
        String address = args[7];

        if (!personExists(personId)) {
            return "not-found";
        }

        if (!describeRole(personId).equals("admin")) {
            return "permission-denied";
        }

        if (!isCorrectPassword(personId, personPassword)) {
            return "invalid-pass";
        }

        if (libraryExists(libraryId)) {
            return "duplicate-id";
        }

        Library library = new Library(libraryId, name, year, numberOfTables, address);
        libraries.put(libraryId, library);
        return "success";
    }

    /**
     * Adds a new category to the system.
     * @param args An array of arguments containing details of the category to be added.
     * @return A string indicating the result of the operation.
     */
    public static String addCategory(String[] args) {
        String personId = args[1];
        String personPassword = args[2];
        String categoryId = args[3];
        String categoryName = args[4];
        String superCategory = args[5];

        if (!personExists(personId)) {
            return "not-found";
        }

        if (!describeRole(personId).equals("admin")) {
            return "permission-denied";
        }

        if (!isCorrectPassword(personId, personPassword)) {
            return "invalid-pass";
        }

        if (categoryExists(categoryId)) {
            return "duplicate-id";
        }

        if (!superCategory.equals("null") && !categoryExists(superCategory)) {
            return "not-found";
        }

        Category category = new Category(categoryId, categoryName, superCategory);
        categories.put(categoryId, category);
        return "success";
    }

    /**
     * Adds a new person (user) to the system.
     * @param args An array of arguments containing details of the person to be added.
     * @return A string indicating the result of the operation.
     */
    public static String addPerson(String[] args) {
        String[] method = args[0].split("-");
        String adminId = args[1];
        String adminPassword = args[2];
        String personId = args[3];
        String personPassword = args[4];
        String name = args[5];
        String lastName = args[6];
        String nationalCode = args[7];
        String birthdate = args[8];
        String address = args[9];

        if (!personExists(adminId)) {
            return "not-found";
        }

        if (!describeRole(adminId).equals("admin")) {
            return "permission-denied";
        }

        if (!isCorrectPassword(adminId, adminPassword)) {
            return "invalid-pass";
        }

        if (personExists(personId)) {
            return "duplicate-id";
        }

        String extraAttribute = "";
        if (args.length == 11) {
            extraAttribute = args[10];
        }

        if (method[1].equals("student")) {
            addStudent(personId, personPassword, name, lastName, nationalCode,
                    birthdate, address);
        } else if (method[1].equals("staff")) {
            addStaff(personId, personPassword, name, lastName, nationalCode,
                    birthdate, address, extraAttribute);
        } else if (method[1].equals("manager")) {
            if (!libraryExists(extraAttribute)) {
                return "not-found";
            }
            addManager(personId, personPassword, name, lastName, nationalCode,
                    birthdate, address, extraAttribute);
        }
        return "success";
    }

    /**
     * Removes a person (user) from the system.
     * @param args An array of arguments containing details of the person to be removed.
     * @return A string indicating the result of the operation.
     */
    // TODO -> not-allowed statement
    public static String removePerson(String[] args) {
        String adminId = args[1];
        String adminPassword = args[2];
        String personId = args[3];

        if (!personExists(adminId)) {
            return "not-found";
        }

        if (!describeRole(adminId).equals("admin")) {
            return "permission-denied";
        }

        if (!isCorrectPassword(adminId, adminPassword)) {
            return "invalid-pass";
        }

        if (!personExists(personId)) {
            return "not-found";
        }

        persons.remove(personId);
        return "success";
    }

    /**
     * Adds a new student to the system.
     * @param args An array of arguments containing details of the student to be added.
     *             The arguments include studentId, studentPassword, name, lastName,
     *             nationalCode, birthdate, and address.
     */
    private static void addStudent(String... args) {
        Student student = new Student(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
        persons.put(args[0], student);
    }

    /**
     * Adds a new staff member to the system.
     * @param args An array of arguments containing details of the staff member to be added.
     *             The arguments include staffId, staffPassword, name, lastName,
     *             nationalCode, birthdate, address, and role (staff or professor).
     */
    private static void addStaff(String... args) {
        String role = args[7];
        if (role.equals("staff")) {
            Staff staff = new Staff(args[0], args[1], args[2], args[3], args[4], args[5],
                    args[6], args[7]);
            persons.put(args[0], staff);
        } else if (role.equals("professor")) {
            Professor professor = new Professor(args[0], args[1], args[2], args[3], args[4], args[5],
                    args[6], args[7]);
            persons.put(args[0], professor);
        }
    }

    /**
     * Adds a new manager to the system.
     * @param args An array of arguments containing details of the manager to be added.
     *             The arguments include managerId, managerPassword, name, lastName,
     *             nationalCode, birthdate, address, and libraryId.
     */
    private static void addManager(String... args) {
        Manager manager = new Manager(args[0], args[1], args[2], args[3], args[4], args[5],
                args[6], args[7]);
        persons.put(args[0], manager);
    }

    /**
     * Adds a resource (item) to the system.
     * @param item The item to be added.
     * @param personId The ID of the person (user) performing the operation.
     * @param password The password of the person performing the operation.
     * @return A string indicating the result of the operation.
     */
    public static String addResource(Item item, String personId, String password) {
        if (!categoryExists(item.getCategoryId()) && !item.getCategoryId().equals("null")) {
            return "not-found";
        }
        if (!libraryExists(item.getLibraryId())) {
            return "not-found";
        }
        if (!personExists(personId)) {
            return "not-found";
        }

        Library library = libraries.get(item.getLibraryId());

        if (!isLibraryManager(personId, library.getLibraryId())) {
            return "permission-denied";
        }

        if (!isCorrectPassword(personId, password)) {
            return "invalid-pass";
        }

        if (bookExists(library, item.getItemId())) {
            return "duplicate-id";
        }

        library.addItem(item);
        return "success";
    }

    /**
     * Removes a resource (item) from the system.
     * @param args An array of arguments containing details of the resource to be removed.
     *             The arguments include personId, personPassword, resourceId, and libraryId.
     * @return A string indicating the result of the operation.
     */
    // TODO - not-allowed statement.
    public static String removeResource(String[] args) {
        if (!libraryExists(args[4])) {
            return "not-found";
        }

        Library library = libraries.get(args[4]);
        if (!bookExists(library, args[3])) {
            return "not-found";
        }

        if (!personExists(args[1])) {
            return "not-found";
        }

        if (!isLibraryManager(args[1], args[4])) {
            return "permission-denied";
        }

        if (!isCorrectPassword(args[1], args[2])) {
            return "invalid-pass";
        }

        library.removeItem(args[3]);
        return "success";
    }

    /**
     * Adds a new normal book to the system.
     * @param args An array of arguments containing details of the book to be added.
     *             The arguments include personId, personPassword, bookId, title, author,
     *             publisher, year, copies, categoryId, and libraryId.
     * @return A string indicating the result of the operation.
     */
    public static String addBook(String[] args) {
        NormalBook book = new NormalBook(args[3], args[4], args[5], args[6], args[7], Integer.parseInt(args[8]),
                args[9], args[10]);
        return addResource(book, args[1], args[2]);
    }

    /**
     * Adds a new thesis to the system.
     * @param args An array of arguments containing details of the thesis to be added.
     *             The arguments include personId, personPassword, thesisId, title, author,
     *             supervisor, year, categoryId, and libraryId.
     * @return A string indicating the result of the operation.
     */
    public static String addThesis(String[] args) {
        Thesis thesis = new Thesis(args[3], args[4], args[5], args[6], args[7], args[8],
                args[9]);
        return addResource(thesis, args[1], args[2]);
    }

    /**
     * Adds a new Ganjineh (treasure book) to the system.
     * @param args An array of arguments containing details of the Ganjineh to be added.
     *             The arguments include personId, personPassword, ganjinehId, title, author,
     *             publisher, year, donateName, categoryId, and libraryId.
     * @return A string indicating the result of the operation.
     */
    public static String addGanjineh(String[] args) {
        TreasureBook tBook = new TreasureBook(args[3], args[4], args[5], args[6], args[7], args[8],
                args[9], args[10]);
        return addResource(tBook, args[1], args[2]);
    }

    /**
     * Adds a new selling book to the system.
     * @param args An array of arguments containing details of the selling book to be added.
     *             The arguments include personId, personPassword, sellingBookId, title, author,
     *             publisher, year, copies, price, discount, categoryId, and libraryId.
     * @return A string indicating the result of the operation.
     */
    public static String addSellingBook(String[] args) {
        PurchasableBook pBook = new PurchasableBook(args[3], args[4], args[5], args[6], args[7], Integer.parseInt(args[8]),
                args[9], args[10], args[11], args[12]);
        return addResource(pBook, args[1], args[2]);
    }

    /**
     * Checks if a library with the specified key exists in the system.
     * @param key The ID of the library to check.
     * @return {@code true} if the library exists, {@code false} otherwise.
     */
    public static boolean libraryExists(String key) {
        return libraries.containsKey(key);
    }

    /**
     * Checks if a category with the specified key exists in the system.
     * @param key The ID of the category to check.
     * @return {@code true} if the category exists, {@code false} otherwise.
     */
    private static boolean categoryExists(String key) {
        return categories.containsKey(key);
    }

    /**
     * Checks if a person with the specified key exists in the system.
     * @param key The ID of the person to check.
     * @return {@code true} if the person exists, {@code false} otherwise.
     */
    public static boolean personExists(String key) {
        return persons.containsKey(key);
    }

    /**
     * Retrieves the role of a person with the specified ID.
     * @param personId The ID of the person.
     * @return The role of the person as a {@code String}.
     */
    private static String describeRole(String personId) {
        return persons.get(personId).getRole();
    }

    /**
     * Checks if the provided password matches the password of the specified person.
     * @param personId The ID of the person.
     * @param password The password to check.
     * @return {@code true} if the password is correct, {@code false} otherwise.
     */
    public static boolean isCorrectPassword(String personId, String password) {
        return persons.get(personId).getPassword().equals(password);
    }

    /**
     * Checks if a person with the specified ID is a manager of the specified library.
     * @param personId The ID of the person.
     * @param libraryId The ID of the library.
     * @return {@code true} if the person is a manager of the library, {@code false} otherwise.
     */
    private static boolean isLibraryManager(String personId, String libraryId) {
        if (persons.get(personId) instanceof Manager) {
            Manager manager = (Manager)persons.get(personId);
            return manager.getLibraryId().equals(libraryId);
        }
        return false;
    }

    /**
     * Checks if a book with the specified item ID exists in the provided library.
     * @param library The library to search for the book.
     * @param itemId The ID of the book to check.
     * @return {@code true} if the book exists, {@code false} otherwise.
     */
    public static boolean bookExists(Library library, String itemId) {
        HashMap<String, Item> books = library.getItems();
        return books.containsKey(itemId);
    }

    public static HashMap<String, Person> getPersons() {
        return persons;
    }

    public static HashMap<String, Library> getLibraries() {
        return libraries;
    }

    public static HashMap<String, Category> getCategories() {
        return categories;
    }
}
