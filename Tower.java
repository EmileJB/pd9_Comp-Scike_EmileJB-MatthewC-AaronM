import java.util.*;
import javax.imageio.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Tower extends Actor {
    protected int id,price;
    protected double buff;
    protected int damage;
    protected int basedamage;
    protected int rate, turn;
    protected int baserate;
    protected int range;
    protected int baserange;
    protected Grid board;
    protected ArrayList<Loc> targets; //all locs in tower's range
    protected ArrayList<Enemy> activeTargets; //all enemies in tower's range
    protected int numTargets; //amount of targets that can be shot at once
    //protected Projectile projectile;
    protected JPanel info;
    protected Image norm;
    public int[] upgrades;
    protected int[] maxUpgrades;
    protected int[][] upgradePrices;//damage prices, speed prices, range prices, special prices

    public Tower (Loc loc, int d, int s, int r, int n, int p,Image im) {
	super(loc);
	upgrades = new int[]{0,0,0,0};
	maxUpgrades = new int[]{3,3,2,0};
	upgradePrices =new int [4][3];
	upgradePrices[0] = new int[]{p,p*2,p*3};
	upgradePrices[1] = new int[]{p,p*2,p*3};
	upgradePrices[2] = new int[]{p,p*2,p*3};
	upgradePrices[3] = new int[]{p,p*2,p*3};
	price = p;
	id=1;
	basedamage = damage = d;
	baserate = rate = s;
	baserange = range = r;
	turn = 0;
	buff = 1.0001;
	numTargets = n;
	if (location != null) {
	    board = loc.getGrid();
	    setTargets();
	}
	norm = im;
	info = new JPanel();
	ImageIcon icon = new ImageIcon(norm);
	info.setPreferredSize(new Dimension(50,75));
	String text= "<html>Price: " + price + "<br>Damage: " + damage + "<br>Rate: " + baserate + "<br>Range: " + range + "</html>";
	JLabel label = new JLabel(text);
	    label.setIcon(icon);
	info.add(label);
    }

    public int getDamage() {
	return damage;
    }

    public int getRate() {
	return rate;
    }

    public int getBaseRate() {
	return baserate;
    }

    public int getRange() {
	return range;
    }

    public int getNumTargets() {
	return numTargets;
    }

    public Loc getLoc() {
	return location;
    }

    public Grid getGrid() {
	return board;
    }

    public int getPrice() {
	return price;
    }

    public int[] getMaxUpgrades() {
	return maxUpgrades;
    }

  public int[][] getUpgradePrices() {
	return upgradePrices;
    }

    public Image getNorm() {
	return norm;
    }

    public void setDamage(int d) {
	damage = d;
    }

    public void setRate(int r) {
	rate = r;
    }

   public void setBaseRate(int r) {
	baserate = r;
    }

    public void setRange(int r) {
	range = r;
    }

    public void setNumTargets(int n) {
	numTargets = n;
    }

   public void setPrice(int p) {
	price = p;
    }

    public void setLoc(Loc l) {
	location = l;
	board = location.getGrid();
    }

    public ArrayList<Loc> getLocs(Loc l) {
	targets = new ArrayList<Loc>();
	int x = location.getX();
	int y = location.getY();
	for (int i = x + range; i >= x - range; i--) {
	    for (int j = y + range; j >= y - range; j--) {
       	if ( j >= 0 && j < board.getCol() && i >= 0 && i < board.getRow()) {
		    targets.add(board.getLoc(i,j));
		}
	    }
	}
	return targets;
    }


    public void setTargets() {
	targets = new ArrayList<Loc>();
	activeTargets = new ArrayList<Enemy>();
	int x = location.getX();
	int y = location.getY();
	for (int i = x + range; i >= x - range; i--) {
	    for (int j = y + range; j >= y - range; j--) {
		if ( j >= 0 && j < board.getCol() && i >= 0 && i < board.getRow() && board.getLoc(i,j).getID() >= 0) {
		    targets.add(board.getLoc(i,j));
		}
	    }
	}//sort targets according to ID using insertion sort, because this method is called only a few times by each tower and targets has very few elements.
		int i = 0;
	Loc key;
	for (int j = 1;j<targets.size();j++) {
	    key = targets.get(j);
	    i = j-1;
	    while (i>=0 && targets.get(i).getID() < key.getID()) {
		targets.set(i+1,targets.get(i));
		targets.set(i,key);
		i--;
	    }
	}
	//System.out.println(targets);
    }

    public void setActiveTargets() {
	activeTargets = new ArrayList<Enemy>();//clear all previous targets
	for (Loc l:targets) {
	    for (Actor a:l.getActors()) {
		if (a instanceof Enemy) 
		    activeTargets.add((Enemy)a);
	    }
	}
    }
	   
    public void act() {
	setActiveTargets();
	if (turn%rate == 0) {
	    for (int i = 0; i < activeTargets.size() && i <= numTargets;i++) {
		//activeTargets.get(0).setHP(activeTargets.get(0).getHP()-damage);
		Projectile p = new Projectile(location, activeTargets.get(0), board.getGui(), (int)(damage * buff), 2*range, 0);
		p.act();
		//activeTargets.get(0).setImg();
		activeTargets.remove(0);
		
	    }
	}
	//System.out.println(targets);
	//System.out.println(activeTargets);
	    turn++;
    }
    public int ID() {
	return id;
    }

    public JPanel getJPanel() {
	return info;
    }

    public Image getImage() {
	return norm;
    }

    public void SpecUpgrade() {
    }

    public void setTargets(ArrayList<Loc> l) {
	targets = l;
    }

    public String getSpecDescription() {
	return "Swag +1";
    }

    public void setBuff(double d) {
	buff = d;
    }
}
