import java.awt.*;

public class Loc{
    private int x,y,ID; //row, col, ID
    private Color color; //color of square (obviously)
    //private ArrayList<Actor> occupants; what entity is located at this location(to be used later)

    public Loc(int xcor, int ycor, int pathID){
	x = xcor;
	y = ycor;
	ID = pathID;
	color = Color.WHITE;
	//occupant = null;
    }

    public Loc(int xcor, int ycor, int pathID, Color c){
	x = xcor;
	y = ycor;
	ID = pathID;
	color = c;
	//occupant = null;
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

    //public void addActor(Actor a) 
    //public boolean removeActor(Actor a)
    //public boolean removeActor(int i) (?)
    //public ArrayList<Actor> getActors()

    
}
