public class Chance {
  public static double getRandomDouble(double min, double max) {
    double range = max - min;
    return Math.random() * range + min;
  }

  public static int getRandomInt(int min, int max) {
    int range = max - min;
    return (int) (Math.random() * range) + min;
  }

  public static boolean getRandomBoolean() {
    int index = (int) (Math.random() * 2);
    return (new boolean[]{true, false})[index];
  }
}
