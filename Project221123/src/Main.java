import java.util.*;

public class Main {
	// 상수
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String UID = "COM02";
	public static final String UPW = "COM02";
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		ArrayList<PetUserVO> petuserVO;
		ArrayList<HospitalVO> HospitalVO;
		ArrayList<PetVO> petVO;
		ArrayList<ReviewVO> reviewVO;
		ArrayList<AppointmentVO> appointmentVO;
		ArrayList<BookMark_ListVO> bookmarkVO;


		PetUserDAO petuserDAO = new PetUserDAO();
		HospitalDAO hospitalDAO = new HospitalDAO();
		PetDAO petDAO = new PetDAO();
		ReviewDAO reviewDAO = new ReviewDAO();
		AppointmentDAO appointmentDAO = new AppointmentDAO();
		BookMark_ListDAO bookmarkDAO = new BookMark_ListDAO();


		boolean bool = true;

		while(bool) {

			try {

				System.out.println("메뉴 [1. 조회  2. 추가  3. 종료]");



				String menu = sc.nextLine();


				switch (menu) {
				case "1":
					try {

						System.out.println("메뉴 [1. 병원별 리뷰 조회  2. 예약 내역(특정 병원)  3. 회원별 동물 조회  4. 회원별 리뷰 조회  5. 즐겨찾기 조회 ]");

						String menu1 = sc.nextLine();


						switch (menu1) {


						case "1":

							reviewVO = reviewDAO.selectHospitalReviewInfo();
							for(ReviewVO vo : reviewVO) {
								System.out.println("병원명:" + vo.getH_name() + ", 병원주소:" + vo.getH_addr() + ", 병원전화번호:" + vo.getH_contact());
								System.out.println();
								if(vo.getR_no() == null) {
									System.out.println("아직 리뷰가 없습니다.");
								}else {

									System.out.println("리뷰 제목: " + vo.getR_title());
									System.out.println("리뷰 내용: " + vo.getR_content());
									System.out.println("별점: " + vo.getR_rate() + "점");
									System.out.println();
								}

							}
							System.out.println();
							break;

						case "2":

							appointmentVO = appointmentDAO.selectInfo();



							for(AppointmentVO vo : appointmentVO) {
								System.out.println("날짜: " + vo.getA_date());
								System.out.println("병원명:" + vo.getH_name() + ", 예약자명:" + appointmentDAO.pu_name );
								System.out.println();

							}

							System.out.println();


							break;

						case "3":

							petuserVO = petuserDAO.selectPetInfo();

							for(PetUserVO vo : petuserVO) {

								System.out.println("이름: " + vo.getPu_name());
								System.out.println("고양이: " + petuserDAO.catCnt + "마리");
								System.out.println("강아지: " + petuserDAO.dogCnt + "마리");

							}
							System.out.println();


							break;

						case "4":
							reviewVO = reviewDAO.selectPetUserReviewInfo();
							for(ReviewVO vo : reviewVO) {
								System.out.println("병원명: " + vo.getH_name());
								System.out.println("이름: " + reviewDAO.pu_name );
								System.out.println("리뷰 제목: " + vo.getR_title());	
								System.out.println("내용: " + vo.getR_content());	
								System.out.println("별점: " + vo.getR_rate() + "점");
								System.out.println();
							}



							break;

						case "5":
							bookmarkVO = bookmarkDAO.selectPetUserBookMark();
							if(bookmarkDAO.pu_name == null) {
								System.out.println("즐겨찾기가 없습니다");
							}else {

								for(BookMark_ListVO vo : bookmarkVO) {
									System.out.println("이름: " + bookmarkDAO.pu_name );
									System.out.println("병원 이름: " + vo.getH_name() );
									System.out.println();
								}
							}


							break;


						default:
							System.out.println("다른 번호를 입력하세요");
							break;
						}

						break;
					}catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	



				case "2":
					try {

						System.out.println("메뉴 [1.리뷰 작성 하기  2.병원 추가   3. 즐겨찾기 추가]");
						String menu1 = sc.nextLine();


						switch (menu1) {


						case "1":
							System.out.print("리뷰번호> ");
							String r_no = sc.nextLine();
							System.out.print("글제목> ");
							String r_title = sc.nextLine();
							System.out.print("글내용> ");
							String r_content = sc.nextLine();
							System.out.print("별점> ");
							int r_rate = sc.nextInt();
							sc.nextLine();
							System.out.print("병원번호> ");
							int h_no = sc.nextInt();
							sc.nextLine();
							System.out.print("회원아이디> ");
							String pu_id = sc.nextLine();

							int result = reviewDAO.insertReview(r_no, r_title, r_content, r_rate, h_no, pu_id);
							System.out.println(result);

							if(result == 1) {
								System.out.println("정상 입력되었습니다");
							} else {
								System.out.println("입력 오류 발생");
							}

							break;

						case "2":


							System.out.println("병원 사업자 번호>");
							int h_no1 = sc.nextInt();
							sc.nextLine();
							System.out.println("병원이름>");
							String h_name = sc.nextLine();
							System.out.println("병원주소>");
							String h_addr = sc.nextLine();
							System.out.println("병원 전화번호>");
							String h_contact = sc.nextLine();


							int result1 = hospitalDAO.insertHospital(h_no1, h_name, h_addr, h_contact);

							if(result1 == 1) {
								System.out.println("정상입력되었습니다");
							}else {
								System.out.println("입력오류발생");
							}



							break;

						case "3":

							System.out.println("즐겨찾기 번호>");
							String b_no = sc.nextLine();

							System.out.println("병원번호>");
							int h_no2 = sc.nextInt();
							sc.nextLine();
							System.out.println("USER_ID>");
							String pu_id1 = sc.nextLine();

							int result2 = bookmarkDAO.insertBookMark(b_no, h_no2, pu_id1);

							if(result2 == 1) {
								System.out.println("정상입력되었습니다");
							}else {
								System.out.println("입력오류발생");
							}



							break;


						default:
							System.out.println("다른 번호를 입력하세요");
							break;
						}

						break;
					}catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	





				case "3":
					bool = false;
					break;

				default:
					System.out.println("다른 번호를 입력하세요");
					break;




				} 
			}

			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

		}
	}
}

//		while(bool) {
//
//			try {
//
//				System.out.println("메뉴 [1. 병원별 리뷰 조회  2. 예약 내역(특정 병원)  3. 회원별 동물 조회]");
//				System.out.println("메뉴 [4. 회원별 리뷰 조회  5. 즐겨찾기 추가  6. 리뷰 작성 하기]");
//				System.out.println("메뉴 [7. 병원 추가, 8. 즐겨찾기 추가 9. 프로그램 종료]");
//
//
//				String menu = sc.nextLine();
//
//
//				switch (menu) {
//				case "1":
//
//					reviewVO = reviewDAO.selectHospitalReviewInfo();
//					for(ReviewVO vo : reviewVO) {
//						System.out.println("병원명:" + vo.getH_name() + ", 병원주소:" + vo.getH_addr() + ", 병원전화번호:" + vo.getH_contact());
//						System.out.println();
//						if(vo.getR_no() == null) {
//							System.out.println("아직 리뷰가 없습니다.");
//						}else {
//
//							System.out.println("리뷰 제목: " + vo.getR_title());
//							System.out.println("리뷰 내용: " + vo.getR_content());
//							System.out.println("별점: " + vo.getR_rate() + "점");
//							System.out.println();
//						}
//
//					}
//					System.out.println();
//					break;
//
//				case "2":
//
//					appointmentVO = appointmentDAO.selectInfo();
//
//
//
//					for(AppointmentVO vo : appointmentVO) {
//						System.out.println("병원명:" + vo.getH_name() + ", 예약자명:" + appointmentDAO.pu_name );
//						System.out.println();
//
//					}
//
//					System.out.println();
//
//
//					break;
//
//				case "3":
//
//					petuserVO = petuserDAO.selectPetInfo();
//
//					for(PetUserVO vo : petuserVO) {
//
//						System.out.println("이름: " + vo.getPu_name());
//						System.out.println("고양이: " + petuserDAO.catCnt + "마리");
//						System.out.println("강아지: " + petuserDAO.dogCnt + "마리");
//
//					}
//					System.out.println();
//
//
//					break;
//
//				case "4":
//					reviewVO = reviewDAO.selectPetUserReviewInfo();
//					for(ReviewVO vo : reviewVO) {
//						System.out.println("병원명: " + vo.getH_name());
//						System.out.println("이름: " + reviewDAO.pu_name );
//						System.out.println("리뷰 제목: " + vo.getR_title());	
//						System.out.println("내용: " + vo.getR_content());	
//						System.out.println("별점: " + vo.getR_rate() + "점");
//						System.out.println();
//					}
//
//
//
//					break;
//
//				case "5":
//
//					System.out.print("리뷰번호> ");
//					String r_no = sc.nextLine();
//					System.out.print("글제목> ");
//					String r_title = sc.nextLine();
//					System.out.print("글내용> ");
//					String r_content = sc.nextLine();
//					System.out.print("별점> ");
//					int r_rate = sc.nextInt();
//					sc.nextLine();
//					System.out.print("병원번호> ");
//					int h_no = sc.nextInt();
//					sc.nextLine();
//					System.out.print("회원아이디> ");
//					String pu_id = sc.nextLine();
//
//					int result = reviewDAO.insertReview(r_no, r_title, r_content, r_rate, h_no, pu_id);
//					System.out.println(result);
//
//					if(result == 1) {
//						System.out.println("정상 입력되었습니다");
//					} else {
//						System.out.println("입력 오류 발생");
//					}
//
//					break;
//
//
//
//				case "6":
//
//					bookmarkVO = bookmarkDAO.selectPetUserBookMark();
//					if(bookmarkDAO.pu_name == null) {
//						System.out.println("즐겨찾기가 없습니다");
//					}else {
//
//						for(BookMark_ListVO vo : bookmarkVO) {
//							System.out.println("이름: " + bookmarkDAO.pu_name );
//							System.out.println("병원 이름: " + vo.getH_name() );
//							System.out.println();
//						}
//					}
//
//
//
//
//					break;
//
//				case "7":
//
//
//
//					System.out.println("병원 사업자 번호>");
//					int h_no1 = sc.nextInt();
//					sc.nextLine();
//					System.out.println("병원이름>");
//					String h_name = sc.nextLine();
//					System.out.println("병원주소>");
//					String h_addr = sc.nextLine();
//					System.out.println("병원 전화번호>");
//					String h_contact = sc.nextLine();
//
//
//					int result1 = hospitalDAO.insertHospital(h_no1, h_name, h_addr, h_contact);
//
//					if(result1 == 1) {
//						System.out.println("정상입력되었습니다");
//					}else {
//						System.out.println("입력오류발생");
//					}
//
//
//
//					break;
//
//				case "8":
//					System.out.println("즐겨찾기 번호>");
//					String b_no = sc.nextLine();
//
//					System.out.println("병원번호>");
//					int h_no2 = sc.nextInt();
//					sc.nextLine();
//					System.out.println("USER_ID>");
//					String pu_id1 = sc.nextLine();
//
//					int result2 = bookmarkDAO.insertBookMark(b_no, h_no2, pu_id1);
//
//					if(result2 == 1) {
//						System.out.println("정상입력되었습니다");
//					}else {
//						System.out.println("입력오류발생");
//					}
//
//
//
//
//
//					break;
//
//
//				case "9":
//
//
//					System.out.println("프로그램 종료");
//					bool = false;
//
//
//
//					break;
//				default:
//					System.out.println("다시 입력해주세요>");
//					break;
//				}
//
//
//			} 
//
//			catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//
//		}

//	try {
//	pu = puDAO.selectEx();
//	for(PetUserVO vo : pu) {
//		System.out.println(vo.toString());
//	}
//	
//	h=hDAO.selectEx();
//	for(HospitalVO vo : h) {
//		System.out.println(vo.toString());
//
//	}
//	
//	p = pDAO.selectEx();
//	for(PetVO vo : p) {
//		System.out.println(vo.toString());
//		
//	}
//	
//	r = rDAO.selectEx();
//	for(ReviewVO vo : r) {
//		System.out.println(vo.toString());
//		
//	}} 
