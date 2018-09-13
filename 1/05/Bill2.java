public class Bill2 {
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
    int dinerCount = IBIO.inputInt("How many diners are there? ");
    double costPerDiner = ceil(bill / dinerCount, 2);
    IBIO.output("\nBill\n--------");

    for (int i = 0; i < dinerCount; i++) {
      String desc = getRandomString(descs);
      String noun = getRandomString(nouns);
      String name = String.format(desc, noun);
      IBIO.output(String.format("%s: $%.2f", name, costPerDiner));
    }
  }

  private static String getRandomString(String[] strings) {
    int index = (int) (Math.random() * strings.length);
    return strings[index];
  }

  private static double ceil(double num, int places) {
    double roundFactor = Math.pow(10, places);
    return Math.ceil(num * roundFactor) / roundFactor;
  }

  private static String[] descs = new String[] {
    "%s that can't tell left from right",
    "ugly %s",
    "kind of mean %s",
    "terrible-as-my-mother-in-law %s",
    "inconsiderate %s",
    "impatient %s",
    "%s that didn't wash hands after going to washroom",
    "smelly %s",
    "perfect %s who makes me feel insecure",
    "face-made-for-radio %s",
    "lying %s",
    "%s that somehow got a bachelor's degree",
    "just a %s, no comment",
    "%s, the center of the universe",
    "two-faced %s",
    "my ex-spouse %s",
  };

  private static String[] nouns = new String[] {
    "baby",
    "boy",
    "cat",
    "doctor",
    "dog",
    "ghost",
    "girl",
    "glass of orange juice",
    "jogger",
    "lady",
    "lawyer",
    "man",
    "physicist",
    "pirate",
    "programmer",
    "skater",
    "train conductor",
    "witch"
  };
}
