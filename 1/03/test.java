public class test {
  public static void main(String[] args) {
    String[] descs = new String[] {
      "a made-up name",
      "a colour",
      "another colour",
      "a number",
      "another number"
    };
    String[] words = new String[descs.length];
    TempPair[] template = new TempPair[] {
      new TempPair("A Strange Animal..... \nThe ", 0),
      new TempPair(" is an animal \nthat has ", 1),
      new TempPair(" fur with \n", 2),
      new TempPair(" spots on its tail. An \nadult ", 0),
      new TempPair(" may weigh over \n", 3),
      new TempPair(" pounds and stand over ", 4),
      // I really couldn't think of a better way to make
      // the code more readable
      // I guess we'll have to settle for this awkward -1
      new TempPair("\nfeet tall.", -1)
    };
    MadLib madlib = new MadLib(descs, template);

    for (int i = 0; i < descs.length; i++) {
      String word = IBIO.inputString("Enter " + descs[i] + ": ");
      words[i] = word;
    }

    String story = madlib.getFilledStory(words);
    System.out.println("");
    System.out.println(story);
  }
}
