public class Bomber extends Tower {

    private int size = 1;

    public Bomber() {
	super(null,75,30,4,0,400,Images.bm());
	id = 11;
	maxUpgrades = new int[]{3,3,3,1};
	upgradePrices[3] = new int[]{800,0,0,0};
    }

    public void act() {
	setActiveTargets();
	if (turn%rate == 0) {
	    for (int i = 0; i < activeTargets.size() && i <= numTargets;i++) {
		//activeTargets.get(0).setHP(activeTargets.get(0).getHP()-damage);
		Projectile p = new Bomb(location, activeTargets.get(0), board.getGui(), (int)(damage * buff), 2*range, 1, size);
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
     size++;
    }

  public String getSpecDescription() {
	return "Explosion radius: " + size + "<br>+1";
    }
}
