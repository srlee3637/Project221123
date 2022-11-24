
public class AppointmentVO extends HospitalVO{
	private String a_no;
	private String a_date;
	private String pu_id;
	private int h_no;
	
	public AppointmentVO() {
		// TODO Auto-generated constructor stub
	}

	public AppointmentVO(String a_no, String a_date, String pu_id, int h_no) {
		super();
		this.a_no = a_no;
		this.a_date = a_date;
		this.pu_id = pu_id;
		this.h_no = h_no;
	}

	@Override
	public String toString() {
		return "AppointmentVO [a_no=" + a_no + ", a_date=" + a_date + ", pu_id=" + pu_id + ", h_no=" + h_no + "]";
	}

	public String getA_no() {
		return a_no;
	}

	public void setA_no(String a_no) {
		this.a_no = a_no;
	}

	public String getA_date() {
		return a_date;
	}

	public void setA_date(String a_date) {
		this.a_date = a_date;
	}

	public String getPu_id() {
		return pu_id;
	}

	public void setPu_id(String pu_id) {
		this.pu_id = pu_id;
	}

	public int getH_no() {
		return h_no;
	}

	public void setH_no(int h_no) {
		this.h_no = h_no;
	}
	
	

}
