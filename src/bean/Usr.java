package bean;
/**
 * 用户表usr对应的bean类
 * @author soft
 *
 */
public class Usr {
	private int id;
	private String name;
	private String password;
	private int auth;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public Usr(int id, String name, String password, int auth) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.auth = auth;
	}
	public Usr(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	public Usr(){}
}
