/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbreaker;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import static jdk.nashorn.internal.objects.NativeMath.random;

/**
 *
 * @author admin
 */
public class BlockBreakerPanel extends JPanel implements KeyListener{
    ArrayList<Block> blocks=new ArrayList<>();
    ArrayList<Block> ball=new ArrayList<>();
    ArrayList<Block> powerup=new ArrayList<>();
    Block paddle;
    Thread thread;
    Animate animate;
    int size=25;//ball size
    BlockBreakerPanel(){
        paddle=new Block(175,480,150,25,"paddle.png");
        for (int i=0;i<8;i++){
        blocks.add(new Block((i*60+2),0,60,25,"blue.png"));
        }
        for (int i=0;i<8;i++){
        blocks.add(new Block((i*60+2),25,60,25,"red.png"));
        }
        for (int i=0;i<8;i++){
        blocks.add(new Block((i*60+2),50,60,25,"green.png"));
        }
        for (int i=0;i<8;i++){
        blocks.add(new Block((i*60+2),75,60,25,"yellow.png"));
        }
        Random random;
        random = new Random();
        blocks.get(random.nextInt(32)).powerup=true;
        blocks.get(random.nextInt(32)).powerup=true;
        blocks.get(random.nextInt(32)).powerup=true;
        blocks.get(random.nextInt(32)).powerup=true;
        blocks.get(random.nextInt(32)).powerup=true;
        ball.add(new Block(237,437,25,25,"ball.png"));
        addKeyListener(this);
        setFocusable(true);//w/o above two lines it would actually listen/read key 
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);///clears screen and repaints
        blocks.forEach((b) -> {
            b.draw(g, this);
            paddle.draw(g, this);
        });
        ball.forEach((ba) -> {
            ba.draw(g, this);
        });
        powerup.forEach((p)->{
            p.draw(g, this);
        });
    }
        
     public void update(){
         for(Block p:powerup)
         {
             p.y+=1;//each time block is destroyed ,the powerup drops down..(y direction)
            if(p.intersects(paddle) && !p.destroyed){
                p.destroyed=true;
                ball.add(new Block((paddle.dx+75),437,25,25,"ball.png"));
            }
         }
     
         for(Block ba:ball)
         {
            ba.x+=ba.dx;
             if( ba.x>(getWidth()-size) && ba.dx>0  || ba.x<0)
                 ba.dx*=-1;//change axes or reflect the ball
             if( ba.y>(getWidth()-size) && ba.dy>0  || ba.y<0 || ba.intersects(paddle))
                 ba.dy*=-1;
             ba.y+=ba.dy;//ball just bounces..but goes out of border /panel
             for (Block b:blocks){
                 //for destroyal of more than  brick/block in vicinity of the ball
                 if(b.left.intersects(ba)||b.right.intersects(ba)&& !b.destroyed){//area surrounding ball(left/right rectangle touches bricks/blocks
                     ba.dx*=-1;
                     b.destroyed=true;//if ball touches existing block,block should be destroyed and reflect back the ball
                     if (b.powerup)//to check whether the block had any powerup attached with it..
                         powerup.add(new Block(b.x,b.y,25,19,"extra.png"));
                 }
                 //for destroyal of block touched by ball
                 if (ba.intersects(b)&& !b.destroyed){//if ball itself touches blocks
                    b.destroyed=true;//if ball touches existing block,block should be destroyed and reflect back the ball
                    ba.dy*=-1;
                    if (b.powerup)//to check whether the block had any powerup attached with it..
                         powerup.add(new Block(b.x,b.y,25,19,"extra.png"));
                 }
             }
         
         }
         repaint();
     }
    
    
     

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
        animate= new Animate(this);
        thread=new Thread(animate);
        thread.start();
        }
        //throw new UnsupportedOperationException("Not supported yet."); 
        if(e.getKeyCode()==KeyEvent.VK_LEFT && paddle.x>0){
        paddle.x-=15;
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT && paddle.x<getWidth()-paddle.width){
        paddle.x+=15;
        }
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.
    }
}
