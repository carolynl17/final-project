// Carolyn Lober
// CS III
// March 22, 2015
// Melody.java

import java.util.*; // Stack and Queue

public class Melody 
{
   LinkedList<Note> theSong;
   int carolyn;
   
   public Melody(Queue<Note> song)
   {
      this.theSong = (LinkedList) song;
   }
   
   public double getLength()
   {
      if (theSong.isEmpty())
         return 0;
      double length = 0;
      for (int ii = 0; ii < theSong.size(); ii++)
      {
         Note current = theSong.peek();
         length += current.getDuration();
         theSong.remove(current);
         theSong.add(current);
      }
      return length;
   }
   
   public String toString()
   {
      return "";
   }
   
   public void changeTempo(double tempo)
   {
   }
   
   public void reverse()
   {
      LinkedList<Note> reverseSong = new LinkedList<Note>();
      Stack<Note> stackReverseSong = new Stack<Note>();
      for (int ii = 0; ii < theSong.size(); ii++)
      {
         Note thisNote = theSong.peek();
         stackReverseSong.push(thisNote);
         theSong.remove(thisNote);
      }
      for (int ii = 0; ii < stackReverseSong.size(); ii++)
      {
         reverseSong.add(stackReverseSong.pop());
      }
   }
   
   public void append(Melody other)
   {
      theSong.addAll(other.theSong);
   }
   
   public void play()
   {
   }
}