import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class FireBlast extends Projectile {
    protected Image img, nw, ne, n, s, e, w, se, sw;
    private int dur,mag;

    public FireBlast(Loc spawn, Enemy en, Gui gu, int d, int r, int sp, int du, int ma) {
	super(spawn,en,gu,d,r,sp);
	dur = du;
	mag = ma;
	//System.out.println(mag);
    try {
	    nw = ImageIO.read(new File ( "images/fireballnw.gif"));
	    ne = ImageIO.read(new File ( "images/fireballne.gif"));
	    w = ImageIO.read(new File ( "images/fireballw.gif"));
	    n = ImageIO.read(new File ( "images/fireballn.gif"));
	    s = ImageIO.read(new File ( "images/fireballs.gif"));
	    e = ImageIO.read(new File ( "images/fireballe.gif"));
	    se = ImageIO.read(new File ( "images/fireballse.gif"));
	    sw = ImageIO.read(new File ( "images/fireballsw.gif"));
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
    location.getEnemy().addStatus(new Status(0,dur,mag,Status.BURN));
    location.getEnemy().damage(damage);
    die();
	    }
	if  (location.getX() == target.getX() && location.getY() == target.getY()) {
	    if (mark != null && mark.getLoc().equals(target)) {
		mark.addStatus(new Status(0,dur,mag,Status.BURN));
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
}
