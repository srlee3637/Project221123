import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PetDAO {
	private String url = Main.URL; // 주소
	private String uid = Main.UID; // 계정
	private String upw = Main.UPW; // 비밀번호
	
	public ArrayList<PetVO> selectEx() {

		ArrayList<PetVO> list = new ArrayList<>();

		String sql = "select * from pet";

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
				int p_no = rs.getInt("p_no");
				String p_type= rs.getString("p_type"); // 컬럼명
				String pu_id  = rs.getString("pu_id");

				// VO는 한번 돌때마다 하나씩 생성되어야함
				// vo에 행 저장
				// vo를 list에 저장
				PetVO vo = new PetVO(p_no, p_type, pu_id);
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
