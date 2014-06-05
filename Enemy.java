import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Enemy extends Actor {
    protected int id;
    protected int reward;
    protected int basereward;
    protected int speed;
    protected int basespeed;
    protected int hp;
    protected int basehp;
    // protected Loc location;
    protected Grid board;
    protected int pathPos;
    protected Image img, norm, atkd;
    //protected World world (may be useful to modify things like lives and money)

    public Enemy(int h, int s, int r, Loc loc) {
	super(loc);
	id=0;
	basehp = hp = h;
	basespeed = speed = s;
	basereward = reward = r;
	//location = loc;
	if (location != null)
	board = location.getGrid();
	try {
	    norm = ImageIO.read(new File ( "images/Enemy.gif"));
	    atkd = ImageIO.read(new File ( "images/atkdactor.gif"));
	}catch (IOException ex) {
	    System.out.println("you goofed Dx");
	}
	img = norm;
    }

    public int getHP() {
	return hp;
    }
    public int getSpeed() {
	return speed;
    }

    public int getReward() {
	return reward;
    }

    public Loc getLoc() {
	return location;
    }

    public Grid getGrid() {
	return board;
    }

    public void setHP(int h) {
	hp = h;
    }

    public void setSpeed(int s) {
	speed = s;
    }

    public void setReward(int r) {
	reward = r;
    }

    public void swapLoc(Loc l) {
	Loc tmp = location;
	location = l;
	location.addActor(this);
	tmp.removeActor(this);
	}

    public void setLoc(Loc l) {
	location = l;
	board = l.getGrid();
    }

    public void damage(int d) {
	hp = hp - d;
	img = atkd;
    }

    public void move() {
	if (speed <= 0) {
	    pathPos++;
	    swapLoc(board.getPathLoc(pathPos));
	    speed = basespeed;
		   }
	else
	    speed--;
    }

    public void checkHP() {
	if (hp <= 0)
	    die();
	if (hp > basehp)
	    hp = basehp;
    }

    public void checkPos() {
	if (location.getID() < 0)
	    swapLoc(board.getPathLoc(pathPos));
	if (location.equals(board.getEnd()) && speed == 0)
	    finish();
    }

    public void die() {
	location.removeActor(this);
	Gui g = board.getGui();
	g.removeEnemy(this);
	g.setMoney(g.getMoney()+reward);
    }

    public void finish() {
	System.out.println("win");
	location.removeActor(this);
	Gui g = board.getGui();
	g.removeEnemy(this);
	g.setLives(g.getLives()-1);
    } 
  
    public void act() {
	checkPos(); 
    	checkHP();
	move();
    }
    public void setImg(){
	img = atkd;
    }
    public void resetImg(){
	img= norm;
    }
    public Image getImg(){
	return img;
    }
    public int ID(){
	return id;
    }
}
