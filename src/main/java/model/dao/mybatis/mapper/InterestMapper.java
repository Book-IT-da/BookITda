package model.dao.mybatis.mapper;

import model.Interest;

public interface InterestMapper {

    /*
     * public void createUserInterest(String userId, Interest userInterest);
     * 
     * public void modifyInterestCat(String userId, Interest userInterest);
     * 
     * public void modifyInterestLang(String userId, Interest userInterest);
     * 
     * public void modifyUserLevel(String userId, Interest userInterest);
     */

    public Interest findInterestById(String userId);
}
