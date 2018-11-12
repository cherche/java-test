public class Question {
  public String question;
  public String[] answers;
  public double correct;

  public Question(String question, double correct, String[] answers) {
    this.question = question;
    this.correct = correct;
    this.answers = answers;
  }

  public Question(String question, double correct) {
    this.question = question;
    this.correct = correct;
    this.answers = new String[0];
  }
}
