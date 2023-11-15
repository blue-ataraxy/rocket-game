import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import java.awt.Rectangle;


public class Base {

    private Image image;
    

    public Base(){
        loadImage();
    }

    private void loadImage(){
        ImageIcon ii = new ImageIcon("pics/yellowportal.png");
        image = ii.getImage();
    }

    //getters

    public Image getImage(){
        return image;
    }

}
