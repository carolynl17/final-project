import javax.swing.*; // JComponents
import java.awt.event.*; // MouseEvents
import java.awt.*; //Layouts

public class PianoPlayMelody 
{
   public PianoPlayMelody()
   {
      JFrame frame = new JFrame();
      JPanel panel1 = new JPanel();
      JPanel panel2 = new JPanel();
      
      frame.setSize(400, 200);
      
      panel1.setLayout(new FlowLayout());
      frame.setLayout(new BorderLayout());
      panel2.setLayout(new GridLayout(2, 1));
      
      JLabel whichSong = new JLabel("What song would you like to hear?", (int) JLabel.CENTER_ALIGNMENT);
      JLabel pleaseEnter = new JLabel("Please enter a file name.", (int) JLabel.CENTER_ALIGNMENT);
      JButton open = new JButton("Open file");
      JTextField answer = new JTextField(10);
      open.addActionListener(new OpenFile(answer));
      
      panel1.add(answer);
      panel1.add(open);
      panel2.add(whichSong);
      panel2.add(pleaseEnter);
      
      frame.add(panel1, BorderLayout.SOUTH);
      frame.add(panel2, BorderLayout.NORTH);
      frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
      
   }

}