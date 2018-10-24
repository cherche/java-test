public class Pyramid2 {
  public static void main(String[] args) {
    int rowCount = IBIO.inputInt("How many rows are there? ");

    for (int i = 0; i < rowCount; i++) {
      int blankCount = rowCount - i - 1;
      int starCount = 2 * i + 1;

      for (int j = 0; j < blankCount; j++) {
        System.out.print(" ");
      }

      for (int j = 0; j < starCount; j++) {
        System.out.print("*");
      }

      System.out.println("");
    }
  }
}
