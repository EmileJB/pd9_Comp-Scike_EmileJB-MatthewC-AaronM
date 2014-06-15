public class Fang extends Tower {

    public boolean piercing;

    public Fang() {
	super(null,10000,35,10,0,100,Images.fang());
	id = 9;
	maxUpgrades = new int[]{3,3,0,1};
	upgradePrices[1] = new int[]{200,400,600};
	piercing = false;
    }

    public void act() {
	setActiveTargets();
	if (turn%rate == 0) {
	    for (int i = 0; i < activeTargets.size() && i <= numTargets;i++) {
		//activeTargets.get(0).setHP(activeTargets.get(0).getHP()-damage);
		Projectile p = new Bullet(location, activeTargets.get(0), board.getGui(), damage, 2*range, 0, piercing);
		p.act();
		//activeTargets.get(0).setImg();
		activeTargets.remove(0);
		
	    }
	}
	//System.out.println(targets);
	//System.out.println(activeTargets);
	    turn++;
    }

    public void SpecUpgrade() {
	piercing = true;
    }

    public String getSpecDescription() {
	return "Piercing Shot";
    }
}

