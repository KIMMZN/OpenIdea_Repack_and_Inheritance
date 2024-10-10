package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.IdeaDTO;

public class IdeaDAO {
	private String username= "system";
	private String password= "11111111";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String driverName= "oracle.jdbc.driver.OracleDriver";
	private Connection conn = null; // 커넥션 자원 변수
	
	
	public static IdeaDAO ideadao =null;
	
	private IdeaDAO() {
		init();
	}
	
	public static IdeaDAO getInstance() {
		if(ideadao == null) {
			ideadao = new IdeaDAO();
		}
		return ideadao;
	}
	
	private void init() {	// 드라이버 로드	
		try {
			Class.forName(driverName);
			System.out.println("드라이버 로드");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean conn() { // 커넥션 
		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("커넥션 자원 획득");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void insert(IdeaDTO ideadto) {
		//커넥션, 쿼리, 매핑, 
		if(conn()) {		
			try {
				String sql = "insert into ideabank values" +"(ideabank_seq.nextval, ?,?,?, default)";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, ideadto.getTitle());
				psmt.setString(2, ideadto.getContent());
				psmt.setString(3, ideadto.getWriter());
				//쿼리 실행
				int resultInt = psmt.executeUpdate();
				//트랜잭션 처리
				if(resultInt > 0) {
					conn.commit();
				}else {
					conn.rollback();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("데이터베이스 개선실패");
		}
	}
	public ArrayList<IdeaDTO> selectAll() {
		ArrayList<IdeaDTO> ilist = new ArrayList<>();
		if(conn()) {
			try {
				String sql = "select * from ideabank";
				PreparedStatement psmt = conn.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery();
				while(rs.next()) {
					IdeaDTO iTemp = new IdeaDTO();
					iTemp.setTitle(rs.getString("title"));
					iTemp.setNum(rs.getInt("num"));
					iTemp.setContent(rs.getString("content"));
					iTemp.setWriter(rs.getString("writer"));
					iTemp.setIndate(rs.getString("indate"));
					ilist.add(iTemp);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else {
			System.out.println("실패");
		}
		return ilist;
	}
	public void delete(int delId) {
		  if(conn()) {
			  try {
				String sql = "delete from ideabank where num=?";
				  PreparedStatement psmt = conn.prepareStatement(sql);			 
				  	psmt.setInt(1, delId);
					psmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			  
		  }else {
			  System.out.println("실패");
		  }
			
	}
	
	public IdeaDTO selectOne (int findId) {
		//ArrayList<IdeaDTO> idealist = new ArrayList<>();
		if(conn()) {
			try {
				//String sql = "delete from ideadata where ideanum = ?";			
				String sql = "select * from ideabank where num=?";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				//mapping
				pstmt.setInt(1, findId);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {//next()메서드는 rs에서 참조하는 테이블에서
								//튜플을 순차적으로 하나씩 접근하는 메서드
					IdeaDTO iTemp = new IdeaDTO();
					//rs.getString("id") rs가 접근한 튜플에서 id컬럼의 값을 가져옴
					iTemp.setTitle(rs.getString("title"));
					iTemp.setNum(rs.getInt("num"));
					iTemp.setContent(rs.getString("content"));
					iTemp.setWriter(rs.getString("writer"));
					iTemp.setIndate(rs.getString("indate"));
					
					return iTemp;
					//idealist.add(iTemp);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			System.out.println("실패");
		}
		return null;
	}
	public void update(IdeaDTO ideadto) {
		if(conn()) {
			try {
				String sql ="update ideabank set"+" title=?,content=? where num=?";
				//띄어쓰기 중요하다 + " ㄱ " 플러스 다음 한칸 뛰어야됨.
				//String sql = "update ideabank set"+"title=?,content=? where num=?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(2, ideadto.getContent());
				psmt.setString(1, ideadto.getTitle());
				psmt.setInt(3, ideadto.getNum());
				psmt.executeUpdate();
				System.out.println("업데이트 완료");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public ArrayList<IdeaDTO> select(String searchW){
		ArrayList<IdeaDTO> flist = new ArrayList<IdeaDTO>();
		if(conn()) {
			try {
				String sql="select * from ideabank where "+
						"title like '%"+searchW+"%'";
	
				PreparedStatement psmt = conn.prepareStatement(sql);
				ResultSet rs =psmt.executeQuery();
				//Resultset은 테이블 형식으로 가져온다고 이해합니다.
				while(rs.next()) {  //next()메서드는 rs에서 참조하는 테이블에서
					                // 튜플을 순차적으로 하나씩 접근하는 메서드
					IdeaDTO iTemp = new IdeaDTO();
					iTemp.setTitle(rs.getString("title"));
					iTemp.setNum(rs.getInt("num"));
					iTemp.setContent(rs.getString("content"));
					iTemp.setWriter(rs.getString("writer"));
					iTemp.setIndate(rs.getString("indate"));
					flist.add(iTemp);
				}
			} catch (SQLException e) {e.printStackTrace();}
		}		
		return flist;
	}	
	
	

}
