import java.util.*;

/**
 * Contains everything needed to actually run the game
 * @author Ryan Nguyen
 * @version 2018-11-07
 */
public class Game {
  // Obviously, set DEF_DELAY to 0 if you want to speed things up a bit
  private static int DEF_DELAY = 10;
  private static int SHORT_DELAY = 75;
  private static int MEDIUM_DELAY = 125;
  private static int LONG_DELAY = 175;
  private static Printer printer = getPrinter(DEF_DELAY, SHORT_DELAY, MEDIUM_DELAY, LONG_DELAY);
  private static boolean isFirstPlay = true;
  private static Room veronicaRoom;
  private static Room robertRoom;
  private static boolean hasChristinaTrust = false;
  private static boolean hasVeronicaTrust = false;
  private static boolean hasRobertTrust = false;
  private static boolean hasPlayedPiano = false;
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
  */
  private static Room[] rooms = new Room[] {
    new Room("Living Room", new Action[] {
      new Action("Inspect piano", new Function() {
        public void run() {
          printer.dialogueln("There is sheet music on the music stand.");
          printer.dialogueChain("* Dancing Queen by ABBA *");

          if (hasPlayedPiano) {
            printer.dialogueln("You open up the piano and find a journal.");
            System.out.println("/=====/");
            System.out.println("|...../");
            System.out.println("|LUCIA/");
            System.out.println("|...../");
            System.out.println("L_____/");
            isWon = true;
          }
        }
      }),
      new Action("Play the piano", new Function() {
        public void run() {
          if (!hasChristinaTrust || !hasVeronicaTrust || !hasRobertTrust) {
            printer.dialogueln("You played a few notes but you were stared at with disapproval.");
            return;
          }

          hasPlayedPiano = true;
          printer.dialogueln(
            "You played the piano using the sheet music for Dancing Queen by ABBA. "
            + "Oddly, the an entire octave of keys are completely stuck, but everything else plays fine. "
            + "How frustrating."
          );
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
      new Action("Inspect desk", new Function() {
        public void run() {
          printer.dialogueln(
            "The desk has a three-foot tall stack of books about time travel. "
            + "A notebook lays open next to them. It has various equations "
            + "scribbled out, but the rest is totally illegible. "
            + "Thank god Christina isn't a doctor."
          );
        }
      })
    }),
    new Room("Lucia's Room", new Action[] {
      new Action("Inspect room", new Function() {
        public void run() {
          printer.dialogueln(
            "The room is very well-kept, but eerily lacks any personalization. "
            + "There are no photographs, paintings, or even skin care products anywhere. "
            + "In fact, the walls are their original white, and the room contains the "
            + "bare minimum for a bedroom: a bed, and empty small table, and an upright chair."
          );
        }
      }),
      new Action("Inspect closet", new Function() {
        public void run() {
          printer.dialogueln(
            "Maid uniforms fill the closet from wall to wall. "
            + "The top shelf is scattered with what resembles American currency, "
            + "but you've never seen the faces before. It also has a set of "
            + "all-black clothing."
          );
          printer.dialogueChain("LUCIA: What are you doing in my room!?");
          isLost = true;
        }
      })
    }),
    new Room("Veronica's Room", new Action[] {
      new Action("Inspect room", new Function() {
        public void run() {
          printer.dialogueln(
            "The room still smells of fresh paint. You wonder why anyone "
            + "would even bother painting a room in such a faint tint of blue. "
            + "A calendar that would've hung on the wall lies on the desk. "
            + "It's marked with club meetings and assignment due dates."
          );
        }
      }),
      new Action("Inspect bookshelf", new Function() {
        public void run() {
          printer.dialogueln(
            "The bookshelf is full of literature. The specifics might've "
            + "been included if the writer actually did some extra research."
          );
        }
      }),
      new Action("Inspect closet", new Function() {
        public void run() {
          System.out.println("  ____");
          System.out.println(" /   /|");
          System.out.println("/___/ |");
          System.out.println("|   | |");
          System.out.println("|___|/");
          printer.dialogueln("In a small cardboard box, Veronica has stored many little notes, letters, and even a diary, all admiring Robert.");
        }
      }),
      new Action("Check smartphone", new Function() {
        public void run() {
          printer.dialogueChain(new String[] {
            "The phone is open to a text conversation with Robert.",
            "V: Hey\n"
            + "R: Hello\n"
            + "V: Are you coming to the dance\n"
            + "R: I think so. Are you?",
            "Based on the message send times, Veronica took an hour to come up with this brilliant message:\n"
            + "V: Maybe",
            "R: Do you want to come with me?",
            "R: Or come at all?",
            "Although these are just text messages, it was apparent that Robert blushed.\n"
            + "R: I just think we'd all be happier if you came",
            "V: I think I will now :)",
            "R: I'm looking forward to seeing you!"
          });
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
      new Action("Inspect room", new Function() {
        public void run() {
          printer.dialogueln("Although hygiene products are scattered all over the counter and floor, the room surprisingly has no odour.");
        }
      })
    }),
    new Room("Christina's Closet", new Action[] {
      new Action("Inspect room", new Function() {
        public void run() {
          printer.dialogueln(
            "This is her own closet, but Christina's kept it full with her late husband's clothes. "
            + "One may wonder how she even dresses herself for seven days in a row. "
            + "The shelf, however, contains a photo album of her husband's family throughout the years. "
            + "For you, the only photo of note is of Lucia from 2008."
          );
        }
      })
    })
  };
  private static Person[] people = new Person[] {
    new Person("Christina Park", new Action[] {
      new Action("Talk", new Function() {
        public void run() {
          String[] messages = new String[] {
            "Lucia Yom has always been here. She's been here since before Veronica was born.",
            "Lucia is quite peculiar ... sometimes she goes off about the cleaning products. I think she has a good heart, though. Can never tell a lie.",
            "My daughter is everything to me. If you asked me before I had her, I would never have even thought about having a child since it would take away from my work. Now, I wouldn't have it any other way.",
            "It was hard to trust Robert at first. He's so distant. I was actually concerned about Veronica for having friends like that! He's a really good kid, though.",
            "What it was like being young ... Those two kids Veronica and Robert have it so easy, but they're making it so hard for themselves! Just say something already!",
            "Sometimes my parents' voices echo in my head. \"You're getting a degree in both history and physics? That's ridiculous!\" But I guess I showed them with my mildly successful life."
          };
          printer.dialogueln("CHRISTINA: " + messages[(int) (Math.random() * messages.length)]);
        }
      }),
      new Action("Take quiz", new CQuiz())
    }),
    new Person("Veronica Zaveri", new Action[] {
      new Action("Play Blackjack", new Function() {
        public void run() {
          // Just to see whether or not you won the game of Blackjack
          int blackJackStatus = Blackjack.run();

          if (blackJackStatus == 1) {
            int index = 0;

            if (!hasVeronicaTrust) {
              hasVeronicaTrust = true;
              printer.dialogueln("* Gained Veronica's trust *");
            }

            // Need to make sure Veronica moves to a different room
            // Not to the one she's currently in
            do {
              index = (int) (Math.random() * 8);
            } while (rooms[index] == veronicaRoom);

            movePerson(people[1], index);
            veronicaRoom = rooms[index];
          }
        }
      }),
      new Action("Play Rock-Paper-Scissors", new RPS())
    }),
    new Person("Robert Smith", new Action[] {
      new Action("Talk", new Function() {
        public void run() {
          if (robertRoom.people.size() > 1) {
            printer.dialogueln("ROBERT: I'm sorry, I can't do that. There are other people here.");
            return;
          }

          printer.dialogueln(
            "ROBERT: You don't know us. You're unbiased. Do you ... think that Veronica might ... like me? "
            + "I know that that's a weird thing to ask someone you just met, but "
            + "I just want to know. To tell me that maybe ... just maybe she likes me too."
          );
        }
      }),
      new Action("Answer math questions", new MathQuestions()),
      new Action("Ask about flowers", new Daisy())
    }),
    new Person("Lucia Yom", new Action[] {
      new Action("Talk", new Function() {
        public void run() {
          String[] messages = new String[] {
            "I love that everyone consistently does the opposite of what I say. Reverse psychology.",
            "Cleaning is so hard! Maids from this ... place should get paid so much more.",
            "I miss home. It's hard being so far away. It feels like you'll never get to go back.",
            "You ever notice how people seem to have very restricted language? It's like, \"I've got four different sentences and I'll select a random one to say each time.\" So weird."
          };
          printer.dialogueln("LUCIA: " + messages[(int) (Math.random() * messages.length)]);
        }
      })
    })
  };

  public static void main(String[] args) {
    initGame();
    System.out.println("[ TIME AND TIME AGAIN | A Doctor Who fan game ]");
    System.out.println("Note: Enter an invalid response at any point to escape menus.");
    IBIO.input("Press ENTER to start.\n");
    printer.dialogueChain(new String[] {
      "DOCTOR: Well, where to now? Perhaps ... no, been there and done that.",
      "Maybe ... no, absolutely not. Too violent.",
      "Perhaps we just stay on Earth? See Queen Vic?"
    });
    printer.dialogueln(
      "Yes, that seems quite nice.\n"
      + "HERE! WE! GO!\n"
      + "...\n"
      + "...\n"
      + "Well, that doesn't sound very good. Everything sounds terribly terrible."
    );
    printer.dialogueChain("Maybe a kick will help it out.");
    printer.dialogueln(
      "Well, that didn't work at all. I guess I could just-\n"
      + "The TARDIS shook furiously and knocked the Doctor off his feet. "
      + "All of the knobs and switches rattled. The doors flew open and the TARDIS rotated, slowly pouring the Doctor out of its doors.\n"
      + "DOCTOR: No! What are you doing? If there was a list of things that I didn't want you to do, this would be very high up on it!\n"
      + "Barely holding onto the doors, a final bump threw the Doctor through the night sky. He luckily fell onto a tree, but tumbled to the floor."
    );

    while (!isWon) {
      if (moveCount == 0) {
        System.out.println("");
        printer.dialogueln("Your hearing starts to fade along with the rest of your senses.");
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

      if (moveCount >= 12 && !isWon) {
        // Just to catch the user's attention
        printer.dialogueChain("\nAs the clock strikes at midnight, you hear screaming from somewhere in the house.");
        printer.dialogueln(
          "CHRISTINA: Oh my god! What are you doing?!\n"
          + "ROBERT: Miss! Are you alright?\n"
          + "ROBERT: Miss! Please, just say something ... anything at all.\n"
          + "VERONICA: Mum, please stay with us.\n"
          + "Sobbing echoes throughout the house."
        );
      }

      if (isLost || moveCount >= 12) {
        resetGame();
      }
    }

    printer.dialogueChain(new String[] {
      "\tWell, today, I was taken back to 2008 because of that MIPHE - a very awkward name, if I may add - "
      + "and I just want to return home, but that isn't how it works at all. "
      + "It goes one way. You can only go back; not forward. If they just waited "
      + "a bit longer for the technology, but those historians are just obsessed.\n",
      "\tToday, I found work. It's crazy how different things are in the 21st century. "
      + "I think I actually hate it. All of their tools are manual. "
      + "It shocks me to see people working at all. The history books have it all wrong! "
      + "This world, or time, is quite a nightmare. I just wish I could get back to my home. "
      + "That cursed machine! The MIPHE took me here, "
      + "and luckily for me, it was made by my employer: a Ms Christina Park. "
      + "If I can just get rid of her now ... but what if it doesn't work? "
      + "It would so obviously be me. I don't want to be incarcerated for trying to get home!\n",
      "\tI think I fully understand what I'm doing now, but I'm not really a physicist. "
      + "Science fiction movies are a good substitute for a real education, I hope. "
      + "If I kill Ms Park before she creates the MIPHE, I couldn't have possible been "
      + "taken back to the 21st century! I can't see how this could possible go wrong.\n",
      "\tLuckily for me, a man just appeared unconscious on our doorstep! It's only 10AM, but a month "
      + "here is more than enough. Some may say that this timing was way too convenient to seem "
      + "realistic, and they'd be right. It's like the last decade of my life had been thought "
      + "up by the world's laziest writer of all time.\n",
      "\tToday is a big day. Ten years of waiting. It's been eight hours, but he still isn't up. I have thought of something, "
      + "though. If I eliminate Ms Park today, even if something goes wrong and I'm stuck in this "
      + "miserable century for the rest of my life, there's still a stranger to frame. "
      + "What's more likely? I, the innocent long-time maid of the Zaveri family, killed "
      + "Park, or a stranger who has no identification did?\n"
    });
    printer.dialogueln(
      "DOCTOR: Lucia! Could you come here?\n"
      + "LUCIA: Well, if you insist. My day is quite busy thou-\n"
      + "LUCIA: Where are your manners? Never look through someone else's belongings!\n"
      + "DOCTOR: It might be warranted if they're hidden in a piano and contain a poorly thought-out murder scheme.\n"
      + "DOCTOR: Doing something bad doesn't always make you bad, however. Your situation changes you; everyone makes mistakes and, more often than not, be forgiven.\n"
      + "DOCTOR: Huh, that seems like a really straightforward lesson to put in for the children.\n"
      + "LUCIA: Excuse me?\n"
      + "DOCTOR: Oh, nothing. Just imagining my life as a children's video game.\n"
      + "LUCIA: That would explain all of the convniences in the plot of my life. Seriously it-\n"
      + "DOCTOR: I can take you back to your time, which is ...?\n"
      + "LUCIA: The 23rd century: 2205. But how can you do that?\n"
      + "The TARDIS materializes within the house as if answering Lucia's question.\n"
      + "DOCTOR: If you think that that's incredible, just wait until-\n"
      + "But Lucia ignored the Doctor and rushed right into the highly conspicuous blue box.\n"
      + "LUCIA: It's-it's bigg-\n"
      + "DOCTOR: -er on the inside. Yes, I've heard. Come on, let's hurry up.\n"
      + "The Doctor abandoned the house and left a scribbled note on the kitchen countertop.\n"
      + "DOCTOR: Now, it's time to sabotage myself. The TARDIS never fails to land in the right place!\n"
      + "DOCTOR: Well, except for the many times that it does, but regardless, I'll just quickly need to sneak into my old TARDIS.\n"
      + "The present Doctor rushed in and rigged his own TARDIS, just before his past counterpart reappeared.\n"
      + "PRESENT DOCTOR: Oop, I think I may be approaching. You've got an adventure ahead of ... I've got an adventure ... I don't even know. That man entering this TARDIS has got an adventure ahead of him.\n"
      + "The present Doctor dematerializes in his TARDIS.\n"
    );
    printer.dialogueChain(new String[] {
      "PAST DOCTOR: Well, where to now? Perhaps ... no, been there and done that.",
      "Maybe ... no, absolutely not. Too violent.",
      "Perhaps we just stay on Earth? See Queen Vic?"
    });
    printer.dialogueln(
      "Yes, that seems quite nice.\n"
      + "HERE! WE! GO!\n"
    );
    System.out.println("\nTHE END.");
  }

  /**
   * Resets all relevant instance variables to their default value
   */
  private static void resetGame() {
    hasChristinaTrust = false;
    hasVeronicaTrust = false;
    hasRobertTrust = false;
    isLost = false;
    hasPlayedPiano = false;
    moveCount = 0;
    currentRoom = rooms[0];
    // Move all of the people to random rooms
    shufflePeople();
  }

  /**
   * A one-time method to initialize game variables to the desired state
   */
  private static void initGame() {
    // Link all the rooms together
    addLinks(0, new int[] {1, 2, 3, 4, 5, 6, 7});
    addLinksIn(0, new int[] {8, 9});
    addLinks(4, new int[] {8, 9});
    addLinks(8, new int[] {9});
    // Add all of the people to their respective rooms
    movePerson(people[0], 0);
    movePerson(people[1], 0);
    movePerson(people[2], 0);
    movePerson(people[3], 0);
    // Call resetGame()
    resetGame();
  }

  /**
   * Moves each person to a random room
   */
  private static void shufflePeople() {
    for (int i = 0; i < people.length; i++) {
      Person person = people[i];
      int index = (int) (Math.random() * 8);
      movePerson(person, index);

      // Lazy implementation of rooms and people
      // A Person instance, unfortunately, has no idea where they are
      if (i == 2) {
        robertRoom = rooms[index];
      } else if (i == 1) {
        veronicaRoom = rooms[index];
      }
    }
  }

  /**
   * Removes all irrelevant characters (with regards to this program)
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
  // Christina
  private static class CQuiz implements Function {
    public void run() {
      Question[] questions = new Question[] {
        new Question("Who first invented calculus?", 0, new String[] {
          "Newton and Leibniz",
          "Newton",
          "Leibniz",
          "Einstein",
          "Newton and Einstein"
        }),
        new Question("Force may be measured in: ", 1, new String[] {
          "Joules",
          "Newtons",
          "Volts",
          "Metres per second"
        }),
        new Question("Light is _____ a wave ___ a particle.", 3, new String[] {
          "neither; nor",
          "only; and not",
          "not; but is",
          "both; and"
        }),
        new Question("The atomic bombings of Hiroshima and Nagasaki took place in ...", 2, new String[] {
          "1944",
          "1918",
          "1945",
          "1970"
        }),
        new Question("Velocity is measured in", 2, new String[] {
          "Joules",
          "Metres per second squared",
          "Metres per second",
          "Metres per second cubed"
        })
      };
      Quiz quiz = new Quiz(questions);
      quiz.run();

      // You gain Christina's trust if you get at least 50%
      if (quiz.getScore() >= questions.length / 2 && !hasChristinaTrust) {
        hasChristinaTrust = true;
        System.out.println("");
        printer.dialogueln("* Gained Christina's trust *");
      }
    }
  }

  // Veronica
  private static class RPS implements Function {
    public void run() {
      do {
        System.out.println("");
        String playerChoice = "";

        // Make sure that the playerChoice is either rock, paper, or scissors
        // Seriously, if it isn't one of them, what will I do?
        do {
          playerChoice = sanitize(IBIO.inputString("Choose rock, paper, or scissors: "));
        } while (!playerChoice.equals("rock") && !playerChoice.equals("paper") && !playerChoice.equals("scissors"));

        String computerChoice = "";
        boolean playerWins = false;
        int index = (int) (Math.random() * 3);

        if (index == 0) {
          computerChoice = "rock";
        } else if (index == 1) {
          computerChoice = "paper";
        } else {
          computerChoice = "scissors";
        }

        printer.dialogueln("Computer choice: " + computerChoice);

        // If it's a tie, we don't need to deal with any additional comparison
        if (playerChoice.equals(computerChoice)) {
          printer.dialogueln("It's a tie!");
          continue;
        }

        // If it's not a tie, check to see if the player won or lost
        // Now, efficiency-wise, we'd compare the index, but this is
        // a lot more readable
        if (playerChoice.equals("rock")) {
          playerWins = computerChoice.equals("scissors");
        } else if (playerChoice.equals("paper")) {
          playerWins = computerChoice.equals("rock");
        } else {
          playerWins = computerChoice.equals("paper");
        }

        // Now, just print out the result
        if (playerWins) {
          printer.dialogueln("You win: " + playerChoice + " beats " + computerChoice);
        } else {
          if (!hasVeronicaTrust) {
            hasVeronicaTrust = true;
            printer.dialogueln("* Gained Veronica's trust *");
          }

          printer.dialogueln("You lose: " + playerChoice + " loses to " + computerChoice);
        }
      } while ('y' == IBIO.inputChar("Do you want to play again? (y/N) "));
    }
  }

  private static class MathQuestions implements Function {
    public void run() {
      Question[] questions = new Question[] {
        new Question("What is the decimal value of Pi rounded to the nearest hundredth?", 3.14),
        new Question("What is the decimal value of Euler's number rounded to the nearest hundred thousandth?", 2.71828),
        new Question("Determine the length of the hypotenuse of a right triangle with legs of lengths 3 and 4.", 5),
        new Question("Determine an integer root of the polynomial x(2x - 1).", 0),
        new Question("What is the sum of the interior angles of a triangle in degrees?", 180),
        new Question("Determine the length of the remaining leg of a right triangle with a hypotenuse of length 26 and a leg of length 24.", 10)
      };
      Quiz quiz = new Quiz(questions);
      quiz.run();
      printer.dialogueln("You answered " + quiz.getScore() + " of " + questions.length + " questions correctly.");
      // Wow, what a twist. It turns out that doing his
      // math questions actually never gains you his trust.
    }
  }

  // Robert
  private static class Daisy implements Function {
    public void run() {
      if (robertRoom.people.size() > 1) {
        printer.dialogueln("ROBERT: I'm sorry, I can't do that. There are other people here.");
        return;
      }

      printer.dialogueChain(new String[] {
        "ROBERT: Flowers are really beautiful. You know that one little game where you pull off petals-",
        "DOCTOR: And each time you alternate between saying \"she loves me\" and \"she loves me not?\"",
        "ROBERT: Oh, is that really common? Anyways, I like it. Hope is a wonderful thing.",
        "ROBERT: Well, here we go:"
      });

      int petalCount = getRandomInt(15, 25);

      for (int i = 1; i <= petalCount; i++) {
        String message = (i % 2 == 0)
          ? "She loves me."
          : "She loves me not.";
        System.out.println(message);
        // Slow things down a bit to increase anxiety
        caughtSleep(250);
      }

      if (petalCount % 2 == 0) {
        printer.dialogueln("ROBERT: That's lovely! I guess I'll just keep daydreaming as I usually do.");

        if (!hasRobertTrust) {
          hasRobertTrust = true;
          printer.dialogueln("* Gained Robert's trust *");

          // Little fourth wall-breaking Easter egg
          // 5% chance of occurring
          if (Math.random() < 0.05) {
            printer.dialogueln("LUCIA: Did anyone else hear a voice whisper, \"Gained Robert's trust?\" No? Just me?");
          }
        }
      } else {
        printer.dialogueln("ROBERT: That ... means nothing. It's just a silly game.");
      }
    }

    // As usual, min-inclusive and max-exclusive
    private static int getRandomInt(int min, int max) {
      int range = max - min;
      return (int) (Math.random() * range) + min;
    }

    // I wasn't quite sure what to name it,
    // but this seems like a safe bet
    private static void caughtSleep(int time) {
      try {
        Thread.sleep(time);
      } catch (InterruptedException e) {

      }
    }
  }
}
