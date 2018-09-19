public class Daisy {
  public static void main(String[] args) {
    // Note that this generates a random integer in the
    // set {15, 16, ..., 23, 24} (i.e. max-exclusive)
    // This makes it an even distribution for those of us
    // who like to make the confirmation of love from
    // a randomized computer program as fair as possible
    int petalCount = getRandomInt(15, 25);

    for (int i = 0; i < petalCount; i++) {
      String message = (i % 2 == 0)
        ? "She loves me."
        : "She loves me not.";
      System.out.println(message);
      // Slow things down a bit to guarantee anxiety
      caughtSleep(250);
    }
  }

  // As usual, min-inclusive and max-exclusive
  private static int getRandomInt(int min, int max) {
    int range = max - min;
    return (int) (Math.random() * range) + min;
  }

  // I wasn't quite sure what to name it,
  // but this seems like a safe bet
  private static void caughtSleep(int time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {

    }
  }
}
