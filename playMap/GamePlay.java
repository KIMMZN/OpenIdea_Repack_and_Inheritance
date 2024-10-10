package playMap;

import java.util.ArrayList;

import unit.Seok;
import unit.StarUnit;
import unit.zzan;

public class GamePlay {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//여기서 게임을 진행한다.
		//케릭터 석을 선택함 > 객체를 만든다
		ArrayList<StarUnit> seklist = new ArrayList<>();
		//ArrayList<zzan> zzanlist = new ArrayList<>();
		
		//부모인 StarUnit객체에 저장
		StarUnit s1 = new Seok();
		s1.unitName = "석진";
		s1.hp = 6000;
		s1.attack = 100;
		seklist.add(s1);
		
		//부모를 상속받은 Seok객체에 저장
		Seok s2 = new Seok();
		s2.unitName = "홍길";
		s2.hp = 5500;
		s2.attack = 80;
		seklist.add(s2);
		
		//부모를 상속받은 zzan객체에 저장
		zzan z1 = new zzan();
		z1.unitName = "짜장";
		z1.hp = 7000;
		z1.attack = 120;
		seklist.add(z1);
		
		
		//System.out.println(s1.toString());
		//System.out.println(s2.toString());
		
		for(StarUnit s : seklist) {
			//System.out.println(s.toString());
			s.attack();
		}
	}

}
