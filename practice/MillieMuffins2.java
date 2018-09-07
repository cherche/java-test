public class MillieMuffins2 {
  public static void main(String[] args) {
    System.out.print("Welcome to Millie's Marvelous Muffins!\n\n**If you like muffins, ours are marvelous!**\n\n");
    // Not super clear but I don't really know how else to store the data
    int[][] ranges = new int[][]{
      {1, 9, 5},
      {10, 19, 3},
      {20, 39, 2}
      // It's not very nice as it doesn't support intervals to +-infinity
    };
    int muffinCount = IBIO.inputInt("How many muffins would you like? ");
    // We give it a value so the compiler doesn't throw a fit.
    // A more experienced would write code so this wouldn't be necessary.
    int costPerMuffin = 0;

    // This awkward ift is the result of not having intervals to +- infinity
    if (muffinCount >= 40) {
      costPerMuffin = 1;
    } else {
      for (int i = 0; i < ranges.length; i++) {
        int[] range = ranges[i];
        if (muffinCount >= range[0] && muffinCount <= range[1]) {
          costPerMuffin = range[2];
          // In our case, we only break for efficiency.
          // There's no reason to run through the rest of the ranges
          // if we've already found costPerMuffin.
          break;
        }
      }
    }

    // I'm used to having something nice for string interpolation,
    // but this is fine too . . .
    System.out.print("The total cost per muffin is $" + costPerMuffin + "\nThe total cost is $" + costPerMuffin * muffinCount + "\n");
  }
}
