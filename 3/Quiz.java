public class Quiz {
  private Question[] questions;
  private int lastScore = 0;

  public Quiz(Question[] questions) {
    this.questions = questions;
  }

  public void run() {
    lastScore = 0;

    for (int i = 0; i < questions.length; i++) {
      Question question = questions[i];
      System.out.println(question.question);

      for (int j = 0; j < question.answers.length; j++) {
        System.out.println("[" + j + "] " + question.answers[j]);
      }

      double response = IBIO.inputDouble();

      if (response == question.correct) {
        lastScore++;
      }
    }
  }

  public int getScore() {
    return lastScore;
  }
}
