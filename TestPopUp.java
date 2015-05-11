import java.awt.event.*;
import javax.swing.*;

public class TestPopUp implements ActionListener
{
   public void actionPerformed(ActionEvent event)
   {
      JOptionPane.showMessageDialog(null, event.paramString());
   }
}