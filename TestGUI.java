import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TestGUI
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(new Dimension(250, 100));
      frame.setTitle("How are you?");

      frame.setLayout(new FlowLayout());
      JButton positive = new JButton("I'm well.");
      positive.addActionListener(new TestPopUp());
      frame.add(positive);
      JButton negative = new JButton("Life sucks.");
      negative.addActionListener(new TestPopUp());
      frame.add(negative);

      frame.setVisible(true);
   }
}