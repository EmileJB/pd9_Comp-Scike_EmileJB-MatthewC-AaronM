import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Gui extends JFrame implements ActionListener {
    private Image grid,enemy;
    private Container pane;
    private JButton resetButton;
    private Grid board;
    private JPanel boardBorder;
    private int pathNumber;
    private ArrayList<Enemy> Enemies;
    private int[][] path = {{0,3},{1,3},{2,3},{3,3},{4,3},{4,4},{5,4},{6,4},{7,4},{8,4},{9,4}};
    
    
    private class myKeyListener implements KeyListener {
	public void keyPressed(KeyEvent e){
	    System.out.println("xD?");
	}
		public void keyTyped(KeyEvent e){
	    System.out.println("xD?");
	}
	public void keyReleased(KeyEvent e){
	    System.out.println("xD?");
	}
    }
    public void actionPerformed(ActionEvent e) {
	System.out.println("potate");
    }
    
    public Gui(int x, int y) {
	Loc[] pathSent = new Loc[path.length];
	board = new Grid(x,y,this);
	boardBorder=new JPanel();
	boardBorder.setLayout(new GridLayout(10,10));
	this.setTitle("xD");
	this.setSize(750,750);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	try {
	    grid = ImageIO.read(new File ( "images/BoundedGrid.gif"));
	    enemy = ImageIO.read(new File ( "images/Enemy.gif"));
	}catch (IOException ex) {
	    System.out.println("you goofed Dx");
	}
	pane = this.getContentPane();
	pane.setBackground(Color.white);
	pane.setLayout(new BorderLayout());
	pane.add(boardBorder,BorderLayout.CENTER);
	//pathNumber=0;
	//for (int[] panels:board) {
	//  for (int panel:panels) {
	for (int xcor = 0; xcor < x; xcor++) {
	    for (int ycor = 0; ycor < y; ycor++) {
		String qwer = "";
		Loc l = new Loc(xcor,ycor,-1,board,Color.WHITE);
		//if ( (xcor==3 && ycor<5) || (xcor==4 && ycor>=4) ) {
		for (int i = 0; i < path.length; i++) {
		    if (xcor == path[i][0] && ycor == path[i][1]) {
			l.setColor(Color.BLUE);
				       l.setID(i);
			board.setLoc(xcor,ycor,l);
			pathSent[i] = l;
			qwer = "" + i;
			if (i == 0)
			    l.addActor(new Enemy(100,10,12,l));
			break;

		   ///pathNumber++;
		    }
		
		else
		   board.setLoc(xcor,ycor,l);
		}
		JPanel jpanel = new JPanel();
		jpanel.setBackground(board.getLoc(xcor,ycor).getColor());
		JLabel thumb = new JLabel(qwer);
		if (l.getActors().size() > 0) {
		    ImageIcon icon = new ImageIcon(enemy);
		    thumb.setIcon(icon);
		}
		//ImageIcon icon = new ImageIcon(grid);
		//jpanel.setPreferredSize(new Dimension(65,65));	
		jpanel.setBorder(BorderFactory.createLineBorder(Color.black,1));
		//thumb.setIcon(icon);
		jpanel.add(thumb);
		boardBorder.add(jpanel);
	    }
	}
	board.setPath(pathSent);
    }

    public void tick() {
	for (int i = 0; i < Enemies.size(); i++) {
	    Enemy e = Enemies.get(i);
	    e.act();
	}
    }

    public static void main(String[] args){
	Gui g= new Gui(10,10);
	g.setVisible(true);
    }
}
