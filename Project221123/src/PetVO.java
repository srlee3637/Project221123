
public class PetVO {
	private int p_no;
	private String p_type;
	private String pu_id;
	public PetVO() {
		// TODO Auto-generated constructor stub
	}
	public PetVO(int p_no, String p_type, String pu_id) {
		super();
		this.p_no = p_no;
		this.p_type = p_type;
		this.pu_id = pu_id;
	}
	
	@Override
	public String toString() {
		return "PetVO [p_no=" + p_no + ", p_type=" + p_type + ", pu_id=" + pu_id + "]";
	}
	
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	public String getP_type() {
		return p_type;
	}
	public void setP_type(String p_type) {
		this.p_type = p_type;
	}
	public String getPu_id() {
		return pu_id;
	}
	public void setPu_id(String pu_id) {
		this.pu_id = pu_id;
	}
	
	
	
	
	
}
