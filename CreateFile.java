// Carolyn Lober
// May 26, 2015
// CS III
// CreateFile.java

// This class robustly gets user input to create a file to print
// the recording to. Once it has one, it creates a RecordPiano with
// that output file. 

import java.awt.event.*; // ActionListener
import javax.swing.*; // JComponents
import java.awt.*; // Layouts
import java.io.*; // PrintStream

public class CreateFile implements ActionListener
{

/** The text field from which the class gets input 
  */
   private JTextField input;
   
/** The frame that the constructor creates
  */
   private JFrame frame;
   
/** The constructor creates a frame with a text field and button
  * in which the user enters the name of their song. 
  *
  */
   public CreateFile()
   {
      // create frame, panels
      frame = new JFrame();
      JPanel panel1 = new JPanel();
      JPanel panel2 = new JPanel();
      frame.setSize(400, 200);
      frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      
      // set layouts
      panel1.setLayout(new FlowLayout());
      frame.setLayout(new BorderLayout());
      panel2.setLayout(new GridLayout(2, 1));
      
      // add labels, button, text field
      JLabel whichSong = new JLabel("What is your song called?", (int) JLabel.CENTER_ALIGNMENT);
      JLabel pleaseEnter = new JLabel("This is be the name of the file your song saves in.", (int) JLabel.CENTER_ALIGNMENT);
      JButton open = new JButton("Go!");
      input = new JTextField(10);
      open.addActionListener(this);
      
      // add labels, button, text field to panels 
      panel1.add(input);
      panel1.add(open);
      panel2.add(whichSong);
      panel2.add(pleaseEnter);
      
      // add panels to frame, set visible
      frame.add(panel1, BorderLayout.SOUTH);
      frame.add(panel2, BorderLayout.NORTH);
      frame.pack();
      frame.setVisible(true);
   }
   
/** Called when the user enters a song name. Checks if the file already
  * exists and if so, if the user wants to override the file. 
  *
  * @param event - the event that indicates a button has been pressed
  */
   public void actionPerformed(ActionEvent event)
   {
      String filename = input.getText();
      if (filename.length() > 3 && !filename.substring(filename.length() - 4, filename.length()).equals(".txt"))
      { // ^ if filename is not a .txt file 
         filename += ".txt";
      }
      File f = new File(filename);
      int choice = JOptionPane.YES_OPTION;
      if (f.exists() && !f.isDirectory())
      {
         choice = JOptionPane.showConfirmDialog(null, "File already exists. Override existing file?");
      }
      PrintStream file = null;
      if (choice == JOptionPane.YES_OPTION)
      {
         try
         {
            file = new PrintStream(f);
            RecordPiano piano = new RecordPiano(file);
            frame.setVisible(false);
         }
         catch (FileNotFoundException e)
         {
            JOptionPane.showMessageDialog(null, "File not found. Please try again.");
         }
      }
   }
}