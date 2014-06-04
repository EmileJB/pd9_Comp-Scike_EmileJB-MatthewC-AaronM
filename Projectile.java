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
    
    public Projectile(Loc spawn, Loc dest){
	super(spawn);
	id=3;
	spot=location;
	target=dest;
	g= spot.getGrid();
	try {
	    img = ImageIO.read(new File ( "images/misnw.gif"));
	}catch (IOException ex) {
	    System.out.println("you goofed Dx");
	}
	spot.addActor(this);
    }
    public void setTarget( Loc dest){
	target = dest;
    }
    public void moveOne(int x, int y) {
	Loc tmp = spot;
	tmp.removeActor(this);
	//System.out.println(spot);
	g.placeActor(x,y, this);
	
	spot =g.getLoc(x,y);
	
	spot.addActor(this);    
	
	System.out.println(spot + ""+tmp);
    }
    public void act() {
	
	while (!(spot.getX()== target.getX() && spot.getY() == target.getY())) {
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
	    
	}
	    //	spot.removeActor(this);
	
	    //	}
	    //System.out.println("we made it!");
	
    }
    public Image getImg(){
	return img;
    }
    public int ID(){
	return id;
    }
    
}
