package model.dao.mybatis.mapper;

import java.sql.SQLException;
import java.util.List;
import model.ReviewComment;

public interface CommentMapper {

	// 리뷰 조회 시 하단에 있는 리뷰 댓글 조회
    public List<ReviewComment> findCommentList(int reviewId);

    // 리뷰 댓글 생성
    public int createComment(ReviewComment reviewComment);
    
    // 리뷰 댓글 수정
    public int modifyComment(ReviewComment reviewComment);

    //리뷰 댓글 삭제
    public int removeComment (int commentId);
    
    // 마이페이지 - 사용자가 작성한 모든 리뷰 댓글 List에 저장 및 반환 
    public List<ReviewComment> findReviewCommentByUser(String userId);
    
}
