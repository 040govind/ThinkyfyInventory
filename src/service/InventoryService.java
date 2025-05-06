package service;

import java.util.List;

import model.Item;
import model.SearchRequest;

public interface InventoryService {
    void addItem(String brand, String category, int price);
    void addInventory(String brand, String category, int quantity);
    List<Item> searchItemsByBrand(SearchRequest request);
    public List<Item> searchItemsByCategory(SearchRequest request);
    public List<Item> searchByCategoryAndOrderByPrice(SearchRequest request);
    public List<Item> searchItemsByPriceRange(SearchRequest request);
    public List<Item> searchByCategoryAndPriceRangeWithOrderByPrice(SearchRequest request);
    void printAllInventory();
}
