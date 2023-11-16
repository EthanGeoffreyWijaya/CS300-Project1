//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Checkout Kiosk Task
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

/**
 * Manages a grocery checkout system
 * 
 * @author Ethan
 *
 */
public class SelfCheckoutKiosk {

  /**
   * Value for the amount of sales tax
   */
  public static final double TAX_RATE = 0.05;

  /**
   * A perfect size two dimensional array containing all possible grocery items, their names, and
   * prices. Item ID is denoted by array index. Names contained in index 0 within arrays. Prices in
   * index 1 of arrays.
   */
  public static final String[][] GROCERY_ITEMS = new String[][] {{"Apple", "$1.59"},
      {"Avocado", "$0.59"}, {"Banana", "$0.49"}, {"Beef", "$3.79"}, {"Blueberry", "$6.89"},
      {"Broccoli", "$1.79"}, {"Butter", "$4.59"}, {"Carrot", "$1.19"}, {"Cereal", "$3.69"},
      {"Cheese", "$3.49"}, {"Chicken", "$5.09"}, {"Chocolate", "$3.19"}, {"Cookie", "$9.5"},
      {"Cucumber", "$0.79"}, {"Eggs", "$3.09"}, {"Grape", "$2.29"}, {"Ice Cream", "$5.39"},
      {"Milk", "$2.09"}, {"Mushroom", "$1.79"}, {"Onion", "$0.79"}, {"Pepper", "$1.99"},
      {"Pizza", "$11.5"}, {"Potato", "$0.69"}, {"Spinach", "$3.09"}, {"Tomato", "$1.79"}};

  /**
   * Gets the name of the specific grocery item from GROCERY_ITEMS given a specific index.
   * 
   * @param index The index ID of the desired grocery item
   * @return The name of the grocery item denoted by the index
   */
  public static String getItemName(int index) {
    return GROCERY_ITEMS[index][0];
  }

  /**
   * Gets the price of a specific grocery item from GROCERY_ITEMS given a specific index.
   * 
   * @param index The index ID of the desired grocery item
   * @return The price of the grocery item denoted by the index
   */
  public static double getItemPrice(int index) {
    double price = Double.parseDouble(GROCERY_ITEMS[index][1].substring(1).trim());
    return price;
  }

  /**
   * Prints all items in the GROCERY_ITEMS array as a catalog with a specific format. Includes item
   * ID, names, and prices.
   */
  public static void printCatalog() {
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("Item id \tName \t    Price");
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
    for (int i = 0; i < GROCERY_ITEMS.length; i++) {
      System.out.print(i + "\t\t");
      System.out.printf("%-12s$" + getItemPrice(i), getItemName(i));
      System.out.println();
    }
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
  }

  /**
   * Adds the name of a grocery item of a specific id to the end of an oversize array representing a
   * bagging area. If the oversize array is at full capacity when an item is being added, an error
   * message will be printed out.
   * 
   * @param id    The index ID of the grocery item in GROCERY_ITEMS
   * @param items The string oversize array representing the bagging area where items will be added
   * @param size  The number of elements in the oversize array
   * @return The altered size as int of the oversize array after the method implements
   */
  public static int addItemToBaggingArea(int id, String[] items, int size) {
    if (size == items.length) {
      System.out.println("Error! No additional item can be scanned. Please wait for assistance.");
    } else {
      items[size++] = getItemName(id);
    }
    return size;
  }

  /**
   * Counts the number of occurrences of a certain item in a string oversize array by comparing the
   * item name to string values in the array. Comparison is case insensitive.
   * 
   * @param item  The name of the item to look for
   * @param items The string oversize array from which items will be iterated
   * @param size  The number of elements in the oversize array
   * @return The number of occurrences of item in the oversize array as an int
   */
  public static int count(String item, String[] items, int size) {
    if (size == 0) {
      return 0;
    }
    int tally = 0;
    for (int i = 0; i < size; i++) {
      if (items[i].equalsIgnoreCase(item)) {
        tally++;
      }
    }
    return tally;
  }

  // Returns the index of the first occurrence of item in items if found,
  // and -1 if the item not found
  // item - element to search for
  // items - an array of string elements
  // size - number of elements stored in items
  /**
   * Returns the index of the first occurrence of a specific item in an oversize array. If the item
   * is not found within the array, the method will return -1 as opposed to the item index
   * 
   * @param item  The name of the item to locate
   * @param items The oversize array containing the item names
   * @param size  The number of elements in the oversize array
   * @return The int index of the first occurrence of item in the oversize array
   */
  public static int indexOf(String item, String[] items, int size) {
    if (size == 0) {
      return -1;
    }
    for (int i = 0; i < size; i++) {
      if (items[i].equalsIgnoreCase(item)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Removes the first occurrence of a specific item from an oversize string array representing the
   * bagging area. If that specific item does not exist within the array, an error message will be
   * printed and the array will remain unaltered.
   * 
   * @param itemToRemove The name of the item to be removed
   * @param items        The oversize array containing the items
   * @param size         The number of elements in the oversize array
   * @return The size of the array as int after the method is implemented
   */
  public static int remove(String itemToRemove, String[] items, int size) {
    if (size == 0) {
      return size;
    }
    for (int i = 0; i < size; i++) {
      if (items[i].equalsIgnoreCase(itemToRemove)) {
        int j;
        for (j = i; j < size - 1; j++) {
          items[j] = items[j + 1];
        }
        items[j] = null;
        return size - 1;
      }
    }
    System.out.println("WARNING: item not found.");
    return size;
  }

  /**
   * Fills an empty oversize string array with a copy of items array without duplicate items. Unique
   * items in the bagging area are stored in a new array. Does not alter the original items array.
   * 
   * @param items    The original oversize string array containing all items
   * @param size     The number of elements in the items array
   * @param itemsSet The new array which will be filled with items' unique elements
   * @return An int representing size of the itemsSet array when it is created
   */
  public static int getUniqueCheckedOutItems(String[] items, int size, String[] itemsSet) {
    int newSize = 0;
    int count;
    for (int i = 0; i < size; i++) {
      count = 0;
      for (int j = 0; j < itemsSet.length; j++) {
        if (!items[i].equalsIgnoreCase(itemsSet[j])) {
          count++;
        }
      }
      if (count == itemsSet.length) {
        itemsSet[newSize++] = items[i];
      }
    }
    return newSize;
  }

  /**
   * Gets the total price of all items in the bagging area without tax.
   * 
   * @param items The oversize string array containing all items in the bagging area
   * @param size  The number of elements in the items array
   * @return A double representing the total price of all items in the items array
   */
  public static double getSubTotalPrice(String[] items, int size) {
    double total = 0;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < GROCERY_ITEMS.length; j++) {
        if (items[i].equalsIgnoreCase(GROCERY_ITEMS[j][0])) {
          total += getItemPrice(j);
          break;
        }
      }
    }
    return total;
  }

}
