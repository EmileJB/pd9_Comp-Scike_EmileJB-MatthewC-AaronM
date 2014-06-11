import java.awt.*;
import javax.swing.*;
import javax.imageio.*;

public class Moneytree extends Tower {

    public Moneytree() {
	super(null,10,40,0,0,500,Images.moneyTree());
	info.removeAll();
	ImageIcon icon = new ImageIcon(norm);
	String text= "<html>Price: " + price + "<br>Payout: " + damage + "<br>Rate: " + rate + "</html>";
	JLabel label = new JLabel(text);
	label.setIcon(icon);
	info.add(label);
	id = 4;
    }
    
    public void act() {
	if (turn%rate == 0) {
	    getGrid().getGui().setMoney(getGrid().getGui().getMoney() + damage);
	}
	turn++;
    }
}