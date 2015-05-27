// Carolyn Lober
// May 26, 2015
// CS III
// RecordPiano.java

// This class defines RecordPiano, a subclass of Piano with much of the 
// same funcionality, except that it records the notes played and saves 
// them in a text file. It begins recording when the user plays a note
// and ends when the final note ends. It records a string representation
// of each note and rests in a text file as soon as the note ends. 

import javax.swing.*; // JComponents
import java.awt.FlowLayout; // Layouts 
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.*; // ActionListener
import java.util.*; // List, ArrayList
import java.io.*; // PrintStream

public class RecordPiano extends Piano implements ActionListener
{

/** The start time of a note. (Changes for every note played)
  */
   private long startTime;
   
/** The PrintStream for the file to print to
  */
   private PrintStream printFile;
   
/** The number of milliseconds in one second
  */
   public static final double MILL_IN_SEC = 1000.0;
   
/** Constructor that creates a RecordPiano object with the specified 
  * output file and octave
  *
  * @param printFile - the file to record in
  * @param octave - the octave of the piano
  */
   public RecordPiano(PrintStream printFile, int octave)
   {
      super(octave);
      startTime = 0;
      this.printFile = printFile;
   }
   
/** Constructor that creates a piano starting at middle C with the 
  * specified output file
  *
  * @param printFile - the file to record in
  */
   public RecordPiano(PrintStream printFile)
   {
      this(printFile, 5);
   }
   
/** Called when the mouse is pressed. Same funcionality as the method in the superclass,
  * plus it records what time the note was originally played and if a note was played 
  * before it, the time between notes as a rest.
  *
  * @param event - the action of the mouse (pressed)
  */
   public void mousePressed(MouseEvent event)
   {
      if (startTime != 0) // don't record rest if user hasn't played yet
      {
         printFile.println(new Note((System.currentTimeMillis() - startTime) / MILL_IN_SEC, Pitch.R));
      }
      startTime = System.currentTimeMillis();
      super.mousePressed(event);
   }
   
/** Called when the mouse is released. Same funcionality as the method in the 
  * superclass, plus it records the note that just finished playing.
  *
  * @param event - the action of the mouse (released)
  */
   public void mouseReleased(MouseEvent event)
   {
      super.mouseReleased(event);
      printFile.println(new Note((System.currentTimeMillis() - startTime) / MILL_IN_SEC, super.current.getNote().getPitch(),
                      super.current.getNote().getAccidental(), super.current.getNote().getOctave())); 
      startTime = System.currentTimeMillis(); // set startTime in order to measure rest
   }
   
/** Called when a key is pressed. Same funcionality as the method in the super class,
  * plus records the start time of the note and records any preceding rests
  *
  * @param event - the event that indicates the key is pressed
  */
   public void keyPressed(KeyEvent event)
   {
      if (startTime != 0) // don't record rest if user hasn't started playing yet
      {
         printFile.println(new Note((System.currentTimeMillis() - startTime) / MILL_IN_SEC, Pitch.R)); 
      } 
      startTime = System.currentTimeMillis();
      super.keyPressed(event);
   }
   
/** Called when a key is released. Same funcionality as the method in the superclass,
  * plus it records the note that was just played
  *
  * @param event - the event that indicates that a key was released
  */
   public void keyReleased(KeyEvent event)
   {
      super.keyReleased(event);
      printFile.println(new Note((System.currentTimeMillis() - startTime) / MILL_IN_SEC, super.current.getNote().getPitch(),
                          super.current.getNote().getAccidental(), super.current.getNote().getOctave()));
      startTime = System.currentTimeMillis(); // set startTime in order to measure rest
   }
}