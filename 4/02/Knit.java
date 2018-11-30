import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Knit extends JPanel {
  public static void main(String[] args) {
    JPanel content = new Knit();
    content.setBackground(Color.BLUE);
    JFrame window = new JFrame("Knitting");
    window.setContentPane(content);
    window.setSize(1000, 550);
    window.setLocation(200, 200);
    window.setVisible(true);
  }

  public Knit() {

  }

  protected static ImageIcon createImageIcon(String path) {
    java.net.URL url = Knit.class.getResource(path);

    if (url != null) {
      return new ImageIcon(url);
    } else {
      System.out.println("Cannot locate resource at \"" + path + "\".");
      return null;
    }
  }
}
