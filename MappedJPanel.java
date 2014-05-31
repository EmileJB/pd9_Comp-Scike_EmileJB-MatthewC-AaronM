import javax.swing.*;
import javax.imageio.*;
import javax.accessibility.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MappedJPanel extends JPanel implements MenuContainer, Serializable, Accessible, ImageObserver{
    private int x,y;

    public MappedJPanel(int xcor, int ycor) {
	super();
	x = xcor;
	y = ycor;
    }

    public int getX() {return x;}

    public int getY() {return y;}


}