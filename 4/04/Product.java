import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Product extends JPanel implements ActionListener {
  JTextField num1;
  JTextField num2;
  JLabel prod;

  public static void main(String[] args) {
    Product content = new Product();

    JFrame window = new JFrame("Product");
    window.setContentPane(content);
    window.setSize(400, 100);
    window.setLocation(100, 100);
    window.setVisible(true);
  }

  public Product() {
    num1 = new JTextField("", 2);
    add(num1);
    add(new JLabel(" x "));
    num2 = new JTextField("", 2);
    add(num2);
    add(new JLabel(" = "));
    prod = new JLabel("?");
    add(prod);
    JButton calculate = new JButton("Calculate");
    calculate.addActionListener(this);
    add(calculate);
  }

  public void actionPerformed(ActionEvent e) {
    int n1 = Integer.parseInt(num1.getText());
    int n2 = Integer.parseInt(num2.getText());
    prod.setText(String.valueOf(n1 * n2));
  }
}
