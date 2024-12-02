import java.util.HashSet;
import java.util.Set;

public class LibraryPatron implements Comparable<LibraryPatron> {
    private String name;
    private String id;
    private Set<Book> checkedOutBooks;

    public LibraryPatron(String name, String id) {
        this.name = name;
        this.id = id;
        this.checkedOutBooks = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Set<Book> getCheckedOutBooks() {
        return checkedOutBooks;
    }

    public void checkOutBook(Book book) {
        checkedOutBooks.add(book);
    }

    public void checkInBook(Book book) {
        checkedOutBooks.remove(book);
    }

    public void printInfo() {
        System.out.println("Patron Name: " + name + ", ID: " + id);
        System.out.println("Checked-Out Books:");
        for (Book book : checkedOutBooks) {
            System.out.println("- " + book.getTitle() + " (Checked Out)");
        }
    }

    @Override
    public int compareTo(LibraryPatron other) {
        return this.name.compareToIgnoreCase(other.name);
    }
}
