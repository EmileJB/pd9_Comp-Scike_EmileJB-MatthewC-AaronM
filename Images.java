import java.util.*;
import javax.imageio.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Images{
   public static Image enemy() {
	try {
	    Image i = ImageIO.read(new File ( "images/Enemy.gif"));
	    return i;
	}catch (IOException ex) {
	    System.out.println("you dun goofed Dx");
	    return null;
	}
    }
   public static Image potato() {
	try {
	    Image i = ImageIO.read(new File ( "images/potato.gif"));
	    return i;
	}catch (IOException ex) {
	    System.out.println("you dun goofed Dx");
	    return null;
	}
    }
   public static Image caillou() {
	try {
	    Image i = ImageIO.read(new File ( "images/Ciallou.gif"));
	    return i;
	}catch (IOException ex) {
	    System.out.println("you dun goofed Dx");
	    return null;
	}
    }

    public static Image elsa() {
	try {
	    Image i = ImageIO.read(new File ("images/Elsa.gif"));
	    return i;
	}
	catch (IOException e) {
	    return null;
	}
    }

    public static Image ditto() {
	try {
	    Image i = ImageIO.read(new File("images/ditto.gif"));
	    return i;
	}
	catch (IOException e) {
	    return null;
	}
    }

   public static Image moneyTree() {
	try {
	    Image i = ImageIO.read(new File ("images/moneytree.gif"));
	    return i;
	}
	catch (IOException e) {
	    return null;
	}
   }
    public static Image fang(){
	try {
	    Image i = ImageIO.read(new File ("images/fang.gif"));
	    return i;
	}catch (IOException e){
	    return null;
	}
    }

    public static ImageIcon coin() {
        ImageIcon i = new ImageIcon(MappedJPanel.class.getClass().getResource("coin.gif"));
	return i;
    }
}
