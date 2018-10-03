public class BottlesOfPop {
  public static void main(String[] args) {
    for (int i = 100; i > 0; i--) {
      System.out.print(getVerse(i));
    }
  }

  static String getVerse(int count) {
    String[] diffs = new String[3];
    diffs[0] = pluralize(count + " bottle", count);

    diffs[1] = (count == 1)
      ? "that bottle"
      : "one of those bottles";

    if (count == 1) {
      diffs[2] = "no more bottles";
    } else if (count == 2) {
      diffs[2] = "1 last bottle";
    } else {
      diffs[2] = (count - 1) + " bottles";
    }

    return diffs[0] + " of pop on the wall.\n"
      + diffs[0] + " of pop.\n"
      + "If " + diffs[1] + " should happen to fall...\n"
      + "There would be " + diffs[2] + " of pop on the wall.\n\n";
  }

  static String pluralize(String noun, int count) {
    if (count == 1) {
      return noun;
    }

    return noun + "s";
  }
}
