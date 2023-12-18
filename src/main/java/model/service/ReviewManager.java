package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Review;
import model.ReviewComment;
import model.dao.jdbc.ReviewCommentDAO;
import model.dao.jdbc.ReviewDAO;
import model.dao.mybatis.CommentDAO;

public class ReviewManager {
	private static ReviewManager reviewMan = new ReviewManager();
	private ReviewDAO reviewDAO;
	private ReviewCommentDAO reviewCommentDAO;
	private CommentDAO commentDAO;

	private ReviewManager() {
		try {
			reviewDAO = new ReviewDAO();
			reviewCommentDAO = new ReviewCommentDAO();
			commentDAO = new CommentDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static ReviewManager getInstance() {
		return reviewMan;
	}
	
	// 리뷰 페이지 처음 접속 시 리뷰 리스트
	public List<Review> reviewList() throws SQLException {
		return reviewDAO.reviewList();
	}
	
	// 책 정보 조회 아래 리뷰 목록
	public List<Review> findReviewList(String isbn) throws SQLException {
		return reviewDAO.findReviewList(isbn);
	}
	
	// 리뷰 검색 (책 제목)
	public List<Review> findReviewListByTitle(String title) throws SQLException {
		return reviewDAO.findReviewListByTitle(title);
	}
	
	// 리뷰 상세 조회
	public Review readReview(int reviewId) throws SQLException {
		return reviewDAO.readReview(reviewId);
	}
	
	// 리뷰 생성
	public int createReview(Review review) throws SQLException {
		return reviewDAO.createReview(review);
	}
	
	// 리뷰 수정
	public int updateReview(Review review) throws SQLException {
		return reviewDAO.modifyReview(review);
	}
	
	// 리뷰 삭제
	public int removeReview(int reviewId) throws SQLException {
		return reviewDAO.removeReview(reviewId);
	}
	
	// 마이페이지 - 사용자가 작성한 모든 리뷰
	public List<Review> findReviewByUser(String userId) throws SQLException {
		return reviewDAO.findReviewByUser(userId);
	}
	
    // 리뷰 조회 시 하단에 있는 리뷰 댓글 조회
    public List<ReviewComment> findCommentList(int reviewId){
    	return commentDAO.findCommentList(reviewId);
    }

    // 리뷰 댓글 생성
    public ReviewComment createComment(ReviewComment reviewComment) {
    	return commentDAO.createComment(reviewComment);
    }
    
    // 리뷰 댓글 수정
    public int updateComment(ReviewComment reviewComment) {
    	return commentDAO.modifyComment(reviewComment);
    }

    //리뷰 댓글 삭제
    public int removeComment (int commentId) {
    	return commentDAO.removeComment(commentId);
    }
    
    // 마이페이지 - 사용자가 작성한 모든 리뷰 댓글 
    public List<ReviewComment> findReviewCommentByUser(String userId) throws SQLException {
    	return commentDAO.findReviewCommentByUser(userId); 
    }
}
