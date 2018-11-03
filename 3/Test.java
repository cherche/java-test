import java.util.*;

public class Test {
  public static void main(String[] args) {
    Map<Character, Integer> delays = new HashMap<Character, Integer>();
    delays.put('\n', 1000);
    delays.put('i', 0);
    Printer printer = new Printer(100, delays);
    printer.dialogue("this is\na test message\nsplit across\nmany lines");
  }
}
