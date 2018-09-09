public class test {
  public static void main(String[] args) {
    String[] results = new String[]{
      "getRandomInt(1, 7), " + Chance.getRandomDouble(1, 2),
      "getRandomInt(1, 7), " + Chance.getRandomInt(1, 7),
      "getRandomBoolean(), " + Chance.getRandomBoolean()
    };

    for (int i = 0; i < results.length; i++) {
      System.out.println(results[i]);
    }
  }
}
