package caffrey.bean;

public class LoginMsg {

	//-2:�û����� -1:�����ڸ��û�  0����¼�������  1����¼�ɹ�
	int code;
	String name;
	Integer id;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	public LoginMsg() {
		super();
		// TODO Auto-generated constructor stub
		code = -1;
	}
	@Override
	public String toString() {
		return "LoginMsg [code=" + code + ", name=" + name + ", id=" + id + "]";
	}
}
