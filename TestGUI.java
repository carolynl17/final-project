/*import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class TestGUI extends MouseInputAdapter implements ActionListener
{
   private static JFrame frame;
   
   public static void main(String[] args)
   {
      frame = new JFrame();
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
      frame.addMouseListener(new TestGUI());

      frame.setVisible(true);
   }
   
   public void actionPerformed(ActionEvent event)
   {
      JOptionPane.showMessageDialog(null, event.paramString());
   }
   
   public void mousePressed(MouseEvent event)
   {
      frame.setBackground(Color.BLACK);
   }
   
   public void mouseReleased(MouseEvent event)
   {
      frame.setBackground(Color.WHITE);
   }
}*/
   // code from BJP
import java.awt.*;
import javax.swing.*;

 public class TestGUI {
 public static void main(String[] args) {
 JFrame frame = new JFrame();
 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 frame.setLayout(new FlowLayout());
 frame.setSize(new Dimension(200, 100));
 frame.setTitle("A frame");

 JLabel label = new JLabel();
 label.setText("Move the mouse over me!");
 frame.add(label);

 MovementListener mListener = new MovementListener();
 label.addMouseListener(mListener);
 label.addMouseMotionListener(mListener);

 frame.setVisible(true);
 }
 }