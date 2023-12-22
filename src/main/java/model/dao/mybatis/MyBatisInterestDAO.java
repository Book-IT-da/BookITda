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
