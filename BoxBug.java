import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class BoxBug extends Enemy {
    protected Image nn,ns,nw,ne,an,as,aw,ae;
    protected Gui g;
    public BoxBug(int health, int speed, int reward, Loc l){
	super(health+40, speed+8, reward+1, l);
	try {
	    // System.out.println("you goofed boxbug style Dx");
	    nn =  ImageIO.read(new File ( "images/bbn.gif"));
	    ns=ImageIO.read(new File ( "images/bbs.gif"));
	    nw=ImageIO.read(new File ( "images/bbw.gif"));
	    ne=ImageIO.read(new File ( "images/bbe.gif"));
	    an = ImageIO.read( new File ( "images/abbn.gif"));
	    as = ImageIO.read( new File ( "images/abbs.gif"));
	    aw = ImageIO.read( new File ( "images/abbw.gif"));
	    ae = ImageIO.read( new File ( "images/abbe.gif")); 
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
	g= null;
	
	
    }
    public void die(){
	super.die();
	g=location.getGrid().getGui();
	Bug b = new Bug(super.getBHP()-30 , super.getBS()-10, super.getBR()-9, g.getGrid().getPathLoc(pathPos));
	location.addActor(b);
	System.out.println(g.getGrid().getPathLoc(pathPos));
	
	g.addEnemy(b);
    }
	

}
  
