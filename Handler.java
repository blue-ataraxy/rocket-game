import java.util.LinkedList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Handler {
    

    private int level = 1;

    private int baseX;
    private int baseY;

    File level1 = new File("reallevels/level1.png"); 
    File level2 = new File("reallevels/level2.png");
    File level3 = new File("reallevels/level3.png");

    LinkedList<File> levelfiles = new LinkedList<File>(); // { file 1 , file 2 ... }

    public void addLevelFiles(){
        levelfiles.add(level1);
        levelfiles.add(level2);
        levelfiles.add(level3);
    }

    public void setLevel(int i){
        level = i;
    }
    

    //block handler stuff

    private LinkedList<Block> blocklist = new LinkedList<Block>();
    
    private Block tempblock;
    private Image image;

    public void clearBlocklist(){
        blocklist.clear();
    }

    public int getBlocklistsize(){
        int blocklistsize = blocklist.size();
        return blocklistsize;
    }

    public Block getTempblock(int i){
        tempblock = blocklist.get(i);
        return tempblock;
    }

    public Rectangle getBounds(Block block){ //change to : getBlockBounds
        return new Rectangle (block.getX(), block.getY(), 50, 50);
    }
    
    public void drawBlock(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        ImageIcon ii = new ImageIcon("pics/stonerock.png");
        image = ii.getImage();
        
        for(int i = 0; i<blocklist.size(); i++){
            g2d.drawImage(image, blocklist.get(i).getX(), blocklist.get(i).getY(), 50, 50, null);
        }
    }

    public void addBlock(){    
        BufferedImage image = null;

        try{
            File thisfile = levelfiles.get(level-1);
            image = ImageIO.read(thisfile);}
        catch(IOException ioe){
            System.out.println("Trouble reading from the file: " + ioe.getMessage());
        }

        int w = image.getWidth();
        int h = image.getHeight();
    
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                int pixel = image.getRGB(x, y); //in his code, the y and x are swapped. idk why.
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

            
                if(red == 255 && green == 255 && blue == 255){
                    blocklist.add(new Block(x*50, y*50));
                }
            }
        }
    
    }



    //base handler stuff

    public Rectangle getBaseBounds(){
        return new Rectangle(baseX, baseY, 50, 50);
    }


    public void findBase(){

        BufferedImage image1 = null;

        try{
            File thisfile = levelfiles.get(level-1);
            image1 = ImageIO.read(thisfile);}
        catch(IOException ioe){
            System.out.println("Trouble reading from the file: " + ioe.getMessage());
        }
    
        int w = image1.getWidth();
        int h = image1.getHeight();
    
        for(int yy = 0; yy < h; yy++){
            for(int xx = 0; xx < w; xx++){
                int pixel = image1.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
    
            
                if(red == 0 && green == 0 && blue == 255){ //check blue pixel
                    baseX = xx*50;
                    baseY = yy*50;
                }
            }
        }    
    }

    public int getBaseX(){
        return baseX;
    }

    public int getBaseY(){
        return baseY;
    }

    
}
