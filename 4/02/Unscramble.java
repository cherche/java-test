import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Unscramble {
  public static void main(String[] args) {
    JPanel content = new JPanel();
    JLabel title = new JLabel("Unscramble!");
    title.setFont(new Font("Avenir", Font.PLAIN, 48));
    content.add(title);
    JLabel instructions = new JLabel("Enter the unscrambled word. Press done to check.");
    instructions.setFont(new Font("Arial", Font.PLAIN, 12));
    content.add(instructions);
    JLabel word = new JLabel("ertpuocm");
    word.setFont(new Font("Arial", Font.PLAIN, 16));
    content.add(word);
    JTextField guess = new JTextField(10);
    content.add(guess);
    JButton submit = new JButton("Done");
    submit.setOpaque(true);
    submit.setBorderPainted(false);
    submit.setForeground(Color.WHITE);
    submit.setBackground(Color.BLUE);
    content.add(submit);
    JLabel win = new JLabel();
    win.setFont(new Font("Arial", Font.PLAIN, 14));
    content.add(win);
    submit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (sanitize(guess.getText()).equals("computer")) {
          win.setText("That's right!");
          content.remove(submit);
        } else {
          win.setText("Not quite . . .");
        }
      }
    });
    JFrame window = new JFrame("Unscramble");
    window.setContentPane(content);
    window.setSize(300, 175);
    window.setLocation(200, 200);
    window.setVisible(true);
  }

  private static String sanitize(String string) {
    // Regular expressions kind of throw readability
    // out the window, but here we go:
    // 1. Turn all uppercase letters to their lowercase counterparts
    // 2. Remove all non-alphanumeric non-space characters
    // 3. Remove leading and trailing whitespace
    // 4. Compress all adjacent whitespace with a single space
    return string
      .toLowerCase()
      .replaceAll("[^0-9a-z ]+", "")
      .trim()
      .replaceAll("\\s+", " ");
  }
}
