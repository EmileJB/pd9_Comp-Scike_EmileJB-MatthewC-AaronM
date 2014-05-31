import javax.swing.*;

public class MappedJPanel extends JPanel{
    private int x,y;

    public MappedJPanel(int xcor, int ycor) {
	super();
	x = xcor;
	y = ycor;
    }

    public int getX() {return x;}

    public int getY() {return y;}


}