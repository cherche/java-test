public class Tape {
  // I wonder if there's a convention for whether the main method
  // should be defined first or last in a class file
  public static void main(String[] args) {
    for (int i = 0; i < 5; i++) {
      // The requirements didn't really specify the randomness of
      // the tape lengths, but I did it anyway because it's fun
      String tape = getTape(getRandomInt(1, 7));
      System.out.println(tape);
    }
  }

  public static String getTape(int length) {
    String end = "+/\\/\\/\\/\\/\\+\n";
    String tape = "";
    tape += end;

    // If there's a more elegant way to this, I don't know it
    for (int i = 0; i < length; i++) {
      tape += "|          |\n";
    }

    tape += end;

    return tape;
  }

  public static int getRandomInt(int min, int max) {
    // min-inclusive, max-exclusive
    int range = max - min;
    return min + (int) (Math.random() * range);
  }
}
