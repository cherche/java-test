public class test {
  public static void main(String[] args) {
    String[] descs = new String[] {
      "a made-up name",
      "a colour",
      "another colour",
      "a number",
      "another number"
    };
    // Oh my god. It's disgusting. It's 1-based instead of 0-based;
    String template =
      "A Strange Animal.....\n"
      + "The %1$s is an animal\n"
      + "that has %2$s fur with\n"
      + "%3$s spots on its tail. An\n"
      + "adult %1$s may weigh over\n"
      + "%4$s pounds and stand over %5$s\n"
      + "feet tall.";
    MadLib madlib = new MadLib(descs, template);
    /*
    for (int i = 0; i < descs.length; i++) {
      String word = IBIO.inputString("Enter " + descs[i] + ": ");
      words[i] = word;
    }
    */
    String[][] wordSets = new String[][] {
      {
        "Ooklum",
        "green",
        "magenta",
        "3",
        "45"
      },
      {
        "Gahoom",
        "blue",
        "yellow",
        "15",
        "2"
      }
    };

    for (int i = 0; i < wordSets.length; i++) {
      String[] words = wordSets[i];
      String story = madlib.getFilledStory(words);
      System.out.println(story);
      System.out.println();
    }
  }
}
