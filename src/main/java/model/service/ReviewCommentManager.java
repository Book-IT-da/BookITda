package model.service;

import java.sql.SQLException;
import java.util.List;

import model.ReviewComment;
import model.dao.ReviewCommentDAO;

public class ReviewCommentManager {
	private static ReviewCommentManager commentMan = new ReviewCommentManager();
	private ReviewCommentDAO reviewCommentDAO;

	private ReviewCommentManager() {
		try {
			reviewCommentDAO = new ReviewCommentDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}

	public static ReviewCommentManager getInstance() {
		return commentMan;
	}
	
    // 리뷰 조회 시 하단에 있는 리뷰 댓글 조회
    public List<ReviewComment> findCommentList(int reviewId){
    	return reviewCommentDAO.findCommentList(reviewId);
    }

    // 리뷰 댓글 생성
    public int create(ReviewComment reviewComment, int reviewId) {
    	return reviewCommentDAO.createComment(reviewComment, reviewId);
    }
    
    
    // 리뷰 댓글 수정
    public int update(ReviewComment reviewComment, int commentId) {
    	return reviewCommentDAO.modifyComment(reviewComment, commentId);
    }

    //리뷰 댓글 삭제
    public int reviewComment (int commentId) {
    	return reviewCommentDAO.reviewComment(commentId);
    }
    
    // 마이페이지 - 사용자가 작성한 모든 리뷰 댓글 
    public List<ReviewComment> findReviewCommentByUser(String userId) throws SQLException {
    	return reviewCommentDAO.findReviewCommentByUser(userId);
    }

}