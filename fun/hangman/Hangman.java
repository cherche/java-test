import java.util.*;

public class Hangman {
  private String alphabet;
  private String[] words;
  private String placeholder;
  public Set<Character> guesses = new HashSet<Character>();

  public static void main(String[] args) {
    String LATIN_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    String[] words = new String[] {
      "awkward",
      "bagpipes",
      "banjo",
      "bungler",
      "croquet",
      "crypt",
      "dwarves",
      "fervid",
      "fishhook",
      "fjord",
      "gazebo",
      "gypsy",
      "haiku",
      "haphazard",
      "hyphen",
      "ivory",
      "jazzy",
      "jiffy",
      "jinx",
      "jukebox",
      "kayak",
      "kiosk",
      "klutz",
      "memento",
      "mystify",
      "numbskull",
      "ostracize",
      "oxygen",
      "pajama",
      "phlegm",
      "pixel",
      "polka",
      "quad",
      "quip",
      "rhythmic",
      "rogue",
      "sphinx",
      "squawk",
      "swivel",
      "toady",
      "twelfth",
      "unzip",
      "waxy",
      "wildebeest",
      "yacht",
      "zealous",
      "zigzag",
      "zippy",
      "zombie"
    };
    Hangman hangman = new Hangman(LATIN_ALPHABET, words);
    System.out.println("Welcome to Hangman!\n");
    String word = words[(int) (Math.random() * words.length)];

    if (word.contains(" ")) {
      int len = word.length();
      int spaceCount = word.split(" ").length;
      int letterCount = len - spaceCount;
      System.out.println("Your phrase contains "
        + letterCount + " letter" + (letterCount > 0 ? 's' : "") + " and "
        + spaceCount + " space" + (spaceCount > 0 ? 's' : "") + ".");
    } else {
      int letterCount = word.length();
      System.out.println("Your word contains "
        + letterCount + " letter" + (letterCount > 0 ? 's' : "") + ".");
    }

    System.out.println("Good luck!\n");
    int incorrectCount = 0;

    while (!hangman.isWordGuessed(word)) {
      if (incorrectCount >= 5) {
        System.out.println("You're out of guesses!");
        return;
      }

      String display = hangman.getDisplayWord(word);

      if (hangman.guesses.size() > 0) {
        System.out.println("Guessed: " + hangman.getGuessesString());
      }

      char letter = IBIO.inputChar(display + "> ");

      if (!hangman.isLetterInAlphabet(letter)) {
        System.out.println("That isn't even in the alphabet!");
      } else if (!hangman.isLetterGuessed(letter)) {
        hangman.guesses.add(letter);

        if (!hangman.wordContainsLetter(word, letter)) {
          System.out.println("That isn't one of the letters.");
          incorrectCount++;
        }
      } else {
        System.out.println("You already guessed that letter: ");
      }

      System.out.println("");
    }

    System.out.println("You've correctly guessed \"" + word + "\"!");
    System.out.println("Congratulations, you win!");
  }

  public Hangman(String alphabet, String[] words) {
    this.alphabet = alphabet;
    this.words = words;
    this.placeholder = "_";
  }

  public Hangman(String alphabet, String[] words, String placeholder) {
    this.alphabet = alphabet;
    this.words = words;
    this.placeholder = placeholder;
  }

  public String getRandomWord() {
    int index = (int) (Math.random() * words.length);
    return words[index];
  }

  public String getDisplayWord(String word) {
    String string = "";

    for (int i = 0; i < word.length(); i++) {
      char letter = word.charAt(i);

      if (guesses.contains(Character.toLowerCase(letter))) {
        string += letter;
      } else if (letter == ' ') {
        string += ' ';
      } else {
        string += placeholder;
      }

      string += ' ';
    }

    return string;
  }

  public boolean isWordGuessed(String word) {
    for (int i = 0; i < word.length(); i++) {
      char letter = word.charAt(i);

      if (!isLetterInAlphabet(letter)) {
        continue;
      }

      if (!isLetterGuessed(letter)) {
        return false;
      }
    }

    return true;
  }

  public boolean wordContainsLetter(String word, char letter) {
    return word.contains(String.valueOf(letter));
  }

  public boolean isLetterInAlphabet(char letter) {
    // String.contains() takes a CharSequence, so we must convert
    return alphabet.contains(String.valueOf(letter));
  }

  public boolean isLetterGuessed(char letter) {
    return guesses.contains(letter);
  }

  public String getGuessesString() {
    List<Character> list = new ArrayList<Character>(guesses);
    Collections.sort(list, new Comparator<Character>() {
      public int compare(Character a, Character b) {
        return alphabet.indexOf(a) < alphabet.indexOf(b)
          ? -1
          : (a == b ? 0 : 1);
      }
    });
    String string = "";

    for (int i = 0; i < list.size(); i++) {
      string += list.get(i);
    }

    return string;
  }
}
