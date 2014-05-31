public class Enemy extends Actor {

    protected int reward;
    protected int basereward;
    protected int speed;
    protected int basespeed;
    protected int hp;
    protected int basehp;
    // protected Loc location;
    protected Grid board;
    protected int pathPos;
    //protected World world (may be useful to modify things like lives and money)

    public Enemy(int h, int s, int r, Loc loc) {
	super(loc);	
	basehp = hp = h;
	basespeed = speed = s;
	basereward = reward = r;
	//location = loc;
	board = location.getGrid();
    }

    public int getHP() {
	return hp;
    }

    public int getSpeed() {
	return speed;
    }

    public int getReward() {
	return reward;
    }

    public Loc getLoc() {
	return location;
    }

    public Grid getGrid() {
	return board;
    }

    public void setHP(int h) {
	hp = h;
    }

    public void setSpeed(int s) {
	speed = s;
    }

    public void setReward(int r) {
	reward = r;
    }

    public void setLoc(Loc l) {
	    location = l;
	}

    public void move() {
	if (speed == 0) {
	    pathPos++;
	    Loc tmp = location; 
	    location = board.getPathLoc(pathPos);
	    tmp.removeActor(this);
	    location.addActor(this); 
	    speed = basespeed;
		   }
	else
	    speed--;
    }

    /*    public void checkHP() {
	if (hp <= 0)
	    die();
	if (hp > basehhp)
	    hp = basehp;
    }

    public void die() {
	getLoc.remove(this);
    }
    */
    public void act() { 
    	//checkHP();
	move();
    }
}
