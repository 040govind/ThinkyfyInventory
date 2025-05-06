package repositary;

import java.util.HashMap;
import java.util.Map;

import model.Item;
import model.ItemKey;

public class InventoryRepository {
    private final Map<ItemKey, Item> inventory = new HashMap<>();

    public void addItem(Item item) {
        inventory.putIfAbsent(item.getKey(), item);
    }

    public Item getItem(ItemKey key) {
        return inventory.get(key);
    }

    public Map<ItemKey, Item> getAllItems() {
        return inventory;
    }

    public boolean exists(ItemKey key) {
        return inventory.containsKey(key);
    }
}