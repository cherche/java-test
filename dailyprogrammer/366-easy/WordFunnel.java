public class WordFunnel {
  public static void main(String[] args) {
    String[][] testPairs = new String[][] {
      {"leave", "eave"},
      {"reset", "rest"},
      {"dragoon", "dragon"},
      {"eave", "leave"},
      {"sleet", "lets"},
      {"skiff", "ski"}
    };

    for (int i = 0; i < testPairs.length; i++) {
      String[] pair = testPairs[i];
      String sup = pair[0];
      String sub = pair[1];
      System.out.println("funnel(\"" + sup + "\", \"" + sub + "\") // " + funnel(sup, sub));
    }
  }

  private static boolean funnel(String sup, String sub) {
    int supLen = sup.length();
    int subLen = sub.length();

    if (subLen != supLen - 1) {
      return false;
    }

    for (int i = 0; i < supLen; i++) {
      String subCandidate = sup.substring(0, i) + sup.substring(i + 1);

      if (subCandidate.equals(sub)) {
        return true;
      }
    }

    return false;
  }
}
