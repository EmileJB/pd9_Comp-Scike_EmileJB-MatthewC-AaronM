public class Arcade extends Tower {

    public Arcade() {
	super(null,20,10,10,0,150,Images.arcade());
	maxUpgrades = new int[]{3,3,2,1};
	upgradePrices[0] = new int[]{150,300,450};
	upgradePrices[1] = new int[]{150,300,450};
	upgradePrices[2] = new int[]{150,300,0};
	upgradePrices[3] = new int[]{300,0,0};
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
