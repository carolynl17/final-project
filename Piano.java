import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


public class Piano extends JPanel implements MouseInputListener, ActionListener
{
   private List<Key> blackKeys;
   private List<Key> whiteKeys;
   private Timer playNote;
   
   private Key current;
   
   public final int MILLISECS_NOTE = 10; // length of note will be accurate to 10 milliseconds
   public final int WIDTH = 768;
   public final int HEIGHT = 300;
   
   public static void main(String[] args)
   {
      Piano piano = new Piano();
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(piano.WIDTH, piano.HEIGHT);
      frame.setTitle("KEYBOARD");
      
      frame.add(piano);
      frame.setVisible(true);
   }
   
   public Piano(int octave, boolean twoOctaves)
   {
      // create the white key objects
      int currentX = 0;
      int y = 0;
      int whiteKeyWidth = WIDTH / 8;
      blackKeys = new ArrayList<Key>();
      whiteKeys = new ArrayList<Key>();
      List<Note> whiteNotes = oneOctaveNaturals(octave);
      for (int ii = 0; ii < 8; ii++)
      {
         whiteKeys.add(new Key(currentX, y, whiteKeyWidth, HEIGHT, whiteNotes.get(ii)));
         currentX += whiteKeyWidth;
      }
      
      // create the black key objects
      List<Note> blackNotes = oneOctaveBlackKeys(octave);
      int blackKeyWidth = whiteKeyWidth / 2;
      int blackKeyHeight = 2 * HEIGHT / 3;
      currentX = 4 * blackKeyWidth / 3;
      int count = -1; // adds 1 before accessing the index
      blackKeys.add(new Key(currentX, y, blackKeyWidth, blackKeyHeight, blackNotes.get(++count)));
      currentX += (int) (7.0 * blackKeyWidth / 3.0);
      blackKeys.add(new Key(currentX, y, blackKeyWidth, blackKeyHeight, blackNotes.get(++count)));
      currentX += (int) (43.0 * blackKeyWidth / 12.0);
      blackKeys.add(new Key(currentX, y, blackKeyWidth, blackKeyHeight, blackNotes.get(++count)));
      currentX += (int) (9.0 * blackKeyWidth / 4.0);
      blackKeys.add(new Key(currentX, y, blackKeyWidth, blackKeyHeight, blackNotes.get(++count)));
      currentX += (int) (9.0 * blackKeyWidth / 4.0);
      blackKeys.add(new Key(currentX, y, blackKeyWidth, blackKeyHeight, blackNotes.get(++count)));
      
      // create the timer
      playNote = new Timer(MILLISECS_NOTE, this); 
         
   }
   
   public Piano()
   {
      this(5, false);
   }
   
   public List<Note> oneOctaveNaturals(int octave)
   {
      double len = MILLISECS_NOTE / 1000.0;
      List<Note> theNotes = new ArrayList<Note>();
      theNotes.add(new Note(len, Pitch.C, octave));
      theNotes.add(new Note(len, Pitch.D, octave));
      theNotes.add(new Note(len, Pitch.E, octave));
      theNotes.add(new Note(len, Pitch.F, octave));
      theNotes.add(new Note(len, Pitch.G, octave));
      theNotes.add(new Note(len, Pitch.A, octave));
      theNotes.add(new Note(len, Pitch.B, octave));
      theNotes.add(new Note(len, Pitch.C, octave));
      return theNotes;
   }
   
   public List<Note> oneOctaveBlackKeys(int octave)
   {
      double len = MILLISECS_NOTE / 1000.0;
      Accidental acc = Accidental.SHARP;
      List<Note> theNotes = new ArrayList<Note>();
      theNotes.add(new Note(len, Pitch.C, acc, octave));
      theNotes.add(new Note(len, Pitch.D, acc, octave));
      theNotes.add(new Note(len, Pitch.F, acc, octave));
      theNotes.add(new Note(len, Pitch.G, acc, octave));
      theNotes.add(new Note(len, Pitch.A, acc, octave));
      return theNotes;
   }
   
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      for (Key k : whiteKeys)
      {
         k.draw(g, true);
      }
      for (Key k : blackKeys) 
      {
         k.draw(g, false); //draw black keys second so they are on top
      }
   }
   
   public void mousePressed(MouseEvent event)
   {
      playNote.start();
      for (Key k : blackKeys)
      {  
         if (k.isPressed(event.getPoint()))
         {
            current = k;
            return;
         }
      }
      for (Key k : whiteKeys)
      {
         if (k.isPressed(event.getPoint()))
         {
            current = k; 
            return;
         }
      }
   }
   
   public void mouseReleased(MouseEvent event)
   {
      playNote.stop();
   }
   
   public void mouseClicked(MouseEvent event) {}
   public void mouseDragged(MouseEvent event) {}
   public void mouseEntered(MouseEvent event) {}
   public void mouseExited(MouseEvent event) {}
   public void mouseMoved(MouseEvent event) {}
   
   public void actionPerformed(ActionEvent event) 
   {
      current.play();
   }
}