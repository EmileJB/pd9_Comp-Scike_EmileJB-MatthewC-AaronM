public class Fang extends Tower {

    public boolean piercing;

    public Fang() {
	super(null,1000,35,10,0,500,Images.fang());
	id = 9;
	maxUpgrades = new int[]{3,3,0,1};
	piercing = false;
    }

    public void act() {
	setActiveTargets();
	if (turn%rate == 0) {
	    for (int i = 0; i < activeTargets.size() && i <= numTargets;i++) {
		//activeTargets.get(0).setHP(activeTargets.get(0).getHP()-damage);
		Projectile p = new Bullet(location, activeTargets.get(0), board.getGui(), (int)(damage * buff), 2*range, 0, piercing);
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

