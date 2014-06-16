public class Bomber extends Tower {

    public Bomber() {
	super(null,75,20,4,0,200,Images.bm());
	id = 11;
	maxUpgrades = new int[]{3,3,3,1};
    }

    public void act() {
	setActiveTargets();
	if (turn%rate == 0) {
	    for (int i = 0; i < activeTargets.size() && i <= numTargets;i++) {
		//activeTargets.get(0).setHP(activeTargets.get(0).getHP()-damage);
		Projectile p = new Bomb(location, activeTargets.get(0), board.getGui(), (int)(damage * buff), 2*range, 1, 1);
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
