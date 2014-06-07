import java.util.*;
import javax.imageio.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Tower extends Actor {
    protected int id;
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

    public Tower (Loc loc, int d, int s, int r, int n) {
	super(loc);
	id=1;
	basedamage = damage = d;
	baserate = rate = s;
	baserange = range = r;
	turn = 0;
	numTargets = n;
	if (location != null) {
	    board = loc.getGrid();
	    setTargets();
	}
	try {
	    norm = ImageIO.read(new File ( "images/Ciallou.gif"));
	}
	catch (IOException ex) {
	    System.out.println("you dun goofed");
	}
	info = new JPanel();
	ImageIcon icon = new ImageIcon(norm);
	info.setPreferredSize(new Dimension(50,75));
	String text= "<html>Damage: " + damage + "<br>Rate: " + baserate + "<br>Range: " + range + "</html>";
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

    public void setDamage(int d) {
	damage = d;
    }

    public void setRate(int r) {
	rate = r;
    }

    public void setRange(int r) {
	range = r;
    }

    public void setNumTargets(int n) {
	numTargets = n;
    }

    public void setLoc(Loc l) {
	location = l;
	board = location.getGrid();
    }
    public ArrayList getLocs(Loc l) {
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
		Projectile p = new Projectile(location, activeTargets.get(0), board.getGui(), damage, 2*range, 0);
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
}
