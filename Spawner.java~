import java.util.*;
import java.awt.*;
import javax.imageio.*;
public class Spawner {

	private Queue<Enemy> spawns;
	private int rate;
	private int baserate;
	private Loc sendTo;
	private Gui gui;

    public Spawner(int r, Loc l, Gui g, ImageIcon i) {
	    spawns = new LinkedList<Enemy>();
	    baserate = rate = r;
	    sendTo = l;
	    gui = g;
	}

	public void add(Enemy e) {
	    spawns.add(e);
	}

	public Enemy remove() {
	    return spawns.remove();
	}

	public void act() {
	    if (spawns.size() > 0) {
	    if (rate <= 0) {
		Enemy spawn = remove();
		sendTo.addActor(spawn);
		spawn.setLoc(sendTo);
		gui.addEnemy(spawn);
		rate = baserate;
	    }
	    else
		rate--;
	    }
	}
}
