package model.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.ReviewComment;
import model.dao.mybatis.mapper.CommentMapper;

public class CommentDAO {
	private SqlSessionFactory sqlSessionFactory;
	
	public CommentDAO() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	// 리뷰 조회 시 하단에 있는 리뷰 댓글 조회
	public List<ReviewComment> findCommentList(int reviewId){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(CommentMapper.class).findCommentList(reviewId);			
		} finally {
			sqlSession.close();
		}
	}
	
	// 리뷰 댓글 생성
	public ReviewComment createComment(ReviewComment reviewComment) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(CommentMapper.class).createComment(reviewComment);
			if (result > 0) {
				sqlSession.commit();
			} 
			return reviewComment;
		} finally {
			sqlSession.close();
		}
	}

    // 리뷰 댓글 수정
    public int modifyComment(ReviewComment reviewComment) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(CommentMapper.class).modifyComment(reviewComment);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
    }
    
    //리뷰 댓글 삭제
    public int removeComment (int commentId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(CommentMapper.class).removeComment(commentId);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;	
		} finally {
			sqlSession.close();
		}
    }

    // 마이페이지 - 사용자가 작성한 모든 리뷰 댓글 List에 저장 및 반환 
    public List<ReviewComment> findReviewCommentByUser(String userId){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(CommentMapper.class).findReviewCommentByUser(userId);			
		} finally {
			sqlSession.close();
		}
    }
   
    
	
}
