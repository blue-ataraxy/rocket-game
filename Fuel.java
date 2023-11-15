import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class Fuel implements KeyListener{
    
    private int drainfuel = 1;
    private int rwidth = 49;
    private boolean drained = false;

    private Image image;

    public Fuel(){
        loadImage();
    }

    private void loadImage(){
        ImageIcon ii = new ImageIcon("pics/gastank.png");
        image = ii.getImage();
    }

    //if fuel is drained, turn off accelerator

    public boolean checkDrained(){
        return this.drained;
    }

    //listeners

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W){
            if(rwidth >= 0){
                rwidth -= drainfuel;
            }else{
                this.drained = true;
            }
        }
    }

    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}


    //getters & setters

    public Image getImage(){
        return image;
    }

    public int getTankWidth(){
        return rwidth;
    }

    
}
