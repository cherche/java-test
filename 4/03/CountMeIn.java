import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CountMeIn extends JPanel implements ActionListener {
  int num = 0;
  JLabel counter;

  public static void main(String[] args) {
    CountMeIn panel = new CountMeIn();

    JFrame window = new JFrame("Count Me In");
    window.setContentPane(panel);
    window.setSize(300, 450);
    window.setLocation(0, 0);
    window.setVisible(true);
  }

  public CountMeIn() {
    JLabel title = new JLabel("Count Me In");
    title.setFont(new Font("Arial", Font.PLAIN, 50));
    this.add(title);

    counter = new JLabel("   " + String.valueOf(num) + "   ");
    counter.setFont(new Font("Arial", Font.PLAIN, 200));
    this.add(counter);

    JButton adder = new JButton("+");
    adder.setFont(new Font("Arial", Font.PLAIN, 50));
    adder.setBackground(Color.green);
    adder.setOpaque(true);
    adder.setBorderPainted(false);
    adder.setActionCommand("+");
    adder.addActionListener(this);
    this.add(adder);

    JButton subtracter = new JButton("-");
    subtracter.setFont(new Font("Arial", Font.PLAIN, 50));
    subtracter.setBackground(Color.red);
    subtracter.setOpaque(true);
    subtracter.setBorderPainted(false);
    subtracter.setActionCommand("-");
    subtracter.addActionListener(this);
    this.add(subtracter);

    JLabel instructions = new JLabel("Press the button, we keep count for you!");
    instructions.setFont(new Font("Arial", Font.PLAIN, 12));
    this.add(instructions);
  }

  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

    if (command.equals("+")) {
      num++;
      counter.setText("   " + String.valueOf(num) + "   ");
    } else if (command.equals("-")) {
      num--;
      counter.setText("   " + String.valueOf(num) + "   ");
    }
  }
}
