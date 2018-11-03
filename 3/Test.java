import java.util.*;

public class Test {
  public static void main(String[] args) {
    Map<Character, Integer> delays = new HashMap<Character, Integer>();
    delays.put('\n', 300);
    delays.put('i', 0);
    Printer printer = new Printer(100, delays);
    printer.dialogueChain(new String[] {
      "this is\na test message\nsplit across\nmany lines",
      "this is a second test message\nalso split across many lines",
      "this is a final short message"
    });
  }
}
