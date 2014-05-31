public class Grid {

    private Loc[][] board;
    private Loc spawnPoint;
    private Loc endPoint;
    private Loc[] path;
    private Gui gui;


    public Grid(int x, int y, Gui g) {
	board = new Loc[x][y];
	gui = g;
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
	spawnPoint = selected[0];
	endPoint = selected[selected.length-1];
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

    public Loc getSpawn() {
	return spawnPoint;
    }

    public Loc getEnd() {
	return endPoint;
    }

    public void setLoc(int x, int y, Loc loc) {
	board[x][y] = loc;
    }

    public void placeActor(int x, int y, Actor a) {
	board[x][y].addActor(a);
    }

    public Loc getPathLoc(int i) {
	if (i >=  path.length) 
	    return endPoint;
	else
	    return path[i];
    }

    public Gui getGui() {
	return gui;
    }
}
