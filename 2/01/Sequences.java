import java.util.*;

public class Sequences {
  public static void main(String[] args) {
    printA();
    printB();
    printC();
    printD();
    printE();
    printBonus();
  }

  public static void printA() {
    System.out.println("# Group A: Counting");

    for (int i = 0; i <= 12; i++) {
      System.out.print(i + " ");
    }

    System.out.println("");

    for (int i = 1; i <= 19; i++) {
      System.out.print(i + " ");
    }

    System.out.println("");

    for (int i = 12; i <= 22; i++) {
      System.out.print(i + " ");
    }

    System.out.println("");

    for (int i = 101; i <= 115; i++) {
      System.out.print(i + " ");
    }

    System.out.println("\n");
  }

  public static void printB() {
    System.out.println("# Group B: Backwards");

    for (int i = 12; i >= 0; i--) {
      System.out.print(i + " ");
    }

    System.out.println("");

    for (int i = 20; i >= 1; i--) {
      System.out.print(i + " ");
    }

    System.out.println("");

    for (int i = 22; i >= 12; i--) {
      System.out.print(i + " ");
    }

    System.out.println("");

    for (int i = 115; i >= 101; i--) {
      System.out.print(i + " ");
    }

    System.out.println("\n");
  }

  public static void printC() {
    System.out.println("# Group C: Skip Counting");

    for (int i = 0; i <= 18; i += 2) {
      System.out.print(i + " ");
    }

    System.out.println("");

    for (int i = 1; i <= 19; i += 2) {
      System.out.print(i + " ");
    }

    System.out.println("");

    for (int i = 0; i <= 30; i += 3) {
      System.out.print(i + " ");
    }

    System.out.println("");

    for (int i = 0; i <= 40; i += 4) {
      System.out.print(i + " ");
    }

    System.out.println("");

    for (double i = 1.0; i <= 5.5; i += 0.5) {
      System.out.print(i + " ");
    }

    System.out.println("\n");
  }

  public static void printD() {
    System.out.println("# Group D: One thing, repeated");

    for (int i = 0; i < 14; i++) {
      System.out.print("* ");
    }

    System.out.println("");

    for (int i = 0; i < 14; i++) {
      System.out.print("2 ");
    }

    System.out.println("");

    for (int i = 0; i < 14; i++) {
      System.out.print("1 0 ");
    }

    System.out.println("");

    for (int i = 0; i < 20; i++) {
      System.out.print("la");
    }

    System.out.println("");

    for (int i = 0; i < 6; i++) {
      System.out.print("- 1 + 1 ");
    }

    System.out.println("\n");
  }

  public static void printE() {
    System.out.println("# Group E: Powers - Multiply and Divide");

    for (int i = 0; i <= 12; i++) {
      // Math.pow() is for doubles
      // It is definitely less readable to use it,
      // then cast the double to an int
      System.out.print((i * i) + " ");
    }

    System.out.println("");

    for (int i = 0; i <= 12; i++) {
      System.out.print((i * i * i) + " ");
    }

    System.out.println("");

    for (int i = 1; i <= 59049; i *= 3) {
      System.out.print(i + " ");
    }

    System.out.println("");

    for (double i = 1000.0; i >= 0.01; i /= 10.0) {
      System.out.print(i + " ");
    }

    System.out.println("\n");
  }

  public static void printBonus() {
    System.out.println("# Bonus");

    for (int i = 1; i <= 8; i++) {
      int num = i;
      int den = i + 1;
      System.out.print(num + "/" + den + " ");
    }

    System.out.println("");

    for (int i = 1; i <= 362880; i *= (i + 1)) {
      System.out.print(i + " ");
    }

    System.out.println("");

    for (int i = 1; i <= 11; i++) {
      System.out.print(getFibonacci(i) + " ");
    }

    System.out.println("\n");
  }

  public static int getFibonacci(int n) {
    List<Integer> nums = new ArrayList<Integer>();
    nums.add(1);
    nums.add(1);

    for (int i = 2; i < n; i++) {
      int a1 = nums.get(i - 2);
      int a2 = nums.get(i - 1);
      nums.add(a1 + a2);
    }

    int lastTerm = nums.get(n - 1);
    return lastTerm;
  }
}
