import javax.swing.JFrame;
import java.awt.EventQueue;

//extends JFrame, contains main method

public class App extends JFrame{
    
    public App() {
        initUI();
    }

    private void initUI(){
        JFrame frame = new JFrame();
        frame.add(new Canvas());

        frame.setTitle("Rocket Game");
        frame.setSize(1000, 800);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    public static void main(String args[]){
        // EventQueue.invokeLater(() -> {
            App app = new App();
        // });

    }
}
