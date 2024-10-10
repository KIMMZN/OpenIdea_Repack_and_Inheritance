package ZooLand;

import java.util.ArrayList;

import Animal.AnimalOne;
import Animal.Monkey;
import Animal.Tiger;

public class ZooLand {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		ArrayList<AnimalOne> anilist = new ArrayList<>();
		
		AnimalOne a1 = new Monkey();
		a1.AnimalName = "원숭이";
		a1.age = 3;
		a1.weight = 30;
		anilist.add(a1);
		
		AnimalOne a2 = new Tiger();
		a2.AnimalName = "호돌이";
		a2.age = 1;
		a2.weight = 40;
		anilist.add(a2);
		
		AnimalOne a3 = new Tiger();
		a3.AnimalName = "호순이";
		a3.age = 2;
		a3.weight = 60;
		anilist.add(a3);
		
		
		
		for(AnimalOne ss : anilist) {
			System.out.println();
			ss.feed();
			
		}
		
		for(AnimalOne ss : anilist) {
			System.out.println(ss.toString());
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
