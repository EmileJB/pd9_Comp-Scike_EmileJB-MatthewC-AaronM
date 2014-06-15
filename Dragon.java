import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Dragon extends Tower {

    private int burnDuration;

    public Dragon() {
	super(null,1,1,3,0,200,Images.ord());
	id = 10;
	burnDuration = 50;
	maxUpgrades = new int[]{3,1,2,3};
	upgradePrices[0] = new int[]{100,200,300};
	upgradePrices[1] = new int[]{500,0,0};
	upgradePrices[2] = new int[]{200,400,0};
	upgradePrices[3] = new int[]{200,200,200};
    }

public void act() {
	setActiveTargets();
	if (turn%rate == 0) {
	    for (int i = 0; i < activeTargets.size() && i <= numTargets;i++) {
		//activeTargets.get(0).setHP(activeTargets.get(0).getHP()-damage);
		Projectile p = new FireBlast(location, activeTargets.get(0), board.getGui(), damage, range, 0, burnDuration, 8);
		p.act();
		//activeTargets.get(0).setImg();
		activeTargets.remove(0);
		
	    }
	}
	//System.out.println(targets);
	//System.out.println(activeTargets);
	    turn++;
    }

   public String getSpecDescription() {
       return "Burn Duration:<br>" + burnDuration + "+25";
    }

    public void SpecUpgrade() {
	burnDuration+=25;
    }
}
