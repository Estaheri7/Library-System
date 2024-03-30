import java.util.ArrayList;
import java.util.HashMap;

public class Center {
    private final static HashMap<String, Person> persons = new HashMap<>();
    private final static HashMap<String, Library> libraries = new HashMap<>();
    private final static HashMap<String, Category> categories = new HashMap<>();

    public static void initialAdmin() {
        Admin admin = new Admin("admin", "AdminPass", "admin", "admin", "123", "10000", "test");
        persons.put("admin", admin);
    }

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

    private static void addStudent(String... args) {
        Student student = new Student(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
        persons.put(args[0], student);
    }

    public static String addBook(String[] args) {
        NormalBook book = new NormalBook(args[3], args[4], args[5], args[6], args[7], Integer.parseInt(args[8]),
                args[9], args[10]);
        return null;
    }

    public static String addThesis(String[] args) {
        Thesis thesis = new Thesis(args[3], args[4], args[5], args[6], args[7], args[8],
                args[9]);
        return null;
    }

    public static String addGanjineh(String[] args) {
        TreasureBook tBook = new TreasureBook(args[3], args[4], args[5], args[6], args[7], args[8],
                args[9], args[10]);
        return null;
    }
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

    private static void addManager(String... args) {
        Manager manager = new Manager(args[0], args[1], args[2], args[3], args[4], args[5],
                args[6], args[7]);
        persons.put(args[0], manager);
    }

    private static boolean libraryExists(String key) {
        return libraries.containsKey(key);
    }

    private static boolean categoryExists(String key) {
        return categories.containsKey(key);
    }

    private static boolean personExists(String key) {
        return persons.containsKey(key);
    }

    private static String describeRole(String personId) {
        return persons.get(personId).getRole();
    }

    private static boolean isCorrectPassword(String personId, String password) {
        return persons.get(personId).getPassword().equals(password);
    }
}
