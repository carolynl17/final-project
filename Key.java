import java.awt.*;

public class Key
{
   private int leftX;
   private int upperY;
   private int rightX;
   private int lowerY;
   
   private Note note;
   private Graphics g;
   
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
   
   public void setGraphics(Graphics g)
   {
      this.g = g;
   }
   
   public void draw(boolean isWhite)
   {
      g.setColor(Color.BLACK);
      if (isWhite)
      {
         g.drawRect(leftX, upperY, rightX - leftX, lowerY - upperY);
      }
      else 
      {
         g.fillRect(leftX, upperY, rightX - leftX, lowerY - upperY);
      }
   }
   
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
   
   public boolean isPressed(Point p)
   {
      return (p.x >= leftX && p.x < rightX) && (p.y >= upperY && p.y <= lowerY);
   }
   
   public Note getNote()
   {
      return note;
   }
}