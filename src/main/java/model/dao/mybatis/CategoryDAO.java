package model.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.Category;
import model.dao.mybatis.mapper.CategoryMapper;

public class CategoryDAO {
	private SqlSessionFactory sqlSessionFactory;

	public CategoryDAO() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	// 카테고리 생성
	public int createCategory(String category) {
		System.out.print("mybatis로 카테고리 생성");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(CategoryMapper.class).createCategory(category);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}

	// 카테고리 수정
	public int modifyCategory(String category, String renew) throws SQLException {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(CategoryMapper.class).modifyCategory(category, renew);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}

	// 카테고리 삭제
	public int deleteCategory(String category) throws SQLException {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(CategoryMapper.class).deleteCategory(category);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}

	// 카테고리 id 찾기
	public int findCategoryId(String category) throws SQLException {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(CategoryMapper.class).findCategoryId(category);
			if(result >=0) {
				return result;
			}
			else {
				return -1;
			}
		} finally {
			sqlSession.close();
		}
	}

	// 카테고리 리스트 반환
	public List<Category> findAllCategory() throws SQLException {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		System.out.print("mybatis로 카테고리 리스트 반환");
		try {
			return sqlSession.getMapper(CategoryMapper.class).findAllCategory();
		} finally {
			sqlSession.close();
		}
	}

}
