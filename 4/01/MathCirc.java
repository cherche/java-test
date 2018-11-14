import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MathCirc extends JPanel implements ActionListener {
  public static void main(String[] args) {
    MathCirc content = new MathCirc();
    JFrame window = new JFrame("Math Circus");
    window.setContentPane(content);
    window.setSize(400, 200);
    window.setLocation(100, 100);
    window.setVisible(true);
  }

  public MathCirc() {
    setBackground(Color.cyan);
    JLabel welcome = new JLabel("Welcome to ");
    welcome.setFont(new Font("Arial", Font.ITALIC, 14));
    welcome.setBackground(Color.white);
    JLabel mathCircus = new JLabel("Math Circus!");
    mathCircus.setFont(new Font("Raleway", Font.BOLD, 40));
    mathCircus.setForeground(Color.red);
    JButton enter = new JButton("Enter");
    enter.setBackground(Color.blue);
    enter.setForeground(Color.yellow);
    // These next two lines are super necessary
    enter.setOpaque(true);
    enter.setBorderPainted(false);
    JTextField field = new JTextField("Something.");
    field.setBackground(Color.gray);
    add(field);
    add(welcome);
    add(mathCircus);
    add(enter);
  }

  public void actionPerformed(ActionEvent e) {

  }
}
