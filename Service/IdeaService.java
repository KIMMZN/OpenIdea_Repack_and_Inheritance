package Service;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.IdeaDAO;
import DTO.IdeaDTO;

//클라이언트에게 서비스를 제공하는 기능(요청 > 데이터베이스 작업 > 클라이언트 응답)
//역할 : 요청처리, Crud작업 요청, 응답화면
public class IdeaService {
	//ideaDAO 클래스를 싱글톤으로 디자인.
	//IDEASERVEI에서는 개체를 만들지 않고 
	
	//IdeaDAO ideadao = IdeaDAO.getInstance(); // 한번에 instance의 주소 전달
	IdeaDAO ideadao = null; //일단 주소를 null로 하고 init()에서 instance()의주소값 저장


	//ideadoa = IdeaDAO.GE;
	public IdeaService() {
		init();
		menu();
		
	}
	
	private void init() { //init;
		if(ideadao == null) {
			ideadao = ideadao.getInstance();
		}
	}
	
	
	private void menu() {
		Scanner in = new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			System.out.println("1.등록 2.삭제 3.수정 4.전체보기 5.검색 6.종료");
			int selnum = in.nextInt();
			in.nextLine();
			switch(selnum) {
				case 1: ideaAdd(); break;
				case 2: ideaDel(); break;
				case 3: ideaMod(); break;
				case 4: ideaList(); break;
				case 5: ideaSearch(); break;
				case 6:	flag = false; break;
			}
		}
	}
	
	private void ideaAdd() {
		System.out.println("신규 아이디어 등록 하세요");
		Scanner in = new Scanner(System.in);
		
		
		System.out.println("제목 입력하세요");
		String title = in.nextLine();

		System.out.println("내용 입력하세요");
		String content = in.nextLine();
		System.out.println("작성자입력 <작성자는 안돼요>");
		String writer = in.nextLine();
		IdeaDTO ideadto = new IdeaDTO();
		ideadto.setTitle(title);
		ideadto.setContent(content);
		ideadto.setWriter(writer);
		//dao 객체에게 ideadto객체의 주소를 전달
		
		ideadao.insert(ideadto);
		
		
	}
	
	private void ideaDel() {
		//번호와 제목을 보여준다..
		Scanner in = new Scanner(System.in);
		ideaTitleList();
		//삭제할 번호 선택
		System.out.println("삭제할 번호를 선택하시오");
		int delno = in.nextInt();
		in.nextLine();
		//디비에서 삭제
		ideadao.delete(delno);
		
	}
	private void ideaMod() {
		Scanner in = new Scanner(System.in);
		// 번호와 제목 보여주기
		ideaTitleList();
		//수정번호선택
		System.out.println("수정할 번호를 입력하시오");
		int modnum = in.nextInt();
		in.nextLine();
		//수정할 정보 가져오기
		IdeaDTO moddto = ideadao.selectOne(modnum);
		System.out.println("현재 정보");
		//System.out.println(moddto.toString());
		//수정할 데이터 입력하기
		System.out.println("제목수정하세요");
		String title = in.nextLine();
		moddto.setTitle(title);
		//디비에 적용하기
		ideadao.update(moddto);
			
	}
	private void ideaList() { //전체보기
		ArrayList<IdeaDTO> idealist = ideadao.selectAll();
		for(IdeaDTO t : idealist) {
			System.out.println(t.toString());
		}
		
	}
	private void ideaSearch() {
		Scanner in = new Scanner(System.in);
		System.out.println("검색어를 입력 하세요");
		String sw = in.nextLine();
		ArrayList<IdeaDTO> idealist = ideadao.select(sw);
		for(IdeaDTO t : idealist) {
			System.out.println(t.toString());
		}
		
	}
	private void ideaTitleList() { // 숫자 타이틀
		ArrayList<IdeaDTO> idealist = ideadao.selectAll();
		for(IdeaDTO t : idealist) {
			System.out.println(t.getNum() + " : "+t.getTitle());
		}
			
	}
	

}
