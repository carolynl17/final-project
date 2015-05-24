import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class CreateFile implements ActionListener
{
   private JTextField input;
   
   public CreateFile()
   {
      JFrame frame = new JFrame();
      JPanel panel1 = new JPanel();
      JPanel panel2 = new JPanel();
      
      frame.setSize(400, 200);
      
      panel1.setLayout(new FlowLayout());
      frame.setLayout(new BorderLayout());
      panel2.setLayout(new GridLayout(2, 1));
      
      JLabel whichSong = new JLabel("What is your song called?", (int) JLabel.CENTER_ALIGNMENT);
      JLabel pleaseEnter = new JLabel("The piano will begin when you start playing.", (int) JLabel.CENTER_ALIGNMENT);
      JButton open = new JButton("Go!");
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
      String filename = input.getText();
      if (filename.length() > 3 && !filename.substring(filename.length() - 4, filename.length()).equals(".txt"))
      {
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
         }
         catch (FileNotFoundException e)
         {
            JOptionPane.showMessageDialog(null, "File not found. Please try again.");
         }
         RecordPiano piano = new RecordPiano(file);
      }
   }
}