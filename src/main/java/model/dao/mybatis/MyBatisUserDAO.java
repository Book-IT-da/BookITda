package model.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.User;
import model.dao.mybatis.mapper.UserMapper;

public class MyBatisUserDAO {
    private SqlSessionFactory sqlSessionFactory;
    
    public MyBatisUserDAO() {
        String resource = "mybatis-config.xml";
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        sqlSessionFactory = builder.build(inputStream);
    }
    
    // 회원 정보 수정
    public int modifyUser(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int result = sqlSession.getMapper(UserMapper.class).modifyUser(user);
            if (result > 0) {
                sqlSession.commit();
            }
            return result;
        } finally {
            sqlSession.close();
        }
    }
}
