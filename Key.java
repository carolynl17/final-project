import java.awt.*;

public class Key
{
/** The leftmost x value of the key
  */
   private int leftX;
   
/** The uppermost y value of the key
  */
   private int upperY;
   
/** The rightmost x value of the key
  */
   private int rightX;

/** The lowermost y value of the key
  */
   private int lowerY;
   
/** The note that the key represents
  */
   private Note note;
   
/** The graphics that the key is drawn with
  */
   private Graphics g;
      
/** Constructs a key with the upper left coordinate as the specified x and y values
  * and the specified width and height and note
  * 
  * @param x - the leftmost x value of the key
  * @param y - the uppermost y value of the key
  * @param theWidth - the width of the key
  * @param theHeight - the height of the key
  * @param theNote - the note represented by the key
  */
   public Key(int x, int y, int theWidth, int theHeight, Note theNote)
   {  
      leftX = x;
      upperY = y;
      rightX = x + theWidth;
      lowerY = y + theHeight;
      note = theNote;
   }
   
/** Plays the note represented by the key
  *
  */
   public void play()
   {
      note.play();
   }
   
/** Plays the note an octave above that represented by the key
  */
   public void playOctaveUp()
   {
      note.setOctave(note.getOctave() + 1);
      note.play();
      note.setOctave(note.getOctave() - 1);
   }
   
/** Sets the graphics of the key 
  * 
  * @param g - the graphics used to draw the key
  */
   public void setGraphics(Graphics g)
   {
      this.g = g;
   }
   
/** Accesses the graphics of the key
  * 
  * @return - the graphics used to draw the key
  */
   public Graphics getGraphics()
   {
      return g;
   }
   
/** Draws the key with the specified graphics object
  * 
  */
   public void draw()
   {
      g.setColor(Color.BLACK);
      if (note.getAccidental() == Accidental.NATURAL)
      {
         g.drawRect(leftX, upperY, rightX - leftX, lowerY - upperY);
      }
      else 
      {
         g.fillRect(leftX, upperY, rightX - leftX, lowerY - upperY);
      }
   }
   
/** Changes the color of the key to indicate that it is being played
  *
  */
   public void changeColor()
   {
      if (note.getPitch() == Pitch.C)
      {
         g.setColor(Color.RED);
      }
      else if (note.getPitch() == Pitch.D)
      {
         g.setColor(Color.ORANGE);
      }
      else if (note.getPitch() == Pitch.E)
      {
         g.setColor(Color.YELLOW);
      }
      else if (note.getPitch() == Pitch.F)
      {
         g.setColor(Color.GREEN);
      }
      else if (note.getPitch() == Pitch.G)
      {
         g.setColor(Color.BLUE);
      }
      else if (note.getPitch() == Pitch.A)
      {
         g.setColor(Color.MAGENTA);
      }
      else //(note.getPitch() == Pitch.B)
      {
         g.setColor(Color.PINK);
      }
      g.fillRect(leftX, upperY, rightX - leftX, lowerY - upperY);
   }

/** Checks if the key has been pressed on
  * 
  * @param p - the point representing the position of the click
  * 
  * @return - true if the key has been pressed on, false if not
  */
   public boolean isPressed(Point p)
   {
      return (p.x >= leftX && p.x < rightX) && (p.y >= upperY && p.y <= lowerY);
   }
   
/** Accesses the note represented by the key
  * 
  * @return - the note represented by the key
  */
   public Note getNote()
   {
      return note;
   }
   
/** Changes the note represented by the key
  *
  * @param theNote - the new ntoe that the key will represent
  */
   public void setNote(Note theNote)
   {
      note = theNote;
   }
}