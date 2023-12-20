package model.service;

import java.sql.SQLException;
import java.util.List;

import model.User;
import model.dao.jdbc.UserDAO;
import model.dao.mybatis.MyBatisUserDAO;

public class UserManager {
	private static UserManager userMan = new UserManager();
	private UserDAO userDAO;
	private MyBatisUserDAO user_DAO;

	private UserManager() {
		try {
			userDAO = new UserDAO();
			user_DAO = new MyBatisUserDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static UserManager getInstance() {
		return userMan;
	}
	
	public int create(User user) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(user.getUserId()) == true) {
			throw new ExistingUserException(user.getUserId() + "는 존재하는 아이디입니다.");
		}
		return userDAO.createMember(user);
	}
	
	public int update(User user) throws SQLException {
		return user_DAO.modifyUser(user);
	}
	
	public int delete(String userId) throws SQLException {
		return userDAO.removeMember(userId);
	}
	
	public User findUser(String userId) throws SQLException, UserNotFoundException {
		User user = userDAO.findUser(userId);
		if (user == null) {
			throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
		}
		return user;
	}
	
	public List<User> findUserList() throws SQLException {
		return userDAO.findUserList();
	}
	
	public boolean login(String userId, String password) throws SQLException, UserNotFoundException, PasswordMismatchException {
		User user = findUser(userId);
		
		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}
	
	public String findUserId(String name, String email) throws SQLException {
	    return userDAO.findUserId(name, email);
	}
}
