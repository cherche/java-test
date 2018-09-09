// If this looks like garbage, it's because I felt challenged to minimize the
// number of lines of code, but that's a pretty meaningless metric anyway.
// Real code golfers don't really need new lines.
public class MillieMuffins {
  public static void main(String[] args) {
    System.out.print("Welcome to Millie's Marvelous Muffins!\n\n**If you like muffins, ours are marvelous!**\n\n");
    int muffinCount = IBIO.inputInt("How many muffins would you like? ");
    int costPerMuffin = (muffinCount / 10 >= 4) ? 1 : (new int[] {5, 3, 2, 2})[muffinCount / 10];
    System.out.print("The total cost per muffin is $" + costPerMuffin + "\nThe total cost is $" + costPerMuffin * muffinCount + "\n");
  }
}
