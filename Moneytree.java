import java.awt.*;
import javax.swing.*;
import javax.imageio.*;

public class Moneytree extends Tower {

    public Moneytree() {
	super(null,10,40,0,0,100,Images.moneyTree());
	id = 8;
	info.removeAll();
	ImageIcon icon = new ImageIcon(norm);
	String text= "<html>Price: " + price + "<br>Payout: " + damage + "<br>Rate: " + rate + "</html>";
	JLabel label = new JLabel(text);
	label.setIcon(icon);
	info.add(label);
	
    }
    
    public void act() {
	if (turn%rate == 0) {
	    getGrid().getGui().setMoney(getGrid().getGui().getMoney() + damage);
	    id = 10;
	    Coin c = new Coin(location,board.getGui());
	    c.act();
	}
	turn++;
    }
}
