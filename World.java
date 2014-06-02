public class World {

    private Loc[][] grid;
    private int money;
    private int lives;
    // private Spawner enemyspawner; idk
    //private something represents Gui maybe

    public World(int x, int y) {
	grid = new Loc[x][y];
	money = 1000;
	lives = 100;
    }

    public static void main(String[] args){
	Gui g= new Gui(10,10,10);
	g.setVisible(true);
    }
}
