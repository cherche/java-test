import java.util.*;

public class Printer {
  // Not strictly necessary to make this public,
  // but in our case, it will simplify things later on
  public Map<Character, Integer> delays;
  public int defDelay;

  public Printer(int defDelay) {
    this.defDelay = defDelay;
    this.delays = new HashMap<Character, Integer>();
  }

  public Printer(int defDelay, Map<Character, Integer> delays) {
    this.defDelay = defDelay;
    this.delays = delays;
  }

  public void dialogue(String string) {
    for (int i = 0; i < string.length(); i++) {
      char character = string.charAt(i);
      System.out.print(character);
      int delay;

      try {
        delay = delays.get(character);
      } catch (Exception e) {
        delay = defDelay;
      }

      wait(delay);
    }
  }

  public void dialogueln(String string) {
    dialogue(string);
    System.out.println("");
  }

  public void dialogueChain(String[] strings) {
    for (int i = 0; i < strings.length; i++) {
      dialogue(strings[i]);
      // It's nice that IBIO.input() will add a new line for us
      IBIO.input("");
    }
  }

  public void wait(int delay) {
    try {
      Thread.sleep(delay);
    } catch (Exception e) {

    }
  }
}
