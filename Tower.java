import java.util.*;

public class Tower extends Actor {
    
    protected int damage;
    protected int basedamage;
    protected int rate;
    protected int baserate;
    protected int range;
    protected int baserange;
    protected Grid board;
    protected ArrayList<Loc> targets; //all locs in tower's range
    protected ArrayList<Actor> activeTargets; //all actors in tower's range
    protected int numTargets; //amount of targets that can be shot at once
    //protected Projectile projectile;

    public Tower (Loc loc, int d, int s, int r, int n) {
	super(loc);
	basedamage = damage = d;
	baserate = rate = s;
	baserange = range = r;
	if (location != null)
	    board = loc.getGrid();
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

    public void setTargets() {
	targets = new ArrayList<Loc>();
	activeTargets = new ArrayList<Actor>();
	int x = location.getX();
	int y = location.getY();
	for (int i = x - range; (i < x + range) && (x - range > 0 && x + range < board.getRow()); i++) {
	    for (int j = y - range; (i < y + range) && (y - range > 0 && y + range < board.getCol()); j++) {
		targets.add(board.getLoc(i,j));
	    }
	}
    }    
	   
    public void act() {
    }
}