public class Magic8 {
  private String[] responses;

  public static void main(String[] args) {
    String[] responses = new String[] {
      "outlook not so good",
      "don't count on it",
      "my sources say no",
      "without a doubt",
      "reply hazy, try again",
      "it is certain",
      "my reply is no",
      "as I see it yes",
      "most likely",
      "you may rely on it",
      "cannot predict now",
      "outlook good",
      "better not tell you now",
      "very doubtful",
      "yes definitely",
      "concentrate and ask again"
    };
    Magic8 magic8 = new Magic8(responses);
    System.out.println("*** Ask the Magic 8 Ball ***");
    System.out.println("");
    IBIO.input("Enter a yes or no question: ");
    System.out.println("");
    System.out.println("The Magic 8 Ball says:");
    System.out.println("\t" + magic8.getResponse());
  }

  public Magic8(String[] responses) {
    this.responses = responses;
  }

  public String getResponse() {
    int index = Random.getRandomInt(0, responses.length - 1);
    return responses[index];
  }
}
