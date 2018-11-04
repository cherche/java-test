import java.util.*;

/**
 * Contains everything needed to actually run the game
 * @author Ryan Nguyen
 * @version 2018-10-30
 */
public class Game2 {
  private static Printer printer = getPrinter();
  private static boolean isWon = false;
  private static boolean isLost = false;
  // Although we currently just store a move count,
  // it would be more appropriate to use time
  // as the story relies so heavily on it
  private static int moveCount = 0;
  // As you may have guessed, the current room
  private static Room currentRoom;
  // Nothing more than the storage of all information in the game
  private static Room[] rooms = new Room[] {
    new Room("Place", new Action[] {
      new Action("Talk to self", new Function() {
        public void run() {
          printer.dialogueln("Message.");
        }
      }),
      new Action("Roll a die", new Function() {
        public void run() {
          int value = (int) (Math.random() * 6) + 1;
          printer.dialogueln("You rolled a " + value + ".");
        }
      })
    }),
    new Room("Kitchen", new Action[] {
      new Action("Eat", new Function() {
        public void run() {
          String message;

          if (Math.random() < 0.5) {
            isWon = true;
            message = "That was some incredible food.";
          } else {
            isLost = true;
            message = "You don't feel so good . . . it was probably poisonous.";
          }

          printer.dialogueln("You ate food.\n" + message);
        }
      })
    })
  };
  private static Person[] people = new Person[] {
    new Person("Alice", new Action[] {
      new Action("Talk", new Function() {
        public void run() {
          printer.dialogueln("\"You lose the game\"");
          isLost = true;
        }
      })
    }),
    new Person("Bob", new Action[] {
      new Action("Eat", new Function() {
        public void run() {
          printer.dialogueln("That is cannibalism.\nBut I guess I'll give you the win.");
          isWon = true;
        }
      })
    })
  };

  public static void main(String[] args) {
    System.out.println("Note: Enter an invalid response at any point to escape menus.");
    initGame();

    while (!isWon) {
      // Just for debugging
      // System.out.println("Move count: " + moveCount);
      runRoom(currentRoom);

      if (isLost) {
        // At the moment, the time loop is not explained to the user
        // They must discover it themselves
        printer.dialogueChain(". . .");
        resetGame();
      }
    }

    printer.dialogueln("You won the game. Congratulations!");
  }

  private static void resetGame() {
    isLost = false;
    moveCount = 0;
    currentRoom = rooms[0];
  }

  private static void initGame() {
    // Link all the rooms together
    link(rooms, 0, new int[] {1});
    // Add all of the people to their respective rooms
    movePerson(people[0], 0);
    movePerson(people[1], 1);
    // Call resetGame()
    resetGame();
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

  private static void movePerson(Person person, int dest) {
    // This is pretty inefficient
    // Ideally, since a person is expected to be in only one room at a time,
    // this method would know where they are and remove them
    for (int i = 0; i < rooms.length; i++) {
      Room room = rooms[0];
      room.people.remove(person);
    }

    rooms[dest].people.add(person);
  }

  private static Printer getPrinter() {
    Map<Character, Integer> delays = new HashMap<Character, Integer>();
    delays.put(',', 150);
    delays.put(';', 200);
    delays.put('.', 200);
    delays.put('?', 200);
    delays.put('!', 200);
    delays.put('\n', 250);
    return new Printer(75, delays);
  }

  private static void runRoom(Room room) {
    // This part runs like a nested menu
    // Whenever an invalid option is submitted by
    // the user, nothing happens
    // That is to say, the game's while loop will
    // just re-open the currentRoom
    boolean hasPeople = room.people.size() > 0;
    String roomName = room.name.toUpperCase();
    System.out.println("\n# " + roomName);
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
      System.out.println("\n# " + roomName + " : Move");
      Room[] links = room.links.toArray(new Room[0]);

      for (int i = 0; i < links.length; i++) {
        Room link = links[i];
        System.out.println("[" + i + "] " + link.name);
      }

      int linkIndex = IBIO.inputInt("");

      try {
        currentRoom = links[linkIndex];
      } catch (Exception e) {
        return;
      }
    } else if (activity == 1) {
      System.out.println("\n# " + roomName + " : Act");
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
        return;
      }
    } else if (activity == 2 && hasPeople) {
      System.out.println("\n# " + roomName + " : Interact");
      Person[] people = room.people.toArray(new Person[0]);

      for (int i = 0; i < people.length; i++) {
        Person person = people[i];
        System.out.println("[" + i + "] " + person.name);
      }

      int personIndex = IBIO.inputInt("");

      try {
        Person person = people[personIndex];
        System.out.println("\n# " + roomName + " : Interact : " + person.name);
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
          return;
        }
      } catch (Exception e) {
        return;
      }
    } else {
      return;
    }

    // And finally, if we got through all of that with no errors
    // (and therefore no returning):
    moveCount++;
  }
}
