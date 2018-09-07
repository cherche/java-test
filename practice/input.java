public class input {
  public static void main(String[] args) {
    // Everyone loves the Collatz conjecture, right?
    int num = IBIO.inputInt("Starting Number:\n");
    int count = 0;

    // At some point, I thought about storing all of the entries in a list,
    // but I don't really understand Java data structures . . .
    while (num != 1) {
      if (num % 2 == 0) {
        num = num / 2;
      } else {
        // If a future Ryan is confused about the unnecessary
        // "else" statement, it's just for readability.
        //                             - Ryan from 2018.09.05
        num = 3 * num + 1;
      }

      System.out.println(num);
      count++;
    }

    System.out.println("Count: " + String.valueOf(count));
  }
}
