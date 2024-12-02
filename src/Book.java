public class Book {
    private String title;
    private int quantity;

    public Book(String title, int quantity) {
        this.title = title;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decrementQuantity() {
        if (quantity > 0) quantity--;
    }

    public void incrementQuantity() {
        quantity++;
    }

    @Override
    public String toString() {
        return title + (quantity > 0 ? "" : " (Checked Out)");
    }
}
