import java.awt.*;
import javax.swing.*;
import javax.imageio.*;

public class Moneytree extends Tower {

    public Moneytree() {
	super(null,10,40,0,0,500,Images.moneyTree());
	id = 8;
	info.removeAll();
	ImageIcon icon = new ImageIcon(norm);
	String text= "<html>Price: " + price + "<br>Payout: " + damage + "<br>Rate: " + rate + "</html>";
	JLabel label = new JLabel(text);
	label.setIcon(icon);
	info.add(label);
	maxUpgrades = new int[]{0,3,0,3};
	upgradePrices[1] = new int[]{250,500,750};
	upgradePrices[3] = new int[]{250,500,750};
    }
    
    public void act() {
	if (turn%rate == 0) {
	    getGrid().getGui().setMoney(getGrid().getGui().getMoney() + (int)(damage * buff));
	    id = 10;
	    Coin c = new Coin(location,board.getGui());
	    c.act();
	}
	turn++;
    }


   public void SpecUpgrade() {
       setDamage((6*getDamage())/5); 
   }
    
    public String getSpecDescription() {
 	return "Payout: " + damage + "<br>+" + (getDamage()/5);
    }
}
