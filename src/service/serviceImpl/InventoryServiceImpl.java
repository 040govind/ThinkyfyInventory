package service.serviceImpl;

import java.util.*;
import java.util.stream.Collectors;

import exception.InvalidInputException;
import model.Item;
import model.ItemKey;
import model.SearchRequest;
import repositary.InventoryRepository;
import service.InventoryService;

public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository repo = new InventoryRepository();

    @Override
    public void addItem(String brand, String category, int price) {
        ItemKey key = new ItemKey(brand, category);
        if (repo.exists(key)) {
            throw new InvalidInputException("Item already exists: " + key);
        }
        repo.addItem(new Item(key, price));
    }

    @Override
    public void addInventory(String brand, String category, int quantity) {
        ItemKey key = new ItemKey(brand, category);
        Item item = repo.getItem(key);
        if (item == null) throw new InvalidInputException("Item not found: " + key);
        item.addQuantity(quantity);
    }

    @Override
    public List<Item> searchItemsByBrand(SearchRequest request){

        List<Item> items = new ArrayList<>(repo.getAllItems().values());
        
        if(request.brands !=null && !request.brands.isEmpty()){
            items = items.stream().filter(i -> request.brands.contains(i.getKey().getBrand())).collect(Collectors.toList());
            return items;
        }
        return null;
    }
    public List<Item> searchItemsByCategory(SearchRequest request){
        List<Item> items = new ArrayList<>(repo.getAllItems().values());
        if(request.categories != null && !request.categories.isEmpty()){
            items = items.stream().filter(i -> request.categories.contains(i.getKey().getCategory())).collect(Collectors.toList());
            return items;
        }
        return null;
    }

    @Override
    public List<Item> searchByCategoryAndOrderByPrice(SearchRequest request) {
        // Validate request
        if (request.getCategories() == null || request.getCategories().isEmpty()) {
            System.out.println("Category must be provided.");
            return null;
        }
    
        if (request.getOrderBy() == null || !request.getOrderBy().equalsIgnoreCase("price")) {
            System.out.println("Order_By must be 'price'.");
            return null;
        }
    
        // Use existing category filter method
        List<Item> items = searchItemsByCategory(request);
        if (items == null || items.isEmpty()) {
            System.out.println("No items found for the given category.");
            return null;
        }
    
        //Apply sorting
        Comparator<Item> comparator = Comparator.comparingInt(Item::getPrice);
        if ("desc".equalsIgnoreCase(request.getOrder())) {
            comparator = comparator.reversed();
        }
    
        items.sort(comparator);
    
        return items;
    }
    

    @Override
    public List<Item> searchItemsByPriceRange(SearchRequest request) {
    
        if (request.getPriceFrom() == null && request.getPriceTo() == null) {
            System.out.println("At least priceFrom or priceTo must be provided.");
            return null;
        }
    
        List<Item> items = new ArrayList<>(repo.getAllItems().values());
    
        // Apply price range filters
        if (request.getPriceFrom() != null) {
            items = items.stream()
                    .filter(i -> i.getPrice() >= request.getPriceFrom())
                    .collect(Collectors.toList());
        }
    
        if (request.getPriceTo() != null) {
            items = items.stream()
                    .filter(i -> i.getPrice() <= request.getPriceTo())
                    .collect(Collectors.toList());
        }
    
        if (items.isEmpty()) {
            System.out.println("No items found in the given price range.");
            return null;
        }
    
        return items;
    }

    @Override
    public List<Item> searchByCategoryAndPriceRangeWithOrderByPrice(SearchRequest request) {
        //Validation
        if (request.getCategories() == null || request.getCategories().isEmpty()) {
            System.out.println("Category must be provided.");
            return null;
        }
    
        if (request.getPriceFrom() == null && request.getPriceTo() == null) {
            System.out.println("At least priceFrom or priceTo must be provided.");
            return null;
        }
    
        if (request.getOrderBy() == null || !request.getOrderBy().equalsIgnoreCase("price")) {
            System.out.println("Order_By must be 'price'.");
            return null;
        }
    
        //Category filtering (reuse)
        List<Item> items = searchItemsByCategory(request);
        if (items == null || items.isEmpty()) {
            System.out.println("No items found for the given category.");
            return null;
        }
    
        //Price filtering (reuse logic directly)
        if (request.getPriceFrom() != null) {
            items = items.stream()
                    .filter(i -> i.getPrice() >= request.getPriceFrom())
                    .collect(Collectors.toList());
        }
    
        if (request.getPriceTo() != null) {
            items = items.stream()
                    .filter(i -> i.getPrice() <= request.getPriceTo())
                    .collect(Collectors.toList());
        }
    
        //  Sorting by price
        Comparator<Item> comparator = Comparator.comparingInt(Item::getPrice);
        if ("desc".equalsIgnoreCase(request.getOrder())) {
            comparator = comparator.reversed();
        }
    
        items.sort(comparator);
    
        if (items.isEmpty()) {
            System.out.println("No items found matching all criteria.");
            return null;
        }
    
        return items;
    }
    
    
    // @Override
    // public List<Item> searchItems(SearchRequest request) {
    //     List<Item> items = new ArrayList<>(repo.getAllItems().values());

    //     if (request.brands != null && !request.brands.isEmpty()) {
    //         items = items.stream().filter(i -> request.brands.contains(i.getKey().getBrand())).collect(Collectors.toList());
    //     }

    //     if (request.categories != null && !request.categories.isEmpty()) {
    //         items = items.stream().filter(i -> request.categories.contains(i.getKey().getCategory())).collect(Collectors.toList());
    //     }

    //     if (request.priceFrom != null) {
    //         items = items.stream().filter(i -> i.getPrice() >= request.priceFrom).collect(Collectors.toList());
    //     }

    //     if (request.priceTo != null) {
    //         items = items.stream().filter(i -> i.getPrice() <= request.priceTo).collect(Collectors.toList());
    //     }

    //     Comparator<Item> comparator = Comparator.comparingInt(Item::getPrice);
    //     if ("quantity".equalsIgnoreCase(request.orderBy)) {
    //         comparator = Comparator.comparingInt(Item::getQuantity);
    //     }

    //     if ("desc".equalsIgnoreCase(request.order)) {
    //         comparator = comparator.reversed();
    //     }

    //     items.sort(comparator);

    //     if (items.isEmpty()) {
    //         System.out.println("No items found.");
    //         return null;
    //     }
    //         //items.forEach(System.out::println);
    //         return items;
        
    // }
    @Override
public void printAllInventory() {
    for (Map.Entry<ItemKey, Item> entry : repo.getAllItems().entrySet()) {
        Item item = entry.getValue();
        System.out.println(item.getBrand() + ", " + item.getCategory() + ", " +
                           "Price: " + item.getPrice() + ", Qty: " + item.getQuantity());
    }
}
}

