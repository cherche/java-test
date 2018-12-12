public class LinearSearch {
  public static void main(String[] args) {
    int[] arr = {1, 2, 5, 8, 9, 14, 19, 23, 32, 33, 35, 56, 70, 101};

    System.out.println(search(arr, 14));
  }

  public static int search(int[] arr, int item) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == item) {
        return i;
      }
    }

    return -1;
  }
}
