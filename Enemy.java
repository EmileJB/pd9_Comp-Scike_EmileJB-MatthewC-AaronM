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
	Loc tmp = location;
	location = l;
	location.addActor(this);
	tmp.removeActor(this);
	}

    public void move() {
	if (speed == 0) {
	    pathPos++;
	    setLoc(board.getPathLoc(pathPos));
	    speed = basespeed;
		   }
	else
	    speed--;
    }

        public void checkHP() {
	if (hp <= 0)
	    die();
	if (hp > basehp)
	    hp = basehp;
    }

    public void checkPos() {
	if (location.getID() < 0)
	    setLoc(board.getPathLoc(pathPos));
	if (location.equals(board.getEnd()))
	    finish();
    }

    public void die() {
	location.removeActor(this);
	Gui g = board.getGui();
	g.removeEnemy(this);
	g.setMoney(g.getMoney()+reward);
    }

    public void finish() {
	System.out.println("win");
	location.removeActor(this);
	Gui g = board.getGui();
	g.removeEnemy(this);
	g.setLives(g.getLives()-1);
    } 
  
    public void act() { 
    	checkHP();
	move();
	checkPos();
    }
}
