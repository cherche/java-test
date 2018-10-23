// I hope this documentation is satisfactory
// It was certainly lacking before

// This is the class of all possible rock-paper-scissors-like games
// That is, all games in which there are an odd number of weapons
// in which each weapon wins against half of the other weapons and
// loses to the other half.

public class RPS {
  public String[] choices;

  // The choices array should be an array of length 2n + 1 arranged
  // so that (cyclically speaking) the n elements before the kth
  // lose to the kth and the remainder beat the kth
  public RPS(String[] choices) {
    this.choices = choices;
    // This can be hard to conceptualize. Consider the basic case:
    // choices = new String[] {"rock", "paper", "scissors"}
    // The length is 2*1 + 1, i.e. n = 1
    // paper beats the 1 element to its left
    // rock beats the 1 element to its left (in a cyclic sense)
    // scissors beats the 1 element to its left
    //
    // To see why this is nice, consider the five-weapon case of
    // rock-paper-scissors-lizard-spock:
    // choices = new String[] {"rock", "spock", "paper", "lizard", "scissors"}
    // paper beats {rock, spock}
    // spock beats {scissors, rock} (cycle)
    // rock beats {lizard, scissors} (cycle)
    // scissors beats {paper, lizard}
    // lizard beats {spock, paper}
  }

  // The following method will take two indices that correspond to weapons
  // in the choices array and arrange them in a new array such that the first
  // element wins against the second element
  public int[] sort(int first, int second) {
    if (first == compare(first, second)) {
      return new int[] {first, second};
    } else {
      return new int[] {second, first};
    }
  }

  // Compare is the basis of sort. It takes two indices corresponding to
  // weapons and returns the winning index of the two
  public int compare(int first, int second) {
      // We wish to cyclically shift every element so our target index
      // is in the centre of indices. This makes comparison easy.
      // This, of course, involves modular arithmetic, and is best
      // illustrated with a concrete example.
      // {"rock", "paper", "scissors"}, {0, 1, 2}
      // Suppose 0 is the first index. We will shift the first index so that
      // it is the middle, and apply the same shift to the second.
      // 0 -> 0 + 1 = 1 (rock is in the centre)
      // 1 -> 1 + 1 = 2 (paper is the last element)
      // 2 -> 2 + 1 = 0 (scissors is the first element)
      // Using the defined test above, this new array still satisfies the
      // originally properties of the choice array, but has indices that
      // are very easy to compare relative to the first choice of "rock" or 0
      int middle = choices.length / 2;
      // Essentially, we cyclically shift all the choices so that the
      // first choice is in the centre.
      int shift = middle - first;
      int shifted1 = first + shift;
      int shifted2 = (second + shift + choices.length) % choices.length;
      // Now, if choices is an array of length 2n + 1, the n elements to
      // the left of the first choice (shifted1) are exactly the same
      // elements with indices less than shifted1 since shifted1 is the middle
      // The reason that the middle was chosen was that it led to the
      // easiest test condition. If, in the previous example, rock was moved
      // to be the last element:
      // 0 -> 0 + 2 = 2 (rock is on the last element)
      // 1 -> 1 + 2 = 0 (paper is the first element)
      // 2 -> 2 + 2 = 1 (scissors is the middle element)
      // This gives new indices of (2, rock), (0, paper), (1, scissors)
      // Notice that now, it is necessary to ensure that the second choice
      // being compared with the first must not only be less than 2 but also
      // be greater than 0. This is obviously less elegant.

      if (shifted1 > shifted2) {
        return first;
      } else {
        return second;
      }
  }

  // Literally just a getter
  public String[] getChoices() {
    return choices;
  }

  // Provided a choice, getIndex() will locate its index in the choices array
  // If it is not in the choices array, -1 is returned and the user
  // is hopefully dealing with it in some manner
  /* Usage:
    RPS game = new RPS(new String[] {"rock", "paper", "scissors"});
    game.getIndex("rock"); // => 0
    game.getIndex("scissors"); // => 2
    game.getIndex("something not in the choices"); // => -1
  */
  public int getIndex(String choice) {
    for (int i = 0; i < choices.length; i++) {
      if (choices[i].equals(choice)) {
        return i;
      }
    }

    return -1;
  }
}
