import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class HospitalDAO {
	private String url = Main.URL; // 주소
	private String uid = Main.UID; // 계정
	private String upw = Main.UPW; // 비밀번호
	
	
	
	
	public ArrayList<HospitalVO> selectEx() {

		ArrayList<HospitalVO> list = new ArrayList<>();

		String sql = "select * from hospital";

		// 필요한 변수
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // 결과 저장 객체

		try {
			// 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// conn
			conn = DriverManager.getConnection(url, uid, upw);

			// stmt
			pstmt = conn.prepareStatement(sql);

			// sql구문에 ?가 있으면 채워줘야 함

			// sql 실행
			rs = pstmt.executeQuery();

			while(rs.next()) { // 다음 row가 있다면 true
				
				// 한 행에 대한 처리(getInt, getString, getDouble, getDate, getTimestamp 등등)
				int h_no = rs.getInt("h_no");
				String h_name= rs.getString("h_name"); // 컬럼명
				String h_addr  = rs.getString("h_addr");
				String h_contact = rs.getString("h_contact"); 

				// VO는 한번 돌때마다 하나씩 생성되어야함
				// vo에 행 저장
				// vo를 list에 저장
				HospitalVO vo = new HospitalVO(h_no, h_name, h_addr, h_contact);
				list.add(vo);
				
				
				
			}


		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				System.out.println("close 에러");
			}

		}


		return list;
	}
	
	
	
	 public int insertHospital(int h_no, String h_name, String h_addr, String h_contact) {
	      
	      int result = 0;
	      
	      String sql = "INSERT INTO hospital VALUES(?, ?, ?, ?)";
	      
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      
	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         
	         conn = DriverManager.getConnection(url, uid, upw);
	         
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setInt(1, h_no);
	         pstmt.setString(2, h_name );
	         pstmt.setString(3, h_addr );
	         pstmt.setString(4, h_contact );
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
