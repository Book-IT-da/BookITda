package model;
/**
 * 책 리뷰에 대한 댓글
 */

import java.util.*;

public class ReviewComment {
    private int commentId;  // 리뷰 댓글 구분 id
    private int reviewId;   // 댓글 달리는 리뷰의 아이디
    private String userId;  // 사용자 아이디
    private String nickname; // 작성자(닉네임 사용)
    private String rContent;    // 리뷰 댓글 내용
    private Date createDate;    // 래뷰 댓글 작성 날짜

    public ReviewComment() { }

    public ReviewComment(int commentId, int reviewId, 
            String rContent, Date createDate) {
        super();
        this.commentId = commentId;
        this.reviewId = reviewId;
        this.rContent = rContent;
        this.createDate = createDate;
    }
    
    public ReviewComment(int commentId, int reviewId, String userId, 
            String nickname, String rContent,
            Date createDate) {
        super();
        this.commentId = commentId;
        this.reviewId = reviewId;
        this.userId = userId;
        this.nickname = nickname;
        this.rContent = rContent;
        this.createDate = createDate;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getrContent() {
        return rContent;
    }

    public void setrContent(String rContent) {
        this.rContent = rContent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	@Override
	public String toString() {
		return "ReviewComment [commentId=" + commentId + ", reviewId=" + reviewId + ", userId=" + userId + ", nickname="
				+ nickname + ", rContent=" + rContent + ", createDate=" + createDate + "]";
	}    
    
}
