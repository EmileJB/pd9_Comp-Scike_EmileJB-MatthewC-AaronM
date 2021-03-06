public abstract class Actor {

    protected Loc location;

    public Actor(Loc loc) {
	location = loc;
    }

    public abstract void act();
    public abstract int ID();
}
