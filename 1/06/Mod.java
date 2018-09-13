public class Mod {
  public static void main(String[] args) {
    int bin1 = IBIO.inputInt("Enter a binary number (max 8 bits): ");
    int dec1 = toDec(bin1);
    IBIO.output("That's " + dec1 + " in decimal.");
    int dec2 = IBIO.inputInt("Enter a decimal number (0-255): ");
    int bin2 = toBin(dec2);
    IBIO.output("That's " + bin2 + " in binary.");

    int lastDigitDec1 = dec1 % 10;
    int lastDigitBin2 = bin2 % 2;
    IBIO.output("The last digits are " + lastDigitDec1 + " and " + lastDigitBin2);

    int processed1 = lastDigitDec1 + 5;
    int processed2 = lastDigitBin2 + 5;
    IBIO.output("Adding five to each yields " + processed1 + " and " + processed2);

    int prod = processed1 * processed2;
    IBIO.output(processed1 + " * " + processed2 + " is " + prod);

    double sqrt = Math.sqrt(prod);
    IBIO.output("The square root of " + prod + " is " + sqrt);
  }

  private static int toDec(int bin) {
    int dec = 0;
    int placeValue = 1;

    // Up to 8 bits
    for (int i = 0; i < 8; i++) {
      int dit = bin % 10;
      dec += dit * placeValue;
      bin /= 10;
      placeValue *= 2;
    }

    return dec;
  }

  private static int toBin(int dec) {
    int bin = 0;
    int placeValue = 1;

    // Up to 8 bits
    for (int i = 0; i < 8; i++) {
      int bit = dec % 2;
      bin += bit * placeValue;
      dec /= 2;
      placeValue *= 10;
    }

    return bin;
  }
}
