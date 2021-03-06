import java.util.*;
import javax.imageio.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Shapeshifter extends Tower {

    private Tower t;
    private int shiftTime;
    private boolean uber;

    public Shapeshifter() {
	super(null,20,10,3,0,200,Images.ditto());
	t = null;
	shiftTime = 60;
	id = 7;
	uber = false;
	maxUpgrades = new int[]{0,0,0,1};
    }

    public int getDamage() {
	if (t == null)
	    return super.getDamage();
	else
	    return t.getDamage();
    }

    public int getRate() {
		if (t == null)
	    return super.getRate();
	else
	    return t.getRate();
    }


    public int getRange() {
		if (t == null)
	    return super.getRange();
	else
	    return t.getRange();
    }


    public int getNumTargets() {
		if (t == null)
	    return super.getNumTargets();
	else
	    return t.getNumTargets();
    }

    public void setDamage(int d) {
	if (t == null)
	    super.setDamage(d);
	else
	    t.setDamage(d);
    }

    public void setRate(int r) {
	if (t == null)
	    super.setRate(r);
	else
	    t.setRate(r);
    }


    public void setRange(int r) {
	if (t == null)
	    super.setRange(r);
	else
	    t.setRange(r);
    }

    public void setNumTargets(int n) {
	if (t == null)
	    super.setNumTargets(n);
	else
	    t.setNumTargets(n);
    }

public void setTargets() {
    if (t == null)
	super.setTargets();
    else
	t.setTargets();
}

public void setActiveTargets() {
    if (t == null)
	super.setActiveTargets();
    else
	t.setActiveTargets();
}

public void act() {
    if (shiftTime <= 0) {
	if (t != null) {
	    t = null;
	    shiftTime = 60;
	}
	else if (!uber) {
	    int r =(int)(Math.random() * 9);
	    if (r == 0)
	    	t = new Caillou();
	     else if (r == 1)
	    	t = new Potato();
	     else if (r == 2)
		t = new Elsa();
	     else if (r == 3)
		 t = new Moneytree();
	     else if (r == 4)
		 t = new Fang();
	     else if (r == 5)
		 t = new Dragon();
	     else if (r == 6)
		 t = new Bomber();
	     else if (r == 7)
		 t = new Bufftower();
	     else if (r == 8)
		 t = new Arcade();
	    t.setLoc(location);
	    t.setTargets();
	    shiftTime = 180;
	}
	else {
	    int r =(int)(Math.random() * 5);
	     if (r == 0)
		 t = new Moneytree();
	     else if (r == 1)
		 t = new Fang();
	     else if (r == 2)
		 t = new Bomber();
	     else if (r == 3)
		 t = new Bufftower();
	     else if (r == 4)
		 t = new Arcade();
	}
	System.out.println("check");
    }
    else if (t == null) {
	super.act();
	shiftTime--;
    }

    else {
	t.act();
	shiftTime--;
    }
    }

    public Image getImage() {
	if (t == null)
	    return super.getImage();
	else
	    return t.getImage();
    }

   public void SpecUpgrade() {
	uber = true;
    }

    public String getSpecDescription() {
	return "Dittosuar";
    }
}
