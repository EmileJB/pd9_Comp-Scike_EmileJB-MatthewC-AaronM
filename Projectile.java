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
    protected Image img ,nw,ne,n,s,e,w,se,sw;
    protected int damage;
    protected int range;
    protected int speed,basespeed;
    protected Grid g;
    protected Gui gu;
    protected boolean acted;
    
    public Projectile(Loc spawn, Enemy en, Gui gu, int d, int r, int sp){
	super(spawn);
	damage = d;
	id=3;
	//location=location;
	mark = en;
	target = mark.getLoc();	
	g= location.getGrid();
	gu = g.getGui();
	range = r;
	basespeed = speed = sp;
	acted = false;
	//System.out.println(gu);
	try {
	    
	    nw = ImageIO.read(new File ( "images/misnw.gif"));
	    ne = ImageIO.read(new File ( "images/misne.gif"));
	    w = ImageIO.read(new File ( "images/misw.gif"));
	    n = ImageIO.read(new File ( "images/misn.gif"));
	    s = ImageIO.read(new File ( "images/miss.gif"));
	    e = ImageIO.read(new File ( "images/mise.gif"));
	    se = ImageIO.read(new File ( "images/misse.gif"));
	    sw = ImageIO.read(new File ( "images/missw.gif"));

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
	//location.removeActor(this);
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
		setImg(nw);
	    }
	    else if (location.getX()< target.getX() && location.getY() > target.getY()){
		//System.out.println("2");
		moveOne(location.getX() +1, location.getY() - 1);
		setImg(sw);
	    }
	    else if (location.getX()< target.getX() && location.getY() < target.getY()){
		//	System.out.println("3");
		moveOne(location.getX() +1, location.getY() + 1);
		setImg(se);
	    }
	    else if (location.getX()> target.getX() && location.getY() < target.getY()){
		//	System.out.println("4");
		moveOne(location.getX() -1, location.getY() + 1);
		setImg(ne);
	    }
	    else if (location.getX()==target.getX() && location.getY() < target.getY()){
		//		System.out.println("5");
		moveOne(location.getX(), location.getY() + 1);
		setImg(e);
	    }
	    else if (location.getX()==target.getX() && location.getY() > target.getY()){
		//System.out.println("6");		
		moveOne(location.getX(), location.getY() - 1);
		setImg(w);
	    }
	    else if (location.getX() <target.getX() && location.getY()== target.getY()){
		//System.out.println("7");		
		moveOne(location.getX()+1, location.getY());
		setImg(s);
	    }
	    else if(location.getX() >target.getX() && location.getY() == target.getY()){
		    
		//		System.out.println("8");
		moveOne(location.getX()-1, location.getY());
		setImg(n);
	    }
	    speed = basespeed;
	    range--;
	}
	else
	    speed--;
	//System.out.println(location.getActors());
    }
    public void checkPos() {
	//System.out.println("check2099: " + location.getEnemy());
	
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
	if (acted==false){
	  
	    acted=true;
	}
	else{
	    checkEnemy();
	    moveDirection();
	    checkPos();
	    if (mark != null)
		setTarget(mark.getLoc());                     
	}   
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
    public void setImg(Image i){
	img = i;
    }
    public void setNW( Image i){
	nw = i;
    }
   public void setNE( Image i){
	ne = i;
    }
   public void setN( Image i){
	n = i;
    }
   public void setE( Image i){
	e = i;
    }
   public void setW( Image i){
	w = i;
    }
   public void setSW( Image i){
	sw = i;
    }
   public void setSE( Image i){
	se = i;
    }
   public void setS( Image i){
	s= i;
    }
    public int ID(){
	return id;
    }
   
}
