import java.util.*;

public class Arcade extends Tower {

    private int score1,score2;
    private boolean spaceInvaders;

    public Arcade() {
	super(null,20,10,10,0,150,Images.arcade());
	maxUpgrades = new int[]{3,3,2,1};
	upgradePrices[0] = new int[]{150,300,450};
	upgradePrices[1] = new int[]{150,300,450};
	upgradePrices[2] = new int[]{150,300,0};
	upgradePrices[3] = new int[]{300,0,0};
	spaceInvaders = false;
    }

    public void setLoc(Loc l) {
	super.setLoc(l);
	score1 = score2 = board.getGui().getScore();
    }


    public void act() {
	setActiveTargets();
	Gui g = board.getGui();
	if ((g.getScore() - score2) >= 100 && spaceInvaders) {
	    score2 = g.getScore();
	    numTargets = 10;
	    Random r = new Random();
	    for (int i = 0; i < g.getEnemies().size() && i <= numTargets;i++) {
		Projectile p = new Invader(board.getLoc(r.nextInt(board.getCol()),r.nextInt(board.getRow())),g.getEnemies().get(r.nextInt(g.getEnemies().size())),g,5*(int)(damage * buff), 2*range, 2);
		p.act();
	    }
	    numTargets = 0;
	}

	if ((g.getScore() - score1) >= 25) {
	    score1 = g.getScore();
for (int i = 0; i < activeTargets.size() && i <= numTargets;i++) {
	    Projectile p = new Pac(location, activeTargets.get(0), g, 5*damage, 2*range, 0);
	    p.act();
	    activeTargets.remove(0);}
	}
    
	else if (turn%rate == 0) {
	    for (int i = 0; i < activeTargets.size() && i <= numTargets;i++) {
		//activeTargets.get(0).setHP(activeTargets.get(0).getHP()-damage);
		Projectile p = new DDR(location, activeTargets.get(0), g, damage, 2*range, 0, 4);
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
     spaceInvaders = true;
    }

    public String getSpecDescription() {
	return "Space Invaders";
    }


}
