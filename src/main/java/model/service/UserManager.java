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
	
	// 사용자 생성
	public int create(User user) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(user.getUserId()) == true) {
			throw new ExistingUserException(user.getUserId() + "는 존재하는 아이디입니다.");
		}
		return userDAO.createMember(user);
	}
	
	// 사용자 정보 수정
	public int update(User user) throws SQLException {
		return user_DAO.modifyUser(user);
	}
	
	// 사용자 삭제(탈퇴)
	public int delete(String userId) throws SQLException {
		return userDAO.removeMember(userId);
	}
	
	// 아이디로 사용자 찾기
	public User findUser(String userId) throws SQLException, UserNotFoundException {
		User user = userDAO.findUser(userId);
		if (user == null) {
			throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
		}
		return user;
	}
	
	// 전체 사용자 정보
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
	
	// 아이디 찾기
	public String findUserId(String name, String email) throws SQLException {
	    return userDAO.findUserId(name, email);
	}
	
	// 비밀번호 찾기
	public String findPasswd(String userId, String name, String email) throws SQLException {
	    return userDAO.findPasswd(userId, name, email);
	}
}
