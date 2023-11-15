import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.LinkedList;
import java.awt.Rectangle;


//constructor (x and y)
//code for drawing a block

//in canvas, call blockhandler.addblock() and blockhandler.drawblock()


public class Block {


    public Rectangle getBounds(){
        return new Rectangle(x, y, 50, 50);
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    
    private int x;
    private int y;
    private BufferedImage image;
    
    public Block(int x, int y){

        this.x = x;
        this.y = y;
    }
    //getters
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

}
