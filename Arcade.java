public class Arcade extends Tower {

    public Arcade() {
	super(null,20,10,10,0,150,Images.arcade());
    }

    public void act() {
	setActiveTargets();
	if (turn%rate == 0) {
	    for (int i = 0; i < activeTargets.size() && i <= numTargets;i++) {
		//activeTargets.get(0).setHP(activeTargets.get(0).getHP()-damage);
		Projectile p = new DDR(location, activeTargets.get(0), board.getGui(), damage, 2*range, 0, 4);
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
