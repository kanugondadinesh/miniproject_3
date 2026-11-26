import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;
public class ProductInventorySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, Integer> inventory = new HashMap<>();
        HashMap<Integer, String> names = new HashMap<>();
        HashMap<Integer, String> prodCategories = new HashMap<>();
        HashMap<Integer, Double> prices = new HashMap<>();
        HashSet<String> categories = new HashSet<>();
        LinkedHashSet<String> recentProducts = new LinkedHashSet<>();
        while (true) {
            System.out.println("\n========================================");
            System.out.println("       PRODUCT INVENTORY SYSTEM");
            System.out.println("========================================\n");
            System.out.println("1. Add Product");
            System.out.println("2. Display All Products");
            System.out.println("3. Search Product");
            System.out.println("4. Update Product Quantity");
            System.out.println("5. Sell Product");
            System.out.println("6. Remove Product");
            System.out.println("7. Add Product Category");
            System.out.println("8. Display Unique Categories");
            System.out.println("9. Display Sorted Categories");
            System.out.println("10. Display Recently Added Products");
            System.out.println("11. Generate Inventory Report");
            System.out.println("12. Exit\n");
            System.out.print("Enter Your Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("\nProduct ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    if (inventory.containsKey(id)) {
                        System.out.println("\nProduct ID already exists! Use a unique ID.");
                        break;
                    }
                    System.out.print("Product Name: ");
                    String name = sc.nextLine();
                    System.out.print("Category: ");
                    String cat = sc.nextLine();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();
                    sc.nextLine();
                    if (price < 0 || qty < 0) {
                        System.out.println("\nPrice and Quantity cannot be negative!");
                    } else {
                        inventory.put(id, qty);
                        names.put(id, name);
                        prodCategories.put(id, cat);
                        prices.put(id, price);
                        categories.add(cat);
                        recentProducts.add(name);
                        System.out.println("\nProduct Added Successfully!");
                    }
                    break;
                case 2:
                    if (inventory.isEmpty()) {
                        System.out.println("\nNo products available in inventory.");
                    } else {
                        System.out.println("\n---------- PRODUCT INVENTORY ----------\n");
                        System.out.printf("%-8s %-15s %-15s %-12s %s%n", "ID", "Product", "Category", "Price", "Quantity");
                        for (Map.Entry<Integer, Integer> entry : inventory.entrySet()) {
                            int pid = entry.getKey();
                            System.out.printf("%-8d %-15s %-15s %-12.2f %d%n", pid, names.get(pid), prodCategories.get(pid), prices.get(pid), entry.getValue());
                        }
                        System.out.println("\nTotal Products: " + inventory.size());
                    }
                    break;
                case 3:
                    System.out.print("\nEnter Product ID to Search: ");
                    int searchId = sc.nextInt();
                    sc.nextLine();
                    if (inventory.containsKey(searchId)) {
                        System.out.println("\nProduct Found!");
                        System.out.println("Product ID: " + searchId);
                        System.out.println("Name: " + names.get(searchId));
                        System.out.println("Category: " + prodCategories.get(searchId));
                        System.out.println("Price: " + prices.get(searchId));
                        System.out.println("Available Quantity: " + inventory.get(searchId));
                    } else {
                        System.out.println("\nProduct ID " + searchId + " not found!");
                    }
                    break;
                case 4:
                    System.out.print("\nEnter Product ID: ");
                    int updateId = sc.nextInt();
                    if (inventory.containsKey(updateId)) {
                        System.out.print("Enter New Stock Quantity to Add: ");
                        int addQty = sc.nextInt();
                        sc.nextLine();
                        if (addQty < 0) {
                            System.out.println("\nQuantity to add cannot be negative!");
                        } else {
                            inventory.put(updateId, inventory.get(updateId) + addQty);
                            System.out.println("\nStock Updated Successfully!");
                            System.out.println("Product: " + names.get(updateId));
                            System.out.println("Available Quantity: " + inventory.get(updateId));
                        }
                    } else {
                        sc.nextLine();
                        System.out.println("\nProduct not found!");
                    }
                    break;
                case 5:
                    System.out.print("\nEnter Product ID: ");
                    int sellId = sc.nextInt();
                    if (inventory.containsKey(sellId)) {
                        System.out.print("Enter Quantity Sold: ");
                        int sellQty = sc.nextInt();
                        sc.nextLine();
                        int available = inventory.get(sellId);
                        if (sellQty <= 0) {
                            System.out.println("\nSold quantity must be greater than 0!");
                        } else if (sellQty > available) {
                            System.out.println("\nInsufficient stock! Available Quantity: " + available);
                        } else {
                            inventory.put(sellId, available - sellQty);
                            System.out.println("\nSale Completed Successfully!\n");
                            System.out.println("Product ID: " + sellId);
                            System.out.println("Product: " + names.get(sellId));
                            System.out.println("Remaining Quantity: " + inventory.get(sellId));
                        }
                    } else {
                        sc.nextLine();
                        System.out.println("\nProduct not found!");
                    }
                    break;
                case 6:
                    System.out.print("\nEnter Product ID to Remove: ");
                    int remId = sc.nextInt();
                    sc.nextLine();
                    if (inventory.containsKey(remId)) {
                        String remName = names.get(remId);
                        inventory.remove(remId);
                        names.remove(remId);
                        prodCategories.remove(remId);
                        prices.remove(remId);
                        recentProducts.remove(remName);
                        System.out.println("\nProduct '" + remName + "' removed from inventory.");
                    } else {
                        System.out.println("\nProduct not found!");
                    }
                    break;
                case 7:
                    System.out.println("\n1. Add New Category");
                    System.out.println("2. Search Category");
                    System.out.println("3. Remove Category");
                    System.out.print("Choose Option: ");
                    int catOpt = sc.nextInt();
                    sc.nextLine();
                    if (catOpt == 1) {
                        System.out.print("Enter Category Name: ");
                        String newCat = sc.nextLine();
                        if (categories.add(newCat)) {
                            System.out.println("Category Added Successfully!");
                        } else {
                            System.out.println("Category already exists!");
                        }
                    } else if (catOpt == 2) {
                        System.out.print("Enter Category to Search: ");
                        String searchCat = sc.nextLine();
                        if (categories.contains(searchCat)) {
                            System.out.println("Category '" + searchCat + "' exists.");
                        } else {
                            System.out.println("Category not found!");
                        }
                    } else if (catOpt == 3) {
                        System.out.print("Enter Category to Remove: ");
                        String remCat = sc.nextLine();
                        if (categories.remove(remCat)) {
                            System.out.println("Category '" + remCat + "' removed.");
                        } else {
                            System.out.println("Category not found!");
                        }
                    } else {
                        System.out.println("Invalid Option!");
                    }
                    break;
                case 8:
                    if (categories.isEmpty()) {
                        System.out.println("\nNo categories available.");
                    } else {
                        System.out.println("\n--------- PRODUCT CATEGORIES ---------\n");
                        for (String c : categories) {
                            System.out.println(c);
                        }
                        System.out.println("\nTotal Unique Categories: " + categories.size());
                    }
                    break;
                case 9:
                    if (categories.isEmpty()) {
                        System.out.println("\nNo categories available to sort.");
                    } else {
                        TreeSet<String> sortedCategories = new TreeSet<>(categories);
                        System.out.println("\n--------- SORTED CATEGORIES ----------\n");
                        for (String c : sortedCategories) {
                            System.out.println(c);
                        }
                        System.out.println("\nFirst Category: " + sortedCategories.first());
                        System.out.println("Last Category: " + sortedCategories.last());
                        System.out.println("Descending Order: " + sortedCategories.descendingSet());
                    }
                    break;
                case 10:
                    if (recentProducts.isEmpty()) {
                        System.out.println("\nNo recently added products.");
                    } else {
                        System.out.println("\n--- RECENTLY ADDED PRODUCTS ---");
                        for (String p : recentProducts) {
                            System.out.println(p);
                        }
                        System.out.println("\n1. Search Product   2. Remove Product   3. Back");
                        System.out.print("Choose Option: ");
                        int recOpt = sc.nextInt();
                        sc.nextLine();
                        if (recOpt == 1) {
                            System.out.print("Enter Product Name to Search: ");
                            String searchProd = sc.nextLine();
                            if (recentProducts.contains(searchProd)) {
                                System.out.println(searchProd + " is in the recently added list.");
                            } else {
                                System.out.println(searchProd + " not found in the list.");
                            }
                        } else if (recOpt == 2) {
                            System.out.print("Enter Product Name to Remove: ");
                            String remProd = sc.nextLine();
                            if (recentProducts.remove(remProd)) {
                                System.out.println(remProd + " removed from recently added products.");
                            } else {
                                System.out.println(remProd + " not found in the list.");
                            }
                        }
                    }
                    break;
                case 11:
                    if (inventory.isEmpty()) {
                        System.out.println("\nInventory is empty. Nothing to report.");
                    } else {
                        System.out.println("\n========== INVENTORY REPORT ==========\n");
                        System.out.printf("%-8s %-15s %-15s %-12s %s%n", "ID", "Product", "Category", "Price", "Quantity");
                        int totalUnits = 0;
                        int maxQty = -1;
                        int maxId = -1;
                        for (Map.Entry<Integer, Integer> entry : inventory.entrySet()) {
                            int pid = entry.getKey();
                            int q = entry.getValue();
                            System.out.printf("%-8d %-15s %-15s %-12.2f %d%n", pid, names.get(pid), prodCategories.get(pid), prices.get(pid), q);
                            totalUnits += q;
                            if (q > maxQty) {
                                maxQty = q;
                                maxId = pid;
                            }
                        }
                        System.out.println("\nProducts with Quantity Below 5:");
                        boolean lowFound = false;
                        for (Map.Entry<Integer, Integer> entry : inventory.entrySet()) {
                            if (entry.getValue() < 5) {
                                int pid = entry.getKey();
                                System.out.println("- " + names.get(pid) + " (ID: " + pid + ", Qty: " + entry.getValue() + ")");
                                lowFound = true;
                            }
                        }
                        if (!lowFound) {
                            System.out.println("None");
                        }
                        System.out.println("\nTotal Product Units Available: " + totalUnits);
                        System.out.println("Highest Stock Product: " + names.get(maxId) + " (ID: " + maxId + ", Qty: " + maxQty + ")");
                        System.out.println("Unique Categories: " + categories);
                        System.out.println("Total Unique Categories: " + categories.size());
                    }
                    break;
                case 12:
                    System.out.println("\nExiting Product Inventory System...");
                    sc.close();
                    return;
                default:
                    System.out.println("\nInvalid Choice! Please enter a number between 1 and 12.");
            }
            System.out.println("\n--------------------------------------");
        }
    }
}