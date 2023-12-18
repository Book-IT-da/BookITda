package model.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.Interest;
import model.dao.mybatis.mapper.InterestMapper;

public class MyBatisInterestDAO {
    private SqlSessionFactory sqlSessionFactory;
    
    public MyBatisInterestDAO() {
        String resource = "mybatis-config.xml";
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /*
     * public void createUserInterest(String userId, Interest userInterest) {
     * SqlSession sqlSession = sqlSessionFactory.openSession(); try {
     * sqlSession.getMapper(InterestMapper.class).createUserInterest(userId,
     * userInterest); sqlSession.commit(); } finally { sqlSession.close(); } }
     * 
     * public void modifyInterestCat(String userId, Interest userInterest) {
     * SqlSession sqlSession = sqlSessionFactory.openSession(); try {
     * sqlSession.getMapper(InterestMapper.class).modifyInterestCat(userId,
     * userInterest); sqlSession.commit(); } finally { sqlSession.close(); } }
     * 
     * public void modifyInterestLang(String userId, Interest userInterest) {
     * SqlSession sqlSession = sqlSessionFactory.openSession(); try {
     * sqlSession.getMapper(InterestMapper.class).modifyInterestLang(userId,
     * userInterest); sqlSession.commit(); } finally { sqlSession.close(); } }
     * 
     * public void modifyUserLevel(String userId, Interest userInterest) {
     * SqlSession sqlSession = sqlSessionFactory.openSession(); try {
     * sqlSession.getMapper(InterestMapper.class).modifyUserLevel(userId,
     * userInterest); sqlSession.commit(); } finally { sqlSession.close(); } }
     */

    public Interest findInterestById(String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
           return sqlSession.getMapper(InterestMapper.class).findInterestById(userId);     
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error executing findInterestById", e);
        } finally {
            sqlSession.close();
        }
    }
}
