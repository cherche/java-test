public class Bus {
  public static void main(String[] args) {
    String[][] diffs = {
      {"wheels", "go", "'round and 'round"},
      {"wipers", "go", "swish, swish, swish"},
      {"door", "goes", "open and shut"},
      {"horn", "goes", "beep, beep, beep"},
      {"gas", "goes", "glunk glunk glunk"},
      {"money", "goes", "clink, clink, clink"},
      {"baby", "goes", "wah, wah, wah"},
      {"mommy", "says", "\"I love you\""}
    };

    for (int i = 0; i < diffs.length; i++) {
      String[] diff = diffs[i];
      String sub = diff[0];
      String verb = diff[1];
      String obj = diff[2];
      System.out.println(getVerse(sub, verb, obj) + "\n");
    }
  }

  public static String getVerse(String sub, String verb, String obj) {
    String verse =
      "The " + sub + " on the bus " + verb + " " + obj + ",\n"
      + capitalize(obj) + ", " + obj + ";\n"
      + "The " + sub + " on the bus " + verb + " " + obj + ",\n"
      + "All through the town.";
    return verse;
  }

  public static String capitalize(String string) {
    char lead = string.charAt(0);
    String rest = string.substring(1);

    if (string.length() == 0) {
      return string;
    } else if (isASCIILetter(lead)) {
      return Character.toUpperCase(lead) + rest;
    } else {
      return lead + capitalize(rest);
    }
  }

  public static boolean isASCIILetter(char a) {
    return (a >= 'A' && a <= 'Z') || (a >= 'a' && a <= 'z');
  }
}
