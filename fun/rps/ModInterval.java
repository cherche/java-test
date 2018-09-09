public class ModInterval {
  private int lower;
  private int upper;
  private int divisor;

  public ModInterval(int[] interval, int divisor) {
    // When bounds are outside of {0, divisor - 1},
    // they get corrected to be within it
    this.lower = Math.floorMod(interval[0], divisor);
    this.upper = Math.floorMod(interval[1], divisor);
    this.divisor = divisor;
  }

  public boolean includes(int num) {
    if (num < 0 || num >= divisor) {
      return false;
    }

    if (lower <= upper) {
      // Example: {0, 3} on the divisor 5 includes 0, 1, 2, 3
      return (num >= lower) && (num <= upper);
    } else {
      // Example: {4, 1} on the divisor 5 includes 4, 0, 1
      return (num >= lower) || (num <= upper);
    }
  }
}
