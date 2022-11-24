
public class HospitalVO {
	
	private int h_no;
	private String h_name;
	private String h_addr;
	private String h_contact;
	
	public HospitalVO() {
		// TODO Auto-generated constructor stub
	}

	public HospitalVO(int h_no, String h_name, String h_addr, String h_contact) {
		super();
		this.h_no = h_no;
		this.h_name = h_name;
		this.h_addr = h_addr;
		this.h_contact = h_contact;
	}

	@Override
	public String toString() {
		return "HospitalVO [h_no=" + h_no + ", h_name=" + h_name + ", h_addr=" + h_addr + ", h_contact=" + h_contact
				+ "]";
	}

	public int getH_no() {
		return h_no;
	}

	public void setH_no(int h_no) {
		this.h_no = h_no;
	}

	public String getH_name() {
		return h_name;
	}

	public void setH_name(String h_name) {
		this.h_name = h_name;
	}

	public String getH_addr() {
		return h_addr;
	}

	public void setH_addr(String h_addr) {
		this.h_addr = h_addr;
	}

	public String getH_contact() {
		return h_contact;
	}

	public void setH_contact(String h_contact) {
		this.h_contact = h_contact;
	}
	
	
	
	
}
