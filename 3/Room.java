import java.util.*;

/**
 * An implementation of the Room object
 * @author Ryan Nguyen
 * @version 2018-11-07
 */
public class Room {
  public String name;
  public Action[] actions;
  public Set<Room> links = new HashSet<Room>();
  public Set<Person> people = new HashSet<Person>();

  public Room(String name, Action[] actions) {
    this.name = name;
    this.actions = actions;
  }

  /*
  public Room(String name, Action[] actions, Set<Room> links) {
    this.name = name;
    this.actions = actions;
    this.links = links;
  }
  */
}
