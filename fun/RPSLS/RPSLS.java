import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
/*
import java.util.*;
import java.awt.Image;
import javax.imageio.ImageIO;
*/

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
  JLabel userScore = new JLabel("Wins: 0");
  JLabel computerScore = new JLabel("Losses: 0");
  JLabel userPicture = new JLabel(createImageIcon(choices[0] + ".png"));
  JLabel computerPicture = new JLabel();
  int wins = 0;
  int losses = 0;

  public static void main(String[] args) {
    JPanel content = new RPSLS();
    JFrame window = new JFrame();
    window.setContentPane(content);
    window.setSize(400, 300);
    // Centers window
    window.setLocationRelativeTo(null);
    window.setVisible(true);
    window.setResizable(false);
    /*
    String[] paths = new String[] {
      "icon-16.png",
      "icon-32.png",
      "icon-64.png",
      "icon-128.png",
      "icon-256.png",
      "icon-512.png",
      "icon-1024.png"
    };
    ArrayList<Image> icons = new ArrayList<Image>();

    for (int i = 0; i < paths.length; i++) {
      icons.add(createImage(paths[i]));
    }

    window.setIconImages(icons);
    */
  }

  public RPSLS() {
    Color BACK = new Color(36, 36, 36);
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
    JPanel scores = new JPanel();
    scores.setBackground(BACK_DARKER);
    scores.setLayout(new GridLayout(1, 2));
    userScore.setHorizontalAlignment(JLabel.CENTER);
    userScore.setForeground(TEXT);
    scores.add(userScore);
    computerScore.setHorizontalAlignment(JLabel.CENTER);
    computerScore.setForeground(TEXT);
    scores.add(computerScore);
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

      if (result > 0) {
        message = "You win!";
        wins++;
      } else if (result < 0) {
        message = "You lose.";
        losses++;
      } else {
        message = "It was a tie.";
      }

      //System.out.println(selected + " vs. " + choices[computerChoice] + " -> " + result);
      status.setText(message);
      userScore.setText("Wins: " + wins);
      computerScore.setText("Losses: " + losses);
    } else if ("reset".equals(command)) {
      // Just totally wipe everything
      // No mercy
      wins = 0;
      losses = 0;
      userPicture.setIcon(createImageIcon(buttonGroup.getSelection().getActionCommand() + ".png"));
      computerPicture.setIcon(null);
      status.setText("");
      userScore.setText("Wins: 0");
      computerScore.setText("Losses: 0");
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

    // Similar to a Java Comparator
    return adj1 - adj2;
    // Is positive if choice1 beats choice2
    // Is negative if choice1 loses to choice2
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
    URL url = RPSLS.class.getResource(path);

    if (url != null) {
      return new ImageIcon(url);
    } else {
      System.out.println("Could not find \"" + path + "\"");
      return null;
    }
  }

  /*
  protected static Image createImage(String path) {
    URL url = RPSLS.class.getResource(path);

    try {
      return ImageIO.read(url);
    } catch (Exception e) {
      System.out.println("Could not find \"" + path + "\"");
      return null;
    }
  }
  */
}
