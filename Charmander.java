
public class Charmander extends Character implements Boostable {
	public static final int CONST_POWER;
	public static final int CONST_HEAL_AMOUNT;

	public Charmander() {
		super("Charmander", CONST_POWER, CONST_HEAL_AMOUNT, true);
	}

	static {
		CONST_POWER = (int) (Math.random() * 10 + 8);
		CONST_HEAL_AMOUNT = (int) (Math.random() * 10 + 7);
	}

	@Override
	public void boost() {		
		if(hasBoost) {
			this.setPower((int) (this.getPower() * 1.8));
		}
	}

	@Override
	public void special() {
		this.setPower((int) (this.getPower() * 1.6));

	}
}


