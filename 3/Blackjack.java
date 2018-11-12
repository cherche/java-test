import java.util.*;

public class Blackjack {
  public static int run() {
    Deck deck = new Deck();
    deck.shuffle();
    List<Card> player = new ArrayList<Card>();
    List<Card> dealer = new ArrayList<Card>();
    deck.deal(player, 2);
    deck.deal(dealer, 2);
    int playerTotal = getHandTotal(player);
    int dealerTotal = getHandTotal(dealer);
    System.out.println(handToString(player));
    System.out.println("Your total: " + playerTotal);

    if (playerTotal == 21) {
      System.out.println("VERONICA: ...");
      return 1;
    }

    // The code doesn't tell us to handle a hand total
    // of 22 (which is only possible on the first hand)
    // because of the game simplification, so I'll just ignore it
    // If something weird happens, it's your fault
    while ((player.size() < 5) && (IBIO.inputChar("Want another card? (y/n) ") == 'y')) {
      deck.deal(player);
      playerTotal = getHandTotal(player);
      System.out.println("");
      System.out.println(handToString(player));
      System.out.println("Your total: " + playerTotal);

      if (playerTotal > 21) {
        System.out.println("---");
        System.out.println("VERONICA: You lost even faster than I'd've thought!");
        return -1;
      }
    }

    System.out.println("---");
    System.out.println(handToString(dealer));
    System.out.println("Veronica's total: " + dealerTotal);

    while (dealerTotal < 16 && dealerTotal < playerTotal) {
      deck.deal(dealer);
      dealerTotal = getHandTotal(dealer);
      IBIO.input("Press ENTER to continue.\n");
      System.out.println(handToString(dealer));
      System.out.println("My total: " + dealerTotal);

      if (dealerTotal > 21) {
        System.out.println("---");
        System.out.println("VERONICA: I went over 21!");
        return -1;
      }
    }

    System.out.println("---");
    System.out.println("Your total: " + playerTotal);
    System.out.println("Veronica's total: " + dealerTotal);
    System.out.println("");

    if (playerTotal > dealerTotal) {
      System.out.println("VERONICA: ...");
      return 1;
    } else if (playerTotal < dealerTotal) {
      System.out.println("VERONICA: I won, as expected!");
      return -1;
    } else {
      System.out.println("VERONICA: We've tied this time.");
      return 0;
    }
  }

  private static String handToString(List<Card> hand) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < hand.size(); i++) {
      Card card = hand.get(i);
      sb.append(card.rank + card.suit);
      sb.append(" ");
    }

    return sb.toString();
  }

  private static int getHandTotal(List<Card> hand) {
    int sum = 0;

    for (int i = 0; i < hand.size(); i++) {
      Card card = hand.get(i);
      sum += card.value;
    }

    return sum;
  }
}
