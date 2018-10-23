// 11 minutes 30 seconds
public class BlackJack {
  public static void main(String[] args) {
    int playerTotal = getCardValue(2);
    int dealerTotal = getCardValue(2);
    int playerCardCount = 2;
    System.out.println("Player total: " + playerTotal);

    while (playerTotal < 21 && playerCardCount < 5) {
      if ('y' != IBIO.inputChar("Do you want another card? (y/N) ")) {
        break;
      }

      playerTotal += getCardValue();
      playerCardCount++;
      System.out.println("Player total: " + playerTotal);
    }

    if (playerTotal == 21) {
      System.out.println("You have 21! You win!");
      return;
    } else if (playerTotal > 21) {
      System.out.println("Woah, you're over 21. You lose.");
      return;
    }

    System.out.println("Dealer total: " + dealerTotal);

    while (dealerTotal < 16 || dealerTotal < playerTotal) {
      IBIO.inputString("Press ENTER to continue.");
      dealerTotal += getCardValue();
      System.out.println("Dealer total: " + dealerTotal);
    }

    if (dealerTotal > 21) {
      System.out.println("Woah, the dealer's over 21. You win!");
      return;
    }

    if (dealerTotal > playerTotal) {
      System.out.println("The dealer wins!");
    } else if (dealerTotal == playerTotal) {
      System.out.println("It's a tie!");
    } else {
      System.out.println("You win!");
    }
  }

  private static int getCardValue(int count) {
    int total = 0;

    for (int i = 0; i < count; i++) {
      total += getCardValue();
    }

    return total;
  }

  private static int getCardValue() {
    return (int) (Math.random() * 11) + 1;
  }
}
