public class Game {
  public static void main(String[] args) {
    // Technically, I didn't make rock-paper-scissors,
    // but I really wanted to show off my RPS class
    String[] choices = new String[] {
      "scissors",
      "rock",
      "spock",
      "paper",
      "lizard"
    };
    RPS game = new RPS(choices);
    System.out.println("Welcome to rock-paper-scissors-lizard-spock.");
    int wins = 0;
    int losses = 0;
    int ties = 0;
    int playerIndex = 0;
    int computerIndex = 0;

    if ('y' != Character.toLowerCase(IBIO.inputChar("Do you want to play (y/n)? "))) {
      return;
    }

    // Just keep playing until the player says "no more"
    do {
      computerIndex = getRandomInt(0, choices.length);

      // We really want the user to have a legitimate choice
      // That is to say, we need to ensure that the choice that the user
      // has given is indeed one of the choices in the choices array
      do {
        System.out.println("");
        System.out.println("Choose rock, paper, scissors, lizard, or spock.");
        String choice = IBIO.inputString("Your choice: ");
        playerIndex = game.getIndex(choice.toLowerCase());
      } while (playerIndex == -1);

      System.out.println("Computer choice: " + choices[computerIndex]);
      System.out.println("");

      if (playerIndex == computerIndex) {
        System.out.println("It was a tie!");
        ties++;
      } else {
        // If it wasn't a tie, we need to do some comparisons
        // between the two and create output messages
        int[] indices = game.sort(playerIndex, computerIndex);
        int winner = indices[0];
        String winnerChoice = choices[winner];
        String loserChoice = choices[indices[1]];
        String diff;

        if (playerIndex == winner) {
          diff = "win";
          wins++;
        } else {
          diff = "lose";
          losses++;
        }

        System.out.println("Well, " + winnerChoice + " beats " + loserChoice + ". You " + diff + "!");
      }
    } while ('y' == Character.toLowerCase(IBIO.inputChar("Do you want to play again (y/n)? ")));

    System.out.println("");
    System.out.println("# Stats");
    System.out.println("Wins: " + wins);
    System.out.println("Losses: " + losses);
    System.out.println("Ties: " + ties);
  }

  // Exactly as it looks. Its behavior is akin to that of Math.random()
  // as it is min-inclusive and max-exclusive
  private static int getRandomInt(int min, int max) {
    int range = max - min;
    return (int) (Math.random() * range) + min;
  }
}
