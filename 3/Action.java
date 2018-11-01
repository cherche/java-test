/**
 * Stores a Function alongside some String description
 * @author Ryan Nguyen
 * @version 2018-10-30
 */
public class Action {
  public String desc;
  public Function fn;

  public Action(String desc, Function fn) {
    this.desc = desc;
    this.fn = fn;
  }

  public void run() {
    this.fn.run();
  }
}
