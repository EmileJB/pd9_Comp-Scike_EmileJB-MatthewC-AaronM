import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Elsa extends Tower {

    private int freezeChance;
     
    public Elsa() {
	super(null,10,20,4,0,120,Images.elsa());
	freezeChance = 25;
	id = 6;
	maxUpgrades = new int[]{3,2,2,3};
	upgradePrices[0] = new int[]{60,120,180};
	upgradePrices[1] = new int[]{240,480,0};
	upgradePrices[2] = new int[]{120,240,0};
	upgradePrices[3] = new int[]{120,240,360};
	    
    }

public void act() {
	setActiveTargets();
	if (turn%rate == 0) {
	    for (int i = 0; i < activeTargets.size() && i <= numTargets;i++) {
		//activeTargets.get(0).setHP(activeTargets.get(0).getHP()-damage);
		int random = (int)(Math.random() * 100);//0-99
		if (random < freezeChance) {
		    Projectile p = new IceBolt(location, activeTargets.get(0), board.getGui(), (int)(damage * buff), 2*range, 1, 60);
		    p.act();
		}
		else {
		    Projectile p = new IceBolt(location, activeTargets.get(0), board.getGui(), (int)(damage * buff), 2*range, 1, 0);
		    p.act();
		}
		//activeTargets.get(0).setImg();
		activeTargets.remove(0);
		
	    }
	}
	//System.out.println(targets);
	//System.out.println(activeTargets);
	    turn++;
    }

   public String getSpecDescription() {
       return "Freeze Chance:<br>" + freezeChance + "+25%";
    }

    public void SpecUpgrade() {
	freezeChance+=25;
    }
}
