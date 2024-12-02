import java.util.*;

public class Main {
    private static List<Book> books = new ArrayList<>();
    private static List<LibraryPatron> patrons = new ArrayList<>();
    private static Set<Book> checkedOutBooks = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        loadBooks();

        int choice;
        do {
            System.out.println("\nLibrary Menu:");
            System.out.println("1. Add Library Patron");
            System.out.println("2. Check Out Book");
            System.out.println("3. Check In Book");
            System.out.println("4. Print All Library Patrons and Checked-Out Books");
            System.out.println("5. Print Available Books");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addLibraryPatron(scanner);
                    break;
                case 2:
                    checkOutBook(scanner);
                    break;
                case 3:
                    checkInBook(scanner);
                    break;
                case 4:
                    printAllPatrons();
                    break;
                case 5:
                    printAvailableBooks();
                    break;
                case 6:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void loadBooks() {
        books.add(new Book("Computer Science", 1));
        books.add(new Book("C++ For Engineers and Scientists", 1));
        books.add(new Book("Psychology", 0));
        books.add(new Book("Personality Study Guide", 1));
        books.add(new Book("The Passage", 1));
        books.add(new Book("Thinking in Java", 1));
        System.out.println("Books loaded.");
    }

    private static void addLibraryPatron(Scanner scanner) {
        System.out.print("Enter patron name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patron library ID: ");
        String id = scanner.nextLine();

        for (LibraryPatron patron : patrons) {
            if (patron.getId().equals(id)) {
                System.out.println("Patron already exists!");
                return;
            }
        }

        patrons.add(new LibraryPatron(name, id));
        Collections.sort(patrons);
        System.out.println("Patron added successfully.");
    }

    private static void checkOutBook(Scanner scanner) {
        System.out.print("Enter patron name: ");
        String name = scanner.nextLine();
        LibraryPatron patron = findOrCreatePatron(name, scanner);

        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.getQuantity() > 0 && !checkedOutBooks.contains(book)) {
                patron.checkOutBook(book);
                checkedOutBooks.add(book);
                book.decrementQuantity();
                System.out.println("Book checked out successfully.");
                return;
            }
        }
        System.out.println("Book not found or unavailable.");
    }

    private static void checkInBook(Scanner scanner) {
        System.out.print("Enter patron name: ");
        String name = scanner.nextLine();
        LibraryPatron patron = findPatronByName(name);

        if (patron == null) {
            System.out.println("Patron not found.");
            return;
        }

        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        for (Book book : checkedOutBooks) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                patron.checkInBook(book);
                checkedOutBooks.remove(book);
                book.incrementQuantity();
                System.out.println("Book checked in successfully.");
                return;
            }
        }
        System.out.println("Book not found in checked-out list.");
    }

    private static void printAllPatrons() {
        for (LibraryPatron patron : patrons) {
            patron.printInfo();
        }
    }

    private static void printAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (!checkedOutBooks.contains(book) && book.getQuantity() > 0) {
                System.out.println("- " + book.getTitle());
            }
        }
    }

    private static LibraryPatron findOrCreatePatron(String name, Scanner scanner) {
        for (LibraryPatron patron : patrons) {
            if (patron.getName().equalsIgnoreCase(name)) {
                return patron;
            }
        }
        System.out.print("Enter library ID for new patron: ");
        String id = scanner.nextLine();
        LibraryPatron newPatron = new LibraryPatron(name, id);
        patrons.add(newPatron);
        Collections.sort(patrons);
        return newPatron;
    }

    private static LibraryPatron findPatronByName(String name) {
        for (LibraryPatron patron : patrons) {
            if (patron.getName().equalsIgnoreCase(name)) {
                return patron;
            }
        }
        return null;
    }
}
