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
    String[] template = new String[] {
      "A Strange Animal..... \nThe ",
      " is an animal \nthat has ",
      " fur with \n",
      " spots on its tail. An \nadult ",
      " may weight over \n",
      " pounds and stand over ",
      "\nfeet tall."
    };
    int[] indices = new int[] {
      0,
      1,
      2,
      0,
      3,
      4
    };
    MadLib madlib = new MadLib(descs, template, indices);

    for (int i = 0; i < descs.length; i++) {
      String word = IBIO.inputString("Enter " + descs[i] + ": ");
      words[i] = word;
    }

    String story = madlib.getFilledStory(words);
    System.out.println("");
    System.out.println(story);
  }
}
