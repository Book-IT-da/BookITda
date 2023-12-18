package model.service;

import java.sql.SQLException;

import model.Interest;
import model.dao.jdbc.InterestDAO;
import model.dao.mybatis.MyBatisInterestDAO;


public class InterestManager {
    private static InterestManager interestManager = new InterestManager();
    private InterestDAO interestDAO;
    private MyBatisInterestDAO myBatisInterestDAO;

    public InterestManager() {
        try {
            interestDAO = new InterestDAO();
            myBatisInterestDAO = new MyBatisInterestDAO();
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
       return myBatisInterestDAO.findInterestById(userId);
   }
}
