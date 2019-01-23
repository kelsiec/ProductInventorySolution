import org.junit.Assert;
import org.junit.Test;

public class InventoryTests {
    @Test
    public void addApples() {
        Inventory inventory = new Inventory();
        inventory.addProduct("apple", .6, 2);

        Assert.assertEquals(2, inventory.getProducts().get("apple").getQuantity());
    }

    @Test
    public void addMoreApples() {
        Inventory inventory = new Inventory();
        inventory.addProduct("apple", .6, 1);
        inventory.addProduct("apple", .6, 2);

        Assert.assertEquals(3, inventory.getProducts().get("apple").getQuantity());
    }

    @Test
    public void updatePrice() {
        Inventory inventory = new Inventory();
        inventory.addProduct("apple", .6, 1);
        inventory.addProduct("apple", .7, 2);

        Assert.assertEquals(.7, inventory.getProducts().get("apple").getPrice(), .0001);
    }

    @Test(expected = InsufficientInventory.class)
    public void removeApples() throws InsufficientInventory {
        Inventory inventory = new Inventory();
        inventory.addProduct("apple", .6, 10);
        inventory.removeProduct("apple", 12);
    }

    @Test
    public void removeExistingApples() throws InsufficientInventory {
        Inventory inventory = new Inventory();
        inventory.addProduct("apple", .6, 10);
        inventory.removeProduct("apple", 8);

        Assert.assertEquals(2, inventory.getProducts().get("apple").getQuantity());
    }

    @Test(expected = InsufficientInventory.class)
    public void removeFakeProduct() throws InsufficientInventory {
        Inventory inventory = new Inventory();
        inventory.removeProduct("fakeProduct", 10);
    }

    @Test
    public void inStock() {
        Inventory inventory = new Inventory();
        inventory.addProduct("apple", .6, 10);

        Assert.assertTrue(inventory.inStock("apple"));
    }

    @Test
    public void outOfStock() {
        Inventory inventory = new Inventory();
        inventory.addProduct("apple", .6, 0);

        Assert.assertFalse(inventory.inStock("apple"));
    }

    @Test
    public void stockDoesNotExist() {
        Inventory inventory = new Inventory();
        inventory.addProduct("apple", .6, 0);

        Assert.assertFalse(inventory.inStock("fakeProduct"));
    }

    @Test
    public void totalInventoryValue() {
        Inventory inventory = new Inventory();
        inventory.addProduct("apple", .6, 10);
        inventory.addProduct("milk", 3.5, 1);

        Assert.assertEquals(9.5, inventory.totalInventoryValue(), 0.001);
    }
}
