public class Bill {
  public static void main(String[] args) {
    // This series of variable declarations makes me feel uneasy
    // I don't think there's a way around it though
    double tab = IBIO.inputDouble("What is the dinner tab? $");
    double tax = tab * 0.13;
    double tipPercentage = IBIO.inputDouble("What is the tip percentage? ");
    // Also, I think using this IBIO class means that I don't really
    // understand Java. I should really write my own class.
    double tip = tab * tipPercentage / 100;
    double bill = tab + tax + tip;
    // Occasionally, the double will be a perfect whole
    // or a perfect tenth, but the displayed price
    // should include two decimal places all the time
    IBIO.output(
      String.format("The bill is $%.2f", ceil(bill, 2))
    );
    int diners = IBIO.inputInt("How many diners are there? ");
    IBIO.output(
      String.format("Each diner should pay $%.2f", ceil(bill / diners, 2))
    );
  }

  static double ceil(double num, int places) {
    double roundFactor = Math.pow(10, places);
    return Math.ceil(num * roundFactor) / roundFactor;
  }
}
