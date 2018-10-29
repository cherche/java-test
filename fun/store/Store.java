
/**
 * Asks the user what they would like to buy and prints them a bill
 *
 * @author: Ryan Nguyen
 * @version: 2018-09-25
 */
public class Store {
  public static void main(String[] args) {
    System.out.println("# Welcome to Fred's Food");
    System.out.println("");
    Item[] items = new Item[] {
      new Item("egg", "eggs", 0.3),
      new Item("lettuce", "lettuces", 1.02),
      new Item("milk", "cartons of milk", 1.25),
      new Item("cookie", "boxes of cookies", 3.0),
      new Item("apple", "apples", 0.5)
    };
    int[] counts = new int[items.length];
    System.out.println("Enter the amount of each item that you would like to buy.");

    for (int i = 0; i < items.length; i++) {
      Item item = items[i];
      String prompt = (i + 1) + ". How many " + item.display + " would you like? ";
      counts[i] = IBIO.inputInt(prompt);
    }

    double subtotal = 0;
    System.out.println("");
    System.out.println("# Fred's Food Bill");
    System.out.println("");

    for (int i = 0; i < items.length; i++) {
      int count = counts[i];

      if (count <= 0) {
        continue;
      }

      Item item = items[i];
      String name = item.name;
      double price = item.price;
      double cost = count * price;
      subtotal += cost;
      System.out.println(count + " " + name + " @ $" + price + ": $" + cost);
    }

    if (subtotal == 0) {
      System.out.println("We're sorry that we had nothing for you.");
      System.out.println("Hope to see you again soon!");
      return;
    }

    double tax = roundPrice(subtotal * 0.15);
    double total = subtotal + tax;
    System.out.println("");
    System.out.println("Subtotal: $" + subtotal);
    System.out.println("Tax: $" + tax);
    System.out.println("Total: $" + total);
    System.out.println("");
    double paid = IBIO.inputDouble("Enter the amount of money paid: $");
    String message;

    if (paid == total) {
      message = "Thank you! Have a great day.";
    } else if (paid > total) {
      message = "You have overpaid. Here is your change of $" + roundPrice(paid - total);
    } else {
      message = "That is not enough. You need an additional $" + roundPrice(total - paid);
    }

    System.out.println(message);
  }

  private static double roundPrice(double price) {
    return Math.round(price * 100) / 100.0;
  }
}
