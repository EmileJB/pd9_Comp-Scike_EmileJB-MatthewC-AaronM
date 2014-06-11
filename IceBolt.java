import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class IceBolt extends Projectile {
    protected Image img ,nw,ne,n,s,e,w,se,sw;
    private int dur;

    public IceBolt(Loc spawn, Enemy en, Gui gu, int d, int r, int sp, int du) {
	super(spawn,en,gu,d,r,sp);
	dur = du;
	try {
	    nw = ImageIO.read(new File ( "images/iceboltnw.gif"));
	    ne = ImageIO.read(new File ( "images/iceboltne.gif"));
	    w = ImageIO.read(new File ( "images/iceboltw.gif"));
	    n = ImageIO.read(new File ( "images/iceboltn.gif"));
	    s = ImageIO.read(new File ( "images/icebolts.gif"));
	    e = ImageIO.read(new File ( "images/icebolte.gif"));
	    se = ImageIO.read(new File ( "images/iceboltse.gif"));
	    sw = ImageIO.read(new File ( "images/iceboltsw.gif"));
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
    location.getEnemy().addStatus(new Status(0,dur,0,Status.FROZEN));
    location.getEnemy().damage(damage);
    die();
	    }
	if  (location.getX() == target.getX() && location.getY() == target.getY()) {
	    if (mark != null && mark.getLoc().equals(target)) {
		mark.addStatus(new Status(0,dur,0,Status.FROZEN));
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
