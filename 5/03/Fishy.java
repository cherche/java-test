public class Fishy {
  public static void main(String[] args) {
    int[] weights = {
      226,
      305,
      233,
      244,
      224,
      235,
      238,
      244,
      222,
      239,
      233,
      243,
      221,
      230,
      237,
      240,
      309,
      230,
      236,
      242,
      222,
      235,
      237,
      240,
      220,
      235,
      238,
      243,
      222,
      232,
      232,
      242
    };
    int sum = 0;

    for (int i = 0; i < weights.length; i++) {
      sum += weights[i];
    }

    int average = sum / weights.length;
    System.out.println("Average: " + average);
    int max = weights[0];

    for (int i = 0; i < weights.length; i++) {
      if (weights[i] > max) {
        max = weights[i];
      }
    }

    System.out.println("Max: " + max);

    if (max < 300) {
      System.out.println("The fish are not living long enough to represent a healthy population.");
    } else {
      System.out.println("The fish are indeed living long enough.");
    }
  }
}
