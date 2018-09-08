public class Pyramid {
  public static void main(String[] args) {
    int height = IBIO.inputInt("What is the height of the pyramid? ");
    Map map = new Map(height);

    // The Map class just gives us a 2D array of booleans
    // Here, we get to decide what to do with these booleans
    for (int y = 0; y < map.rows; y++) {
      for (int x = 0; x < map.cols; x++) {
        // Wow, I didn't choose variable/class names very well
        if (map.map[x][y]) {
          System.out.print("*");
        } else {
          System.out.print(" ");
        }
      }
      System.out.print("\n");
    }
  }
}
