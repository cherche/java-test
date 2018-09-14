public class Target {
  public static void main(String[] args) {
    int magicValue = (int) (Math.random() * 1000);
    while (true) {
      int num1 = IBIO.inputInt("Enter an integer guess for my number: ");
      int num2 = IBIO.inputInt("Enter another integer guess for my number: ");
      int min = Math.min(num1, num2);
      int max = Math.max(num1, num2);
      IBIO.output("It is " + (magicValue >= min) + " that my number is greater than or equal to " + min);
      IBIO.output("It is " + (magicValue <= max) + " that my number is less than or equal to " + max);
    }
  }
}
