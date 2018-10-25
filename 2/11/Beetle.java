public class Beetle {
  public static void main(String[] args) {
    new Beetle(2);
  }

  public Beetle(int playerCount) {
    printInstructions();
    System.out.println("");
    // Nicely, the elements are initialized to 0 as a result of
    // the language specification itself
    int[] scores = new int[playerCount];
    int winner = 0;
    boolean isWon = false;

    while (!isWon) {
      for (int i = 0; i < scores.length; i++) {
        int humanIndex = i + 1;
        System.out.println("Player " + humanIndex + ": ");
        scores[i] = getNextScore(scores[i]);
        System.out.println("");
        IBIO.inputString("Press ENTER to continue.");

        if (scores[i] == 6) {
          winner = humanIndex;
          isWon = true;
          break;
        }
      }
    }

    System.out.println("Congratulations player " + winner + "!");
    System.out.println("You literally just got lucky, but good ... " + " effort?");
  }

  public void printInstructions() {
    System.out.println(
      "The goal of beetle is to be the first to draw the beetle in the correct order.\n"
      + "To be able to draw a body part, you need to roll the correct thing at the correct time.\n"
    );
    System.out.println("\tBody = 0");
    System.out.println("\tLeg 1 = 1");
    System.out.println("\tLeg 2 = 2");
    System.out.println("\tLeg 3 = 3");
    System.out.println("\tLeg 4 = 4");
    System.out.println("\tLeg 5 = 5");
    System.out.println("\tLeg 6 = 6");
    System.out.println("If you don't roll what you are looking for, the part passes to the other player.");
  }

  public int rollDice() {
    return (int) (Math.random() * 6) + 1;
  }

  public int getNextScore(int score) {
    int roll = rollDice();

    if (roll == score + 1) {
      score++;
      drawBeetle(score);
    } else {
      System.out.println(roll);
      System.out.println("Too bad");
    }

    return score;
  }

  // I hate this method more than I hate myself
  public void drawBeetle(int legCount) {
    switch (legCount) {
      case 1:
        System.out.println("        \\   /\n" +
"        .\\-/.\n" +
"    /\\  () ()\n" +
"   /  \\ /~-~\\\n" +
"       /  Y  \\\n" +
"      /   |   \\\n" +
"     {    |    }\n" +
"      \\   |   /\n" +
"       \\  A  /\n" +
"        \\/ \\/");
        break;
      case 2:
        System.out.println("        \\   /" + "\n" +
"        .\\-/.\n" +
"    /\\  () ()  /\\\n" +
"   /  \\ /~-~\\ /  \\\n" +
"       y  Y  \\\n" +
"      /   |   \\\n" +
"     {    |    }\n" +
"      \\   |   /\n" +
"       \\  A  /\n" +
"        \\/ \\/");
        break;
      case 3:
        System.out.println("        \\   /" + "\n" +
"        .\\-/.\n" +
"    /\\  () ()  /\\\n" +
"   /  \\ /~-~\\ /  \\\n" +
"       y  Y  V\n" +
" ,-^-./   |   \\\n" +
"/    {    |    }\n" +
"      \\   |   /\n" +
"       \\  A  /\n" +
"        \\/ \\/");
        break;
      case 4:
        System.out.println("        \\   /" + "\n" +
"        .\\-/.\n" +
"    /\\  () ()  /\\\n" +
"   /  \\ /~-~\\ /  \\\n" +
"       y  Y  V\n" +
" ,-^-./   |   \\,-^-.\n" +
"/    {    |    }    \\\n" +
"      \\   |   /\n" +
"       \\  A  /\n" +
"        \\/ \\/");
        break;
      case 5:
        System.out.println("        \\   /" + "\n" +
"        .\\-/.\n" +
"    /\\  () ()  /\\\n" +
"   /  \\ /~-~\\ /  \\\n" +
"       y  Y  V\n" +
" ,-^-./   |   \\,-^-.\n" +
"/    {    |    }    \\\n" +
"      \\   |   /\n" +
"      /\\  A  /\n" +
"     /  \\/ \\/\n" +
"    /");
        break;
      default:
        System.out.println("        \\   /" + "\n" +
"        .\\-/.\n" +
"    /\\  () ()  /\\\n" +
"   /  \\ /~-~\\ /  \\\n" +
"       y  Y  V\n" +
" ,-^-./   |   \\,-^-.\n" +
"/    {    |    }    \\\n" +
"      \\   |   /\n" +
"      /\\  A  /\\\n" +
"     /  \\/ \\/  \\\n" +
"    /           \\");
    }

/*
        \   /
        .\-/.
        () ()
        /~-~\
       /  Y  \
      /   |   \
     {    |    }
      \   |   /
       \  A  /
        \/ \/

        \   /
        .\-/.
    /\  () ()
   /  \ /~-~\
       y  Y  \
      /   |   \
     {    |    }
      \   |   /
       \  A  /
        \/ \/

        \   /
        .\-/.
    /\  () ()  /\
   /  \ /~-~\ /  \
       y  Y  V
      /   |   \
     {    |    }
      \   |   /
       \  A  /
        \/ \/

        \   /
        .\-/.
    /\  () ()  /\
   /  \ /~-~\ /  \
       y  Y  V
 ,-^-./   |   \
/    {    |    }
      \   |   /
       \  A  /
        \/ \/

        \   /
        .\-/.
    /\  () ()  /\
   /  \ /~-~\ /  \
       y  Y  V
 ,-^-./   |   \,-^-.
/    {    |    }    \
      \   |   /
       \  A  /
        \/ \/


        \   /
        .\-/.
    /\  () ()  /\
   /  \ /~-~\ /  \
       y  Y  V
 ,-^-./   |   \,-^-.
/    {    |    }    \
      \   |   /
      /\  A  /
     /  \/ \/
    /

        \   /
        .\-/.
    /\  () ()  /\
   /  \ /~-~\ /  \
       y  Y  V
 ,-^-./   |   \,-^-.
/    {    |    }    \
      \   |   /
      /\  A  /\
     /  \/ \/  \
    /           \
*/

  }
}
