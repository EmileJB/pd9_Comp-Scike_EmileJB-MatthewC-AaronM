import java.io.*;
import java.util.*;
import javax.imageio.*;
import java.awt.*;

public class Bomb extends Projectile {

    protected Image img, nw, ne, n, s, e, w, se, sw, exp;
    private int radius;
    private boolean detonated;

    public Bomb(Loc spawn, Enemy en, Gui gu, int d, int r, int sp, int size) {
	super(spawn,en,gu,d,r,sp);
	radius = size;
	//System.out.println(mag);
    try {
	    nw = ImageIO.read(new File ( "images/bombnw.gif"));
	    ne = ImageIO.read(new File ( "images/bombne.gif"));
	    w = ImageIO.read(new File ( "images/bombw.gif"));
	    n = ImageIO.read(new File ( "images/bombn.gif"));
	    s = ImageIO.read(new File ( "images/bombs.gif"));
	    e = ImageIO.read(new File ( "images/bombe.gif"));
	    se = ImageIO.read(new File ( "images/bombse.gif"));
	    sw = ImageIO.read(new File ( "images/bombsw.gif"));
	    exp = ImageIO.read(new File ( "images/explosion.gif"));
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
	detonated = false;
    }

public void checkPos() {
if (location.getEnemy() != null) {
    explosion();
	    }
	if  (location.getX() == target.getX() && location.getY() == target.getY()) {
	    if (mark != null && mark.getLoc().equals(target)) {
		explosion();
	    }
	    /* else if (mark == null && target.getEnemy() != null) {
		    target.getEnemy().damage(damage);
		    die(); 
	}*/
		
	    }
	else if (range <= 0) {
	    explosion();
	}
}

    public void explosion() {
	int x = location.getX();
	int y = location.getY();
	ArrayList<Loc> targets = new ArrayList<Loc>();
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	for (int i = x + radius; i >= x - radius; i--) {
	    for (int j = y + radius; j >= y - radius; j--) {
		if ( j >= 0 && j < g.getCol() && i >= 0 && i < g.getRow() && g.getLoc(i,j).getID() >= 0) {
		    targets.add(g.getLoc(i,j));
		}
	    }
	}
	//activeTargets = new ArrayList<Enemy>();//clear all previous targets
	Enemy en = null;
	for (Loc l:targets) {
	    for (Actor a:l.getActors()) {
		if (a instanceof Enemy && a != null) {
		    en = (Enemy)a; 
		    en.damage(damage);
		}
	    }
	}
	detonated = true;
	setImg(exp);
    }

    public void act() {
	if (detonated)
	    die();
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
}

