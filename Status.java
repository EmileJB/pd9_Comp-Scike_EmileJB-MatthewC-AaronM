public class Status {

    public static final int FROZEN = 1;



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
	return ret;
    }
    public int Effect(int i) {
	if (ticks != 0 || ID != i) {
	    ticks--;
	    return 0;
	}
	else if (dur <= 0) 
	    return -1;
	else {
	    ticks = baseticks;
	    dur--;
	    return ID;
	}
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

	  