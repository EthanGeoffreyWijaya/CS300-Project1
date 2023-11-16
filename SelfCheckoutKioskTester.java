//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Test Methods for Checkout Kiosk Task
// Course: CS 300 Spring 2021
//
// Author: Ethan Geoffrey Wijaya
// Email: egwijaya@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: none
// Online Sources: none
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Arrays;

public class SelfCheckoutKioskTester {

  /**
   * Checks whether SelfCheckoutKiosk.getItemName() and SelfCheckoutKiosk.getItemPrice() method work
   * as expected.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testItemNameAndPriceGetterMethods() {
    for (int i = 0; i < SelfCheckoutKiosk.GROCERY_ITEMS.length; i++) {
      if (!SelfCheckoutKiosk.getItemName(i).equals(SelfCheckoutKiosk.GROCERY_ITEMS[i][0])) {
        System.out.println("Problem detected: Called your getItemName() method with "
            + "input value " + i + ". But it did not return the expected output.");
        return false;
      }

      double expectedPriceOutput =
          Double.valueOf(SelfCheckoutKiosk.GROCERY_ITEMS[i][1].substring(1).trim());
      if (Math.abs((SelfCheckoutKiosk.getItemPrice(i) - expectedPriceOutput)) > 0.001) {
        System.out.println("Problem detected: Called getItemPrice method with input " + i
            + ". Didn't get expected output./nExpected: " + expectedPriceOutput + " Actual: "
            + SelfCheckoutKiosk.getItemPrice(i));
        return false;
      }
    }
    return true;
  }

  /**
   * Checks the correctness of SelfCheckoutKiosk.addItemToBaggingArea() method
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddItemToBaggingArea() {
    String[] items = new String[10];
    int size = 0;

    // Test 1 (Adding ID 0 to an empty bagging area)
    size = SelfCheckoutKiosk.addItemToBaggingArea(0, items, size);
    if (size != 1) {
      System.out.println("Problem detected: Tried to add one item to an empty, "
          + "bagging area. The returned size must be 1. But your addItemToBaggingArea "
          + "method returned a different output");
      return false;
    }
    if (!items[0].equals(SelfCheckoutKiosk.getItemName(0))) {
      // notice here the importance of checking for the correctness of your
      // getItemName()
      // method before calling it above
      System.out.println("Problem detected: Tried to add only one item to an empty, "
          + "bagging area. But that item was not appropriately added to the contents "
          + "of the items array.");
      return false;
    }
    // (2) Consider a non-empty bagging area
    items = new String[] {"Milk", "Chocolate", "Onion", null, null, null, null};
    size = 3;
    size = SelfCheckoutKiosk.addItemToBaggingArea(10, items, size);

    if (size != 4) {
      System.out.println("Problem detected: Tried to add only one item to an non-empty, "
          + "bagging area. The size must be incremented after the method returns. But "
          + "it was not the case");
      return false;
    }
    if (!items[3].equals(SelfCheckoutKiosk.getItemName(10))) {
      System.out.println("Problem detected: Tried to add one item to an non-empty, "
          + "bagging area. But that item was not appropriately added to the contents "
          + "of the items array.");
      return false;
    }
    // (3) Consider adding an item to a full bagging area
    items = new String[] {"Pizza", "Eggs", "Apple"};
    size = 3;
    size = SelfCheckoutKiosk.addItemToBaggingArea(2, items, size);
    if (size != 3) {
      System.out.println("Problem detected: Tried to add an item into a full bagging area."
          + "Size should remain unaltered. Expected size: 3. Actual size: " + size);
      return false;
    }
    if (!items[0].equals("Pizza") || !items[1].equals("Eggs") || !items[2].equals("Apple")) {
      System.out.println(
          "Problem detected: Array was altered, whereas it should remain the same when bagging area"
              + "is full. Expected array: [Pizza, Eggs, Apple]. Actual array: [" + items[0] + ", "
              + items[1] + ", " + items[2] + "]");
      return false;
    }
    return true;
  }

  /**
   * Checks the correctness of SelfCheckoutKiosk.count() method
   * 
   * @return true if there are no errors, false otherwise
   */
  public static boolean testCount() {
    String[] items =
        new String[] {"Apple", "Spinach", "Tomato", "Broccoli", "Blueberry", "Blueberry"};
    int size = 6;

    // Test 1 (0 occurrences)
    if (SelfCheckoutKiosk.count("Beef", items, size) != 0) {
      System.out.println("Error detected: Improper int returned for item not found"
          + "Expected: 0. Actual: " + SelfCheckoutKiosk.count("Beef", items, size));
      return false;
    }
    // Test 2 (1 occurrence)
    if (SelfCheckoutKiosk.count("Tomato", items, size) != 1) {
      System.out.println("Error detected: Improper count of occurrences. Expected: 1." + "Actual: "
          + SelfCheckoutKiosk.count("Tomato", items, size));
      return false;
    }
    // Test 3 (2 occurrences)
    if (SelfCheckoutKiosk.count("Blueberry", items, size) != 2) {
      System.out.println("Error detected: Improper count of occurrences. Expected: 2." + "Actual: "
          + SelfCheckoutKiosk.count("Blueberry", items, size));
      return false;
    }
    return true;
  }

  /**
   * Checks the correctness of SelfCheckoutKiosk.indexOf() method.
   * 
   * @return true if there are no errors, false otherwise
   */
  public static boolean testIndexOf() {
    String[] items = new String[] {"Broccoli", "Carrot", "Beef", "Carrot", null};
    int size = 4;

    // Test 1 (Item of one occurrence found)
    if (SelfCheckoutKiosk.indexOf("Beef", items, size) != 2) {
      System.out.println("Error detected: Improper index returned. Expected: 2. Actual: "
          + SelfCheckoutKiosk.indexOf("Beef", items, size));
      return false;
    }
    // Test 2 (Item of two occurrences found)
    if (SelfCheckoutKiosk.indexOf("Carrot", items, size) != 1) {
      System.out.println("Error detected: Improper index returned."
          + "Index of first occurrence must be returned. Expected: 1. Actual: "
          + SelfCheckoutKiosk.indexOf("Carrot", items, size));
      return false;
    }
    // Test 3 (Item not found)
    if (SelfCheckoutKiosk.indexOf("Pizza", items, size) != -1) {
      System.out.println(
          "Error detected: Improper index returned for item not found. Expected: -1. Actual: "
              + SelfCheckoutKiosk.indexOf("Pizza", items, size));
      return false;
    }
    return true;
  }

  /**
   * Checks the correctness of SelfCheckoutKiosk.remove() method. Checks for array contents and
   * size.
   * 
   * @return true if there are no errors, false otherwise.
   */
  public static boolean testRemove() {
    String[] items = new String[] {"Pizza", "Tomato", "Apples", "Tomato", null};
    int size = 4;

    // Test 1 (Item is removed)
    size = SelfCheckoutKiosk.remove("Pizza", items, size);
    if (size != 3) {
      System.out.println("Error detected(1): Wrong size value. Expected: 3. Actual: " + size);
      return false;
    }
    if (!Arrays.equals(items, new String[] {"Tomato", "Apples", "Tomato", null, null})) {
      System.out.println("Error detected(1): Array altered improperly."
          + "Expected: [Tomato, Apples, Tomato, null, null]. Actual: " + Arrays.toString(items));
      return false;
    }

    // Test 2 (First occurrence of item is removed)
    size = SelfCheckoutKiosk.remove("Tomato", items, size);
    if (size != 2) {
      System.out.println("Error detected(2): Wrong size value. Expected: 2. Actual: " + size);
      return false;
    }
    if (!Arrays.equals(items, new String[] {"Apples", "Tomato", null, null, null})) {
      System.out
          .println("Error detected(2): Array altered improperly. First occurrence must be removed"
              + "Expected: [Apples, Tomato, null, null, null]. Actual: " + Arrays.toString(items));
      return false;
    }

    // Test 3 (If item not in list, list remains unaltered)
    size = SelfCheckoutKiosk.remove("Broccoli", items, size);
    if (size != 2) {
      System.out.println("Error detected(3): Wrong size value. Expected: 2. Actual: " + size);
      return false;
    }
    if (!Arrays.equals(items, new String[] {"Apples", "Tomato", null, null, null})) {
      System.out.println("Error detected(3): Array should remain unaltered if item not found."
          + "Expected: [Apples, Tomato, null, null, null]. Actual: " + Arrays.toString(items));
      return false;
    }

    // Test 4 (If size is 0, list unaltered)
    items = new String[5];
    size = 0;
    size = SelfCheckoutKiosk.remove("Pizza", items, size);
    if (size != 0) {
      System.out.println("Error detected(4): Wrong size value. Expected: 0. Actual: " + size);
      return false;
    }
    if (!Arrays.equals(items, new String[] {null, null, null, null, null})) {
      System.out.println("Error detected(4): An empty array should remain unaltered. "
          + "Expected: [null, null, null, null, null]. Actual: " + Arrays.toString(items));
      return false;
    }

    return true;
  }

  /**
   * Checks the correctness of SelfCheckoutKiosk.getSubTotalPrice() method.
   * 
   * @return true if there are no errors, false otherwise
   */
  public static boolean testGetSubTotalPrice() {
    String[] items = new String[] {"Apple", "Blueberry", null};
    int size = 2;
    // Test 1 (Ordinary case)
    if (Math.abs(8.48 - SelfCheckoutKiosk.getSubTotalPrice(items, size)) > 0.001) {
      System.out
          .println("Error detected(1): Incorrect subtotal price value. Expected: 8.48. Actual: "
              + SelfCheckoutKiosk.getSubTotalPrice(items, size));
      return false;
    }

    // Test 2 (Ordinary case 2)
    items = new String[] {"Spinach", "Onion", "Grape", "Chocolate", "Carrot", "Beef"};
    size = 6;
    if (Math.abs(14.34 - SelfCheckoutKiosk.getSubTotalPrice(items, size)) > 0.001) {
      System.out
          .println("Error detected(2): Incorrect subtotal price value. Expected: 14.34. Actual: "
              + SelfCheckoutKiosk.getSubTotalPrice(items, size));
      return false;
    }

    // Test 3 (Empty Array)
    items = new String[3];
    size = 0;
    if (Math.abs(0.0 - SelfCheckoutKiosk.getSubTotalPrice(items, size)) > 0.001) {
      System.out.println(
          "Error detected: Incorrect subtotal price value for empty array. Expected: 0. Actual: "
              + SelfCheckoutKiosk.getSubTotalPrice(items, size));
      return false;
    }

    return true;
  }

  /**
   * Checks the correctness of SelfCheckoutKiosk.getUniqueCheckedOutItems() method.
   * 
   * @return true if there are no errors, false otherwise
   */
  public static boolean testGetUniqueCheckedOutItems() {
    String[] items =
        new String[] {"Apple", "Onion", "Milk", "Apple", "Chocolate", "Chocolate", "Milk", "Milk"};
    int size = 7;
    String[] itemsSet = new String[7];
    int itemsSetSize = 0;

    itemsSetSize = SelfCheckoutKiosk.getUniqueCheckedOutItems(items, size, itemsSet);
    if (itemsSetSize != 4) {
      System.out.println("Error dectected: Improper size output for itemsSet array. Expected: 4. "
          + "Actual: " + size);
      return false;
    }
    if (!Arrays.equals(itemsSet,
        new String[] {"Apple", "Onion", "Milk", "Chocolate", null, null, null})) {
      System.out.println("Error detected: Incorrect array copy for getUniqueCheckedOutItems method."
          + "Expected: [Apple, Onion, Milk, Chocolate, null, null, null]. Actual: "
          + Arrays.toString(itemsSet));
      return false;
    }
    return true;
  }

  /**
   * Calls the test methods implemented in this class and displays their output
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out
        .println("testItemNameAndPriceGetterMethods(): " + testItemNameAndPriceGetterMethods());
    System.out.println("testAddItemToBaggingArea(): " + testAddItemToBaggingArea());
    System.out.println("testCount(): " + testCount());
    System.out.println("testIndexOf(): " + testIndexOf());
    System.out.println("testRemove(): " + testRemove());
    System.out.println("testGetSubTotalPrice(): " + testGetSubTotalPrice());
    System.out.println("testGetUniqueCheckedOutItems(): " + testGetUniqueCheckedOutItems());

  }

}
