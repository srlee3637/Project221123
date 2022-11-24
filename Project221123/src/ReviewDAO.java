import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReviewDAO {
	private String url = Main.URL; // 주소
	private String uid = Main.UID; // 계정
	private String upw = Main.UPW; // 비밀번호
	
	String pu_name;



	public ArrayList<ReviewVO> selectEx() {

		ArrayList<ReviewVO> list = new ArrayList<>();

		String sql = "select * from review";

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
				String r_no = rs.getString("r_no");
				String r_title = rs.getString("r_title");
				String r_content = rs.getString("r_content");
				int r_rate = rs.getInt("r_rate");
				int h_no = rs.getInt("h_no");
				String pu_id = rs.getString("pu_id");

				// VO는 한번 돌때마다 하나씩 생성되어야함
				// vo에 행 저장
				// vo를 list에 저장
				ReviewVO vo = new ReviewVO(r_no, r_title, r_content, r_rate, h_no, pu_id);
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

	public ArrayList<ReviewVO> selectHospitalReviewInfo() {

		ArrayList<ReviewVO> list = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		System.out.println("병원번호를 입력하세요>");
		int num  = sc.nextInt();
		String sql = "select * from hospital h left join review r  on r.h_no = h.h_no where h.h_no = ?";

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
			pstmt.setInt(1, num);

			// sql구문에 ?가 있으면 채워줘야 함

			// sql 실행
			rs = pstmt.executeQuery();

			while(rs.next()) { // 다음 row가 있다면 true

				// 한 행에 대한 처리(getInt, getString, getDouble, getDate, getTimestamp 등등)
				String r_no = rs.getString("r_no");
				String r_title = rs.getString("r_title");
				String r_content = rs.getString("r_content");
				int r_rate = rs.getInt("r_rate");
				int h_no = rs.getInt("h_no");
				String pu_id = rs.getString("pu_id");

				String h_name= rs.getString("h_name"); // 컬럼명
				String h_addr  = rs.getString("h_addr");
				String h_contact = rs.getString("h_contact");

				// VO는 한번 돌때마다 하나씩 생성되어야함
				// vo에 행 저장
				// vo를 list에 저장
				ReviewVO vo = new ReviewVO(r_no, r_title, r_content, r_rate, h_no, pu_id);
				list.add(vo);
				vo.setH_name(h_name);
				vo.setH_addr(h_addr);
				vo.setH_contact(h_contact);


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


	public ArrayList<ReviewVO> selectPetUserReviewInfo() {
		Scanner sc = new Scanner(System.in);
		ArrayList<ReviewVO> list = new ArrayList<>();		
		System.out.println("이름을 입력하세요>");
		String name = sc.nextLine();
		String sql = "select * from petuser p left join review r on p.pu_id = r.pu_id left join hospital h on r.h_no = h.h_no where p.pu_name = ?";
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
				String r_no = rs.getString("r_no");
				String r_title = rs.getString("r_title");
				String r_content = rs.getString("r_content");
				int r_rate = rs.getInt("r_rate");
				int h_no = rs.getInt("h_no");
				String pu_id = rs.getString("pu_id");
				pu_name = rs.getString("pu_name");
				String h_name= rs.getString("h_name"); // 컬럼명

				// VO는 한번 돌때마다 하나씩 생성되어야함
				// vo에 행 저장
				// vo를 list에 저장
				ReviewVO vo = new ReviewVO(r_no, r_title, r_content, r_rate, h_no, pu_id);
				vo.setH_name(h_name);
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


	public int insertReview(String r_no, String r_title, String r_content, int r_rate, int h_no, String pu_id ) {
		int result = 0;




		String sql = "insert into review values (?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(url, uid, upw);

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, r_no);
			pstmt.setString(2, r_title);
			pstmt.setString(3, r_content);
			pstmt.setInt(4, r_rate);
			pstmt.setInt(5, h_no);
			pstmt.setString(6, pu_id);

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("close에러");
			}
		}

		return result;
	}






}
