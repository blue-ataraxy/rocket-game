import javax.swing.JPanel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.time.format.FormatStyle;
import java.util.TimerTask;
import java.awt.BasicStroke;
import java.awt.Rectangle;
import java.util.TimerTask;
import java.util.Timer;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Image;
import javax.swing.ImageIcon;


//extends JPanel, implements listeners

public class Canvas extends JPanel implements ActionListener{

    private Rocket rocket;
    private Fuel fuel;
    private Block block;
    private Base base;
    private Handler handler;

    private boolean gamelost = false;
    private boolean levelcomplete = false;
    private boolean gamewon = false;
    
    private boolean gamelostscreen = false;
    private boolean levelcompletescreen = false;
    private boolean gamewonscreen = false;

    private int level = 1;

    public Canvas() {

        initCanvas();
        start();

    }

    private void initCanvas(){
        addKeyListener(new Adapter());
        addMouseListener(new MAdapter());
        setFocusable(true);
        setBackground(Color.black);
    }


    private void start(){

        handler = new Handler();
        newLevel();
    }


    private void newLevel(){
        rocket = new Rocket();
        fuel = new Fuel();
        base = new Base();
        handler.addLevelFiles();
        handler.addBlock();
    }

    private void clearObjects(){
        rocket = null;
        fuel = null;
        base = null;
        handler = null;
    }

    private void restartGame(){
        gamelostscreen = false;
        gamelost = false;
        level = 1;
        handler = new Handler();
        handler.setLevel(level);
        handler.clearBlocklist();
        newLevel();
        repaint();
    }

    private void nextLevel(){
        levelcompletescreen = false;
        levelcomplete = false;
        handler = new Handler();
        handler.setLevel(level);
        handler.clearBlocklist();
        newLevel();
        repaint();
    }


    //add a thread here???

    // private class Framepainter extends TimerTask{
    //     public Framepainter(){}
    //     public void run(){
    //         begin(); //to begin the painting of canvas
    //     }
    // }

    //delay = 20
    // public void begin(){
    //     Timer t = new Timer();
    //     Framepainter fp = new Framepainter();
    //     t.schedule(fp, 20);
    // }

    @Override
    public void paintComponent(Graphics g){

        // if(gamelostscreen || levelcompletescreen || gamewonscreen){
        // }        
        // else{
        //     doEverything(g);
        // }

        if(gamelost == false && levelcomplete == false && gamewon == false){
            doEverything(g);
            drawRocket(g);
        }
        if(gamelost){
            clearObjects();
            gamelostscreen = true;
        }
        if(gamelostscreen){
            drawLostscreen(g);
        }
        if(levelcomplete){
            clearObjects();
            levelcompletescreen = true;
        }  
        if(levelcompletescreen){
            drawCompletescreen(g);
        }
        if(gamewon){
            clearObjects();
            gamewonscreen = true;
        }
        if(gamewonscreen){
            drawWonscreen(g);
        }

    }


    private void doEverything(Graphics g){
        drawGreyRect(g);
        drawLevelnumber(g);
        handler.drawBlock(g);
        // drawGetBoundsBlock(g); //debug
        // drawGetBoundsRocket(g); //debug
        drawFuel(g);
        handler.findBase();
        drawBase(g);

        step();
        checkCollide();
        checkBase(); 

    }


    private void drawGreyRect(Graphics g){
        Color greycolor = new Color(55, 59, 62);
        g.setColor(greycolor);
        g.fillRect(0, 0, 1000, 100);
    }


    private void drawRocket(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform at = new AffineTransform();
        int centreX = rocket.getX() + 50;
        int centreY = rocket.getY() + 50;

        at.rotate(rocket.getRads(), centreX, centreY);

        g2d.setTransform(at);
        g2d.drawImage(rocket.getImage(), rocket.getX(), rocket.getY(), 100, 100, null);
    }

    private void drawFuel(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(fuel.getImage(), 900, 20, 50, 50, null);
        
        //drawing border rectangle
        int bthickness = 3;
        g2d.setColor(new Color(188, 4, 4));
        g2d.setStroke(new BasicStroke(bthickness));
        
        //1
        g2d.drawLine(900, 80, 900, 90);
        //2
        g2d.drawLine(900, 90, 950, 90);
        //3
        g2d.drawLine(950, 90, 950, 80);
        //4
        g2d.drawLine(950, 80, 900, 80);

        //drawing fill rectangle
        g2d.fillRect(900, 80, fuel.getTankWidth(), 10); //900+3, 120-3

    }

    private void drawBase(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.drawImage(base.getImage(), handler.getBaseX(), handler.getBaseY(), 50, 50, null);
    }

    private void drawLevelnumber(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        ImageIcon level1 = new ImageIcon("pics/oneofthree.png");
        ImageIcon level2 = new ImageIcon("pics/twoofthree.png");
        ImageIcon level3 = new ImageIcon("pics/threeofthree.png");

        if(level == 1){
            g2d.drawImage(level1.getImage(), 50, 20, 200, 80, null);
        }
        if(level == 2){
            g2d.drawImage(level2.getImage(), 50, 20, 200, 80, null);
        }
        if(level == 3){
            g2d.drawImage(level3.getImage(), 50, 20, 200, 80, null);
        }
    }




    private void drawLostscreen(Graphics g){


        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.drawRect(500, 500, 100, 100);
        // g2d.drawImage()
        // draw the final score (how many levels completed)

        //draw button to restart from level 1

        ImageIcon ii = new ImageIcon("pics/gameover.png");
        g2d.drawImage(ii.getImage(), 0, 0, 1000, 800,null);

    }

    private void drawCompletescreen(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.drawRect(700, 700, 100, 100);

        ImageIcon ii = new ImageIcon("pics/levelcomplete.png");
        g2d.drawImage(ii.getImage(), 0, 0, 1000, 800,null);

    }

    private void drawWonscreen(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        ImageIcon ii = new ImageIcon("pics/youwin.png");
        g2d.drawImage(ii.getImage(), 0, 0, 1000, 800,null);
    }


    ////////////////////////////////////////////////////////////////////////

    private Rectangle getRocketBounds(){
        int xx = rocket.getX();
        int yy = rocket.getY();

        double[] pt = {xx, yy};
        AffineTransform.getRotateInstance((double) rocket.getRads(), (double) rocket.getX(), (double) rocket.getY()).transform(pt, 0, pt, 0, 1);
        Rectangle rocketBounds = new Rectangle(xx/2, yy/2, 50, 50);

        return rocketBounds;
    }

    private void checkCollide(){
        for(int i = 0; i<handler.getBlocklistsize(); i++){
            if(getRocketBounds().intersects(handler.getTempblock(i).getBounds())){
                gamelost = true;
            }
        }
        if(getRocketBounds().intersects(new Rectangle(0, 780, 10000, 2))){
            gamelost = true;
        }
    }

    private void checkBase(){
        if(getRocketBounds().intersects(handler.getBaseBounds())){
            if(level == 3){
                levelcomplete = true;
                gamewon = true;
            }
            else{
                levelcomplete = true;
            }
        }
    }


    //debugging
    private void drawGetBoundsBlock(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        for(int i = 0; i<handler.getBlocklistsize(); i++){
        g2d.drawRect((int)handler.getTempblock(i).getBounds().getX(), (int)handler.getTempblock(i).getBounds().getY(), (int)handler.getTempblock(i).getBounds().getWidth(), (int)handler.getTempblock(i).getBounds().getHeight());
        }
    }

    private void drawGetBoundsRocket(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        // g2d.draw(rocket.getBounds());
        g2d.draw(getRocketBounds());
    }












    private void step(){
        if(fuel.checkDrained()==false){
            rocket.moveRocket();
        }
        if(fuel.checkDrained()){
            gamelost = true;
        }
        repaint();
    }
    

    @Override
    public void actionPerformed(ActionEvent e){
        step();
    }


    private class Adapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            if(gamelost == false && levelcomplete == false && gamewon == false && gamelostscreen == false && levelcompletescreen == false && gamewonscreen == false){
                rocket.keyPressed(e);
                fuel.keyPressed(e);    
            }
        }

        @Override
        public void keyReleased(KeyEvent e){
            if(gamelost == false && levelcomplete == false && gamewon == false && gamelostscreen == false && levelcompletescreen == false && gamewonscreen == false){
                rocket.keyReleased(e);
            }
        }
    }

    private class MAdapter extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e){
            if(gamelostscreen){
                if(e.getX() > 400 && e.getX() < 600 && e.getY() > 417 && e.getY() < 480){ 
                    restartGame();
                }
            }
            if(levelcompletescreen){
                if(e.getX() > 384 && e.getX() < 616 && e.getY() > 405 && e.getY() < 440){
                    level++;
                    nextLevel();
                }
            }
        }
    }
    
}
