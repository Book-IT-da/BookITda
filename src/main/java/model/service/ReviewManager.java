package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Review;
import model.dao.ReviewDAO;

public class ReviewManager {
	private static ReviewManager reviewMan = new ReviewManager();
	private ReviewDAO reviewDAO;

	private ReviewManager() {
		try {
			reviewDAO = new ReviewDAO();
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
	public List<Review> findReviewList(int isbn) throws SQLException {
		return reviewDAO.findReviewList(isbn);
	}
	
	// 리뷰 검색 (책 제목)
	public List<Review> findReviewListByTitle(String title) throws SQLException {
		return reviewDAO.findReviewListByTitle(title);
	}
	
	// 리뷰 상세 조회
	public Review read(int reviewId) throws SQLException {
		return reviewDAO.readReview(reviewId);
	}
	
	// 리뷰 생성
	public int create(Review review) throws SQLException {
		return reviewDAO.createReview(review);
	}
	
	// 리뷰 수정
	public int update(Review review, int reviewId) throws SQLException {
		return reviewDAO.modifyReview(review, reviewId);
	}
	
	// 리뷰 삭제
	public int remove(int reviewId) throws SQLException {
		return reviewDAO.removeReview(reviewId);
	}
	
	// 마이페이지 - 사용자가 작성한 모든 리뷰
	public List<Review> findReviewByUser(String userId) throws SQLException {
		return reviewDAO.findReviewByUser(userId);
	}
}
