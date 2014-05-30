public class Enemy extends Actor {

    protected int reward;
    protected int speed;
    protected int hp;
    protected Loc location;

    public Enemy(int h, int s, int r, Loc loc) {
	hp = h;
	speed = s;
	reward = r;
	location = loc;
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

    public void Act() {
    
    }
}