public class Status {

    public static final int FROZEN = 1;
    public static final int BURN = 2;



    private int ticks,baseticks;
    private int dur;
    private int mag;
    private int ID;

    public Status (int t, int d, int m, int i) {
	baseticks = ticks = t;
	dur = d;
	mag = m;
	ID = i;
    }
    public String toString(){
	String ret ="";
	if (ID == 1) {
	    ret = "frozen";
	}
	else if (ID == 2) {
	    ret = "burned";
	}
	return ret;
    }
    public int Effect(int i) {
	if (ticks > 0 || ID != i) {
	    ticks--;
	    // System.out.println(ID + " v " + i);
	    return 0;
	}
	else if (dur <= 0) {
	    System.out.println("Check12");
	    return -1;
	}
	else {
	    // System.out.println("Check!!!!");
	    ticks = baseticks;
	    dur--;
	    return ID;
	}
    }
    public int getDur(){
	return dur;
    }
	public int getMagnitude() {
	    return mag;
	}

    public boolean equals(Status s) {
	return this.ID == s.ID;
    }

    public void combine(Status s) {
	if (s.dur > this.dur)
	    this.dur = s.dur; 
	if (this.mag < s.mag) {
	    this.mag = s.mag;
	    this.ticks = s.ticks;
	    this.baseticks = s.baseticks;
	}
    }


}

	  
