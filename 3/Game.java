/**
 * Contains everything needed to actually run the game
 * @author Ryan Nguyen
 * @version 2018-10-30
 */
public class Game {
  // As you may have guessed, the current room
  private static Room currentRoom;

  public static void main(String[] args) {
    // Nothing more than the storage of all information in the game
    Room[] rooms = new Room[] {
      new Room("Living Room", new Action[] {
        new Action("Watch TV", new Function() {
          public void run() {
            System.out.println("You turned it on but got bored");
          }
        }),
        new Action("Roll a die", new Function() {
          public void run() {
            int value = (int) (Math.random() * 6) + 1;
            System.out.println("You rolled a " + value);
          }
        })
      }),
      new Room("Kitchen", new Action[] {
        new Action("Eat", new Function() {
          public void run() {
            System.out.println("You ate food");
          }
        })
      })
    };
    currentRoom = rooms[0];
    link(rooms, 0, new int[] {1});

    while (true) {
      runRoom(currentRoom);
    }
  }

  private static void runRoom(Room room) {
    // This part runs like a nested menu
    // Whenever an invalid option is submitted by
    // the user, nothing happens
    // That is to say, the game's while loop will
    // just re-open the currentRoom
    boolean hasPeople = room.people.size() > 0;
    System.out.println("\n# " + room.name.toUpperCase());
    System.out.println("[0] Move");
    System.out.println("[1] Act");

    // Not all rooms will have a person in them
    // at any given time
    // There is no reason to print out this option
    // if it can't be used
    if (hasPeople) {
      System.out.println("[2] Interact");
    }

    int activity = IBIO.inputInt("");

    if (activity == 0) {
      System.out.println("\n## Move");
      Room[] links = room.links.toArray(new Room[0]);

      for (int i = 0; i < links.length; i++) {
        Room link = links[i];
        System.out.println("[" + i + "] " + link.name);
      }

      int linkIndex = IBIO.inputInt("");

      try {
        currentRoom = links[linkIndex];
      } catch (Exception e) {
        System.out.println("That is not a valid choice.");
      }
    } else if (activity == 1) {
      System.out.println("\n## Act");
      Action[] actions = room.actions;

      for (int i = 0; i < actions.length; i++) {
        Action action = actions[i];
        System.out.println("[" + i + "] " + action.name);
      }

      int actionIndex = IBIO.inputInt("");

      try {
        Action action = actions[actionIndex];
        action.run();
      } catch (Exception e) {
        System.out.println("That is not a valid choice.");
      }
    } else if (activity == 2 && hasPeople) {
      System.out.println("\n## Interact");
      Person[] people = room.people.toArray(new Person[0]);

      for (int i = 0; i < people.length; i++) {
        Person person = people[i];
        System.out.println("[" + i + "] " + person.name);
      }

      int personIndex = IBIO.inputInt("");

      try {
        Person person = people[personIndex];
        Action[] actions = person.actions;

        for (int i = 0; i < actions.length; i++) {
          Action action = actions[i];
          System.out.println("[" + i + "] " + action.name);
        }

        int actionIndex = IBIO.inputInt("");

        try {
          Action action = actions[actionIndex];
          action.run();
        } catch (Exception e) {
          System.out.println("That is not a valid choice.");
        }
      } catch (Exception e) {
        System.out.println("That is not a valid choice.");
      }
    } else {
      System.out.println("That is not a valid choice.");
    }
  }

  private static String sanitize(String string) {
    return string
      .toLowerCase()
      .replaceAll("[^0-9a-z ]+", "")
      .trim()
      .replaceAll("\\s+", " ");
  }

  private static void link(Room[] rooms, int centralNode, int[] linkedNodes) {
    Room centralRoom = rooms[centralNode];

    for (int i = 0; i < linkedNodes.length; i++) {
      int linkedNode = linkedNodes[i];
      Room linkedRoom = rooms[linkedNode];
      centralRoom.links.add(linkedRoom);
      linkedRoom.links.add(centralRoom);
    }
  }
}
