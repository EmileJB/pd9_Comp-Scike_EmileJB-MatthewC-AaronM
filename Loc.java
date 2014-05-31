import java.awt.*;
import java.util.*;

public class Loc{
    private int x,y,ID; //row, col, ID
    private Color color; //color of square (obviously)
    private ArrayList<Actor> occupants; //what entity is located at this location(to be used later)
    private Grid board;

    // public Loc(int xcor, int ycor, int pathID, Grid g){
    //	Loc(xcor,ycor,pathID,g,Color.white);
    //}

    public Loc(int xcor, int ycor, int pathID, Grid g, Color c) {
	x = xcor;
	y = ycor;
	ID = pathID;
	color = c;
	board = g;
	occupants = new ArrayList<Actor>();
    }

    public String toString(){
	return x + "," + "y"; //for now, later return occupant.toString()
    }

    //the gets and sets

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    public int getID() {
	return ID;
    }

    public Color getColor() {
	return color;
    }

    public void setX(int xcor) {
	x = xcor;
    }

    public void setY(int ycor) {
	y = ycor;
    }

    public void setID(int pathID) {
	ID = pathID;
    }

    public void setColor(Color col) {
	color = col;
    }

    public void addActor(Actor a) {
	String name = a.getClass().getName();
	if ((name == "Enemy" && ID >= 0) || (name != "Enemy" && ID < 0)) { 
//won't work when subclasses are implemented, a fix will need to be found
	    occupants.add(a);
	}
    }

    public boolean removeActor(Actor a) {
	return occupants.remove(a);
    }

    public boolean removeActor(int i) {
	if ( i >= 0 && i < occupants.size()) 
	    return occupants.remove(i) != null;
	else
	    return false;
    }


    public ArrayList<Actor> getActors() {
	return occupants;
    }

    public Grid getGrid() {
	return board;
    }


}
