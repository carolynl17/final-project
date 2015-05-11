import java.awt.*; // graphics objects
import java.awt.event.*; // event objects
import javax.swing.*; // frame objects

public class OneKey
{
   public static void main(String[] args)
   {
      JFrame frame = createFrame();
      frame.add(createButton(new Note(0.5, Pitch.C, Accidental.NATURAL)));
      frame.add(createButton(new Note(0.5, Pitch.D, Accidental.NATURAL)));
      frame.add(createButton(new Note(0.5, Pitch.E, Accidental.NATURAL)));
      frame.add(createButton(new Note(0.5, Pitch.F, Accidental.NATURAL)));
      frame.add(createButton(new Note(0.5, Pitch.G, Accidental.NATURAL)));
      frame.add(createButton(new Note(0.5, Pitch.A, Accidental.NATURAL)));
      frame.add(createButton(new Note(0.5, Pitch.B, Accidental.NATURAL)));
      frame.add(createButton(new Note(Pitch.C, Accidental.NATURAL, 5)));
      frame.setVisible(true);
   }
   
   public static JFrame createFrame()
   {
      JFrame frame = new JFrame();
      frame.setLayout(new GridLayout(1, 8));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Single-key piano.");
      frame.setSize(new Dimension(800, 250));
      return frame;
   }
   
   public static JButton createButton(Note note)
   {
      JButton key = new JButton("");
      key.addActionListener(new PlayNote(note));
      return key;
   }
}