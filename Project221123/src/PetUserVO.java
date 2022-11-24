
public class PetUserVO {
	private String pu_id;
	private String pu_name;
	private int pu_age ;
	private String pu_addr;
	public PetUserVO() {
		// TODO Auto-generated constructor stub
	}
	public PetUserVO(String pu_id, String pu_name, int pu_age, String pu_addr) {
		super();
		this.pu_id = pu_id;
		this.pu_name = pu_name;
		this.pu_age = pu_age;
		this.pu_addr = pu_addr;
	}
	
	@Override
	public String toString() {
		return "PetUserVO [pu_id=" + pu_id + ", pu_name=" + pu_name + ", pu_age=" + pu_age + ", pu_addr=" + pu_addr
				+ "]";
	}
	public String getPu_id() {
		return pu_id;
	}
	public void setPu_id(String pu_id) {
		this.pu_id = pu_id;
	}
	public String getPu_name() {
		return pu_name;
	}
	public void setPu_name(String pu_name) {
		this.pu_name = pu_name;
	}
	public int getPu_age() {
		return pu_age;
	}
	public void setPu_age(int pu_age) {
		this.pu_age = pu_age;
	}
	public String getPu_addr() {
		return pu_addr;
	}
	public void setPu_addr(String pu_addr) {
		this.pu_addr = pu_addr;
	}
	
	
	
	
}
