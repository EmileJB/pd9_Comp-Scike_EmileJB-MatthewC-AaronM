import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class Bullet extends Projectile {
    protected Image img ,nw,ne,n,s,e,w,se,sw;
    private boolean piercing;

    public Bullet(Loc spawn, Enemy en, Gui gu, int d, int r, int sp, boolean pierce) {
	super(spawn,en,gu,d,r,sp);
	piercing = pierce;
	try {
	    nw = ImageIO.read(new File ( "images/bulletnw.gif"));
	    ne = ImageIO.read(new File ( "images/bulletne.gif"));
	    w = ImageIO.read(new File ( "images/bulletw.gif"));
	    n = ImageIO.read(new File ( "images/bulletn.gif"));
	    s = ImageIO.read(new File ( "images/bullets.gif"));
	    e = ImageIO.read(new File ( "images/bullete.gif"));
	    se = ImageIO.read(new File ( "images/bulletse.gif"));
	    sw = ImageIO.read(new File ( "images/bulletsw.gif"));
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
    }

    public void checkPos() {
	if (location.getEnemy() != null) {
	    if (!piercing)
		location.getEnemy().damage(damage);
	    else {
		Enemy en;
		for (Actor a:location.getActors()) {
		    if (a instanceof Enemy && a != null) {
			en = (Enemy)a;
			en.damage(damage);
			damage = damage/2;
		    }
		}
	    }
	    die();
	}
	 if  (location.getX() == target.getX() && location.getY() == target.getY()) {
	     // if (!piercing)
	     //	mark.damage(damage);
	     //  else {
		Enemy en;
		for (Actor a:location.getActors()) {
		    if (a instanceof Enemy && a != null) {
			en = (Enemy)a;
			en.damage(damage);
			damage = damage/2;
		    }
		}
		//  }

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
}
