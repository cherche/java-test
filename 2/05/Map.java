// Honestly, I didn't really have to create a whole class
// At least I feel like I'm getting a sense for object-oriented languages
// That's really all that matters, right?
public class Map {
  public int cols; // Number of x-values
  public int rows; // Number of y-values
  public boolean[][] map;

  public Map(int height) {
    cols = 2 * height - 1;
    rows = height;
    map = new boolean[cols][rows];

    for (int x = 0; x < cols; x++) {
      for (int y = 0; y < rows; y++) {
        // Suppose you could only take horizontal or vertical steps
        // We want to include all spaces that are within (rows - 1) steps
        // of some point (which we may call the center of the pyramid)
        int steps = Math.abs(x - (rows - 1)) + Math.abs(y - (rows - 1));
        map[x][y] = steps <= rows - 1;
      }
    }
  }
}
