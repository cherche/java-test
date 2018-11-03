/**
 * Contains an array of Action instances and a name
 * @author Ryan Nguyen
 * @version 2018-10-30
 */
public class Person {
  public String name;
  public Action[] actions;

  public Person(String name, Action[] actions) {
    this.name = name;
    this.actions = actions;
  }
}
