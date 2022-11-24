
public class ReviewVO extends HospitalVO{
	private String r_no;
	private String r_title;
	private String r_content;
	private int r_rate;
	private int h_no;
	private String pu_id;
	
	public ReviewVO() {
		// TODO Auto-generated constructor stub
	}

	public ReviewVO(String r_no, String r_title, String r_content, int r_rate, int h_no, String pu_id) {
		super();
		this.r_no = r_no;
		this.r_title = r_title;
		this.r_content = r_content;
		this.r_rate = r_rate;
		this.h_no = h_no;
		this.pu_id = pu_id;
	}
	
	
	
	@Override
	public String toString() {
		return "ReviewVO [r_no=" + r_no + ", r_title=" + r_title + ", r_content=" + r_content + ", r_rate=" + r_rate
				+ ", h_no=" + h_no + ", pu_id=" + pu_id + "]";
	}

	public String getR_no() {
		return r_no;
	}

	public void setR_no(String r_no) {
		this.r_no = r_no;
	}

	public String getR_title() {
		return r_title;
	}

	public void setR_title(String r_title) {
		this.r_title = r_title;
	}

	public String getR_content() {
		return r_content;
	}

	public void setR_content(String r_content) {
		this.r_content = r_content;
	}

	public int getR_rate() {
		return r_rate;
	}

	public void setR_rate(int r_rate) {
		this.r_rate = r_rate;
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
