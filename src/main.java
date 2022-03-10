
import java.awt.HeadlessException;
import javax.swing.JFrame;

public class main  extends JFrame{
    public static main ekran=new main("Flappy Bird");
    public static void main(String[] args) {
    
        ekran.setResizable(false);
        ekran.setFocusable(false);//JFrame e odaklanmayacak bu sayede Jpanele odaklanacak
        ekran.setSize(500,700);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        oyun oyun=new oyun();
        oyun.requestFocus();
        oyun.addKeyListener(oyun);
        oyun.setFocusable(true);
        oyun.setFocusTraversalKeysEnabled(false);
        ekran.add(oyun);
        ekran.setLocationRelativeTo(null);
        ekran.setVisible(true);
 
    }
    public main (String title) throws HeadlessException{
        super(title);
    }
}
