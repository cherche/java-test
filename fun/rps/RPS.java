public class RPS {
  // I hear that it's good practice to keep
  // your things private as much as you can
  private String[] choices;
  private int length;

  // Technically only defined for an odd number of choices since
  // that's the only way to have a uniform distribution of winning/losing
  public RPS(String[] choices) {
    // In order to work properly, the choices array actually has to be ordered
    // such that for each element, all of the elements that it beats are grouped
    // with one another, and are directly behind it (cyclically)
    this.choices = choices;
    // Example: {"Rock", "Spock", "Paper", "Lizard", "Scissors"}
    //     Rock (0)  beats    Lizard (3)  and  Scissors (4)
    //    Spock (1)  beats  Scissors (4)  and      Rock (0)
    //    Paper (2)  beats      Rock (0)  and     Spock (1)
    //   Lizard (3)  beats     Spock (1)  and     Paper (2)
    // Scissors (4)  beats     Paper (2)  and    Lizard (3)
    this.length = choices.length;
  }

  public String getName(int i) {
    return choices[i];
  }

  // Probably used for generating computer choice
  public String getRandomName() {
    int i = Chance.getRandomInt(0, length);
    return getName(i);
  }

  // In a way, this method is more like getNotLoser()
  // When it's a tie, there is no error thrown, it
  // just returns the second argument j
  public int getWinner(int i, int j) {
    // Because we're working with integers (and length is odd),
    // this expression is equivalent to length / 2
    // It is written this way for readability
    int half = (length - 1) / 2;
    // It's probably a bad idea to write all of this in one line
    // but I'm having trouble coming up with variable names . . .
    boolean iWins = (new ModInterval(new int[]{i - half, i - 1}, length)).includes(j);

    // Technically, it is not necessary to have an else statement
    // because of the return, but this improves readability
    if (iWins) {
      return i;
    } else {
      // This is also returned when i == j
      return j;
    }
  }
}
