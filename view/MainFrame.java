package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.IdeaDAO;
import DTO.IdeaDTO;

//implements 어떤 기능을 구현하기 위해서 사용한다. (actionlister라는 기능을 구현 받앗다?)
public class MainFrame extends JFrame implements ActionListener {
									  //인터페이스 구현 ㅡ 기능을 처리 하기 위해
	
	private JLabel title = new JLabel("IdeaBank");
	private JTextField input = new JTextField();
	private JButton btn = new JButton("Save");
	private List wordList = new List(5,false);
	private JButton btn1 = new JButton("East");
	private JButton btn2 = new JButton("West");
	private JPanel centerP = new JPanel();
	//panel은 컨테이너이면서 컴포넌트이다. .. 기본 레이아웃이 flowLayout
	
	//private IdeaService = new
	private IdeaDAO ideaDao = IdeaDAO.getInstance();
	
	
	public MainFrame () {
		this.setBounds(100,100,300,500);
		// 컨테이너는 컴포넌트를 배치시킨다. 컨테이너는 레이아웃이 있다.
		// JFrame은 컨테이너이고, 기본 레이아웃은 border layout이다.
		// border layout은 하나의 공간에 하나의 컴포넌트만 가능하다.
		this.setVisible(true);
		// 한글을 위한 폰트 설정
		
		//위의 멤버변수를 어디에 배치할것인가
		this.add(title,"North");
		this.add(btn, "South");
		// 가운데 패널
		centerP.setLayout(new BorderLayout());
		centerP.setBackground(Color.red);
		centerP.add(wordList, "Center");
		centerP.add(input, "South");
		this.add(centerP, "Center");
		//this.add(btn1, "East");
		//this.add(btn2, "West");
		
		//리스너 등록
		btn.addActionListener(this);
		input.addActionListener(this);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		loadDB();
		
	}
	
	private void loadDB() {
		ArrayList<IdeaDTO> ideadto = ideaDao.selectAll();
		for(IdeaDTO i : ideadto) {
			wordList.add(i.toString());
		}
		
	}

	@Override // 메서드 재정의
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btn || e.getSource() == input) {
			//System.out.println("버튼이 클릭 됨");
			String t = input.getText();
			//System.out.println("입력하신 글은 : " +t);
			input.setText("");
		//	wordList.add(t);
			
			IdeaDTO dto = new IdeaDTO();
			dto.setTitle(t);
			ideaDao.insert(dto);
		}
	}

}
