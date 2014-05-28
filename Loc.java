import java.awt.*;

public class Loc{
    private Object thing;

    public Loc(){
	thing = null;
    }
    public void select(Object s){
	thing=s;
    }
    public String toString(){
	return thing.toString();
    }
}
