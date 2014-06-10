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
    protected Image img,atkd,norm;
    protected Image normn, norme,normw,norms;
    protected Image atkdn, atkde,atkdw,atkds;
    protected ArrayList<Status> status;
    //protected World world (may be useful to modify things like lives and money)

    public Enemy(int h, int s, int r, Loc loc) {
	super(loc);
	id=0;
	basehp = hp = h;
	basespeed = speed = s;
	basereward = reward = r;
	status = new ArrayList<Status>();
	//location = loc;
	if (location != null)
	board = location.getGrid();
	try {
	    normn = ImageIO.read(new File ( "images/Enemy.gif"));
	    atkdn = ImageIO.read(new File ( "images/atkdactor.gif"));
	    norme=normn;
	    normw=normn;
	    norms=normn;
	    atkds=atkdn;
	    atkdw=atkdn;
	    atkde=atkdn;
	    
       
	}catch (IOException ex) {
	    System.out.println("you goofed Dx");
	}
	norm=norms;
	atkd=atkds;
	img = norms;
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
	if (!checkStatus(Status.FROZEN)) {
	    if (speed <= 0) {
		pathPos++;
		Loc l = board.getPathLoc(pathPos);
		
		//System.out.println( this +": X's- "+ location.getX() + l.getX() + " & Y's:  " +location.getY() + l.getY());
		if (location.getX()==l.getX() && location.getY() < l.getY()){
		    //		System.out.println("5");

		    setNorm(norme);
		    setAtkd(atkde);
		}
		else if (location.getX()==l.getX() && location.getY() > l.getY()){
		    //System.out.println("6");		

		    setNorm(normw);
		    setAtkd(atkdw);
		}
		else if (location.getX() <l.getX() && location.getY()== l.getY()){
		    //System.out.println("7");		

		    setNorm(norms);
		    setAtkd(atkds);
		}
		else if(location.getX() >l.getX() && location.getY() == l.getY()){
		    
		//		System.out.println("8");
		    setNorm(normn);
		    setAtkd(atkdn);
		}
	    resetImg();
	    swapLoc(board.getPathLoc(pathPos));
	    speed = basespeed;
		   }
	else
	    speed--;
	}
    }

    public void checkHP() {
	if (hp <= 0)
	    die();
	if (hp > basehp)
	    hp = basehp;
    }

    public boolean checkStatus(int i) {
	for (Status s:status) {
	    if (s.Effect(i) == i)
		return true;
	}
	return false;
    }

    public void addStatus(Status stat) {
	for (Status s:status) {
	    if (s.equals(stat))
		s.combine(stat);
	    return;
	}
	status.add(stat);
    }
    public ArrayList<Status> getStatus(){
	return status;
    }

    public void checkPos() {
	if (location.getID() < 0)
	    swapLoc(board.getPathLoc(pathPos));
	if (location.equals(board.getEnd()) && speed == 0)
	    finish();
    }

    public void die() {
	Gui g = board.getGui();
	g.removeEnemy(this);
	board.getLoc(location.getX(), location.getY()).removeActor(this);
	g.setMoney(g.getMoney()+reward);
	location.removeActor(this);
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
    public void setNorm(Image i){
	norm = i;
    }
    public void setNormn(Image i){
	normn = i;
    }
    public void setNorms(Image i){
	norms = i;
    }
    public void setNormw(Image i){
	normw = i;
    }
    public void setNorme(Image i){
	norme = i;
    }
    public void setAtkd(Image i){
	atkd=i;
    }
    public void setAtkdn(Image i){
	atkdn=i;
    }
    public void setAtkds(Image i){
	atkds=i;
    }
    public void setAtkde(Image i){
	atkde=i;
    }
    public void setAtkdw(Image i){
	atkdw=i;
    }
    

    public int ID(){
	return id;
    }
}
