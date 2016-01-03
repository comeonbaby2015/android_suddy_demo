package demo.ui_spinner;

/**
 * <pre>
 * 用户信息
 * </pre>
 */
public class User {
	private String name;

	private String addr;

	public User(String pName, String pAddr) {
		// TODO Auto-generated constructor stub
		setName(pName);
		setAddr(pAddr);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
}
