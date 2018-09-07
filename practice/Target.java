public class Target {
  public static void main(String[] args) {
    int magicValue = 365;
    int guess;

    do {
      guess = IBIO.inputInt("Integer Guess: ");
      if (guess < magicValue) {
        System.out.println(guess + " is lower than the magic value.");
      } else if (guess > magicValue) {
        System.out.println(guess + " is greater than magic value.");
      } else {
        System.out.println("You got it right! Congratulations.");
      }
    } while (magicValue != guess);
  }
}
