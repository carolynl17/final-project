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
   
   public void actionPerformed(ActionEvent event)
   {
      JFrame frame = new JFrame();
      JPanel panel1 = new JPanel();
      JPanel panel2 = new JPanel();
      
      frame.setSize(400, 200);
      
      panel1.setLayout(new FlowLayout());
      frame.setLayout(new BorderLayout());
      panel2.setLayout(new GridLayout(2, 1));
      
      JLabel whichSong = new JLabel("What is your song called?", (int) JLabel.CENTER_ALIGNMENT);
      JLabel pleaseEnter = new JLabel("The program will begin recording when you begin playing.", (int) JLabel.CENTER_ALIGNMENT);
      JButton open = new JButton("Go!");
      JTextField answer = new JTextField(10);
      open.addActionListener(new CreateFile(answer));
      
      panel1.add(answer);
      panel1.add(open);
      panel2.add(whichSong);
      panel2.add(pleaseEnter);
      
      frame.add(panel1, BorderLayout.SOUTH);
      frame.add(panel2, BorderLayout.NORTH);
      frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
   }
   
}