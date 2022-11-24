import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class PetUserDAO {
	//멤버변수
		private String url = Main.URL; // 주소
		private String uid = Main.UID; // 계정
		private String upw = Main.UPW; // 비밀번호
		
		int catCnt = 0;
		int dogCnt;
		public ArrayList<PetUserVO> selectPetInfo() {
			Scanner sc = new Scanner(System.in);
			ArrayList<PetUserVO> list = new ArrayList<>();
			System.out.println("이름을 입력하세요>");
			String name = sc.nextLine();
			String sql = "select pu.*, (select count(*) from pet p where p.pu_id = pu.pu_id and p_type = '고양이' group by pu.pu_id) as cntc, "
					+ "(select count(*) from pet p where p.pu_id = pu.pu_id and p_type = '강아지' group by pu.pu_id)as cntd "
					+ "from petuser pu where pu_name = ?";
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
				pstmt.setString(1, name);
				// sql 실행
				rs = pstmt.executeQuery();
				while(rs.next()) { // 다음 row가 있다면 true
					
					// 한 행에 대한 처리(getInt, getString, getDouble, getDate, getTimestamp 등등)
					String pu_id = rs.getString("PU_ID");
					String pu_name= rs.getString("PU_NAME"); // 컬럼명
					int pu_age  = rs.getInt("PU_AGE");
					String pu_addr = rs.getString("PU_ADDR"); 
					catCnt = rs.getInt("cntc");
					dogCnt = rs.getInt("cntd");
					// VO는 한번 돌때마다 하나씩 생성되어야함
					// vo에 행 저장
					// vo를 list에 저장
					PetUserVO vo = new PetUserVO(pu_id, pu_name,  pu_age, pu_addr);
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
		
		
		
		


}
