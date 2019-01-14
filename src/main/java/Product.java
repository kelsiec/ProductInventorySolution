class Product {
    private final String productId;
    private double price;
    private int quantity;

    Product(String productId, double price, int quantity) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    String getProductId() {
        return productId;
    }

    double getPrice() {
        return price;
    }

    void setPrice(double price) {
        this.price = price;
    }

    int getQuantity() {
        return quantity;
    }

    void addStock(int quantity) {
        this.quantity += quantity;
    }

    void removeStock(int quantity) {
        this.quantity -= quantity;
    }

}
