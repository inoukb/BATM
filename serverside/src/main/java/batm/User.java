package batm;

public class User {
	private long uid;
	private String idtel;
	private String gtoken;
	private String email;
	private String password;
	private int rank;
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getIdtel() {
		return idtel;
	}
	public void setIdtel(String idtel) {
		this.idtel = idtel;
	}
	public String getGtoken() {
		return gtoken;
	}
	public void setGtoken(String gtoken) {
		this.gtoken = gtoken;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
}
