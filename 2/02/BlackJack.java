import java.util.*;

public class BlackJack {
  public static void main(String[] args) {
    Deck deck = new Deck();
    deck.shuffle();
    List<Card> player = new ArrayList<Card>();
    List<Card> dealer = new ArrayList<Card>();
    deck.deal(player, 2);
    deck.deal(dealer, 2);
    int playerTotal = getHandTotal(player);
    int dealerTotal = getHandTotal(dealer);
    System.out.println("Your total: " + playerTotal);

    if (playerTotal == 21) {
      System.out.println("You win!");
      return;
    }

    // The code doesn't tell us to handle a hand total
    // of 22 (which is only possible on the first hand)
    // because of the game simplification, so I'll just ignore it
    // If something weird happens, it's your fault
    while ((player.size() < 5) && (IBIO.inputChar("Want another card? (y/n) ") == 'y')) {
      deck.deal(player);
      playerTotal = getHandTotal(player);
      System.out.println("");
      System.out.println("Your total: " + playerTotal);

      if (playerTotal > 21) {
        System.out.println("---");
        System.out.println("You went over 21!");
        return;
      }
    }

    System.out.println("---");
    System.out.println("My total: " + dealerTotal);

    while (dealerTotal < 16) {
      deck.deal(dealer);
      dealerTotal = getHandTotal(dealer);
      IBIO.input("Enter anything to continue.\n");
      System.out.println("My total: " + dealerTotal);

      if (dealerTotal > 21) {
        System.out.println("---");
        System.out.println("I went over 21!");
        return;
      }
    }

    System.out.println("---");
    System.out.println("Your total: " + playerTotal);
    System.out.println("My total: " + dealerTotal);
    System.out.println("");

    if (playerTotal > dealerTotal) {
      System.out.println("You win ... this time.");
    } else if (playerTotal < dealerTotal) {
      System.out.println("Haha, loser!");
    } else {
      System.out.println(
        "It appears that we are equally matched in this\n"
        + "completely skill-dependent game."
      );
    }
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
