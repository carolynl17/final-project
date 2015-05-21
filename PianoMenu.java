import javax.swing.*; // JComponents
import java.awt.*; // Layouts, Fonts
import java.awt.event.*; // ActionListener

public class PianoMenu implements ActionListener
{
   public static void main(String[] args)
   {
      //create frame, set title, layout
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("How are you?");
      frame.setLayout(new BorderLayout());
      
      //create fonts for instructions
      Font menuFont = new Font("Times New Roman", Font.PLAIN, 15);
      Font defFont = new Font("Times New Roman", Font.ITALIC, menuFont.getSize() - 2);
      
      //create directions as JLabel, add to frame
      String menu = "Welcome to the Clavier¹!\nWhat would you like to do?";
      JLabel menuIntro = new JLabel(menu, (int) JLabel.CENTER_ALIGNMENT);
      menuIntro.setFont(menuFont);
      frame.add(menuIntro, BorderLayout.NORTH);
      
      //create definition of clavier, add to frame
      String def = "¹Clavier: (noun) the keyboard of a musical instrument (Dictionary.com)";
      JLabel clavierDef = new JLabel(def, (int) JLabel.CENTER_ALIGNMENT);
      clavierDef.setFont(defFont);
      frame.add(clavierDef, BorderLayout.SOUTH);
      
      //create buttons in a separate JPanel, add to frame
      JPanel choices = new JPanel(new FlowLayout());
      JButton play = new JButton("Play Piano");
      play.addActionListener(new PianoMenu());
      choices.add(play);
      JButton record = new JButton("Record a Song");
      record.addActionListener(new PianoMenu());
      choices.add(record);
      JButton listen = new JButton("Listen to a Song");
      listen.addActionListener(new PianoPlayMelody());
      choices.add(listen);
      frame.add(choices, BorderLayout.CENTER);

      //display the frame
      frame.pack();
      frame.setVisible(true);
   }
   
   public void actionPerformed(ActionEvent event)
   {
      Piano piano = new Piano();
      JFrame frame = new JFrame();
      Piano.frame = frame;
      frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      frame.setSize(piano.WIDTH, piano.HEIGHT);
      frame.setTitle("KEYBOARD");
      
      frame.add(piano);
      frame.addMouseListener(piano);
      frame.addMouseMotionListener(piano);
      frame.setVisible(true);
   }
}