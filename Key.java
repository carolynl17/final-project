import java.awt.*;

public class Key
{
   private int leftX;
   private int upperY;
   private int rightX;
   private int lowerY;
   
   private Note note;
   
   public Key(int x, int y, int theWidth, int theHeight, Note theNote)
   {  
      leftX = x;
      upperY = y;
      rightX = x + theWidth;
      lowerY = y + theHeight;
      note = theNote;
   }
   
   public void play()
   {
      note.play();
   }
   
   public void draw(Graphics g, boolean isWhite)
   {
      if (isWhite)
      {
         g.drawRect(leftX, upperY, rightX - leftX, lowerY - upperY);
      }
      else 
      {
         g.fillRect(leftX, upperY, rightX - leftX, lowerY - upperY);
      }
   }
   
   public boolean isPressed(Point p)
   {
      return (p.x >= leftX && p.x < rightX) && (p.y >= upperY && p.y <= lowerY);
   }
   
   public Note getNote()
   {
      return note;
   }
}