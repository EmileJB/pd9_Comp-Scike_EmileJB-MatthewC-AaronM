import java.io.*;
import java.util.*;
import javax.imageio.*;
import java.awt.*;

public class DDR extends Projectile {

    protected Image img, nw, ne, n, s, e, w, se, sw;
    private int push;

    public DDR(Loc spawn, Enemy en, Gui gu, int d, int r, int sp, int p) {
	super(spawn,en,gu,d,r,sp);
	push = p;
	int ran = (int)(Math.random()*4);
	String im = "images/arrow";
        if (ran == 0)
	    im += "n.gif";
	else if (ran == 1)
	    im += "e.gif";
	else if (ran == 2)
	    im += "s.gif";
	else if (ran == 3)
	    im += "w.gif";

    try {
	    nw = ImageIO.read(new File (im));
	    ne = ImageIO.read(new File (im));
	    w = ImageIO.read(new File (im));
	    n = ImageIO.read(new File (im));
	    s = ImageIO.read(new File (im));
	    e = ImageIO.read(new File (im));
	    se = ImageIO.read(new File (im));
	    sw = ImageIO.read(new File (im));
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
	//System.out.println("check2099: " + location.getEnemy());
	
	if (location.getEnemy() != null) {
	    Enemy en = location.getEnemy();
	    en.damage(damage);
	    en.setPathPos(en.getPathPos()-push);
	    en.swapLoc(g.getPathLoc(en.getPathPos()));
	    die();
	    }
	if  (location.getX() == target.getX() && location.getY() == target.getY()) {
	    if (mark != null && mark.getLoc().equals(target)) {
		mark.damage(damage);
		mark.setPathPos(mark.getPathPos()-push);
		mark.swapLoc(g.getPathLoc(mark.getPathPos()));
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

}
