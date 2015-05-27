// Carolyn Lober
// May 26, 2015
// CS III
// OpenFile.java

// This class robustly get user input in order to open a file and listen
// to the song it defines. 

import javax.swing.*; // JTextField, JOptionPane
import java.awt.event.*; // ActionListener
import java.awt.*; // Layouts
import java.util.*; // Scanner 
import java.io.*; // File

public class OpenFile implements ActionListener
{

/** The text field from which to get the user input
  */
   private JTextField input;
   
/** The constructor creates a frame with a button and text field
  * in which the user enters the name of the file they wish to open 
  * and hear.
  *
  */ 
   public OpenFile()
   {
      // create frame, panels
      JFrame frame = new JFrame();
      JPanel panel1 = new JPanel();
      JPanel panel2 = new JPanel();
      frame.setSize(400, 200);
      frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      
      // set layouts
      panel1.setLayout(new FlowLayout());
      frame.setLayout(new BorderLayout());
      panel2.setLayout(new GridLayout(2, 1));
      
      // add labels, button, text field
      JLabel whichSong = new JLabel("What song would you like to hear?", (int) JLabel.CENTER_ALIGNMENT);
      JLabel pleaseEnter = new JLabel("Please enter a file name.", (int) JLabel.CENTER_ALIGNMENT);
      JButton open = new JButton("Open file");
      input = new JTextField(10);
      open.addActionListener(this);
      
      // add labels, button, text field to panels
      panel1.add(input);
      panel1.add(open);
      panel2.add(whichSong);
      panel2.add(pleaseEnter);
      
      // add panels to frame
      frame.add(panel1, BorderLayout.SOUTH);
      frame.add(panel2, BorderLayout.NORTH);
      frame.pack();
      frame.setVisible(true);
   }
   
/** Called when the button is pressed. Checks that the file exists 
  *
  * @param event - the event that occurred (press on a button)
  */
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
   
/** Given a scanner on a file, plays the song represented by the file.
  *
  * @param fileInput - a scanner on the input file that represents a song
  */ 
   public void playSong(Scanner fileInput)
   {
      
      if (fileInput != null)
      {
         try 
         {
            Melody song = new Melody(MelodyMain.read(fileInput));
            song.play();
         }
         catch (InputMismatchException e)
         {
            JOptionPane.showMessageDialog(null, "Could not read file.\nInvalid formatting.");
         }
      }
   }
}