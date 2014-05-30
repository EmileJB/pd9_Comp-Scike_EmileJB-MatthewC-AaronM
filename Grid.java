public class Grid {

    private Loc[][] board;
    private Loc spawnPoint;
    private Loc endPoint;
    private Loc[] path;


    public Grid(int x, int y) {
	board = new Loc[x][y];
    }

    public Loc getLoc(int x, int y) {
	return board[x][y];
    }

    public void setSpawn(int x, int y) {
	spawnPoint = board[x][y];
    }

    public void setEnd(int x, int y) {
	endPoint = board[x][y];
    }

    public void setPath(Loc[] selected) {
	path = selected;
    }

    public int getRow() {
	return board.length;
    }

    public int getCol() {
	return board[0].length;
    }

    public int getSize() {
	return getRow() * getCol();
    } 
}
