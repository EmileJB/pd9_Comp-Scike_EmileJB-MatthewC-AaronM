public class IceBolt extends Projectile {

    private int dur;

    public IceBolt(Loc spawn, Enemy en, Gui gu, int d, int r, int sp, int du) {
	super(spawn,en,gu,d,r,sp);
	dur = du;
    }

    public void checkPos() {
if (location.getEnemy() != null) {
	    if ((int)(Math.random() * 20) < 15)
		location.getEnemy().addStatus(new Status(0,dur,0,Status.FROZEN));
	    location.getEnemy().damage(damage);
	    die();
	    }
	if  (location.getX() == target.getX() && location.getY() == target.getY()) {
	    if (mark != null && mark.getLoc().equals(target)) {
		if ((int)(Math.random() * 20) < 15)
		    mark.addStatus(new Status(0,dur,0,Status.FROZEN));
		mark.damage(damage);
		die();
	    }
	    /* else if (mark == null && target.getEnemy() != null) {
		    target.getEnemy().damage(damage);
		    die(); 
	}*/
		die();
	    }
	else if (range <= 0) {
	    die();
	}
    }
}
