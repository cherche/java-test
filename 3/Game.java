import java.util.*;

/*
 0 Living Room
 1 Bathroom
 2 Kitchen
 3 Laundry Room
 4 Christina's Room
 5 Lucia's Room
 6 Veronica's Room
 7 Workshop
 8 Christina's Bathroom
 9 Christina's Closet
10 Lucia's Closet
11 Veronica's Closet
*/

/**
 * Contains everything needed to actually run the game
 * @author Ryan Nguyen
 * @version 2018-10-30
 */
public class Game {
  // Obviously, set DEF_DELAY to 0 if you want to speed things up a bit
  private static int DEF_DELAY = 30;
  private static int SHORT_DELAY = 150;
  private static int MEDIUM_DELAY = 200;
  private static int LONG_DELAY = 250;
  private static Printer printer = getPrinter(DEF_DELAY, SHORT_DELAY, MEDIUM_DELAY, LONG_DELAY);
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
    new Room("Living Room", new Action[] {
      new Action("Inspect piano", new Function() {
        public void run() {
          printer.dialogueln("There is sheet music on the music stand.");
          printer.dialogueChain("* Dancing Queen by ABBA *");
        }
      }),
      new Action("Play the piano", new Function() {
        public void run() {
          printer.dialogueln("You played the piano, my dude");
        }
      })
    }),
    new Room("Bathroom", new Action[] {
      new Action("Inspect shelf", new Function() {
        public void run() {
          printer.dialogueln("You inspected the shelf, my man");
        }
      }),
      new Action("Inspect counter", new Function() {
        public void run() {
          printer.dialogueln("You inspected the counter");
        }
      })
    }),
    new Room("Kitchen", new Action[] {
      new Action("Inspect sink", new Function() {
        public void run() {
          printer.dialogueln("Half of the dishes are, like, unwashed");
        }
      }),
      new Action("Inspect fridge", new Function() {
        public void run() {
          printer.dialogueChain(new String[] {
            "The fridge is just like me (the writer breaking the fourth wall right now).",
            "A total mess."
          });
        }
      }),
      new Action("Inspect table", new Function() {
        public void run() {
          printer.dialogueln("You, like, see Veronica's homework");
        }
      })
    }),
    new Room("Laundry Room", new Action[] {
      new Action("Inspect ... everything", new Function() {
        public void run() {
          printer.dialogueln("A long flowing red dress lies on the table");
          printer.dialogueChain("Christina's clothes pile four feet high");
          printer.dialogueln(
            "and they're still unwashed\n"
            + "Veronica's clothes are neatly folded;"
            + "\nthey're ready to take to her closet"
          );
        }
      })
    }),
    new Room("Christina's Room", new Action[] {
      new Action("", new Function() {
        public void run() {

        }
      })
    }),
    new Room("Lucia's Room", new Action[] {
      new Action("", new Function() {
        public void run() {

        }
      })
    }),
    new Room("Veronica's Room", new Action[] {
      new Action("", new Function() {
        public void run() {

        }
      })
    }),
    new Room("Workshop", new Action[] {
      new Action("", new Function() {
        public void run() {

        }
      })
    }),
    new Room("Christina's Bathroom", new Action[] {
      new Action("", new Function() {
        public void run() {

        }
      })
    }),
    new Room("Christina's Closet", new Action[] {
      new Action("", new Function() {
        public void run() {

        }
      })
    }),
    new Room("Lucia's Closet", new Action[] {
      new Action("", new Function() {
        public void run() {

        }
      })
    }),
    new Room("Veronica's Closet", new Action[] {
      new Action("", new Function() {
        public void run() {

        }
      })
    })
  };
  private static Person[] people = new Person[] {
    new Person("Christina Park", new Action[] {
      new Action("Talk", new Function() {
        public void run() {

        }
      }),
      new Action("Take quiz", new Function() {
        public void run() {

        }
      })
    }),
    new Person("Veronica Zaveri", new Action[] {
      new Action("Play Blackjack", new Function() {
        public void run() {

        }
      }),
      new Action("Play Rock-Paper-Scissors", new Function() {
        public void run() {

        }
      })
    }),
    new Person("Robert Smith", new Action[] {
      new Action("Talk", new Function() {
        public void run() {

        }
      }),
      new Action("Answer math questions", new Function() {
        public void run() {

        }
      }),
      new Action("Ask about flowers", new Function() {
        public void run() {

        }
      })
    }),
    new Person("Lucia Yom", new Action[] {
      new Action("Talk", new Function() {
        public void run() {

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
    addLinks(0, new int[] {1, 2, 3, 4, 5, 6, 7});
    addLinksIn(0, new int[] {8, 9, 10, 11});
    addLinks(4, new int[] {8, 9});
    addLinks(8, new int[] {9});
    addLinks(5, new int[] {10});
    addLinks(6, new int[] {11});
    // Add all of the people to their respective rooms
    movePerson(people[0], 0);
    movePerson(people[1], 0);
    movePerson(people[2], 0);
    movePerson(people[3], 0);
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

  private static void addLinksOut(int centralNode, int[] outNodes) {
    Room centralRoom = rooms[centralNode];

    for (int i = 0; i < outNodes.length; i++) {
      int outNode = outNodes[i];
      Room outRoom = rooms[outNode];
      centralRoom.links.add(outRoom);
    }
  }

  private static void addLinksIn(int centralNode, int[] inNodes) {
    Room centralRoom = rooms[centralNode];

    for (int i = 0; i < inNodes.length; i++) {
      int inNode = inNodes[i];
      Room inRoom = rooms[inNode];
      inRoom.links.add(centralRoom);
    }
  }

  private static void addLinks(int centralNode, int[] nodes) {
    Room centralRoom = rooms[centralNode];

    for (int i = 0; i < nodes.length; i++) {
      int node = nodes[i];
      Room room2 = rooms[node];
      centralRoom.links.add(room2);
      room2.links.add(centralRoom);
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

  private static Printer getPrinter(int defDelay, int shortDelay, int mediumDelay, int longDelay) {
    Map<Character, Integer> delays = new HashMap<Character, Integer>();
    delays.put(',', shortDelay);
    delays.put(';', mediumDelay);
    delays.put('.', mediumDelay);
    delays.put('?', mediumDelay);
    delays.put('!', mediumDelay);
    delays.put('\n', longDelay);
    return new Printer(defDelay, delays);
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
