public class test {
  public static void main(String[] args) {
    String[] choices = new String[] {
      "Rock",
      "Spock",
      "Paper",
      "Lizard",
      "Scissors"
    };
    int length = choices.length;
    RPS rps5 = new RPS(choices);

    for (int i = 0; i < length; i++) {
      for (int j = 0; j < length; j++) {
        printWinner(rps5, i, j);
      }
    }
  }

  private static void printWinner(RPS rps, int i, int j) {
    int w = rps.getWinner(i, j);
    System.out.println(rps.getName(i) + ", " + rps.getName(j) + " -> " + rps.getName(w));
  }
}
