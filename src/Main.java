import java.util.Arrays;
import java.util.List;

import exception.InvalidInputException;
import exception.ItemNotFoundException;
import model.Item;
import model.SearchRequest;
import service.InventoryService;
import service.serviceImpl.InventoryServiceImpl;

public class Main {

    public static void printItems(List<Item> items) {
        if (items == null || items.isEmpty()) {
            System.out.println("❌ No items found.");
            return;
        }

        System.out.println("✅ Found Items:");
        items.forEach(i -> System.out
                .println(i.getKey().getBrand() + ", " + i.getKey().getCategory() + ", " + i.getQuantity()+", "+i.getPrice()));
    }

    public static void main(String[] args) {
        

        try {
            InventoryService service = new InventoryServiceImpl();
        
        // InventoryService service = new InventoryServiceImpl();

        System.out.println("🔸 Adding Items...");
        service.addItem("Amul", "Milk", 100);
        service.addItem("Amul", "Curd", 50);
        service.addItem("Nestle", "Milk", 60);
        service.addItem("Nestle", "Curd", 90);
        service.addItem("MotherDairy", "Milk", 80);
        service.addItem("MotherDairy", "Butter", 120);
        service.addItem("Britannia", "Bread", 40);
        service.addItem("Britannia", "Cheese", 150);
        service.addItem("Parle", "Biscuits", 30);
        service.addItem("Parle", "Milk", 70);
        service.addItem("Amul", "Cheese", 160);
        service.addItem("Nestle", "Butter", 110);
        service.addItem("Britannia", "Milk", 85);
        service.addItem("Britannia", "Curd", 55);
        service.addItem("MotherDairy", "Curd", 60);
        service.addItem("Parle", "Curd", 65);
        service.addItem("Amul", "Paneer", 180);
        service.addItem("Nestle", "Paneer", 175);
        service.addItem("Britannia", "Paneer", 170);
        service.addItem("MotherDairy", "Paneer", 165);

        System.out.println("\n🔸 Adding Inventory...");
        service.addInventory("Amul", "Milk", 10);
        service.addInventory("Nestle", "Milk", 5);
        service.addInventory("Nestle", "Curd", 10);
        service.addInventory("Amul", "Milk", 10);
        service.addInventory("Amul", "Curd", 5);
        service.addInventory("Amul", "Milk", 5);

        service.addInventory("MotherDairy", "Milk", 12);
        service.addInventory("MotherDairy", "Butter", 8);
        service.addInventory("Britannia", "Bread", 30);
        service.addInventory("Britannia", "Cheese", 20);
        service.addInventory("Parle", "Biscuits", 25);
        service.addInventory("Parle", "Milk", 10);
        service.addInventory("Amul", "Cheese", 15);
        service.addInventory("Nestle", "Butter", 18);
        service.addInventory("Britannia", "Milk", 16);
        service.addInventory("Britannia", "Curd", 11);
        service.addInventory("MotherDairy", "Curd", 14);
        service.addInventory("Parle", "Curd", 9);
        service.addInventory("Amul", "Paneer", 7);
        service.addInventory("Nestle", "Paneer", 6);
        service.addInventory("Britannia", "Paneer", 5);
        service.addInventory("MotherDairy", "Paneer", 4);

        System.out.println("\n📦 Current Inventory:");
        service.printAllInventory();

        System.out.println(
                "**********************************************************************************************************************************************");

        // Search by brand
        SearchRequest request1 = new SearchRequest();
        request1.setBrands(List.of("Nestle"));
        List<Item> result1 = service.searchItemsByBrand(request1);
        printItems(result1);

        System.out.println(
                "**********************************************************************************************************************************************");

        // search by category
        SearchRequest request2 = new SearchRequest();
        request2.setCategories(List.of("Milk"));
        List<Item> result2 = service.searchItemsByCategory(request2);
        printItems(result2);

        System.out.println(
                "**********************************************************************************************************************************************");

        // search by category and price desc and asc
        SearchRequest request3 = new SearchRequest();
        request3.setCategories(List.of("Milk"));
        request3.setOrderBy("price");
        request3.setOrder("desc");
        List<Item> result3 = service.searchByCategoryAndOrderByPrice(request3);
        printItems(result3);

        System.out.println(
                "**********************************************************************************************************************************************");

        // search by price range
        SearchRequest request4 = new SearchRequest();
        request4.setPriceFrom(70);
        request4.setPriceTo(100);
        List<Item> result4 = service.searchItemsByPriceRange(request4);
        printItems(result4);

        System.out.println(
                "**********************************************************************************************************************************************");

        // search by category price range asc desc
        SearchRequest request5 = new SearchRequest();
        request5.setCategories(List.of("Milk"));
        request5.setPriceFrom(70);
        request5.setPriceTo(100);
        request5.setOrderBy("price");
        request5.setOrder("desc");
        List<Item> result5 = service.searchByCategoryAndPriceRangeWithOrderByPrice(request5);
        printItems(result5);

        System.out.println(
                "**********************************************************************************************************************************************");

        } catch (ItemNotFoundException ex) {
            System.err.println("⚠️ " + ex.getMessage());
        } catch (InvalidInputException ex) {
            System.err.println("⚠️ Invalid input: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("⚠️ Unexpected error: " + ex.getMessage());
        }
    }
}
