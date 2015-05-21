import javax.swing.*; // JTextField, JOptionPane
import java.awt.event.*; // ActionListener
import java.util.*; // Scanner 
import java.io.*; // File

public class OpenFile implements ActionListener
{
   private JTextField input;
   
   public OpenFile(JTextField theTextField)
   {
      input = theTextField;
   }
   
   public void actionPerformed(ActionEvent event)
   {
      String filename = input.getText();
      Scanner fileScan = null;
      File f = new File(filename);
      try
      {
         fileScan = new Scanner(f);
         playSong(fileScan);
      }
      catch (FileNotFoundException e)
      {
         JOptionPane.showMessageDialog(null, "File not found. Please enter a valid file name.");
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