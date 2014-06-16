import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Critter extends Enemy {
    protected Image nn,ns,nw,ne,an,as,aw,ae;
    // protected Gui g;
    public Critter(int health, int speed, int reward, Loc l){
	super(health+50, speed-4, reward+2, l);
	try {
	    // System.out.println("you goofed boxbug style Dx");
	    nn = ImageIO.read(new File ( "images/Critter.gif"));
	    an = ImageIO.read(new File ( "images/catkd.gif"));
	    frozen = ImageIO.read(new File ( "images/frozen.gif"));
	    ne=nn;
	    nw=nn;
	    ns=nn;
	    as=an;
	    aw=an;
	    ae=an;
	    super.setNormn(nn);
	    super.setNorms(ns);
	    super.setNormw(nw);
	    super.setNorme(ne);
	    super.setAtkdn(an);
	    super.setAtkds(as);
	    super.setAtkdw(aw);
	    super.setAtkde(ae);
	    super.setNorm(ns);
	    super.setAtkd(as);
	    super.resetImg();
	}catch (IOException ex) {
	    System.out.println("you goofed boxbug style Dx");
	}
	//g= null;
	
	
    }
}
