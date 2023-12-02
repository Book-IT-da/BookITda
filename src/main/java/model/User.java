package model;

public class User {
    private String userId;      
    private String password;   
    private String name;       
    private String nickname;    
    private String email;          
    private int itLevel;
    
    public User() {
        super();
    }

    public User(String userId, String password, String name, String nickname, String email) {
        super();
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLevelId() {
        return itLevel;
    }

    public void setLevelId(int itLevel) {
        this.itLevel = itLevel;
    }

    /* 비밀번호 검사 */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	public boolean isSameUser(String userid) {
        return this.userId.equals(userid);
    }
	
    @Override
    public String toString() {
        return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", nickname=" + nickname
                + ", email=" + email + ", itLevel=" + itLevel + "]";
    }
}