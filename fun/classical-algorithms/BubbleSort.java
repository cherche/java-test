public class BubbleSort {
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
    for (int i = arr.length - 2; i >= 0; i--) {
      for (int j = 0; j <= i; j++) {
        if (arr[j] > arr[j + 1]) {
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
    }

    return arr;
  }
}
