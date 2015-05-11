import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlayNote implements ActionListener
{
   private Note note;
   
   public PlayNote(Note note)
   {
      this.note = note;
   }
   
   public void actionPerformed(ActionEvent event)
   {
      note.play();
   }
}