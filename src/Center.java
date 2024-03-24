import java.util.ArrayList;
import java.util.HashMap;

public class Center {
    private final static HashMap<String, Person> persons = new HashMap<>();
    private final static HashMap<String, Library> libraries = new HashMap<>();
    private final static HashMap<String, Category> categories = new HashMap<>();

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

        if (libraryExists(libraryId)) {
            return "duplicate-id";
        }

        if (!describeRole(personId).equals("admin")) {
            return "permission-denied";
        }

        if (!isCorrectPassword(personId, personPassword)) {
            return "invalid-pass";
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

        if (categoryExists(categoryId)) {
            return "duplicate-id";
        }

        if (!superCategory.equals("null") && !categoryExists(superCategory)) {
            return "not-found";
        }

        if (!describeRole(personId).equals("admin")) {
            return "permission-denied";
        }

        if (!isCorrectPassword(personId, personPassword)) {
            return "invalid-pass";
        }

        Category category = new Category(categoryId, categoryName, superCategory);
        categories.put(categoryId, category);
        return "success";
    }

    public static String addStudent(String[] args) {
        String adminId = args[1];
        String adminPassword = args[2];
        String studentId = args[3];
        String studentPassword = args[4];
        String name = args[5];
        String lastName = args[6];
        String nationalCode = args[7];
        String birthdate = args[8];
        String address = args[9];

        if (personExists(studentId)) {
            return "duplicate-id";
        }

        if (!personExists(adminId)) {
            return "not-found";
        }

        if (!describeRole(adminId).equals("admin")) {
            return "permission-denied";
        }

        if (!isCorrectPassword(adminId, adminPassword)) {
            return "invalid-pass";
        }

        Student student = new Student(studentId, studentPassword, name, lastName, nationalCode, birthdate, address);
        persons.put(studentId, student);
        return "success";
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
