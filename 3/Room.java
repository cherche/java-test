import java.util.*;

/**
 * An implementation of the Room object
 * @author Ryan Nguyen
 * @version 2018-10-30
 */
public class Room {
  // public Collection<Person> people = new Collection<Person>();
  public String name;
  public Action[] actions;
  public Set<Room> links = new HashSet<Room>();

  public Room(String name, Action[] actions, Set<Room> links) {
    this.actions = actions;
    this.links = links;
  }

  public Room(String name, Action[] actions) {
    this.name = name;
    this.actions = actions;
  }
}
