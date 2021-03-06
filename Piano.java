// Carolyn Lober
// May 26, 2015
// CS III
// Piano.java

// This class defines a piano object that plays notes with both the 
// mouse and the keyboard for as long as you press it down. 

import java.awt.event.*; // ActionListener, KeyListener
import javax.swing.*; // JPanel, Timer, JFrame
import javax.swing.event.*; // MouseInputListener
import java.awt.Graphics; 
import java.util.ArrayList;
import java.util.List;


public class Piano extends JPanel implements MouseInputListener, ActionListener, KeyListener
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
   
/** The number of milliseconds that the timer plays the note for each interval
  */
   public final int MILLISECS_NOTE = 150; // accuracy of the length of the note
   
/** The width of the frame
  */
   public final int WIDTH = 768;
   
/** The height of the frame
  */
   public final int HEIGHT = 400;
      
/** Creates a piano with of the specified octave.
  *
  * @param octave - the octave for which to create the piano
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
      for (int ii = 0; ii < 8; ii++) // 8 white keys
      {
         whiteKeys.add(new Key(currentX, y, whiteKeyWidth, HEIGHT, whiteNotes.get(ii)));
         currentX += whiteKeyWidth;
      }
      
      // create the black key objects
      List<Note> blackNotes = oneOctaveBlackKeys(octave);
      int blackKeyWidth = whiteKeyWidth / 2; // black keys are 1/2 the width of white keys
      int blackKeyHeight = 2 * HEIGHT / 3; // black keys are 2/3 the height of white keys
      currentX = 4 * blackKeyWidth / 3; // all subsequent "magic numbers" are necessary for the appearance
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
      
      // create frame
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      frame.setSize(this.WIDTH, this.HEIGHT);
      frame.setTitle("KEYBOARD");
      
      // add Piano, event reactors to frame 
      frame.add(this);
      frame.addMouseListener(this);
      frame.addMouseMotionListener(this);
      frame.addKeyListener(this);
      frame.setVisible(true); 
         
   }
   
/** Default constructor--creates a single octave piano that starts at middle C
  */
   public Piano()
   {
      this(5);
   }
   
/** Creates a list of notes representing the "white keys" of the piano, starting
  * with C and ending with C in the octave above
  * 
  * @param octave - the octave in which to create the notes (of the starting note)
  * @return - a list of notes that represent the white keys of a single octave of a piano
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
  * @return - the list of notes representing the black keys of a single-octave piano
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
   
   //NOTE: Like with MouseInputListener, I had to implement KeyListener rather
   //       than extend KeyAdapter, so the empty method is necessary
   
/** Called when a key is pressed. Acts like the mouse pressed method--
  * plays the piano key that corresponds to the key pressed and changes
  * its color. 
  *
  * @param event - the event that indicates that a key has been pushed
  */
   public void keyPressed(KeyEvent event)
   {
      Key pressed = whichKey(event);
      current = pressed;
      if (current != null) // if the key pressed does not correspond to a piano key, do nothing
      {
         current.changeColor();
         repaint();
         playNote.start();
      }
   }
   
/** Called when a key is released. Acts like the mouse released method--
  * stops the current note and un-colors the key
  *
  * @param event - the event that indicates that a key has been released
  */
   public void keyReleased(KeyEvent event)
   {
      if (current != null)
      {
         playNote.stop();
         repaint();
      }
   }
   
/** Called when a key is typed--nothing happens
  * 
  * @param event - the event that indicates that a key has been typed
  */
   public void keyTyped(KeyEvent event) {}
   
/** Determines which keyboard key the event indicates and which piano 
  * key that keyboard key corresponds to.
  *
  * @param event - the event that occurred
  * @return - the piano key that corresponds to the key pressed as indicated by the event
  */
   public Key whichKey(KeyEvent event) 
   {
      int theOctave = whiteKeys.get(0).getNote().getOctave(); // the octave of keyboard for all but the high C
      int code = event.getKeyCode();
      if (code == KeyEvent.VK_S)
         return findKey(Pitch.C, Accidental.NATURAL, theOctave);
      else if (code == KeyEvent.VK_E)
         return findKey(Pitch.C, Accidental.SHARP, theOctave);
      else if (code == KeyEvent.VK_D)
         return findKey(Pitch.D, Accidental.NATURAL, theOctave);
      else if (code == KeyEvent.VK_R)
         return findKey(Pitch.D, Accidental.SHARP, theOctave);
      else if (code == KeyEvent.VK_F)
         return findKey(Pitch.E, Accidental.NATURAL, theOctave);
      else if (code == KeyEvent.VK_G)
         return findKey(Pitch.F, Accidental.NATURAL, theOctave);
      else if (code == KeyEvent.VK_Y)
         return findKey(Pitch.F, Accidental.SHARP, theOctave);
      else if (code == KeyEvent.VK_H)
         return findKey(Pitch.G, Accidental.NATURAL, theOctave);
      else if (code == KeyEvent.VK_U)
         return findKey(Pitch.G, Accidental.SHARP, theOctave);
      else if (code == KeyEvent.VK_J)
         return findKey(Pitch.A, Accidental.NATURAL, theOctave);
      else if (code == KeyEvent.VK_I)
         return findKey(Pitch.A, Accidental.SHARP, theOctave);
      else if (code == KeyEvent.VK_K)
         return findKey(Pitch.B, Accidental.NATURAL, theOctave);
      else if (code == KeyEvent.VK_L)
         return findKey(Pitch.C, Accidental.NATURAL, theOctave + 1); // the high C is the next octave
      else 
         return null; // other keys do not correspond to notes
   }
      
/** Given a pitch, accidental, and octave, finds the piano key that corresponds
  *
  * @param pitch - the pitch of the key seeked
  * @param acc - the accidental of the key seeked
  * @param octave - the octave of the key seeked
  * @return - the key matching the parameters, otherwise null
  */
   public Key findKey(Pitch pitch, Accidental acc, int octave)
   {
      if (acc == Accidental.NATURAL)
      {
         for (Key k : whiteKeys)
         {
            if (k.getNote().getPitch() == pitch && k.getNote().getOctave() == octave)
            {  // ^ the octave only makes a difference with the upper and lower C's 
               return k;
            }
         }
      }
      else 
      {
         for (Key k : blackKeys)
         {
            if (k.getNote().getPitch() == pitch)
            {
               return k;
            }
         }
      }
      return null;
   }
   
}