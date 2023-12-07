package model.dao.jdbc;

import java.util.ArrayList;
import java.util.List;
import model.ReviewComment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewCommentDAO {
    private JDBCUtil jdbcUtil = null;

    public ReviewCommentDAO() {
        jdbcUtil = new JDBCUtil();
    }
    
    // 리뷰 조회 시 하단에 있는 리뷰 댓글 조회
    public List<ReviewComment> findCommentList(int reviewId){
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT r.commentId, rContent, r.reviewId, r.userId, member.nickname, r.createDate ");
        query.append("FROM reviewComment r ");
        query.append("INNER JOIN member ON r.userId = member.userId ");
        query.append("INNER JOIN review ON r.reviewId = review.reviewId ");
        query.append("WHERE review.reviewId = ? ");
        query.append("ORDER BY r.createdate DESC");

        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {reviewId});
        
        try {
            ResultSet rs = jdbcUtil.executeQuery(); 
            
            List<ReviewComment> commentList = new ArrayList<ReviewComment>();    
            while (rs.next()) {             
                ReviewComment comment = new ReviewComment(
                        rs.getInt("commentId"), 
                        rs.getInt("reviewId"), 
                        rs.getString("userId"), 
                        rs.getString("nickname"), 
                        rs.getString("rContent"), 
                        rs.getDate("createDate")
                    );  
                commentList.add(comment);         
            }           
            return commentList;                    
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();       
        }
        return null;
    }
    
    // 리뷰 댓글 생성
    public int createComment(ReviewComment reviewComment, int reviewId) {
        

        StringBuilder query = new StringBuilder();
        
        query.append("INSERT INTO reviewComment ");
        query.append("VALUES(sequence_commentid.nextval, ?, ?, ?, SYSDATE)");
        
        System.out.println("확확 " + reviewComment.getrContent() + " " +  reviewId + " " +  reviewComment.getUserId());
        jdbcUtil.setSqlAndParameters(query.toString(), 
                new Object[]{reviewComment.getrContent(), reviewId, reviewComment.getUserId()});
        
        try {               
            int result;
            
            result = jdbcUtil.executeUpdate(); 
            
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {     
            jdbcUtil.commit();
            jdbcUtil.close();   
        }       
        
        return 0;
    }
    
    // 리뷰 댓글 수정
    public int modifyComment(ReviewComment reviewComment, int commentId) {
        
        StringBuilder query = new StringBuilder();
        
        query.append("UPDATE ReviewComment SET ");
        query.append("rContent = ?, ");
        query.append("WHERE commentId = ? ");
        
        jdbcUtil.setSqlAndParameters(query.toString(), 
                new Object[]{reviewComment.getrContent(), commentId});

        try {
            int result;
            
            result = jdbcUtil.executeUpdate();
            
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {     
            jdbcUtil.commit();
            jdbcUtil.close();   
        }       
        
        return 0;
    }
    
    //리뷰 댓글 삭제
    public int removeComment (int commentId) {
        
        StringBuilder query = new StringBuilder();
        
        query.append("DELETE FROM ReviewComment WHERE commentId = ?");
        
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {commentId});    
        
        try {               
            int result;
            result = jdbcUtil.executeUpdate();  
            
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }           
        return 0;
    }
    
    // 마이페이지 - 사용자가 작성한 모든 리뷰 댓글 List에 저장 및 반환 -> ReviewCommentDAO 
    public List<ReviewComment> findReviewCommentByUser(String userId) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT commentId, rcontent, c.reviewId, c.createDate ");
        query.append("FROM REVIEWCOMMENT c ");
        query.append("JOIN REVIEW r ON c.reviewId = r.reviewId ");
        query.append("JOIN MEMBER m ON c.userId = m.userId ");
        query.append("WHERE m.userId = ? ");
        
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {userId});
        
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<ReviewComment> reviewComments = new ArrayList<ReviewComment>();
            while(rs.next()) {
                ReviewComment reviewComment = new ReviewComment(
                        rs.getInt("commentId"),
                        rs.getInt("reviewId"),
                        rs.getString("userId"),
                        rs.getString("rcontent"),
                        rs.getDate("createDate"));
                reviewComments.add(reviewComment);
            }
            return reviewComments;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }
}
