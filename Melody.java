// Carolyn Lober
// CS III
// March 22, 2015
// Melody.java

import java.util.*; // Stack and Queue

public class Melody 
{

/** The ordered list of Note objects that represents the song
  */
   private LinkedList<Note> theSong; 
   
/** Constructor for a melody object- inicializes theSong
  *
  * @param song - the queue of notes that represent the song
  */
   public Melody(Queue<Note> song)
   {
      this.theSong = (LinkedList) song;
   }
   
/** Calculates the length of the song, in seconds
  *
  * @return - the length of the song, in seconds
  */
   public double getLength()
   {
//       if (theSong.isEmpty())
//          return 0;
      double length = 0;
      boolean repeated = false;
      for (int ii = 0; ii < theSong.size(); ii++)
      {
         Note current = theSong.remove();
         length += current.getDuration();
         if (current.isRepeat() || repeated)
            length += current.getDuration(); // if part of a repeated section, add length twice
         else {} 
         if (current.isRepeat() && repeated)
            repeated = false;
         else 
         { 
            if (current.isRepeat() && !repeated) // I still couldn't get this to work, but I don't know what else to try.
               repeated = true;
         }
         theSong.add(current);
      }
      return length;
   }
   
/** Returns the formatted string representation of the melody
  * 
  * @return the string representation of the melody
  */
   public String toString()
   {
      String str = "";
      for (int ii = 0; ii < theSong.size(); ii++)
      {
         Note thisNote = theSong.remove();
         str += thisNote.toString() + "\n";
         theSong.add(thisNote);
      }
      return str;
   }
   
/** Changes the length of the notes to make the song faster or slower
  * by the factor input into the parameters
  * 
  * @param tempo - the factor by which to change the tempo
  */
   public void changeTempo(double tempo)
   {
      for (int ii = 0; ii < theSong.size(); ii++)
      {
         Note thisNote = theSong.remove();
         thisNote.setDuration(thisNote.getDuration() * (1 / tempo));
         theSong.add(thisNote);
      }
   }
   
/** Reverses the order of the notes in the melody
  * 
  */
   public void reverse()
   {
      Stack<Note> stackReverseSong = new Stack<Note>();
      int lenSong = theSong.size();
      for (int ii = 0; ii < lenSong; ii++)
      {
         Note thisNote = theSong.remove();
         stackReverseSong.push(thisNote);
      }
      for (int ii = 0; ii < lenSong; ii++)
      {
         theSong.add(stackReverseSong.pop());
      }
   }
  
/** Adds the specified melody to the end of the current melody
  * without changing the inputted melody.
  * 
  * @param other - the notes to add to the end of the melody
  */
   public void append(Melody other)
   {
      for (int ii = 0; ii < other.theSong.size(); ii++)
      {
         Note thisNote = other.theSong.remove();
         theSong.add(thisNote);
         other.theSong.add(thisNote);
      }
   }
   
/** Plays the notes, in order, with repeats where specified.
  * 
  */
   public void play()
   {
      LinkedList<Note> repeatedSection = new LinkedList<Note>();
      int len = theSong.size();
      boolean repeated = false;
      for (int ii = 0; ii < len; ii++)
      {
         Note thisNote = theSong.remove();
         thisNote.play();
         theSong.add(thisNote);
         if (thisNote.isRepeat() && repeated)
         {
            repeated = false;
            repeatedSection.add(thisNote);
            int lenRepeat = repeatedSection.size();
            for (int kk = 0; kk < lenRepeat; kk++)
            {
               Note theNote = repeatedSection.remove();
               theNote.play();
            }
         }
         else if (thisNote.isRepeat() || repeated)
         {
            repeatedSection.add(thisNote);
            repeated = true;
         }
         
      }
   }
}