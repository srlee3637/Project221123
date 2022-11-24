
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class AppointmentDAO {
	//멤버변수
	private String url = Main.URL; // 주소
	private String uid = Main.UID; // 계정
	private String upw = Main.UPW; // 비밀번호
	static String h_name; // 컬럼명
	
	String pu_name = "";

	public ArrayList<AppointmentVO> selectInfo() {
		
		ArrayList<AppointmentVO> list = new ArrayList<>();
		System.out.println("예약자 명을 입력하세요>");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		String sql = "select * from petuser pu "
				+ "left join appointment_list a on pu.pu_id = a.pu_id "
				+ "left join hospital h on a.h_no = h.h_no "
				+ "where pu.pu_name = ?";

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
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			

			while(rs.next()) { // 다음 row가 있다면 true

				// 한 행에 대한 처리(getInt, getString, getDouble, getDate, getTimestamp 등등)
				String a_no = rs.getString("A_NO");
				String a_date= rs.getString("A_DATE"); // 컬럼명
				String pu_id  = rs.getString("PU_ID");
				int h_no = rs.getInt("H_NO"); 
				
				pu_name= rs.getString("PU_NAME");
				String h_name= rs.getString("h_name"); // 컬럼명
				
				// VO는 한번 돌때마다 하나씩 생성되어야함
				// vo에 행 저장
				// vo를 list에 저장
				AppointmentVO vo = new AppointmentVO(a_no, a_date, pu_id, h_no);
				list.add(vo);
				vo.setH_name(h_name);
				


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