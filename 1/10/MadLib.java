/*
This mad lib was created by Brainy Bunny, CC BY 2.0.
https://creativecommons.org/licenses/by/2.0/ca/

I have adapted it to collect user input and
output the resulting story in the command line.
*/
public class MadLib {
  public static void main(String[] args) {
    String[] descs = new String[] {
      "an adjective",
      "an adjective",
      "a type of bird",
      "a room in a house",
      "a verb (past tense)",
      "a verb",
      "a relative's name", // 6
      "a noun",
      "a liquid",
      "a verb ending in -ing",
      "a part of the body (plural)",
      "a plural noun",
      "a verb ending in -ing",
      "a noun"
    };
    String[] words = new String[descs.length];
    //   If no words are used multiple times, one may
    // simply use %s without specifying the index
    //   Either way, even though it's easier to read, it's
    // hard to match each %s with its corresponding word
    //   If the template was created so each user input
    // was an index-description pair, we would lose
    // the flexibility of word persistence (a word being)
    // used in many places in the story)
    //   If the descriptions are stored separately,
    // there's little advantage as it's hard to tell
    // what each index corresponds to
    String template =
      "It was a %s, cold November day. I\n"
      + "woke up to the %s smell of %s\n"
      + "roasting in the %s downstairs. I\n"
      + "%s down the stairs to see if I could\n"
      + "help %s the dinner. My mom said,\n"
      + "\"See if %s needs a fresh %s.\" So I\n"
      + "carried a tray of glasses full of %s into\n"
      + "the %s room. When I got there, I\n"
      + "couldn't believe my %s! There were\n"
      + "%s %s on the %s.";

    for (int i = 0; i < descs.length; i++) {
      String word = IBIO.inputString("Enter " + descs[i] + ": ");

      // The sixth word is actually a name
      // It should be capitalized correctly
      if (i == 6 && isLowerCase(word.charAt(0))) {
        word = capitalize(word);
        String respectMessage =
          String.format("Show some respect! %s should be capitalized.", word);
        System.out.println(respectMessage);
      }

      words[i] = word;
    }

    String story = String.format(template, (Object[]) words);
    System.out.println();
    System.out.println(story);
  }

  public static boolean isLowerCase(char myChar) {
    int charCode = (int) myChar;
    // All other characters are ignored
    // Only (regular expression) [a-z] matter
    return (97 <= charCode) && (charCode <= 122);
  }

  public static String capitalize(String str) {
    char lead = str.charAt(0);

    // The reason the isLowerCase() method is used
    // is that it will ignore non-letter characters
    // that have neither an uppercase nor a lowercase
    if (!isLowerCase(lead)) {
      return str;
    }

    int leadCode = (int) lead;
    // Recall that the uppercase and lowercase ASCII
    // codes differ by a single bit (with a value of 32)
    lead = (char) (leadCode - 32);
    // Now, just concatenate the corrected first char
    // and the remainder of the string
    return lead + str.substring(1);
  }
}
