import javax.swing.*; // JTextField, JOptionPane
import java.awt.event.*; // ActionListener
import java.awt.*;
import java.util.*; // Scanner 
import java.io.*; // File

public class OpenFile implements ActionListener
{
   private JTextField input;
   
   public OpenFile()
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
      input = new JTextField(10);
      open.addActionListener(this);
      
      panel1.add(input);
      panel1.add(open);
      panel2.add(whichSong);
      panel2.add(pleaseEnter);
      
      frame.add(panel1, BorderLayout.SOUTH);
      frame.add(panel2, BorderLayout.NORTH);
      frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
      
   }
   
   public void actionPerformed(ActionEvent event)
   {
      String filename = input.getText().trim();
      if (filename.length() > 3 && !filename.substring(filename.length() - 4, filename.length()).equals(".txt"))
      {
         filename += ".txt";
      }
      Scanner fileScan = null;
      File f = new File(filename);
      try
      {
         fileScan = new Scanner(f);
         playSong(fileScan);
      }
      catch (FileNotFoundException e)
      {
         JOptionPane.showMessageDialog(null, "File not found. Please enter a valid file name.\n(Must be a .txt file)");
      }
   } 
   
   public void playSong(Scanner fileInput)
   {
      
      if (fileInput != null)
      {
         Melody song = new Melody(MelodyMain.read(fileInput));
         song.play();
      }
   }
}