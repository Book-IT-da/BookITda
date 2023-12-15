package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Category;
import model.Interest;
import model.dao.jdbc.InterestDAO;


/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
public class InterestManager {
    private static InterestManager interestManager = new InterestManager();
    private InterestDAO interestDAO;

    public InterestManager() {
        try {
            interestDAO = new InterestDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }           
    }
    
    public static InterestManager getInstance() {
        return interestManager;
    }
    
   public void createUserInterest(String userId, Interest userInterest) throws SQLException{
       interestDAO.createUserInterest(userId, userInterest);
   }
   public void modifyInterestCat(String userId, Interest userInterest) throws SQLException{
       interestDAO.modifyInterestCat(userId, userInterest);
   }
   public void modifyInterestLang(String userId, Interest userInterest) throws SQLException{
       interestDAO.modifyInterestLang(userId, userInterest);
   }
   public void modifyUserLevel(String userId, Interest userInterest) throws SQLException{
       interestDAO.modifyUserLevel(userId, userInterest);
   }
   public Interest findInterestById(String userId) throws SQLException {
       return interestDAO.findInterestById(userId);
   }
}
