import info.gridworld.grid.*;


public class Board {
    public static void main (String[] args) {
	BoundedGrid<Loc> b = new BoundedGrid<Loc> ( 10,10);
	Game g = new Game( b);
       	System.setProperty("info.gridworld.gui.selection", "hide");
	g.show();
    }
}
