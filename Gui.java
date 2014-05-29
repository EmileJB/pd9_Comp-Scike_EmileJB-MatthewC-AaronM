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
    private int[][] board = new int[10][10];
    private JPanel boardBorder;

    
    
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
    public void actionPerformed(ActionEvent e){
	
    }

    public Gui() {
	boardBorder=new JPanel();
	this.setTitle("xD");
	this.setSize(750,750);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	try {
	    grid = ImageIO.read(new File ( "images/BoundedGrid.gif"));
	}catch (IOException ex) {
	    System.out.println("you goofed Dx");
	}
	pane = this.getContentPane();
	pane.setLayout(new BorderLayout());
	JPanel jpanel = new JPanel();
	JPanel thumb = new JLabel();
	ImageIcon icon = new ImageIcon(grid);
	
	jpanel.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
	thumb.setIcon(icon);
	jpanel.add(thumb);
	boardBorder.add(jpanel);
    
    }
    public static void main(String[] args){
	Gui g= new Gui();
	g.setVisible(true);
    }
}