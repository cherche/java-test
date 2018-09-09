public class Cards {
  public static void main(String[] args) {
    String[] suits = new String[]{
      "Hearts",
      "Diamonds",
      "Spades",
      "Clubs"
    };
    String[] ranks = new String[]{
      "Ace",
      "1",
      "2",
      "3",
      "4",
      "5",
      "6",
      "7",
      "8",
      "9",
      "Jack",
      "Queen",
      "King"
    };

    for (int i = 0; i < suits.length; i++) {
      for (int j = 0; j < ranks.length; j++) {
        System.out.println(ranks[j] + " of " + suits[i]);
      }
    }
  }
}
