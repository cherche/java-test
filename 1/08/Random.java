public class Random {
  /*
  returns a random integer from min (inclusive) to max (exclusive)
  */
  public static int getRandomInt(int min, int max) {
    int range = max - min;
    int rand = (int) (Math.random() * range) + min;
    return rand;
  }
}
