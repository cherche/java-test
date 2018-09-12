public class Average {
  public static void main(String[] args) {
    printTitle();
    int numOfStudents = IBIO.inputInt("How many students are there? ");
    // Technically, the initial value is already 0.0
    // but this is a bit more readable
    double sum = 0.0;
    System.out.println("");

    for (int i = 0; i < numOfStudents; i++) {
      int humanIndex = i + 1;
      String prompt = String.format("What is the mark of student #%d? ", humanIndex);
      int mark = IBIO.inputInt(prompt);
      if (mark < 0 || mark > 100) {
        throw new RuntimeException("mark must be between 0 (inclusive) and 100 (inclusive)");
      }
      sum += mark;
    }

    double average = sum / numOfStudents;
    String message =
      "There are %d students in the class.\n"
      + "The average mark was %f%%.";
    System.out.println("");
    System.out.println(String.format(message, numOfStudents, average));
  }

  private static void printTitle() {
    System.out.println("# Average Calculator");
    System.out.println("");
  }
}
