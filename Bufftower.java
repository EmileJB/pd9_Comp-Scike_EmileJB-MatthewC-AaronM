import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.util.*;

public class Bufftower extends Tower {

    private double damageBuff;
    private ArrayList<Tower> towersInRange;

    public Bufftower() {
	super(null,0,0,1,0,400,Images.bufftower());
	damageBuff = (double)(damage);
	damageBuff = damageBuff*.01;
	id = 11;
	info.removeAll();
	ImageIcon icon = new ImageIcon(norm);
	damageBuff = 1.2;
	String text= "<html>Price: " + price + "<br>Damage Buff: " + (int)(100*damageBuff) + "%<br>Range: " + range + "</html>";
	System.out.println(text);
	JLabel label = new JLabel(text);
	label.setIcon(icon);
	info.add(label);
	maxUpgrades = new int[]{0,0,2,3};
	upgradePrices[2] = new int[]{800,1600,0};
	upgradePrices[3] = new int[]{800,1600,3200};
    }

    public double getDamageBuff() {
	return damageBuff;
    }

    public void setDamageBuff(double d) {
	damageBuff = d;
    }

    public void setDamageBuff(int i) {
	damageBuff = (double)(i);
	damageBuff = damageBuff * .01;
    }

    public void setTargets() {
	targets = getLocs(location);
    }

   public void setActiveTargets() {
	towersInRange = new ArrayList<Tower>();//clear all previous targets
	for (Loc l:targets) {
	    for (Actor a:l.getActors()) {
		if (a instanceof Tower) 
		    towersInRange.add((Tower)a);
	    }
	}
    }

    public void act() {
	setActiveTargets();
	for (Tower t: towersInRange) {
	    t.setBuff(damageBuff);
	}
    }

    public void SpecUpgrade() {
	damageBuff = damageBuff + .2;
    }

    public String getSpecDescription() {
	return "Damage Buff +20%";
    }
}