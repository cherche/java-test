import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FindMe extends JPanel implements ActionListener {
  CardLayout cdLayout = new CardLayout();

  public static void main(String[] args) {
    JFrame window = new JFrame("Find Me");
    FindMe content = new FindMe();

    window.setContentPane(content);
    window.setSize(400, 200);
    window.setLocation(0, 0);
    window.setVisible(true);
  }

  public FindMe() {
    setLayout(cdLayout);
    screen1();
    screen2();
    screen3();
    screen4();
    screen5();
    screen6();
    screen7();
  }

  private void screen1() {
    JPanel screen = new JPanel();

    screen.add(new JLabel("You are in the hall."));
    screen.add(new JLabel("Go to:"));
    JButton r2 = new JButton("Study");
    r2.setActionCommand("2");
    r2.addActionListener(this);
    screen.add(r2);
    JButton r3 = new JButton("Lounge");
    r3.setActionCommand("3");
    r3.addActionListener(this);
    screen.add(r3);
    JButton r7 = new JButton("Ballroom");
    r7.setActionCommand("7");
    r7.addActionListener(this);
    screen.add(r7);

    this.add("1", screen);
  }

  private void screen2() {
    JPanel screen = new JPanel();

    screen.add(new JLabel("You are in the study."));
    screen.add(new JLabel("Go to:"));
    JButton r1 = new JButton("Hall");
    r1.setActionCommand("1");
    r1.addActionListener(this);
    screen.add(r1);
    JButton r4 = new JButton("Library");
    r4.setActionCommand("4");
    r4.addActionListener(this);
    screen.add(r4);

    this.add("2", screen);
  }

  private void screen3() {
    JPanel screen = new JPanel();

    screen.add(new JLabel("You are in the lounge."));
    screen.add(new JLabel("Go to:"));
    JButton r1 = new JButton("Hall");
    r1.setActionCommand("1");
    r1.addActionListener(this);
    screen.add(r1);
    JButton r5 = new JButton("Conservatory");
    r5.setActionCommand("5");
    r5.addActionListener(this);
    screen.add(r5);

    this.add("3", screen);
  }

  private void screen4() {
    JPanel screen = new JPanel();

    screen.add(new JLabel("You are in the library."));
    screen.add(new JLabel("Go to:"));
    JButton r2 = new JButton("Study");
    r2.setActionCommand("2");
    r2.addActionListener(this);
    screen.add(r2);
    JButton r6 = new JButton("Billard Room");
    r6.setActionCommand("6");
    r6.addActionListener(this);
    screen.add(r6);

    this.add("4", screen);
  }

  private void screen5() {
    JPanel screen = new JPanel();

    screen.add(new JLabel("You are in the conservatory."));
    screen.add(new JLabel("Go to:"));
    JButton r3 = new JButton("Lounge");
    r3.setActionCommand("3");
    r3.addActionListener(this);
    screen.add(r3);
    JButton r7 = new JButton("Ballroom");
    r7.setActionCommand("7");
    r7.addActionListener(this);
    screen.add(r7);

    this.add("5", screen);
  }

  private void screen6() {
    JPanel screen = new JPanel();

    screen.add(new JLabel("You are in the billard room."));
    screen.add(new JLabel("Go to:"));
    JButton r1 = new JButton("Hall");
    r1.setActionCommand("1");
    r1.addActionListener(this);
    screen.add(r1);

    this.add("6", screen);
  }

  private void screen7() {
    JPanel screen = new JPanel();

    screen.add(new JLabel("You are in the ballroom."));
    screen.add(new JLabel("Go to:"));
    JButton r5 = new JButton("Conservatory");
    r5.setActionCommand("5");
    r5.addActionListener(this);
    screen.add(r5);
    JButton r6 = new JButton("Billard Room");
    r6.setActionCommand("6");
    r6.addActionListener(this);
    screen.add(r6);

    this.add("7", screen);
  }

  public void actionPerformed(ActionEvent e) {
    cdLayout.show(this, e.getActionCommand());
  }

  protected static ImageIcon createImageIcon(String path) {
    java.net.URL url = FindMe.class.getResource(path);

    if (url != null) {
      return new ImageIcon(url);
    } else {
      System.out.println("That file is like my happiness.");
      System.out.println("It doesn't exist.");
      return null;
    }
  }
}
