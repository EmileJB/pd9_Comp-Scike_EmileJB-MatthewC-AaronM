import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Dragon extends Tower {

    public Dragon() {
	super(null,1,1,3,0,200,Images.caillou());
	id = 10;
    }

public void act() {
	setActiveTargets();
	if (turn%rate == 0) {
	    for (int i = 0; i < activeTargets.size() && i <= numTargets;i++) {
		//activeTargets.get(0).setHP(activeTargets.get(0).getHP()-damage);
		Projectile p = new FireBlast(location, activeTargets.get(0), board.getGui(), damage, range, 0, 60, 4);
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