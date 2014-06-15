import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class Invader extends Projectile {
    protected Image img ,nw,ne,n,s,e,w,se,sw;

    public Invader(Loc spawn, Enemy en, Gui gu, int d, int r, int sp) {
	super(spawn,en,gu,d,r,sp);
	try {
	    nw = ne = n = s = e = w = se = sw = ImageIO.read(new File ( "images/invader.gif"));
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
	super.setImg(n);
    }
}

