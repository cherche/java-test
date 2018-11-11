import java.util.*;

/**
 * Adds nice methods for string outputs
 * @author Ryan Nguyen
 * @version 2018-11-07
 */
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

  /**
   * Prints a string character-by-character
   *
   * @param string the String to be printed
   */
  public void dialogue(String string) {
    // Essentially, if the delay is 0, why bother iterating anyway?
    // In words, the case of defDelay == 0 overrides the behaviour of
    // dialogue entirely. There never be a wait().
    if (defDelay == 0) {
      System.out.print(string);
      return;
    }

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

  /**
   * Calls dialogue() on a string then prints a new line
   *
   * @param string the String to be printed
   */
  public void dialogueln(String string) {
    dialogue(string);
    System.out.println("");
  }

  /**
   * Calls dialogueln() on a string, then opens a prompt to pause the program until the ENTER key is pressesd
   *
   * @param string the String to be printed
   */
  public void dialogueChain(String string) {
    dialogue(string);

    if (defDelay == 0) {
      // Again, we wish to make this Printer super fast
      // if the defDelay is 0
      System.out.println("");
    } else {
      // It's nice that IBIO.input() will add a new line for us
      IBIO.input("");
    }
  }

  /**
   * Calls dialogueln() on each string, also opens a prompt after each string is printed
   *
   * @param strings an array of String instances to be printed
   */
  public void dialogueChain(String[] strings) {
    for (int i = 0; i < strings.length; i++) {
      dialogueChain(strings[i]);
    }
  }

  /**
   * Pauses the program for some length of time
   *
   * @param delay the time for which the program will be paused in milliseconds
   */
  public void wait(int delay) {
    try {
      Thread.sleep(delay);
    } catch (Exception e) {

    }
  }
}
