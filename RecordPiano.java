import javax.swing.*; // JComponents
import java.awt.FlowLayout; // Layouts 
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.*; // ActionListener
import java.util.*; // List, ArrayList
import java.io.*; // PrintStream

public class RecordPiano extends Piano implements ActionListener
{
   private List<Note> notes;
   
   private long startTime;
   
   private PrintStream printFile;
   
   public RecordPiano(PrintStream printFile, int octave)
   {
      super(octave);
      notes = new ArrayList<Note>();
      startTime = 0;
      this.printFile = printFile;
   }
   
   public RecordPiano(PrintStream printFile)
   {
      this(printFile, 5);
   }
   
   public void mousePressed(MouseEvent event)
   {
      if (startTime != 0)
      {
         notes.add(new Note((System.currentTimeMillis() - startTime) / 1000.0, Pitch.R));
         printFile.println(notes.get(notes.size() - 1));
      }
      startTime = System.currentTimeMillis();
      super.mousePressed(event);
   }
   
   public void mouseReleased(MouseEvent event)
   {
      super.mouseReleased(event);
      notes.add(new Note((System.currentTimeMillis() - startTime) / 1000.0, super.current.getNote().getPitch(),
                          super.current.getNote().getAccidental(), super.current.getNote().getOctave()));
      printFile.println(notes.get(notes.size() - 1));
   }
   
   public void keyPressed(KeyEvent event)
   {
      if (startTime != 0)
      {
         notes.add(new Note((System.currentTimeMillis() - startTime) / 1000.0, Pitch.R));
         printFile.println(notes.get(notes.size() - 1));
      }
      startTime = System.currentTimeMillis();
      super.keyPressed(event);
   }
   
   public void keyReleased(KeyEvent event)
   {
      super.keyReleased(event);
      notes.add(new Note((System.currentTimeMillis() - startTime) / 1000.0, super.current.getNote().getPitch(),
                          super.current.getNote().getAccidental(), super.current.getNote().getOctave()));
      printFile.println(notes.get(notes.size() - 1));
   }
      
   
}