public class HoneyBees {
  public static void main(String[] args) {
    int[] weights = {
      95,
      92,
      93,
      95,
      92,
      100,
      89,
      83,
      88,
      89,
      88,
      89,
      87,
      95,
      95,
      92,
      101,
      78,
      79,
      82,
      83,
      95,
      88,
      90,
      91,
      89,
      85,
      82,
      81,
      94,
      94,
      95,
      96,
      97,
      98,
      95,
      98,
      103,
      95,
      91,
      96,
      93,
      82,
      95,
      88,
      89,
      90
    };
    System.out.println("Number of bees: " + weights.length);
    int sum = 0;
    int min = weights[0];
    int max = weights[0];
    int overweightCount = 0;
    int underAverageCount = 0;

    for (int i = 0; i < weights.length; i++) {
      sum += weights[i];

      if (weights[i] < min) {
        min = weights[i];
      }

      if (weights[i] > max) {
        max = weights[i];
      }

      if (weights[i] > 95) {
        overweightCount++;
      }
    }

    double average = 1.0 * sum / weights.length;

    for (int i = 0; i < weights.length; i++) {
      if (weights[i] < average) {
        underAverageCount++;
      }
    }

    System.out.println("Max weight (mg): " + max);
    System.out.println("Min weight (mg): " + min);
    System.out.println("Average weight (mg): " + average);
    System.out.println("# of bees weighing over 95 mg: " + overweightCount);
    System.out.println("# of bees weighing under " + (int) (10000 * average) / 10000.0 + " mg: " + overweightCount);
    int[] sorted = weights.clone();

    // Bubble sort, because it's the only one I know
    for (int m = sorted.length - 1; m >= 0; m--) {
      for (int i = 0; i < m; i++) {
        if (sorted[i] > sorted[i + 1]) {
          int temp = sorted[i];
          sorted[i] = sorted[i + 1];
          sorted[i + 1] = temp;
        }
      }
    }

    System.out.println("\nWeights:");

    for (int i = 0; i < sorted.length; i++) {
      System.out.print(sorted[i] + " ");
    }

    System.out.println("\n\nWeights, reversed:");

    for (int i = sorted.length - 1; i >= 0; i--) {
      System.out.print(sorted[i] + " ");
    }

    System.out.println("\n\nWeights, overweight:");

    for (int i = 0; i < sorted.length; i++) {
      int weight = sorted[i];

      if (weight > 95) {
        System.out.print(weight + " ");
      }
    }

    System.out.println("");
  }
}
