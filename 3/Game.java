/**
 * Contains everything needed to actually run the game
 * @author Ryan Nguyen
 * @version 2018-10-30
 */
public class Game {
  private static boolean isWon = false;
  private static boolean isLost = false;
  // As you may have guessed, the current room
  private static Room currentRoom;
  // Nothing more than the storage of all information in the game
  private static Room[] rooms = new Room[] {
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
          animatedPrint("You ate food.", 100);
          System.out.println("");
          String message;

          if (Math.random() < 0.5) {
            isWon = true;
            message = "That was some incredible food.";
          } else {
            isLost = true;
            message = "You don't feel so good . . . it was probably poisonous.";
          }

          animatedPrint(message, 100);
          System.out.println("");
        }
      })
    })
  };
  private static Person[] people = new Person[] {
    new Person("Bob", new Action[] {
      new Action("Talk", new Function() {
        public void run() {
          animatedPrint("I really don't like you.", 100);
          System.out.println("");
          animatedPrint("DIE!", 400);
          System.out.println("");
          isLost = true;
        }
      })
    })
  };

  public static void main(String[] args) {
    initGame();
    rooms[0].people.add(people[0]);

    while (!isWon) {
      runRoom(currentRoom);

      if (isLost) {
        // At the moment, the time loop is not explained to the user
        // They must discover it themselves
        System.out.println("");
        animatedPrint(". . .", 700);
        System.out.println("");
        resetGame();
      }
    }

    System.out.println("You won the game. Congratulations!");
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
    System.out.println("Note: Enter an invalid response at any point to escape menus.");
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

        }
      } catch (Exception e) {

      }
    }
  }

  private static void resetGame() {
    currentRoom = rooms[0];
    isLost = false;
  }

  private static void initGame() {
    // Link all the rooms together
    link(rooms, 0, new int[] {1});
    resetGame();
  }

  private static void animatedPrint(String string, int delay) {
    for (int i = 0; i < string.length(); i++) {
      try {
        System.out.print(string.charAt(i));
        Thread.sleep(delay);
      } catch (Exception e) {

      }
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
