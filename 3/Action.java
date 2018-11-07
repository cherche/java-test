/**
 * Stores a Function alongside some String description
 * @author Ryan Nguyen
 * @version 2018-11-07
 */
public class Action {
  public String name;
  public Function fn;

  public Action(String name, Function fn) {
    this.name = name;
    this.fn = fn;
  }

  /**
   * Runs the contained method
   */
  public void run() {
    this.fn.run();
  }
}
