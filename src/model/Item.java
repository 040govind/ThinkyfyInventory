package model;

public class Item {
    private ItemKey key;
    private int price;
    private int quantity;

    public Item(ItemKey key, int price) {
        this.key = key;
        this.price = price;
        this.quantity = 0;
    }

    public ItemKey getKey() { return key; }
    public int getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getBrand(){
        return key.getBrand();
    }

    public String getCategory(){
        return key.getCategory();
    }

    public void addQuantity(int q) { this.quantity += q; }

    @Override
    public String toString() {
        return key + ", Price: " + price + ", Qty: " + quantity;
    }
}