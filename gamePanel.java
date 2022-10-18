package My__SnakeProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class gamePanel extends JPanel implements ActionListener, KeyListener {
  private ImageIcon title=new ImageIcon(getClass().getResource("title.jpeg"));
    private ImageIcon leftmouth=new ImageIcon(getClass().getResource("leftmouth.jpeg"));
    private ImageIcon rightmouth=new ImageIcon(getClass().getResource("rightmouth.jpeg"));
    private ImageIcon upmouth=new ImageIcon(getClass().getResource("upmouth.jpeg"));
    private ImageIcon downmouth=new ImageIcon(getClass().getResource("downmouth.jpeg"));
    private ImageIcon body=new ImageIcon(getClass().getResource("body.jpeg"));
    private ImageIcon food=new ImageIcon(getClass().getResource("food.jpeg"));


    private int[] snakexlen=new int[750];
    private int[] snakeylen=new int[750];

    //for food
    private int[] xpos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[] ypos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    private Random random=new Random();
    private int foodx,foody;
    int score=0;
    boolean gameover=false;
    private boolean left=false;
    private boolean right=true;
    private boolean up=false;
    private boolean down=false;
    int moves=0;
    int lenofsn=3;
    private Timer timer;
    int delay=100;


    public gamePanel(){
        timer=new Timer(delay,this);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeys(true);
        timer.start();

        newfood();


    }

    private void newfood() {
        foodx=xpos[random.nextInt(34)];
        foody=ypos[random.nextInt(23)];

        for(int i=0;i<lenofsn;i++){
            if(snakexlen[i]==foodx&&snakeylen[i]==foody){
                newfood();
            }
        }
    }
    private void collideWithBody(){
        for(int i=1;i<lenofsn;i++){
            if(snakexlen[0]==snakexlen[i]&&snakeylen[0]==snakeylen[i]){
                gameover=true;
                timer.stop();

            }
        }
    }
    private void collideWithFood()
    {
        if(snakexlen[0]==foodx&&snakeylen[0]==foody){
            lenofsn++;
            score++;
            snakexlen[lenofsn-1]=snakexlen[lenofsn-2];
            snakeylen[lenofsn-1]=snakeylen[lenofsn-2];
            newfood();
        }
    }
    private void restart(){
        moves=0;
        timer.start();

        score=0;
        lenofsn=3;
        left=false;
        right=true;
        up=false;
        down=false;
        gameover=false;
        repaint();
    }
    private void setFocusTraversalKeys(boolean b) {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.drawRect(24,10,851,55);
        g.drawRect(24,74,851,576);
        title.paintIcon(this,g,25,11);
        g.fillRect(25,75,850,575);
        g.drawString("score "+score,700,30);
        if(gameover){
            g.setColor(Color.white);
            g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
            g.drawString("Game Over",400,400);
            g.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,15));
            g.drawString("press SPACE to restart",450,450);


        }
        if(moves==0){
            snakexlen[0]=100;
            snakexlen[1]=75;
            snakexlen[2]=50;
            snakeylen[0]=100;
            snakeylen[1]=100;
            snakeylen[2]=100;
            moves++;
        }
        if(left){
            leftmouth.paintIcon(this,g,snakexlen[0],snakeylen[0]);
        }
        if(right){
            rightmouth.paintIcon(this,g,snakexlen[0],snakeylen[0]);
        }
        if(up){
            upmouth.paintIcon(this,g,snakexlen[0],snakeylen[0]);
        }
        if(down){
            downmouth.paintIcon(this,g,snakexlen[0],snakeylen[0]);
        }
        for(int i=1;i<lenofsn;i++){
            body.paintIcon(this,g,snakexlen[i],snakeylen[i]);
        }
        food.paintIcon(this,g,foodx,foody);

      g.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=lenofsn-1;i>0;i--){
            snakexlen[i]=snakexlen[i-1];
            snakeylen[i]=snakeylen[i-1];

        }
        if(left){
            snakexlen[0]=snakexlen[0]-25;
        }
        if(right){
            snakexlen[0]=snakexlen[0]+25;
        }
        if(up){
            snakeylen[0]=snakeylen[0]-25;
        }
        if(down){
            snakeylen[0]=snakeylen[0]+25;
        }

        if(snakexlen[0]>850) snakexlen[0]=25;
        if(snakexlen[0]<25) snakexlen[0]=850;
        if(snakeylen[0]>625) snakeylen[0]=75;
        if(snakeylen[0]<75) snakeylen[0]=625;
        collideWithFood();
        collideWithBody();
     repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE&&gameover){
           restart();
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT&&(!right)){
            left=true;
            right=false;
            up=false;
            down=false;
           // moves++;
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT&&(!left)){
            left=false;
            right=true;
            up=false;
            down=false;
          //  moves++;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP&&(!down)){
            left=false;
            right=false;
            up=true;
            down=false;
           // moves++;
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN&&(!up)){
            left=false;
            right=false;
            up=false;
            down=true;
           // moves++;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
