import info.gridworld.grid.*;
import info.gridworld.actor.*;
import info.gridworld.actor.ActorWorld;
import java.awt.Color;

public class Board {
    public static void main (String[] args) {
	ActorWorld w = new ActorWorld();
	w.add(new Bug());
	Flower j = new Flower();
	w.add(new Location(2,2), j);
	j.setDirection(Location.EAST);
	
	w.show();
	/*
	BoundedGrid<Location> b = new BoundedGrid<Location> ( 10,10);
	Game g = new Game( b);
       	System.setProperty("info.gridworld.gui.selection", "hide");
	g.show();
	*/
    }
}
