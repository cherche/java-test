public class SelectionSort {
  public static void main(String[] args) {
    int[] arr = {-1, 3, 2, 5, -3, 5, 5, 6};

    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }

    int[] sorted = sort(arr);
    System.out.println("");

    for (int i = 0; i < sorted.length; i++) {
      System.out.print(sorted[i] + " ");
    }
  }

  public static int[] sort(int[] arr) {
    for (int i = arr.length - 1; i > 0; i--) {
      int maxIndex = 0;

      for (int j = 1; j <= i; j++) {
        if (arr[j] > arr[maxIndex]) {
          maxIndex = j;
        }
      }

      int temp = arr[i];
      arr[i] = arr[maxIndex];
      arr[maxIndex] = temp;
    }

    return arr;
  }
}
