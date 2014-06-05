import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class Projectile extends Actor {
    protected int id;
    protected Loc  target;
    protected Enemy mark;
    protected Image img ,nw;
    protected int damage;
    protected int range;
    protected int speed,basespeed;
    protected Grid g;
    protected Gui gu;
    
    public Projectile(Loc spawn, Enemy e, Gui gu, int d, int r, int s){
	super(spawn);
	damage = d;
	id=3;
	//location=location;
	mark = e;
	target = mark.getLoc();	
	g= location.getGrid();
	gu = g.getGui();
	range = r;
	basespeed = speed = s;
	//System.out.println(gu);
	try {
	    img = ImageIO.read(new File ( "images/misnw.gif"));
	}catch (IOException ex) {
	    System.out.println("you goofed Dx");
	}
	location.addActor(this);
	gu.addProjectile(this);
    }
    public void setTarget( Loc dest){
	target = dest;
    }

    public void moveOne(int x, int y) {
	//gu = g.getGui();
	//Loc tmp = location;
	location.removeActor(this);
	g.getLoc(location.getX(),location.getY()).removeActor(this);
	//System.out.println(location.getActors());
	//g.placeActor(x,y, this);
	
	location =g.getLoc(x,y);
	
	location.addActor(this);    
	
	//System.out.println(location + ""+tmp);
    }

    public void moveDirection() {
	if (speed <= 0) {
	    if (location.getX() > target.getX() && location.getY() > target.getY()){
		//System.out.println("1");
		moveOne(location.getX() -1, location.getY() - 1);
	    }
	    else if (location.getX()< target.getX() && location.getY() > target.getY()){
		//System.out.println("2");
		moveOne(location.getX() +1, location.getY() - 1);
	    }
	    else if (location.getX()< target.getX() && location.getY() < target.getY()){
		//	System.out.println("3");
		moveOne(location.getX() +1, location.getY() + 1);
	    }
	    else if (location.getX()> target.getX() && location.getY() < target.getY()){
		//	System.out.println("4");
		moveOne(location.getX() -1, location.getY() + 1);
	    }
	    else if (location.getX()==target.getX() && location.getY() < target.getY()){
		//		System.out.println("5");
		moveOne(location.getX(), location.getY() + 1);
	    }
	    else if (location.getX()==target.getX() && location.getY() > target.getY()){
		//System.out.println("6");		
		moveOne(location.getX(), location.getY() - 1);
	    }
	    else if (location.getX() <target.getX() && location.getY()== target.getY()){
		//System.out.println("7");		
		moveOne(location.getX()+1, location.getY());
	    }
	    else if(location.getX() >target.getX() && location.getY() == target.getY()){
		    
		//		System.out.println("8");
		moveOne(location.getX()-1, location.getY());
	    }
	    speed = basespeed;
	    range--;
	}
	else
	    speed--;
	System.out.println(location.getActors());
    }

    public void checkPos() {
	System.out.println("check2099: " + location.getEnemy());
	
	if (location.getEnemy() != null) {
	    location.getEnemy().damage(damage);
	    die();
	    }
	if  (location.getX() == target.getX() && location.getY() == target.getY()) {
	    if (mark != null && mark.getLoc().equals(target)) {
		mark.damage(damage);
		    die(); 
	    }
	    /* else if (mark == null && target.getEnemy() != null) {
		    target.getEnemy().damage(damage);
		    die(); 
	}*/
		die();
	    }
	else if (range <= 0) {
	    die();
	}
    }

    public void checkEnemy() {
	if (mark != null) {
	    gu = g.getGui();
	    if (!gu.isActive(mark))
		mark = null;
	}
    }

    public void act() {
	checkEnemy();
	moveDirection();
	checkPos();
	if (mark != null)
	    setTarget(mark.getLoc());
    }                         
     	
	//	while (!(location.getX()== target.getX() && location.getY() == target.getY())) {
	//System.out.println("X: "+target.getX()+location.getX() + "\nY: "+ target.getY() + location.getY()


    public void die(){
	location.removeActor(this);	
	g.getLoc(location.getX(),location.getY()).removeActor(this);
	g.getLoc(target.getX(),target.getY()).removeActor(this);
	gu.removeProjectile(this);
    }
    public Image getImg(){
	return img;
    }

    public int ID(){
	return id;
    }
   
}
