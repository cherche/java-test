/**
 * Asks the user for tine length, tine spacing, and handle length in order to print out a trident
 *
 * @author  Ryan Nguyen
 * @version 2018-10-24
 */
public class Trident {
  // This right here is why I don't like "what" comments
  // They're, for the most part, totally redundant
  /**
   * The number of tines
   */
  private int tineCount;
  /**
   * The length of each tine
   */
  private int tineLength;
  /**
   * The spacing between each tine
   */
  private int tineSpacing;
  /**
   * The length of the handle
   */
  private int handleLength;

  public static void main(String[] args) {
    int tineCount = IBIO.inputInt("Enter number of tines:\n");
    int tineLength = IBIO.inputInt("Enter tine length:\n");
    int tineSpacing = IBIO.inputInt("Enter tine spacing:\n");
    int handleLength = IBIO.inputInt("Enter handle length:\n");
    // If you didn't see the class, this would look like magic.
    Trident trident = new Trident(tineCount, tineLength, tineSpacing, handleLength);
    System.out.println("");
    System.out.println(trident.toString());
  }

  public Trident(int tineCount, int tineLength, int tineSpacing, int handleLength) {
    this.tineCount = tineCount;
    this.tineLength = tineLength;
    this.tineSpacing = tineSpacing;
    this.handleLength = handleLength;
  }

  /**
   * Returns the trident in a string using the values of the instance
   *
   * @return a String version of the Trident object using the instance's values
   */
  public String toString() {
    String string = "";
    // I hope you can trust that this is the trident width
    // It's just math, so don't worry too much
    // Essentially, the padded length ("*   " would be 4) is tineSpacing + 1
    // and we multiply that by the tineCount. This is almost right,
    // but we counted the additional right padding for the last prong
    // and must subtract it.
    int tridentWidth = (tineSpacing + 1) * tineCount - tineSpacing;
    String paddedTineBit = "*" + repeatString(" ", tineSpacing);
    String tineRow = repeatString(paddedTineBit, tineCount);

    // Append the tines
    for (int i = 0; i < tineLength; i++) {
      string += tineRow + "\n";
    }

    // Append the "bar" connecting them
    string += repeatString("*", tridentWidth) + "\n";
    // Get each row of the handle
    String centredHandleBit = centre("*", tridentWidth);

    // Append the handle
    for (int i = 0; i < handleLength; i++) {
      string += centredHandleBit + "\n";
    }

    return string;
  }

  /**
   * Returns a new string that centres the original string given the line width
   *
   * @param string   the string to be centred
   * @param maxWidth the maximum line line width
   * @return         a new String that centres the original
   */
  private static String centre(String string, int maxWidth) {
    int padLength = (maxWidth - string.length()) / 2;
    String padding = repeatString(" ", padLength);

    return padding + string;
  }

  /**
   * Returns a new string that is simply the original concatenated with itself a number of times
   *
   * @param string the string to be repeated
   * @param count  the number of times to repeat it
   * @return       a new String that is the original string repeated count times
   */
  private static String repeatString(String string, int count) {
    String repeated = "";

    for (int i = 0; i < count; i++) {
      repeated += string;
    }

    return repeated;
  }

  // Well, we'll add in all the getters and setters just for fun
  // In practice, maybe people want their Trident instances to change . . .
  /**
   * Returns the value of tineCount of the instance
   *
   * @return the value of tineCount
   */
  public int getTineCount() {
    return tineCount;
  }

  /**
   * Returns the value of tineLength of the instance
   *
   * @return the value of tineLength
   */
  public int getTineLength() {
    return tineLength;
  }

  /**
   * Returns the value of tineSpacing of the instance
   *
   * @return the value of tineSpacing
   */
  public int getTineSpacing() {
    return tineSpacing;
  }

  /**
   * Returns the value of handleLength of the instance
   *
   * @return the value of handleLength
   */
  public int getHandleLength() {
    return handleLength;
  }

  /**
   * Sets the value of tineCount of the instance
   *
   * @param tineCount the new value of tineCount
   */
  public void setTineCount(int tineCount) {
    this.tineCount = tineCount;
  }

  /**
   * Sets the value of tineLength of the instance
   *
   * @param tineLength the new value of tineLength
   */
  public void setTineLength(int tineLength) {
    this.tineLength = tineLength;
  }

  /**
   * Sets the value of tineSpacing of the instance
   *
   * @param tineSpacing the new value of tineSpacing
   */
  public void setTineSpacing(int tineSpacing) {
    this.tineSpacing = tineSpacing;
  }

  /**
   * Sets the value of handleLength of the instance
   *
   * @param handleLength the new value of handleLength
   */
  public void setHandleLength(int handleLengh) {
    this.handleLength = handleLength;
  }
}
