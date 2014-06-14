import java.awt.*;
import javax.swing.*;
import javax.imageio.*;

public class Bufftower extends Tower {

    private double damageBuff;

    public Bufftower() {
	super(null,10,0,1,0,400,Images.moneyTree());
	damageBuff = (double)(damage);
	damageBuff = damageBuff*.01;
	id = 9;
	info.removeAll();
	ImageIcon icon = new ImageIcon(norm);
	String text= "<html>Price: " + price + "<br>Damage Buff: " + (int)(100*damageBuff) + "%<br>Range: " + range + "</html>";
	System.out.println(text);
	JLabel label = new JLabel(text);
	label.setIcon(icon);
	info.add(label);
	maxUpgrades = new int[]{3,0,2,0};
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

    public void act() {
	
    }
}