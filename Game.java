import info.gridworld.grid.*;
import info.gridworld.world.*;

public class Game extends World<Location> {
    private Location[] [] crib = new Location[10][10];
    private boolean placeable;

    public Game(BoundedGrid<Location> b){
	super(b);
	start();
    }
    
    public void start() {
	for (int r =0; r<=9; r++){
	    for (int c=0; c <=9; c++){
		crib[r][c]= new Location(r, c);
	    }
	}
	

    }
}
