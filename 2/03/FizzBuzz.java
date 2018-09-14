public class FizzBuzz {
  private static P[] dict = new P[] {
    new P(3, "Fizz"),
    new P(5, "Buzz")
  };

  private static String getDictWord(int num) {
    String word = "";

    for (int i = 0; i < dict.length; i++) {
      P pair = dict[i];
      if (num % pair.num == 0) {
        word += pair.word;
      }
    }

    if (word.length() == 0) {
      return String.valueOf(num);
    } else {
      return word;
    }
  }

  public static void main(String[] args) {
    for (int i = 1; i < 100; i++) {
      String word = getDictWord(i);
      System.out.println(word);
    }
  }

  private static class P {
    private int num;
    private String word;

    public P(int num, String word) {
      this.num = num;
      this.word = word;
    }
  }
}
