/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbreaker;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 *functions in rectangle class :Intersect breadth width height position 
 * thus this are inherited by extending rect class 
 * @author admin
 */
public class Block extends Rectangle{
    Image pic;
    int dx=3;
    int dy=-3;
    Rectangle left,right;
    boolean destroyed=false;
    boolean powerup=false;
    //boolean balldestroy=false;
    Block(int a,int b,int w,int h,String s){
        x=a;
        y=b;
        width=w;
        height=h;
        pic=Toolkit.getDefaultToolkit().getImage(s);
        left=new Rectangle(a-1,b,1,h);
        right=new Rectangle(a+w+1,b,1,h);
    }
    public void draw(Graphics g,Component c){
        if (!destroyed){
         g.drawImage(pic,x,y,width,height,c);
        }
    }
}
