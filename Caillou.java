public class Caillou extends Tower {
	
    public Caillou() {
	super(null,20,6,3,0,50,Images.caillou());
	id = 4;
	maxUpgrades = new int[]{3,3,2,0};
	upgradePrices[0] = new int[]{50,100,200};
	upgradePrices[1] = new int[]{50,100,200};
	upgradePrices[2] = new int[]{50,100,0};
    }
}
