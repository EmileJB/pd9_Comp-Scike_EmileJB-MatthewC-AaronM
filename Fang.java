public class Fang extends Tower {

    public Fang() {
	super(null,10000,35,10,0,100,Images.fang());
	id = 9;
	maxUpgrades = new int[]{3,3,0,0};
	upgradePrices[1] = new int[]{200,400,600};
    }
}
