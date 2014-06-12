import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Gui extends JFrame implements ActionListener, MouseListener {
    private Image caillou,potato,enemy,elsa,ditto,moneyTree,fang;
    private Container pane;
    private JButton resetButton, jb0, jb1, jb2, jb3, jb4, jb5, jb6;
    private Grid board;
    private JPanel boardBorder,info,towerShop; //main JPanels
    private JPanel towerInfo, availableTowers; //subJPanels
    private int pathNumber;
    private ArrayList<Enemy> Enemies;
    private ArrayList<Tower> Towers;
    private ArrayList<Projectile> Projectiles;
    private int[][] path = {{0,3},{1,3},{2,3},{3,3},{4,3},{4,4},{5,4},{6,4},{7,4},{8,4},{9,4}};
    private int money;
    private int lives;
    private static int numthings;
    private Spawner enemySpawner;
    private Tower currentTower;
    private boolean addTowerMode;
    private int counter = 0;
    

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
	if (e.getSource() == jb0) {
	    currentTower = new Caillou();//new Tower(null,20,6,3,0,50);//loc, damage, rate,range, numbtargets, ID
	}
	else if (e.getSource() == jb1) {
	    currentTower = new Potato();//new Tower(null,3,1,1,0,100);
	} 

	else if (e.getSource() == jb2) {
	    currentTower = new Elsa();
	}

	else if (e.getSource() == jb3) {
	    currentTower = new Shapeshifter();
	}

	else if (e.getSource() == jb4) {
	    currentTower = new Moneytree();
	}
	else if (e.getSource() == jb5) {
	    currentTower = new Fang();
	}
	else if (e.getSource() == jb6) {
	    currentTower = new Dragon();
	}
	else {
	    currentTower = new Caillou();
	}
	    
	addTowerMode = true;
	towerInfo = currentTower.getJPanel();
	updateTowerShop();
    }
    
    public Gui(int x, int y, int r) {
	potato = Images.potato();
	caillou = Images.caillou();
	enemy = Images.enemy();
	elsa = Images.elsa();
	ditto = Images.ditto();
	moneyTree = Images.moneyTree();
	fang = Images.fang();
	Loc[] pathSent = new Loc[path.length];
	board = new Grid(x,y,this);
	Enemies = new ArrayList<Enemy>();
	Towers = new ArrayList<Tower>();
	Projectiles = new ArrayList<Projectile>();
	lives = 100;
	money = 1000;
	info = new JPanel(new GridLayout());
	info.add(new JLabel("Lives: "+lives));
	info.add(new JLabel("Money: "+money));
	boardBorder=new JPanel(new GridLayout(x,y));
	towerShop = new JPanel(new BorderLayout(0,6));
	towerInfo = new JPanel();
	availableTowers = new JPanel(new GridLayout(5,2,2,2));
	//towerInfo.setPreferredSize(new Dimension(100,100));
	towerShop.add(towerInfo,BorderLayout.PAGE_START);
	towerShop.add(availableTowers,BorderLayout.CENTER);
	this.setTitle("xD");
	this.setSize(800,750);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	pane = this.getContentPane();
	pane.setBackground(Color.white);
	pane.setLayout(new BorderLayout());
	pane.add(boardBorder,BorderLayout.CENTER);
	pane.add(info,BorderLayout.PAGE_START);
	pane.add(towerShop,BorderLayout.LINE_END);
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
;
		//boardBorder.add(jpanel);
		JPanel panel = new JPanel(new GridLayout(1,1));
		panel.add(thumb);
		panel.setBackground(board.getLoc(xcor,ycor).getColor());
		panel.setBorder(BorderFactory.createLineBorder(Color.black,1));
		panel.add(jpanel);
		boardBorder.add(panel);
	    }
	}
	updateTowerShop();

	//addMouseListener(this);
	board.setPath(pathSent);
	enemySpawner = new Spawner(r, board.getSpawn(), this);
	//	fill(300,5,5,10,0,65,65);
	pane.validate();
    }
    //mouselistener stuff
    public void mousePressed(MouseEvent e) {//needs some way of finding the jpanel's coordinates
	if (addTowerMode) {
	    MappedJPanel jpanel = (MappedJPanel)e.getSource();
	if (board.getLoc(jpanel.getX(),jpanel.getY()).getID() == -1) { 
	    if (money >= currentTower.getPrice()) {
		int ID = -2;
		/*	if (currentTower.getPrice() == 50)
		    ID = -2;
		else if (currentTower.getPrice() == 100)
		    ID = -3;
		else if (currentTower.getPrice() == 120)
		    ID = -4;
		else if (currentTower.getPrice() == 500)
		    ID = -6; */
		Loc l = new Loc(jpanel.getX(), jpanel.getY(), ID, board,Color.WHITE);
		l.addActor(currentTower);//loc, damage,rate,range,numtargets,price
		currentTower.setLoc(l);
		currentTower.setTargets();
		Towers.add(currentTower);
		board.setLoc(jpanel.getX(),jpanel.getY(),l);
		money -= currentTower.getPrice();		
	    }
	}
	currentTower = null;
	addTowerMode = false;
	towerInfo = new JPanel();
	updateBoard();
	updateTowerShop();
	}
    }

    public void mouseReleased(MouseEvent e) {
	//System.out.println("moo");
	//	System.out.println(Enemies);
    }
    
    public void mouseEntered(MouseEvent e) {
	MappedJPanel jpanel = (MappedJPanel)e.getSource();
	Loc l = board.getLoc(jpanel.getX(),jpanel.getY()); 
	if (l.getID() >= 0){
	    System.out.println(l.getActors());
	    if (l.getActors().size()>0 && l.getActors().get(0).ID()==0){
		Enemy q = (Enemy) (board.getLoc(jpanel.getX(),jpanel.getY()).getActors().get(0));
		System.out.println(""+q.getHP() + " " + q.getStatus());
		
		
	    }
	}
	
    }

   
    public void mouseExited(MouseEvent e) {
	//System.out.println("moo");
    }

    public void mouseClicked(MouseEvent e) {
	//System.out.println("moo");
	System.out.println(Enemies);
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
		    
		    
		    //updateBoard();
		    // Thread.sleep(10);
		    //x--;
		}
	
		for (int i = 0; i < Projectiles.size(); i++) {
		    //System.out.println("check");
		    Projectile t = Projectiles.get(i);
		    t.act();
		    
		    
		    //updateBoard();
		    // Thread.sleep(10);
		    //x--;
		}
		for (int i = 0; i < Enemies.size(); i++){
		    Enemy e = Enemies.get(i);
		    e.checkHP();
		    
		    }
		
		   
		enemySpawner.act();
		Thread.sleep(17);
	    }
	    catch(InterruptedException ex) {
		System.out.println("gotcha");
		Thread.currentThread().interrupt();
	    }
		updateBoard();
		updateInfo();
		pane.validate();
		try {
		Thread.sleep(100);
		}catch(InterruptedException ex) {
		System.out.println("gotcha");
		Thread.currentThread().interrupt();
	    }
		for (int i = 0; i < Enemies.size(); i++){
		    Enemy e = Enemies.get(i);
		    e.resetImg();

		}
	
		counter++;		
		fill(1,5,5,6 + counter/150,3 + counter/75,30 + 3*counter + counter*counter/10000,15 + 3*counter/2 + counter*counter/20000);

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
		Tower t = l.getTower();
		if (t != null) {
		    /*if (t.ID() == 4) {
			ImageIcon icon = new ImageIcon(caillou);
			thumb.setIcon(icon);
		    }
		    if (t.ID() == 5) {
			ImageIcon icon = new ImageIcon(potato);
			thumb.setIcon(icon);
		    }
		    if (t.ID() == 6) {
			ImageIcon icon = new ImageIcon(elsa);
			thumb.setIcon(icon);
		    }
		    if (t.ID() == 7) {
			ImageIcon icon = new ImageIcon(ditto);
			thumb.setIcon(icon);
		    }
		    if (t.ID() == 8) {
			ImageIcon icon = new ImageIcon(moneyTree);
			thumb.setIcon(icon);
		    }
		    if (t.ID() == 9) {
			ImageIcon icon = new ImageIcon(fang);
			thumb.setIcon(icon);
		    }*/	
		    ImageIcon icon = new ImageIcon(t.norm);
		    thumb.setIcon(icon);
		    
		}
		JLabel proj = new JLabel();
		if (l.getActors().size() > 0 ){
		    // System.out.println("here1");
		    for (int q =0; q < l.getActors().size();q++){
			//System.out.println("here2");

			if (l.getActors().get(q).ID()==0) {
			    Enemy en = (Enemy) l.getActors().get(q);
			    if (thumb.getIcon() == null) {
				ImageIcon icon = new ImageIcon(en.getImg());
				thumb.setIcon(icon);
			    }
			    else {
				ImageIcon icon1 = (ImageIcon)thumb.getIcon();
				int w = icon1.getIconWidth();
				int h = icon1.getIconHeight();
				BufferedImage combined = new BufferedImage (w, h, BufferedImage.TYPE_INT_ARGB);
				Graphics g = combined.getGraphics();
				g.drawImage(icon1.getImage(), 0, 0, null);
				g.drawImage(en.getImg(), 0, 0, null);
				thumb.setIcon (new ImageIcon(combined));
			    }
			}

			else if (l.getActors().get(q).ID() ==3){
			    // System.out.println("here3");			    
			    Projectile p = (Projectile) l.getActors().get(q);
			    if (thumb.getIcon() == null) {
				ImageIcon icon = new ImageIcon(p.getImg());
				thumb.setIcon(icon);
			    }
			    else {
				ImageIcon icon1 = (ImageIcon)thumb.getIcon();
				int w = icon1.getIconWidth();
				int h = icon1.getIconHeight();
				BufferedImage combined = new BufferedImage (w, h, BufferedImage.TYPE_INT_ARGB);
				Graphics g = combined.getGraphics();
				g.drawImage(icon1.getImage(), 0, 0, null);
				g.drawImage(p.getImg(), 0, 0, null);
				thumb.setIcon (new ImageIcon(combined));
			    }
			}

		    }
		}
		//ImageIcon icon = new ImageIcon(grid);
		//jpanel.setPreferredSize(new Dimension(65,65));	
		//thumb.setIcon(icon);
		jpanel.add(thumb);
		jpanel.add(proj);
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

    public void updateTowerShop() {
       	towerShop.removeAll();
  	towerShop.add(towerInfo,BorderLayout.NORTH);
	availableTowers = new JPanel(new GridLayout(5,2,2,2));
	ImageIcon icon0 = new ImageIcon(caillou);
	ImageIcon icon1 = new ImageIcon(potato);
	ImageIcon icon2 = new ImageIcon(elsa);
	ImageIcon icon3 = new ImageIcon(ditto);
	ImageIcon icon4 = new ImageIcon(moneyTree);
	ImageIcon icon5 = new ImageIcon(fang);
	for (int i= 0; i < 10; i++) {
	    if(i == 0) {
		jb0 = new JButton(icon0);
		jb0.addActionListener(this);
		jb0.setToolTipText("Caillou");
		jb0.setMnemonic(48+i);
		availableTowers.add(jb0);
	    }
	    else if (i == 1) {
		jb1 = new JButton(icon1);
		jb1.addActionListener(this);
		jb1.setToolTipText("Potato");
		jb1.setMnemonic(48+i);
		availableTowers.add(jb1);
	    }
	    else if (i == 2) {
		jb2 = new JButton(icon2);
		jb2.addActionListener(this);
		jb2.setToolTipText("Elsa");
		jb2.setMnemonic(48+i);
		availableTowers.add(jb2);
	    }
	    else if (i == 3) {
		jb3 = new JButton(icon3);
		jb3.addActionListener(this);
		jb3.setToolTipText("Shapeshifter");
		jb3.setMnemonic(48+i);
		availableTowers.add(jb3);
	    }
	    else if (i == 4) {
		jb4 = new JButton(icon4);
		jb4.addActionListener(this);
		jb4.setToolTipText("Money Tree");
		jb4.setMnemonic(48+i);
		availableTowers.add(jb4);
	    }
	    else if (i == 5) {
		jb5 = new JButton(icon5);
		jb5.addActionListener(this);
		jb5.setToolTipText("Fang the Sniper");
		jb5.setMnemonic(48+i);
		availableTowers.add(jb5);
	    }
	    else if (i == 6) {
		jb6 = new JButton(icon0);
		jb6.addActionListener(this);
		jb6.setToolTipText("Dragon");
		jb6.setMnemonic(48+i);
		availableTowers.add(jb6);
	    }
	    else {
		JButton jb = new JButton(icon0);
		jb.addActionListener(this);
		jb.setToolTipText("Caillou");
		jb.setMnemonic(48+i);
		availableTowers.add(jb);
	    }
	}
	towerShop.add(availableTowers,BorderLayout.CENTER);
    }


    public boolean removeEnemy(Enemy e) {
	return Enemies.remove(e);
    }
    public void addProjectile(Projectile p){
	Projectiles.add(p);
    }
    public boolean removeProjectile(Projectile p){
	return Projectiles.remove(p);
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

    public boolean isActive(Enemy e) {
	return Enemies.contains(e);
    }

    public boolean isActive(Tower t) {
	return Towers.contains(t);
    }

    public boolean isActive(Projectile p) {
	return Projectiles.contains(p);
    }

    public void fill(int n, int basespeed, int maxspeed, int basereward, int maxreward, int basehp, int maxhp) {
	int g;
	Random r = new Random();
	for (int i = 0; i < n; i++) {
	    int hp = (int)(Math.random()*maxhp) + basehp;
	    int speed = (int)(Math.random()*maxspeed) + basespeed;
	    int reward = (int)(Math.random()*maxreward) + basereward;
	    if ( counter < 100 ) {
		enemySpawner.add(new Enemy(hp, speed, reward, null));
	    }					
	    else {
		g = r.nextInt(2);
		if (g==0){
		    enemySpawner.add(new Enemy(hp,speed,reward,null));
		}
		else{
		    enemySpawner.add(new Bug(hp,speed,reward,null));
		}
	    }
	}
    }
    public static void main(String[] args){
	Gui g= new Gui(10,10,8);
	g.setVisible(true);
	//System.out.println(g.Enemies.size());
	g.tick();
	//System.out.println(g.Enemies.size());
    }
}
