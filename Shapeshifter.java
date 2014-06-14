import java.util.*;
import javax.imageio.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Shapeshifter extends Tower {

    private Tower t;
    private int shiftTime;

    public Shapeshifter() {
	super(null,20,10,3,0,99,Images.ditto());
	t = null;
	shiftTime = 60;
	id = 7;
	maxUpgrades = new int[]{0,0,0,3};
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
	else {
	    int r =(int)(Math.random() * 6);
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
	    t.setLoc(location);
	    t.setTargets();
	    shiftTime = 180;
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

}
