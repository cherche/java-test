public class Plants {
  private String[] names;
  private int[] prices;
  private int size = 0;

  public static void main(String[] args) {
    Plants plants = new Plants(20);
    plants.add("Sunflower", 50);
    plants.add("Pea shooter", 100);
    plants.add("Cherry Bomb", 150);
    plants.add("Wall-Nut", 50);
    plants.add("Potato Mine", 25);
    plants.add("Snow Pea", 175);
    plants.add("Chomper", 150);
    String option = "";

    do {
      System.out.println("\n# LIST");
      System.out.println(plants.toString());
      option = sanitize(IBIO.inputString("\n# MENU\nAdd\nDelete\nAverage\nQuit\n\n> "));

      if ("add".equals(option)) {
        String name = IBIO.inputString("Name (case-sensitive): ");
        int price = IBIO.inputInt("Price: ");
        plants.add(name, price);
        System.out.println("Succesfully added \"" + name + "\" with price " + price);
      } else if ("delete".equals(option)) {
        String name = IBIO.inputString("Name (case-sensitive): ");
        int[] prices = plants.getPrices();
        int index = plants.remove(name);

        if (index == -1) {
          System.out.println("Could not find item with name \"" + name + "\".");
        } else {
          System.out.println("Succesfully removed \"" + name + "\" with price " + prices[index] + " at index " + index);
        }
      } else if ("average".equals(option)) {
        int[] prices = plants.getPrices();
        int sum = 0;
        int size = plants.size();

        for (int i = 0; i < prices.length; i++) {
          sum += prices[i];
        }

        System.out.println("Average Price: " + sum / size);
      }
    } while (!"quit".equals(option));
  }

  public Plants(int max) {
    names = new String[max];
    prices = new int[max];
  }

  // Notice that the getters should be copies so as not
  // to refer to the same array and allow external mutability
  public String[] getNames() {
    return names.clone();
  }

  public int[] getPrices() {
    return prices.clone();
  }

  public int size() {
    return size;
  }

  public String toString() {
    if (size == 0) {
      return "";
    }

    String string = "";

    for (int i = 0; i < size; i++) {
      string += '\n' + names[i] + '\t' + prices[i];
    }

    // Remove the leading new line
    return string.substring(1);
  }

  public void add(String name, int price) {
    names[size] = name;
    prices[size] = price;
    size++;
  }

  public int remove(String name) {
    int index = -1;

    for (int i = 0; i < size; i++) {
      if (name.equals(names[i])) {
        index = i;
        break;
      }
    }

    if (index != -1) {
      size--;

      for (int i = index; i < size; i++) {
        names[i] = names[i + 1];
        prices[i] = prices[i + 1];
      }
    }

    return index;
  }

  public void removeAll(String name) {
    int status = 0;

    do {
      status = remove(name);
    } while (status != -1);
  }

  public static String sanitize(String string) {
    return string
      .toLowerCase()
      .replaceAll("[^0-9a-z ]+", "")
      .trim()
      .replaceAll("\\s+", " ");
  }
}
