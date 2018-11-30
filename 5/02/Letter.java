public class Letter {
  public static void main(String[] args) {
    char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    double[] frequencies = {8.167, 1.492, 2.782, 4.253, 12.702, 2.228, 2.015, 6.094, 6.966, 0.153, 0.772, 4.025, 2.406, 6.749, 7.507, 1.929, 0.095, 5.987, 6.327, 9.056, 2.758, 0.978, 2.360, 0.150, 1.974, 0.074};
    double sum = 0;

    for (int i = 0; i < frequencies.length; i++) {
      sum += frequencies[i];
    }

    System.out.println("Average: " + sum / frequencies.length);
    System.out.println("Sum: " + sum);

    if (sum != 100) {
      System.out.println("Mild rounding error");
    } else {
      System.out.println("Accurate");
    }

    int minIndex = 0;
    int maxIndex = 0;
    for (int i = 0; i < frequencies.length; i++) {
      if (frequencies[i] < frequencies[minIndex]) {
        minIndex = i;
      }

      if (frequencies[i] > frequencies[maxIndex]) {
        maxIndex = i;
      }
    }

    System.out.println(letters[minIndex] + " has min frequency of " + frequencies[minIndex]);
    System.out.println(letters[maxIndex] + " has max frequency of " + frequencies[maxIndex]);

    char letter = Character.toLowerCase(IBIO.inputChar("Enter a letter (type anything else to exit): "));

    while (96 < letter && letter <= 96 + 26) {
      int index = (int) letter - 97;
      System.out.println(letter + " has frequency of " + frequencies[index]);
      letter = Character.toLowerCase(IBIO.inputChar("Enter a letter (type anything else to exit): "));
    }
  }
}
