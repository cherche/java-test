import java.util.*;

public class Deck {
  private List<Card> cards = new ArrayList<Card>();

  public Deck() {
    String[] ranks = new String[] {
      "2",
      "3",
      "4",
      "5",
      "6",
      "7",
      "8",
      "9",
      "10",
      "J",
      "Q",
      "K",
      "A"
    };
    String[] suits = new String[] {
      "S",
      "H",
      "C",
      "D"
    };

    for (int i = 0; i < ranks.length; i++) {
      String rank = ranks[i];
      for (int j = 0; j < suits.length; j++) {
        String suit = suits[j];
        int value = getValue(rank);
        Card card = new Card(rank, suit, value);
        this.cards.add(card);
      }
    }
  }

  private void swap(int i, int j) {
    Card origI = cards.get(i);
    Card origJ = cards.get(j);

    cards.set(i, origJ);
    cards.set(j, origI);
  }

  // I love this shuffle algorithm
  // It's super magical
  public void shuffle() {
    int length = cards.size();

    // We run while (i < length - 1) because if (i = length - 1),
    // we are just swapping a card with itself, which is totally trivial
    for (int i = 0; i < length - 1; i++) {
      int maxIndex = length - 1 - i;
      int index = (int) (Math.random() * maxIndex);
      swap(maxIndex, index);
    }
  }

  public void deal(List<Card> hand, int count) {
    for (int i = 0; i < count; i++) {
      int index = cards.size() - 1;
      Card card = cards.get(index);
      hand.add(card);
      cards.remove(index);
    }
  }

  public void deal(List<Card> hand) {
    deal(hand, 1);
  }

  private static int getValue(String rank) {
    int value = 0;

    try {
      value = Integer.parseInt(rank, 10);
    } catch (Exception e) {
      switch (rank) {
        case "J":
        case "Q":
        case "K":
          value = 10;
          break;
        case "A":
          value = 11;
          break;
      }
    }

    return value;
  }
}
