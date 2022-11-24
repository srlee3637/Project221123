import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookMark_ListDAO  {


	private String url = Main.URL; // 주소
	private String uid = Main.UID; // 계정
	private String upw = Main.UPW; // 비밀번호
	
	String pu_name;

	public ArrayList<BookMark_ListVO> selectEx() {

		ArrayList<BookMark_ListVO> list = new ArrayList<>();

		String sql = "select * from bookmark_list";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;


		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, uid, upw);

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				String b_no = rs.getString("b_no");
				int h_no = rs.getInt("h_no");
				String pu_id = rs.getString("pu_id");

				BookMark_ListVO vo = new BookMark_ListVO(b_no, h_no, pu_id);
				list.add(vo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}



		return list;
	}

	public ArrayList<BookMark_ListVO> selectPetUserBookMark() {

		ArrayList<BookMark_ListVO> blList = new ArrayList<>();
		
		Scanner sc = new Scanner(System.in);
		System.out.print("이름 입력> ");
		String id = sc.nextLine();

		String sql = "select * from petuser pu left join bookmark_list bl on bl.pu_id = pu.pu_id left join hospital h on bl.h_no = h.h_no where pu.pu_name = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, uid, upw);

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				String h_name = rs.getString("h_name");
				pu_name = rs.getString("pu_name");
			
				String b_no = rs.getString("b_no");
				int h_no = rs.getInt("h_no");
				String pu_id = rs.getString("pu_id");
				String h_addr = rs.getString("h_addr");
				String h_contact = rs.getString("h_contact");

				BookMark_ListVO vo = new BookMark_ListVO(b_no, h_no, pu_id);
				vo.setH_name(h_name);
				blList.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return blList;
	}
	
	public int insertBookMark(String b_no, int h_no, String pu_id) {
	      
	      int result = 0;
	      
	      String sql = "INSERT INTO bookmark_list VALUES(?, ?, ?)";
	      
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      
	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         
	         conn = DriverManager.getConnection(url, uid, upw);
	         
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1, b_no);
	         pstmt.setInt(2, h_no );
	         pstmt.setString(3, pu_id );
	         result = pstmt.executeUpdate(); //매개변수가 없어야 함, 성공 실패 결과를 반환함
	         
	         if(result == 1) {
	            System.out.println("성공");
	         }else {
	            System.out.println("실패");
	         }
	         

	      } catch(Exception e) {
	         e.printStackTrace();
	      } finally {
	         
	         try {
	            conn.close();
	            pstmt.close();
	         } catch (Exception e2) {
	            System.out.println("close에러");
	         }
	      }
	      return result;
	   }


}
