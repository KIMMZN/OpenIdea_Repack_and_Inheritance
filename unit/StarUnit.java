package unit;

//super class
public class StarUnit {
	public int hp = 100;
	public int attack=10;
	public String unitName = null;
	
	public void attack() {
		System.out.println("공격점수 100점");
	}
	

	@Override
	public String toString() {
		return "StarUnit [hp=" + hp + ", attack=" + attack + ", unitName=" + unitName + "]";
	}


	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
}
