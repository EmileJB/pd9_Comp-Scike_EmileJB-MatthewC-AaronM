public class World {

    private Loc[][] grid
    private int money;
    private int lives;
    // private Spawner enemyspawner; idk
    //private something represents Gui maybe

    public World(int x, int y) {
	grid = new Loc[x][y];
	money = 1000;
	lives = 100;
    }

}
