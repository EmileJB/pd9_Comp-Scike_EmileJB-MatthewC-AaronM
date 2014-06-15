import java.io.*;
import java.util.*;
import javax.imageio.*;
import java.awt.*;

public class Pac extends Projectile {

    protected Image img, nw, ne, n, s, e, w, se, sw, pac;
    private boolean eating;

    public Pac(Loc spawn, Enemy en, Gui gu, int d, int r, int sp) {
	super(spawn,en,gu,d,r,sp);
    try {
	    nw = ImageIO.read(new File ( "images/pacmannw.gif"));
	    ne = ImageIO.read(new File ( "images/pacmanne.gif"));
	    w = ImageIO.read(new File ( "images/pacmanw.gif"));
	    n = ImageIO.read(new File ( "images/pacmann.gif"));
	    s = ImageIO.read(new File ( "images/pacmans.gif"));
	    e = ImageIO.read(new File ( "images/pacmane.gif"));
	    se = ImageIO.read(new File ( "images/pacmanse.gif"));
	    sw = ImageIO.read(new File ( "images/pacmansw.gif"));
	    pac = ImageIO.read(new File ( "images/pacman.gif"));
	}catch (IOException ex) {
	    System.out.println("you goofed Dx");
	}
	super.setNW(nw);
	super.setNE(ne);
	super.setSE(se);
	super.setSW(sw);
	super.setN(n);
	super.setS(s);
	super.setW(w);
	super.setE(e);
	eating = false;
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

	if (!eating) {
	    setImg(pac);
	    eating = true;
	}
	else if (eating) {
	    eating = false;
	}
	//System.out.println(location.getActors());
    }

    public void checkPos() {
	
	if (location.getEnemy() != null) {
	    Enemy en;
	    for (Actor a:location.getActors()) {
		if (a instanceof Enemy && a != null) {
		    en = (Enemy)a;
		    en.damage(damage);
		}
	    }
	}
	if  (location.getX() == target.getX() && location.getY() == target.getY()) {
	    if (mark != null && mark.getLoc().equals(target)) {
		mark.damage(damage * 2);
		die();
	    }
	     else if (mark == null) {
		    die(); 
	}
		die();
	    }
	else if (range <= 0) {
	    die();
	}
    }

}

