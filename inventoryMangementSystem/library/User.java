package library;

import java.util.Objects;

public class User {
	
	Integer userId;
	String userName;
	String userType;
	String password;
	
	public User(Integer userId, String userName, String userType, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userType = userType;
		this.password = password;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userType=" + userType + ", password=" + password
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(password, userId, userName, userType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(password, other.password) && Objects.equals(userId, other.userId)
				&& Objects.equals(userName, other.userName) && Objects.equals(userType, other.userType);
	}


	

}
