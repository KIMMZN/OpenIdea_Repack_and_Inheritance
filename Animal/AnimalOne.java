package Animal;

public class AnimalOne {
	
	public String ZooName = "humanzoo";
	public String AnimalName = null;
	public int age = 0;
	public int weight = 0;
	
	
	
	
	
	@Override
	public String toString() {
		return "AnimalOne [ZooName=" + ZooName + ", AnimalName=" + AnimalName + ", age=" + age + ", weight=" + weight
				+ "]";
	}






	public void feed() {
		
		System.out.println("먹이주기");
	}
	
	
	
	
	
	
	public String getZooName() {
		return ZooName;
	}
	public void setZooName(String zooName) {
		ZooName = zooName;
	}
	public String getAnimalName() {
		return AnimalName;
	}
	public void setAnimalName(String animalName) {
		AnimalName = animalName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
	//public void eat() {
	//	System.out.println("공격점수 100점");
	//}
	

}
