import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PetUserInsert {

	public static void main(String[] args) {
		
		//insert
		Scanner sc = new Scanner(System.in);
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 주소
		String uid = "COM02"; // 계정
		String upw = "COM02"; // 비밀번호
		
		String sql = "insert into PETUSERS values(?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//connection
			conn = DriverManager.getConnection(url, uid, upw);
			System.out.println(conn);
			
			//statement객체
			pstmt = conn.prepareStatement(sql);
		
			//3. ?값 세팅 - 첫번째부터 1번 순서 (setString, setInt, setDate, setTimestamp 등등)
			while(true) {
				System.out.println("아이디 입력>");
				String pu_id = sc.nextLine();
				System.out.println("이름 입력>");
				String pu_name = sc.nextLine();
				System.out.println("나이 입력>");
				int pu_age = sc.nextInt();
				System.out.println(pu_age);
				sc.nextLine();
				System.out.println("주소 입력>");
				String pu_addr = sc.nextLine();
				
				
				pstmt.setString(1, pu_id);
				pstmt.setString(2, pu_name);
				pstmt.setInt(3, pu_age);
				pstmt.setString(4, pu_addr);
				
				if(pu_age==1) {
					System.out.println("프로그램 정상 종료");
					break;
				}
				int result = pstmt.executeUpdate(); // 성공, 실패 결과를 정수로 반환해줌, 이미 위에서 sql을 pstmt에 담아줬기 때문에 매개변수가 없는 메소드를 선택해야 함

				if(result == 1) {
					System.out.println("성공");
				} else {
					System.out.println("실패");
				}
				
				
			}
			sc.close();
			
			//4. sql 실행 (select문은 query문장 실행, insert, delete, update문은 update메소드로 실행)
			
			 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Close 에러");
			}
		}
		
		
		
		
		
		
		
	}

}
