import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Rocket extends JComponent implements KeyListener{



    //getbounds for rocket
    public Rectangle getBounds(){
        return new Rectangle(x, y, 50, 50);
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    
    private double cx; //changex
    private double cy; //changey
    private double rads = 0;
    private int x = 0;
    private int y = 400;
    private int width;
    private int height;

    private int gravity = 0;
    private boolean falling = true;

    private Image image;

    public Rocket(){
        loadImage();
    }

    private void loadImage(){
        ImageIcon ii = new ImageIcon("pics/pixelrocket3.png");
        image = ii.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);
        
    }



    //movement methods

    public void moveRocket(){
        this.x += cx;
        this.y += cy;

        if(cx > 0 && cx < 1){
            this.x += 1;
        }
        if(cy > 0 && cy < 1){
            this.y += 1;
        }
        if(cx < 0 && cx > -1){
            this.x -= 1;
        }
        if(cy < 0 && cy > -1){
            this.y -= 1;
        }
        if(falling){
            this.y -= gravity;
        }

        //collision bounds
        if(x < 0){
            x = 0;
        }
        if(x > 1920){
            x = 1920;
        }
        if(y < 0){
            y = 0;
        }
        if (y > 1470){
            y = 1470;
        }

    }

    //key listeners

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_A){
            rads -= Math.toRadians(21);

            //rotation counter-clockwise
        }
        if(key == KeyEvent.VK_D){
            rads += Math.toRadians(21);
            
            //rotation clockwise
        }

        if(key == KeyEvent.VK_W){
            //move forward (depending on angle rotated)
            cx = 3* Math.cos(rads);
            cy = 3* Math.sin(rads);
            falling = false;
        }

    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_A){
            cx = 0;
            cy = 0;
        }
        if(key == KeyEvent.VK_D){
            cx = 0;
            cy = 0;
        }
        if(key == KeyEvent.VK_W){
            cx = 0;
            cy = 0;
            falling = true;
        }


    }

    public void keyTyped(KeyEvent e){}

    

    //getters and setters

    public double getRads(){
        return this.rads;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }    

    public Image getImage() {
        return image;
    }

    public void setFalling(boolean falling){
        this.falling = falling;
    }

    public boolean getFalling(){
        return falling;
    }


}
