
public class BookMark_ListVO extends HospitalVO {
	private String b_no;
	private int h_no;
	private String pu_id;
	
	public BookMark_ListVO() {
		// TODO Auto-generated constructor stub
	}

	public BookMark_ListVO(String b_no, int h_no, String pu_id) {
		super();
		this.b_no = b_no;
		this.h_no = h_no;
		this.pu_id = pu_id;
	}
	

	@Override
	public String toString() {
		return "BookMark_ListVO [b_no=" + b_no + ", h_no=" + h_no + ", pu_id=" + pu_id + "]";
	}

	public String getB_no() {
		return b_no;
	}

	public void setB_no(String b_no) {
		this.b_no = b_no;
	}

	public int getH_no() {
		return h_no;
	}

	public void setH_no(int h_no) {
		this.h_no = h_no;
	}

	public String getPu_id() {
		return pu_id;
	}

	public void setPu_id(String pu_id) {
		this.pu_id = pu_id;
	}
	
	
	
	
}
