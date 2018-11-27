import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Play rock-paper-scissors-lizard-spock with a subjectively pretty GUI
 *
 * @author  Ryan Nguyen
 * @version 2018-11-27
 */
public class RPSLS extends JPanel implements ActionListener {
  /**
   * Array of specially arranged spaces
   */
  public static String[] choices = new String[] {
    "rock",
    "spock",
    "paper",
    "lizard",
    "scissors"
  };
  // Notice: each element beats the two elements "before" it, cyclically
  // "scissors" beats the two before: "paper" and "lizard"
  // "spock" beats the two before: "scissors" and "rock"
  ButtonGroup buttonGroup = new ButtonGroup();
  JLabel status = new JLabel();
  JLabel scores = new JLabel("You: 0; Computer: 0");
  JLabel userPicture = new JLabel(createImageIcon(choices[0] + ".png"));
  JLabel computerPicture = new JLabel();
  int wins = 0;
  int losses = 0;

  public static void main(String[] args) {
    JPanel content = new RPSLS();
    JFrame window = new JFrame();
    window.setContentPane(content);
    window.setSize(400, 300);
    window.setLocation(100, 100);
    window.setVisible(true);
    window.setResizable(false);
  }

  public RPSLS() {
    Color BACK = new Color(40, 40, 40);
    Color TEXT = new Color(179, 179, 179);
    Color BACK_DARKER = new Color(24, 24, 24);
    Color BACK_DARKEST = new Color(18, 18, 18);
    Color TEXT_FOCUSED = Color.WHITE;

    // Grid layout is my best friend
    this.setLayout(new GridLayout(2, 2));
    // But only because I don't know how grid bag layout works yet . . .

    JPanel player = new JPanel();
    player.setBackground(BACK);
    JLabel playerLabel = new JLabel("          You          ");
    playerLabel.setForeground(TEXT);
    player.add(playerLabel);
    player.add(userPicture);
    this.add(player);

    JPanel computer = new JPanel();
    computer.setBackground(BACK);
    JLabel computerLabel = new JLabel("      Computer      ");
    computerLabel.setForeground(TEXT);
    computer.add(computerLabel);
    computer.add(computerPicture);
    this.add(computer);

    JPanel choose = new JPanel();
    choose.setBackground(BACK_DARKEST);
    JLabel chooseLabel = new JLabel("        Pick one:        ");
    choose.add(chooseLabel);
    chooseLabel.setForeground(TEXT_FOCUSED);
    JPanel choiceContainer = new JPanel();
    choiceContainer.setBackground(BACK_DARKEST);
    choiceContainer.setLayout(new GridLayout(3, 0));

    for (int i = 0; i < choices.length; i++) {
      JRadioButton button = new JRadioButton(choices[i]);
      button.setForeground(TEXT_FOCUSED);

      if (i == 0) {
        button.setSelected(true);
      }

      button.setActionCommand(choices[i]);
      button.addActionListener(this);
      buttonGroup.add(button);
      choiceContainer.add(button);
    }

    choose.add(choiceContainer);
    this.add(choose);

    JPanel results = new JPanel();
    results.setBackground(BACK_DARKER);
    results.setLayout(new GridLayout(4, 1));
    status.setForeground(TEXT);
    status.setHorizontalAlignment(JLabel.CENTER);
    results.add(status);
    JButton submit = new JButton("Play");
    submit.setOpaque(true);
    submit.setBorderPainted(false);
    submit.setForeground(TEXT_FOCUSED);
    submit.setBackground(BACK_DARKEST);
    submit.setActionCommand("play");
    submit.addActionListener(this);
    results.add(submit);
    JButton reset = new JButton("Reset Scores");
    reset.setOpaque(true);
    reset.setBorderPainted(false);
    reset.setForeground(TEXT_FOCUSED);
    reset.setBackground(BACK_DARKEST);
    reset.setActionCommand("reset");
    reset.addActionListener(this);
    results.add(reset);
    scores.setForeground(TEXT);
    scores.setHorizontalAlignment(JLabel.CENTER);
    results.add(scores);
    this.add(results);
  }

  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

    if ("play".equals(command)) {
      String selected = buttonGroup.getSelection().getActionCommand();
      int userChoice = choiceToIndex(selected);
      userPicture.setIcon(createImageIcon(selected + ".png"));
      int computerChoice = (int) (Math.random() * choices.length);
      computerPicture.setIcon(createImageIcon(choices[computerChoice] + ".png"));
      int result = getResult(userChoice, computerChoice);

      String message = "";

      if (result == 1) {
        message = "You win!";
        wins++;
      } else if (result == -1) {
        message = "You lose.";
        losses++;
      } else {
        message = "It was a tie.";
      }

      //System.out.println(selected + " vs. " + choices[computerChoice] + " -> " + result);
      status.setText(message);
      scores.setText("You: " + wins + "; " + "Computer: " + losses);
    } else if ("reset".equals(command)) {
      // Just totally wipe everything
      // No mercy
      wins = 0;
      losses = 0;
      userPicture.setIcon(createImageIcon(buttonGroup.getSelection().getActionCommand() + ".png"));
      computerPicture.setIcon(null);
      status.setText("");
      scores.setText("You: 0; Computer: 0");
    } else { // Otherwise, we must have pressed a radio button
      // We want to update the screen as different radio buttons are selected
      status.setText("");
      userPicture.setIcon(createImageIcon(command + ".png"));
      computerPicture.setIcon(null);
    }
  }

  /**
   * Gets the result of a rock-paper-scissors match given two choices
   *
   * @param choice1 the first choice
   * @param choice2 the second choice
   * @return an integer that represents the status from the perspective of choice1
   */
  private static int getResult(int choice1, int choice2) {
    // If there's a tie...
    if (choice1 == choice2) {
      return 0;
    }

    int diff = choices.length / 2 - choice1;
    int adj1 = choice1 + diff;
    int adj2 = Math.floorMod(choice2 + diff, choices.length);

    // Returns 1 if adj1 > adj2
    // Returns -1 if adj1 < adj2
    if (adj1 > adj2) {
      return 1;
    } else {
      return -1;
    }
    // That is, 1 represents a win (from perspective of choice1)
    // -1 represents a loss (from perspective of choice1)
  }

  /**
   * Finds the index of a string in the choices array
   *
   * @param choice the choice
   * @return the index of the choice
   */
  private static int choiceToIndex(String choice) {
    for (int i = 0; i < choices.length; i++) {
      if (choice.equals(choices[i])) {
        return i;
      }
    }

    return 0;
  }

  /**
   * Creates and image icon given a path, handling possible errors
   *
   * @param path the path to the image
   * @return the desired ImageIcon object
   */
  protected static ImageIcon createImageIcon(String path) {
    java.net.URL url = RPSLS.class.getResource(path);

    if (url != null) {
      return new ImageIcon(url);
    } else {
      System.out.println("Could not find \"" + path + "\"");
      return null;
    }
  }
}
