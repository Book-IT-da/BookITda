package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAO {
    private JDBCUtil jdbcUtil = null;
    
    public UserDAO() {
        jdbcUtil = new JDBCUtil();
    }
    
    // Member 테이블에 새로운 사용자 생성
    public int createMember(User user) {
        int result = 0;
        
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO MEMBER (USERID, PASSWORD, NAME, NICKNAME, EMAIL, LEVELID) ");
        query.append("VALUES (?, ?, ?, ?, ?, null) ");
        
        Object[] param = new Object[] {user.getUserId(), user.getPassword(), user.getName(), user.getNickname(), user.getEmail(), };
        jdbcUtil.setSqlAndParameters(query.toString(), param);

        try {
            result = jdbcUtil.executeUpdate();
            System.out.println(user.getUserId() + " 님이 삽입되었습니다.");
        } catch (SQLException ex) {
            System.out.println("Input error occurred");
            if (ex.getErrorCode() == 1)
                System.out.println("Member information already exists");
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        return result;
    }
    
    // 사용자 정보 수정
    public int updateMember(User user) throws SQLException {
        int result = 0;
        
        StringBuilder query = new StringBuilder();
        query.append("UPDATE MEMBER ");
        query.append("SET password = ?, name = ?, nickname = ?, email = ? ");
        query.append("WHERE userid = ? ");
        
        Object[] param = new Object[] {user.getPassword(), user.getName(), user.getNickname(), user.getEmail(), user.getUserId()};
        jdbcUtil.setSqlAndParameters(query.toString(), param);
        
        try {
            result = jdbcUtil.executeUpdate();
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        return result;
    }
    
    // 사용자 ID에 해당하는 사용자 삭제(탈퇴도 포함)
    public int removeMember(String userId) throws SQLException {
        int result = 0;
        
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM MEMBER ");
        query.append("WHERE userId = ? ");
        
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {userId});
        
        try {
            result = jdbcUtil.executeUpdate();
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        return result;
    }
    
    // 주어진 사용자 ID에 해당하는 사용자 정보를 찾아 User 도메인 클래스에 저장해 반환. (마이페이지? / 관리자가 회원 상세 정보 확인)
    public User findUser(String userId) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT userId, password, name, nickname, email ");
        query.append("FROM MEMBER ");
        query.append("WHERE userId = ? ");
        
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {userId});
        
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("userId"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setNickname(rs.getString("nickname"));
                user.setEmail(rs.getString("email"));
                return user;
            } 
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }
    
    // 전체 사용자 정보 검색해서 List에 저장 및 반환
    public List<User> findUserList() throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT userId, nickname, email ");
        query.append("FROM MEMBER ");
        
        jdbcUtil.setSqlAndParameters(query.toString(), null);
        
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<User> userList = new ArrayList<User>();     
            while(rs.next()) {
                User user = new User(
                        rs.getString("userId"),
                        null, null,
                        rs.getString("nickname"),
                        rs.getString("email"));
                userList.add(user);
            }
            return userList;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }
    
    // 주어진 사용자 ID에 해당하는 사용자가 존재하는지 검사
    public boolean existingUser(String userId) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT COUNT(*) ");
        query.append("FROM MEMBER ");
        query.append("WHERE userId = ? ");
        
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {userId});
        
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return (count == 1 ? true : false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return false;
    }
}