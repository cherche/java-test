/**
 * Contains everything needed to actually run the game
 * @author Ryan Nguyen
 * @version 2018-10-30
 */
public class Game {
  public static void main(String[] args) {
    Room[] rooms = new Room[] {
      new Room("House", new Action[] {
        new Action("Eat", new Function() {
          public void run() {
            System.out.println("You ate");
          }
        }),
        new Action("Roll a die", new Function() {
          public void run() {
            int value = (int) (Math.random() * 6) + 1;
            System.out.println("You rolled a " + value);
          }
        })
      }),
      new Room("Not house", new Action[] {
        new Action("Die", new Function() {
          public void run() {
            System.out.println("You are dead");
          }
        })
      })
    };

    link(rooms, 0, new int[] {1});
    Room[] links = rooms[0].links.toArray(new Room[0]);
    for (int i = 0; i < links.length; i++) {
      System.out.println(links[i].name);
    }
    runRoom(rooms[0]);
  }

  private static void runRoom(Room room) {
    Action[] actions = room.actions;

    for (int i = 0; i < actions.length; i++) {
      Action action = actions[i];
      System.out.println("[" + (i + 1) + "] " + action.desc);
    }

    int choice = 0;

    do {
      choice = IBIO.inputInt() - 1;
    } while (choice < 0 || choice >= actions.length);

    Function fn = actions[choice].fn;
    fn.run();
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
