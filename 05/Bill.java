public class Bill {
  public static void main(String[] args) {
    // This series of variable declarations makes me feel uneasy
    // I don't think there's a way around it though
    double tab = IBIO.inputDouble("What is the dinner tab? $");
    double tax = tab * 0.13;
    double tipRate = IBIO.inputDouble("What is the tip percentage? ");
    // Also, I think using this IBIO class means that I don't really
    // understand Java. I should really write my own class.
    double tip = tab * tipRate / 100;
    double bill = tab + tax + tip;
    IBIO.output("The bill is $" + round(bill, 2));
    int diners = IBIO.inputInt("How many diners are there? ");
    // Technically, this should be rounded up to the nearest cent
    IBIO.output("Each diner should pay $" + round(bill / diners, 2));
  }

  static double round(double num, int places) {
    double roundFactor = Math.pow(10, places);
    return Math.round(num * roundFactor) / roundFactor;
  }
}
