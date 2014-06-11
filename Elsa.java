public class Elsa extends Tower {

    public Elsa() {
	super(null,10,20,4,0,120,Images.elsa());
    }

public void act() {
	setActiveTargets();
	if (turn%rate == 0) {
	    for (int i = 0; i < activeTargets.size() && i <= numTargets;i++) {
		//activeTargets.get(0).setHP(activeTargets.get(0).getHP()-damage);
		Projectile p = new IceBolt(location, activeTargets.get(0), board.getGui(), damage, 2*range, 2, 20);
		p.act();
		//activeTargets.get(0).setImg();
		activeTargets.remove(0);
		
	    }
	}
	//System.out.println(targets);
	//System.out.println(activeTargets);
	    turn++;
    }
}
