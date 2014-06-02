import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Gui extends JFrame implements ActionListener, MouseListener {
    private Image grid,enemy,tower;
    private Container pane;
    private JButton resetButton;
    private Grid board;
    private JPanel boardBorder,info;
    private int pathNumber;
    private ArrayList<Enemy> Enemies;
    private ArrayList<Tower> Towers;
    private int[][] path = {{0,3},{1,3},{2,3},{3,3},{4,3},{4,4},{5,4},{6,4},{7,4},{8,4},{9,4}};
    private int money;
    private int lives;
    private static int numthings;
    private Spawner enemySpawner;
    
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
    
    public Gui(int x, int y, int r) {
	Loc[] pathSent = new Loc[path.length];
	board = new Grid(x,y,this);
	Enemies = new ArrayList<Enemy>();
	Towers = new ArrayList<Tower>();
	lives = 100;
	money = 100;
	info = new JPanel(new GridLayout());
	info.add(new JLabel("Lives: "+lives));
	info.add(new JLabel("Money: "+money));
	boardBorder=new JPanel(new GridLayout(10,10));
	this.setTitle("xD");
	this.setSize(800,750);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	try {
	    grid = ImageIO.read(new File ( "images/BoundedGrid.gif"));
	    enemy = ImageIO.read(new File ( "images/Enemy.gif"));
	    tower = ImageIO.read(new File ( "images/Ciallou.gif"));
	}catch (IOException ex) {
	    System.out.println("you goofed Dx");
	}
	pane = this.getContentPane();
	pane.setBackground(Color.white);
	pane.setLayout(new BorderLayout());
	pane.add(boardBorder,BorderLayout.CENTER);
	pane.add(info,BorderLayout.PAGE_START);
	//pathNumber=0;
	//for (int[] panels:board) {
	//  for (int panel:panels) {
	for (int xcor = 0; xcor < x; xcor++) {
	    for (int ycor = 0; ycor < y; ycor++) {
		String numLabel = "";
		Loc l = new Loc(xcor,ycor,-1,board,Color.WHITE);
		//if ( (xcor==3 && ycor<5) || (xcor==4 && ycor>=4) ) {
		for (int i = 0; i < path.length; i++) {
		    if (xcor == path[i][0] && ycor == path[i][1]) {
			l.setColor(Color.BLUE);
				       l.setID(i);
			board.setLoc(xcor,ycor,l);
			pathSent[i] = l;
			numLabel = "" + i;
			/*if (i == 0) {
			    l.addActor(new Enemy(50,5,10,l));
			    Enemies.add((Enemy)l.getActors().get(0));
				    }
			*/
			break;

		   ///pathNumber++;
		    }
		
		else
		   board.setLoc(xcor,ycor,l);
		}

		MappedJPanel jpanel = new MappedJPanel(xcor,ycor);
		jpanel.setBackground(board.getLoc(xcor,ycor).getColor());
		//jpanel.setBorder(BorderFactory.createLineBorder(Color.black,1));
		jpanel.addMouseListener(this);
		JLabel thumb = new JLabel(numLabel);
		if (l.getActors().size() > 0) {
		    ImageIcon icon = new ImageIcon(enemy);
		    thumb.setIcon(icon);
		}
		//ImageIcon icon = new ImageIcon(grid);
		//jpanel.setPreferredSize(new Dimension(65,65));	
		//thumb.setIcon(icon);
		jpanel.add(thumb);
		//boardBorder.add(jpanel);
		JPanel panel = new JPanel(new GridLayout(1,1));
		panel.add(jpanel);
		panel.setBackground(board.getLoc(xcor,ycor).getColor());
		panel.setBorder(BorderFactory.createLineBorder(Color.black,1));
		boardBorder.add(panel);
	    }
	}
	addMouseListener(this);
	board.setPath(pathSent);
	enemySpawner = new Spawner(r, board.getSpawn(), this);
	fill(25,5,5,10,90,50,50);
	pane.validate();
    }
    //mouselistener stuff
    public void mousePressed(MouseEvent e) {//needs some way of finding the jpanel's coordinates
	try {
	MappedJPanel jpanel = (MappedJPanel)e.getSource();
	if (board.getLoc(jpanel.getX(),jpanel.getY()).getID() == -1) { 
	    if (/*currTower = 1 &&*/money >= 50) {
		Loc l = new Loc(jpanel.getX(), jpanel.getY(), -2, board,Color.WHITE);
		Tower t = new Tower(l,5,2,2,1);//damage,rate,range,numtargets
		Towers.add(t);
		l.addActor(t);//different kinds of tower
		board.setLoc(jpanel.getX(),jpanel.getY(),l);
		money -=50;		
	    }
	}
	updateBoard();
	}
	catch (Exception ex) {//
	}
    }
    
    public void mouseReleased(MouseEvent e) {
	//System.out.println("moo");
    }
    
    public void mouseEntered(MouseEvent e) {
	numthings++;
	System.out.println("moo" + numthings);
    }
    
    public void mouseExited(MouseEvent e) {
	//System.out.println("moo");
    }

    public void mouseClicked(MouseEvent e) {
	//System.out.println("moo");
    }

    //end of stupid mouselistener stuff

    public void tick() {
	//	int x = 23;
	while ((Enemies.size() > 0) || (board.getEnd().getActors().size()==0)) {
	    //System.out.println("check");
		try {
		for (int i = 0; i < Enemies.size(); i++) {
		    //System.out.println("check");
		    Enemy e = Enemies.get(i);
		    e.act();
		    //x--;
		}
		for (int i = 0; i < Towers.size(); i++) {
		    //System.out.println("check");
		    Tower t = Towers.get(i);
		    t.act();
		    //x--;
		}
		enemySpawner.act();
		Thread.sleep(100);
	    }
	    catch(InterruptedException ex) {
		System.out.println("gotcha");
		Thread.currentThread().interrupt();
	    }
	     updateBoard();
	    updateInfo();
	    pane.validate();
	}
    }
    
    public void updateBoard() {
        boardBorder.removeAll();
	for (int xcor = 0; xcor < board.getRow(); xcor++) {
	    for (int ycor = 0; ycor < board.getCol(); ycor++) {
		Loc l = board.getLoc(xcor,ycor);
		MappedJPanel jpanel = new MappedJPanel(xcor,ycor);
		jpanel.setBackground(board.getLoc(xcor,ycor).getColor());
		jpanel.addMouseListener(this);
		//addMouseListener(this);
		//JLabel thumb = new JLabel(""+board.getLoc(xcor,ycor).getID());
		JLabel thumb = new JLabel();
		if (l.getActors().size() > 0) {
		    ImageIcon icon = new ImageIcon(enemy);
		    thumb.setIcon(icon);
		}
		if (l.getID() == -2) {
		    ImageIcon icon = new ImageIcon(tower);
		    thumb.setIcon(icon);
		}
		//ImageIcon icon = new ImageIcon(grid);
		//jpanel.setPreferredSize(new Dimension(65,65));	
		//thumb.setIcon(icon);
		jpanel.add(thumb);
		JPanel panel = new JPanel(new GridLayout(1,1,0,0));
		panel.add(jpanel);
		panel.setBorder(BorderFactory.createLineBorder(Color.black,1));
		panel.setBackground(board.getLoc(xcor,ycor).getColor());
		boardBorder.add(panel);
	    }
	}	
    }

    public void updateInfo() {
	info.removeAll();
	info.add(new JLabel("Lives: "+lives));
	info.add(new JLabel("Money: "+money));
    }

    public boolean removeEnemy(Enemy e) {
	return Enemies.remove(e);
    }

    public void addEnemy(Enemy e) {
	Enemies.add(e);
    }

    public int getMoney() {
	return money;
    }

    public int getLives() {
	return lives;
    }

    public void setMoney(int m) {
	money = m;
    }

    public void setLives(int l) {
	lives = l;
    }

    public void fill(int n, int basespeed, int maxspeed, int basereward, int maxreward, int basehp, int maxhp) {
	for (int i = 0; i < n; i++) {
	    int hp = (int)(Math.random()*maxhp) + basehp;
	    int speed = (int)(Math.random()*maxspeed) + basespeed;
	    int reward = (int)(Math.random()*maxreward) + basereward;
	    enemySpawner.add(new Enemy(hp,speed,reward,null));
	}
    }

    public static void main(String[] args){
	Gui g= new Gui(10,10,10);
	g.setVisible(true);
	//System.out.println(g.Enemies.size());
	g.tick();
	//System.out.println(g.Enemies.size());
    }
}
