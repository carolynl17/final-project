import javax.swing.*;

public class TestGUI
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      int choice = JOptionPane.showConfirmDialog(null, "Are you doing well today?");
   }
}