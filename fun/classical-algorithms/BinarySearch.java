public class BinarySearch {
  public static void main(String[] args) {
    int[] arr = {1, 2, 5, 8, 9, 14, 19, 23, 32, 33, 35, 56, 70, 101};

    System.out.println(search(arr, 14));
  }

  public static int search(int[] arr, int item) {
    int min = 0;
    int max = arr.length;
    int index;

    do {
      index = (min + max) / 2;

      if (item == arr[index]) {
        return index;
      } else if (item > arr[index]) {
        min = index;
      } else {
        max = index;
      }
    } while (arr[index] != item && max != min + 1);

    return -1;
  }
}
