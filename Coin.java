import javax.imageio.*;
import java.io.*;
import java.util.*;

public class Coin extends Projectile {

    public Coin(Loc spawn, Gui gu) {
	super(spawn,null,gu,0,0,0);
	try {
	    nw=ne=w=n=s=e=se=sw = ImageIO.read(new File ( "images/coin.gif"));
	}catch (IOException ex) {
	    System.out.println("you goofed Dx");
	}
	//	target = g.getLoc(spawn.getX(), spawn.getY());
	//target = g.getLoc(Math.max(spawn.getX(),0),spawn.getY());
	setImg(n);
    }
    public void act() {	
	if (acted==false){	  
	    acted=true;
	}
	else {
	    if (range > 0 && location.getX() > 0) {
		moveOne(location.getX()-1,location.getY());
		range--;
		System.out.println(range);
	    }
	    else
		die();
	}   
	
    }
    public void die() {
	location.removeActor(this);	
	g.getLoc(location.getX(),location.getY()).removeActor(this);
    }
}
