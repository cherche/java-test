import java.util.*;

/**
 * Contains everything needed to actually run the game
 * @author Ryan Nguyen
 * @version 2018-11-07
 */
public class Game {
  // Obviously, set DEF_DELAY to 0 if you want to speed things up a bit
  private static int DEF_DELAY = 0;
  private static int SHORT_DELAY = 100;
  private static int MEDIUM_DELAY = 150;
  private static int LONG_DELAY = 200;
  private static Printer printer = getPrinter(DEF_DELAY, SHORT_DELAY, MEDIUM_DELAY, LONG_DELAY);
  private static boolean isFirstPlay = true;
  private static boolean hasChristinaTrust = false;
  private static boolean hasVeronicaTrust = false;
  private static boolean hasRobertTrust = false;
  private static boolean isWon = false;
  private static boolean isLost = false;
  // Although we currently just store a move count,
  // it would be more appropriate to use time
  // as the story relies so heavily on it
  private static int moveCount = 0;
  // As you may have guessed, the current room
  private static Room currentRoom;
  // Nothing more than the storage of all information in the game
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
          if (!hasChristinaTrust || !hasVeronicaTrust || !hasRobertTrust) {
            printer.dialogueln("You played a few notes but you were stared at with disapproval.");
            return;
          }

          printer.dialogueln("You played the piano using the sheet music for Dancing Queen by ABBA.");
        }
      })
    }),
    new Room("Bathroom", new Action[] {
      new Action("Inspect shelf", new Function() {
        public void run() {
          printer.dialogueChain(new String[] {
            "- Toilet paper",
            "- So\n- Much\n- Toilet Paper"
          });
        }
      }),
      new Action("Inspect counter", new Function() {
        public void run() {
          printer.dialogueChain(new String[] {
            "- Hand lotion",
            "- Hand soap",
            "- Potted flowers (yellow tulips)"
          });
        }
      })
    }),
    new Room("Kitchen", new Action[] {
      new Action("Inspect sink", new Function() {
        public void run() {
          printer.dialogueln("Half of the dishes are unwashed");
        }
      }),
      new Action("Inspect fridge", new Function() {
        public void run() {
          printer.dialogueChain("The fridge is just like me (the programmer breaking the fourth wall):");
          printer.dialogueln("A terrible mess that's falling apart.");
        }
      }),
      new Action("Inspect table", new Function() {
        public void run() {
          printer.dialogueln(
            "You see various documents of Veronica.\n"
            + "* Trigonometry Review *\n"
            + "* Macbeth *\n"
            + "* Types of Colloids *"
          );
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
      new Action("Inspect bookshelf", new Function() {
        public void run() {
          printer.dialogueln(
            "The shelves are full of textbooks:\n"
            + "Biology, chemistry, physics,\n"
            + "and so much history"
          );
        }
      }),
      new Action("Inspect table", new Function() {
        public void run() {
          printer.dialogueln(
            "A metal box ahead stands taller than you do.\n"
            + "It's but a mess of wires and metal scraps.\n"
            + "On the other hand, blueprints titled\n"
            + "\"Machine for Inspection of Past Historical Events\"\n"
            + "engulf the table from one wooden edge to the other."
          );
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
      new Action("Take quiz", new Quiz())
    }),
    new Person("Veronica Zaveri", new Action[] {
      new Action("Play Blackjack", new Blackjack()),
      new Action("Play Rock-Paper-Scissors", new RPS())
    }),
    new Person("Robert Smith", new Action[] {
      new Action("Talk", new Function() {
        public void run() {

        }
      }),
      new Action("Answer math questions", new MathQuestions()),
      new Action("Ask about flowers", new Daisy())
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
      if (moveCount == 0) {
        printer.dialogueChain(". . . . . . . .");

        // This first bit should only print the first time because
        // it would be frustratingly repetitive if it did every time
        if (isFirstPlay) {
          printer.dialogueChain(new String[] {
            "LUCIA: I say we get rid of him as soon as we can.",
            "CHRISTINA: Absolutely not! He's clearly very sick.",
            "ROBERT: Well-\n"
            + "CHRISTINA: It's just the right thing to do, keepin' him here.",
            "VERONICA: Mum, I think he's awake..."
          });
          isFirstPlay = false;
        }

        printer.dialogueln(
          "CHRISTINA: Oh dear, how are you? We found you just at our door.\n"
          + "VERONICA: You're welcome to stay here for as long as you need.\n"
          + "LUCIA: Bu-but he's a complete stranger!\n"
          + "CHRISTINA: One who's hurt and needs help!\n"
          + "ROBERT: I think we should ... should let him rest for now. We can ... we can discuss this later.\n"
          + "VERONICA: Yes, please - make yourself feel at home."
        );
      }

      runRoom(currentRoom);

      if (moveCount >= 12) {
        // Just to catch the user's attention
        printer.dialogueChain("You hear screaming from somewhere in the house.");
        printer.dialogueln(
          "CHRISTINA: Oh my god! What are you doing?!\n"
          + "ROBERT: Miss! Are you alright?\n"
          + "ROBERT: Miss! Please, just say something ... anything at all.\n"
          + "VERONICA: Mum, please stay with us.\n"
          + "Your hearing starts to fade along with the rest of your senses."
        );
      }

      if (isLost || moveCount >= 12) {
        resetGame();
      }
    }

    printer.dialogueln("You won the game. Congratulations!");
  }

  /**
   * Resets all relevant instance variables to their default value
   */
  private static void resetGame() {
    hasChristinaTrust = false;
    hasVeronicaTrust = false;
    hasRobertTrust = false;
    isLost = false;
    moveCount = 0;
    currentRoom = rooms[0];
  }

  /**
   * A one-time method to initialize game variables to the desired state
   */
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

  /**
   * Removes all irrelevant characters for text input (in this program)
   *
   * @param string a string to be sanitized
   * @return       a sanitized String
   */
  private static String sanitize(String string) {
    // Regular expressions kind of throw readability
    // out the window, but here we go:
    // 1. Turn all uppercase letters to their lowercase counterparts
    // 2. Remove all non-alphanumeric non-space characters
    // 3. Remove leading and trailing whitespace
    // 4. Compress all adjacent whitespace with a single space
    return string
      .toLowerCase()
      .replaceAll("[^0-9a-z ]+", "")
      .trim()
      .replaceAll("\\s+", " ");
  }

  /**
   * Adds rooms to the links of a room
   *
   * @param centralNode the index of the room to which links will be added
   * @param outNodes    an array of indices of rooms which will be added
   */
  private static void addLinksOut(int centralNode, int[] outNodes) {
    Room centralRoom = rooms[centralNode];

    for (int i = 0; i < outNodes.length; i++) {
      int outNode = outNodes[i];
      Room outRoom = rooms[outNode];
      centralRoom.links.add(outRoom);
    }
  }

  /**
   * For a list of rooms, adds a room to the links of each
   *
   * @param centralNode the index of the room which will be added
   * @param inNodes     an array of indices of rooms to which links will be added
   */
  private static void addLinksIn(int centralNode, int[] inNodes) {
    Room centralRoom = rooms[centralNode];

    for (int i = 0; i < inNodes.length; i++) {
      int inNode = inNodes[i];
      Room inRoom = rooms[inNode];
      inRoom.links.add(centralRoom);
    }
  }

  /**
   * For a list of rooms, adds a room to the links of each and vice-versa
   *
   * @param centralNode the index a room
   * @param nodes       an array of indices of rooms
   */
  private static void addLinks(int centralNode, int[] nodes) {
    Room centralRoom = rooms[centralNode];

    for (int i = 0; i < nodes.length; i++) {
      int node = nodes[i];
      Room room2 = rooms[node];
      centralRoom.links.add(room2);
      room2.links.add(centralRoom);
    }
  }

  /**
   * Removes a person from all rooms, then adds them to one of them
   *
   * @param person just some Person instance
   * @param dest   the index of the room to which the person will be added
   */
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

  /**
   * Creates a Printer instance
   *
   * @param defDelay    the default-length delay in milliseconds
   * @param shortDelay  the short-length delay in milliseconds
   * @param mediumDelay the medium-length delay in milliseconds
   * @param longDelay   the long-length delay in milliseconds
   */
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


  /**
   * Executes code given a room
   *
   * @param room a room
   */
  private static void runRoom(Room room) {
    // This part runs like a nested menu
    // Whenever an invalid option is submitted by
    // the user, nothing happens
    // That is to say, the game's while loop will
    // just re-open the currentRoom
    boolean hasPeople = room.people.size() > 0;
    String roomName = room.name.toUpperCase();
    String title = "\n# " + roomName + " @ " + getTime(moveCount);
    System.out.println(title);
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
      title += " : Move";
      System.out.println(title);
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

      // Basically, we don't want movement from one room to
      // another to count as a move so we'll return right here
      return;
    } else if (activity == 1) {
      title += " : Act";
      System.out.println(title);
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
      title += " : Interact";
      System.out.println(title);
      Person[] people = room.people.toArray(new Person[0]);

      for (int i = 0; i < people.length; i++) {
        Person person = people[i];
        System.out.println("[" + i + "] " + person.name);
      }

      int personIndex = IBIO.inputInt("");

      try {
        Person person = people[personIndex];
        title += " : " + person.name;
        System.out.println(title);
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

  private static String getTime(int moveCount) {
    int minutes = moveCount * 5;
    int hour = 11 + minutes / 60;
    int minute = minutes % 60;
    // The String.format() just ensures that leading zeros are included where necessary
    return String.format("%02d", hour) + ":" + String.format("%02d", minute) + "PM";
  };

  // If I were a better person, I would've modularized things a bit better,
  // but as you can see, all of these classes must be in this class
  private static class Quiz implements Function {
    public void run() {
      printer.dialogueln("Quiz");
    }
  }

  private static class Blackjack implements Function {
    public void run() {
      printer.dialogueln("Blackjack");
    }
  }

  private static class RPS implements Function {
    public void run() {
      printer.dialogueln("RPS");
    }
  }

  private static class MathQuestions implements Function {
    public void run() {
      printer.dialogueln("MathQuestions");
    }
  }

  private static class Daisy implements Function {
    public void run() {
      printer.dialogueln("Daisy");
    }
  }
}
