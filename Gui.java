import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Gui extends JFrame implements ActionListener {
    private Image grid;
    private Container pane;
    private JButton resetButton;
    private Loc[][] board = new Loc[10][10];
    private JPanel boardBorder;
    private int pathNumber;
    
    
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
	boardBorder=new JPanel();
	boardBorder.setLayout(new GridLayout(10,10));
	this.setTitle("xD");
	this.setSize(750,750);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	try {
	    grid = ImageIO.read(new File ( "images/BoundedGrid.gif"));
	}catch (IOException ex) {
	    System.out.println("you goofed Dx");
	}
	pane = this.getContentPane();
	pane.setBackground(Color.white);
	pane.setLayout(new BorderLayout());
	pane.add(boardBorder,BorderLayout.CENTER);
	pathNumber=0;
	//for (int[] panels:board) {
	//  for (int panel:panels) {
	for (int xcor = 0; xcor < x; xcor++) {
	    for (int ycor = 0; ycor < y; ycor++) {
		//String qwer = "";
		if ( (xcor==3 && ycor<5) || (xcor==4 && ycor>=4) ) {
		    board[xcor][ycor] = new Loc(xcor,ycor,pathNumber,Color.BLUE);
		    //qwer="" +pathNumber;
		    pathNumber++;
		    
		}
		else
		     board[xcor][ycor] = new Loc(xcor,ycor,-1);
		JPanel jpanel = new JPanel();
		jpanel.setBackground(board[xcor][ycor].getColor());
		JLabel thumb = new JLabel();
		//ImageIcon icon = new ImageIcon(grid);
		//jpanel.setPreferredSize(new Dimension(65,65));	
		jpanel.setBorder(BorderFactory.createLineBorder(Color.black,1));
		//thumb.setIcon(icon);
		jpanel.add(thumb);
		boardBorder.add(jpanel);
	    }
	}
	//}
    }

    public static void main(String[] args){
	Gui g= new Gui(10,10);
	g.setVisible(true);
    }
}
