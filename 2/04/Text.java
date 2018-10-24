import java.util.*;

public class Text {
  public static void main(String[] args) {
    String[][] pairs = new String[][] {
      {"CU", "see you"},
      {":-)", "I'm happy"},
      {":-(", "I'm unhappy"},
      {";-)", "wink"},
      {":-P", "stick out my tongue"},
      {"(~.~)", "sleepy"},
      {"TA", "totally awesome"},
      {"CUZ", "because"},
      {"TY", "thank you"},
      {"YW", "you're welcome"},
      {"TTYL", "talk to you later"}
    };
    Map<String, String> lookup = arrayToMap(pairs);
    String key = "";

    do {
      key = IBIO.inputString("Enter phrase> ");
      String value = lookup.get(key);

      if (value != null) {
        IBIO.output(value);
      } else {
        IBIO.output(key);
      }
    } while (!key.equals("TTYL"));
  }

  private static Map<String, String> arrayToMap(String[][] pairs) {
    Map<String, String> map = new HashMap<String, String>();

    for (int i = 0; i < pairs.length; i++) {
      String[] pair = pairs[i];
      String key = pair[0];
      String value = pair[1];
      map.put(key, value);
    }

    return map;
  }
}
