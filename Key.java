// Carolyn Lober
// May 26, 2015
// CS III
// RecordPiano.java

// This class defines a Key object, which the piano consists of. 
// Keys contain information about what note they represent and 
// the size and position on the piano.

import java.awt.*; // Graphics

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
   
/** The current color of the key (changes when the key is played)
  */
   private Color color;
      
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
      color = Color.BLACK;
   }
   
/** Plays the note represented by the key
  *
  */
   public void play()
   {
      note.play();
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
      g.setColor(color);
      if (note.getAccidental() == Accidental.NATURAL && color.equals(Color.BLACK))
      {
         g.drawRect(leftX, upperY, rightX - leftX, lowerY - upperY);
      }
      else 
      {
         g.fillRect(leftX, upperY, rightX - leftX, lowerY - upperY);
      }
      color = Color.BLACK;
   }
   
/** Changes the color of the key to indicate that it is being played
  *
  */
   public void changeColor()
   {
      float ordinal = (float) note.getPitch().ordinal();
      if (note.getAccidental() == Accidental.SHARP) // all black keys are sharp
      {
         ordinal += (float) .5;
      }
      float div = ordinal / (float) Pitch.values().length;
      color = Color.getHSBColor(div, 1, (float) .7);
   }

/** Checks if the key has been pressed on
  * 
  * @param p - the point representing the position of the click
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