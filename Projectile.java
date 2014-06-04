import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class Projectile extends Actor {
    protected int id;
    protected Loc target, spot;
    protected Image img ,nw;
    protected Grid g;
    protected Gui gu;
    
    public Projectile(Loc spawn, Loc dest, Gui gu){
	super(spawn);
	id=3;
	spot=location;
	target=dest;	
	g= spot.getGrid();
	gu = g.getGui();
	//System.out.println(gu);
	try {
	    img = ImageIO.read(new File ( "images/misnw.gif"));
	}catch (IOException ex) {
	    System.out.println("you goofed Dx");
	}
	spot.addActor(this);
	gu.addProjectile(this);
    }
    public void setTarget( Loc dest){
	target = dest;
    }
    public void moveOne(int x, int y) {
	gu = g.getGui();
	Loc tmp = spot;
	spot.removeActor(this);
	g.getLoc(spot.getX(),spot.getY()).removeActor(this);
	System.out.println(spot.getActors());
	g.placeActor(x,y, this);
	
	spot =g.getLoc(x,y);
	
	spot.addActor(this);    
	
	System.out.println(spot + ""+tmp);
    }
    public void act() {
	
	//	while (!(spot.getX()== target.getX() && spot.getY() == target.getY())) {
	//System.out.println("X: "+target.getX()+spot.getX() + "\nY: "+ target.getY() + spot.getY());
	    
	    if (spot.getX() > target.getX() && spot.getY() > target.getY()){
		
		moveOne(spot.getX() -1, spot.getY() - 1);
	    }
	    else if (spot.getX()< target.getX() && spot.getY() > target.getY()){
		
		moveOne(spot.getX() +1, spot.getY() + 1);
	    }
	    else if (spot.getX()< target.getX() && spot.getY() < target.getY()){
	
		moveOne(spot.getX() +1, spot.getY() + 1);
	    }
	    else if (spot.getX()> target.getX() && spot.getY() < target.getY()){
		
		moveOne(spot.getX() -1, spot.getY() + 1);
	    }
	    else if (spot.getX()==target.getX() && spot.getY() < target.getY()){
		
	
		moveOne(spot.getX(), spot.getY() + 1);
	    }
	    else if (spot.getX()==target.getX() && spot.getY() > target.getY()){
		moveOne(spot.getX(), spot.getY() - 1);
	    }
	    else if (spot.getX() <target.getX() && spot.getY()== target.getY()){
		moveOne(spot.getX()+1, spot.getY());
	    }
	    else if(spot.getX() >target.getX() && spot.getY() == target.getY()){
		    
		//	System.out.println("here");
		moveOne(spot.getX()-1, spot.getY());
	    }
	    else {
		this.die();
	    }
    

	
     
    }
    public void die(){
	location.removeActor(this);	
		g.getLoc(spot.getX(),spot.getY()).removeActor(this);
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
