import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


public class Piano extends JPanel implements MouseInputListener, ActionListener
{
/** The black keys of the piano
  */
   private List<Key> blackKeys;
   
/** The white keys of the piano
  */
   private List<Key> whiteKeys;
   
/** The timer that allows the note to keep playing the whole time
  * that the key is pressed.
  */
   private Timer playNote;
   
/** The key that is currently being played
  */
   protected Key current;
   
/** The frame that the piano is in
  */
   public static JFrame frame;
   
/** The number of milliseconds that the timer plays the note for each interval
  */
   public final int MILLISECS_NOTE = 150; // accuracy of the length of the note in milliseconds
   
/** The width of the frame
  */
   public final int WIDTH = 768;
   
/** The height of the frame
  */
   public final int HEIGHT = 400;
   
   /*public static void main(String[] args)
   {
      Piano piano = new Piano();
      frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(piano.WIDTH, piano.HEIGHT);
      frame.setTitle("KEYBOARD");
      
      frame.add(piano);
      frame.addMouseListener(piano);
      frame.addMouseMotionListener(piano);
      frame.setVisible(true);
   }*/
   
/** Creates a piano with of the specified octave.
  *
  */
   public Piano(int octave)
   {
      // create the white key objects
      int currentX = 0;
      int y = 0;
      int whiteKeyWidth = WIDTH / 8; // There are 8 white keys
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
      currentX += 7 * blackKeyWidth / 3;
      blackKeys.add(new Key(currentX, y, blackKeyWidth, blackKeyHeight, blackNotes.get(++count)));
      currentX += 43 * blackKeyWidth / 12;
      blackKeys.add(new Key(currentX, y, blackKeyWidth, blackKeyHeight, blackNotes.get(++count)));
      currentX += 9 * blackKeyWidth / 4;
      blackKeys.add(new Key(currentX, y, blackKeyWidth, blackKeyHeight, blackNotes.get(++count)));
      currentX += 9 * blackKeyWidth / 4;
      blackKeys.add(new Key(currentX, y, blackKeyWidth, blackKeyHeight, blackNotes.get(++count)));
      
      // create the timer
      playNote = new Timer(MILLISECS_NOTE, this);
      
      // create frame and add piano
      JFrame frame = new JFrame();
      Piano.frame = frame;
      frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      frame.setSize(this.WIDTH, this.HEIGHT);
      frame.setTitle("KEYBOARD");
      
      frame.add(this);
      frame.addMouseListener(this);
      frame.addMouseMotionListener(this);
      frame.setVisible(true); 
         
   }
   
/** Default constructor--creates a single octave piano that starts at middle C
  */
   public Piano()
   {
      this(5);
   }
   
/** Creates a list of notes representing the "white keys" of the keyboard, starting
  * with C and ending with C in the octave above
  * 
  * @param octave - the octave in which to create the notes (of the starting note)
  */
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
      theNotes.add(new Note(len, Pitch.C, octave + 1));
      return theNotes;
   }
   
/** Creates a list of notes representing the "black keys" of single octave 
  * starting with C# and ending with A#
  *
  * @param octave - the octave in which to create the notes
  * @return - the list of notes
  */
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
   
/** Overrides JPanel's paintComponent method--draws the keys of the keyboard
  *
  * @param g - the graphics that method draws to
  */
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      for (Key k : whiteKeys)
      {
         k.setGraphics(g);
         k.draw();
      }
      for (Key k : blackKeys) 
      {
         k.setGraphics(g);
         k.draw(); //draw black keys second so they are on top
      }
   }
   
/** Called when the mouse is pressed down--plays the key that is pressed on and
  * changes the color of the pressed key
  *
  * @param event - the mouse's action (pressed)
  */
   public void mousePressed(MouseEvent event)
   {
      for (Key k : whiteKeys)
      {
         if (k.isPressed(event.getPoint()))
         {
            current = k; 
            break;
         }
      }
      for (Key k : blackKeys) // because the keys overlap, so black keys take precedence
      {  
         if (k.isPressed(event.getPoint()))
         {
            current = k;
            break;
         }
      }
      current.changeColor();
      repaint();
      playNote.start();
   }

/** Called when the mouse is released--stops the timer that plays the note
  * and changes the key back to black or white
  *
  * @param event - the mouse's action (release)
  */
   public void mouseReleased(MouseEvent event)
   {
      frame.update(current.getGraphics());
      playNote.stop();
      repaint();
   }
   
    //NOTE: I had to implement MouseInputListener rather than extend MouseInputAdapter
    //       because Piano already extends JPanel, so the empty methods are necessary.
    
/** Called when the mouse is clicked--nothing happens
  *
  * @param event - the mouse's action (click)
  */
   public void mouseClicked(MouseEvent event) {}
   
/** Called when the mouse is dragged--nothing happens
  *
  * @param event - the mouse's action (dragging)
  */
   public void mouseDragged(MouseEvent event) {}
   
/** Called when the mouse enters the component--nothing happens
  *
  * @param event - the mouse's action (entering)
  */
   public void mouseEntered(MouseEvent event) {}
   
/** Called when the mouse exits the component--nothing happens
  *
  * @param event - the mouse's action (exiting)
  */
   public void mouseExited(MouseEvent event) {}

/** Called when the mouse moves--nothing happens
  *
  * @param event - the mouse's action (movement)
  */
   public void mouseMoved(MouseEvent event) {}
   
/** The timer calls this method in regular intervals-- plays the current note
  * that is being pressed 
  * 
  * @param event - the ActionEvent that occured 
  */
   public void actionPerformed(ActionEvent event) 
   {
      current.play(); // Timer plays the note every 10 milliseconds
   }
   
   
}