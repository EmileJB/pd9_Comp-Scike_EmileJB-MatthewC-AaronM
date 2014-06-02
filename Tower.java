import java.util.*;

public class Tower extends Actor {
    
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

    public Tower (Loc loc, int d, int s, int r, int n) {
	super(loc);
	basedamage = damage = d;
	baserate = rate = s;
	baserange = range = r;
	turn = 0;
	numTargets = n;
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
	activeTargets = new ArrayList<Enemy>();
	int x = location.getX();
	int y = location.getY();
	for (int i = x - range; i <= x + range; i++) {
	    for (int j = y - range; j <= y + range; j++) {
		if ( j >= 0 && j < board.getCol() && i >= 0 && i < board.getRow()) {
		    targets.add(board.getLoc(i,j));
		    for (Actor a:board.getLoc(i,j).getActors()) {
			if (a instanceof Enemy) 
			    activeTargets.add((Enemy)a);
		    }
		}
	    }
	}
    }    
	   
    public void act() {
	setTargets();
	if (turn%rate == 0) {
	    for (int i = 0; i < activeTargets.size() && i <= numTargets;i++) {
		activeTargets.get(0).setHP(activeTargets.get(0).getHP()-damage);
		activeTargets.remove(0);
	    }
	}
	//System.out.println(targets);
	//System.out.println(activeTargets);
	    turn++;
    }
}