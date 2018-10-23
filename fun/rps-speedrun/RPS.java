/*
@author: Ryan Nguyen
@version: 2018-10-23
@purpose: Play rock-paper-scissors with user and CPU
Completed in less than 10 minutes, because I hate myself
*/

public class RPS {
  public static void main(String[] args) {
    System.out.println("Welcome to rock-paper-scissors.");

    if ('y' != IBIO.inputChar("Do you want to play? (y/N) ")) {
      return;
    }

    int ties = 0;
    int wins = 0;
    int losses = 0;

    do {
      System.out.println("");
      String playerChoice = IBIO.inputString("Choose rock, paper, or scissors. ");
      String computerChoice = "";
      boolean playerWins = false;
      int index = (int) (Math.random() * 3);

      if (index == 0) {
        computerChoice = "rock";
      } else if (index == 1) {
        computerChoice = "paper";
      } else {
        computerChoice = "scissors";
      }

      System.out.println("Computer choice: " + computerChoice);

      // If it's a tie, we don't need to deal with any additional comparison
      if (playerChoice.equals(computerChoice)) {
        System.out.println("It's a tie!");
        ties++;
        continue;
      }

      // If it's not a tie, check to see if the player won or lost
      if (playerChoice.equals("rock")) {
        playerWins = computerChoice.equals("scissors");
      } else if (playerChoice.equals("paper")) {
        playerWins = computerChoice.equals("rock");
      } else {
        playerWins = computerChoice.equals("paper");
      }

      // Now, just print out the result
      if (playerWins) {
        wins++;
        System.out.println(playerChoice + " beats " + computerChoice);
      } else {
        losses++;
        System.out.println(playerChoice + " loses to " + computerChoice);
      }
    } while ('y' == IBIO.inputChar("Do you want to play again? (y/N) "));

    System.out.println("");
    System.out.println("Wins: " + wins);
    System.out.println("Ties: " + ties);
    System.out.println("Losses: " + losses);
  }
}
