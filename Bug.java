import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Bug extends Enemy {
    protected Image nn,ns,nw,ne,an,as,aw,ae;
    public Bug(int health, int speed, int reward, Loc l){
	super(health, speed-3, reward+12, l);
	try {
	    nn =  ImageIO.read(new File ( "images/bugn.gif"));
	    ns=ImageIO.read(new File ( "images/bugs.gif"));
	    nw=ImageIO.read(new File ( "images/bugw.gif"));
	    ne=ImageIO.read(new File ( "images/buge.gif"));
	    an = ImageIO.read( new File ( "images/abugn.gif"));
	    as = ImageIO.read( new File ( "images/abugs.gif"));
	    aw = ImageIO.read( new File ( "images/abugw.gif"));
	    ae = ImageIO.read( new File ( "images/abuge.gif")); 
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
	    System.out.println("you goofed bug style Dx");
	}
	
	
    }

}
    
