import java.awt.*;

public class Loc{
    int x; //row
    int y; //col
    int ID; //what kind of entity can be placed here (also path number)
    Color color; //color of square (obviously)
    //private Entity occupant; what entity is located at this location(to be used later)

    public Loc(int xcor, int ycor, int pathID){
	setLocation(xcor,ycor);
	ID = pathID;
	//color = default; idk what to do here yet
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

    public  int getColor() {
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
}
