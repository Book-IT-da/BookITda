package model.dao;


import java.util.ArrayList;
import java.util.List;
import model.Review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {
    private JDBCUtil jdbcUtil = null;
    
    public ReviewDAO() {        
        jdbcUtil = new JDBCUtil();
    }
    
    // book 정보의 평점을 구하기 위한 star 평균 구하기
    public float setupBookStar(String ISBN) throws SQLException {
        float starAvg = 0;
        
        StringBuilder query = new StringBuilder();
        
        query.append("SELECT AVG(starscore) AS avgS ");
        query.append("FROM review ");
        query.append("WHERE ISBN = ?");
        
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{ISBN});
        
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if(rs.next()) {
                starAvg = rs.getFloat("avgS");
                return starAvg;
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        } finally {     
            jdbcUtil.close();   
        }  
        return -1;
    }
    
    // 리뷰 페이지 처음 접속 시 리뷰 리스트
    public List<Review> reviewList()throws SQLException {
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT reviewId, starScore, itLevel, purpose, recommObj, keyword, "
                + "onelineReview, detailReview, title, nickname, createdate ");
        query.append("FROM review ");
        query.append("INNER JOIN member ON review.userId = member.userId ");
        query.append("INNER JOIN book ON review.isbn = book.isbn; ");
        try {
            ResultSet rs = jdbcUtil.executeQuery(); 
            
            List<Review> reviewList = new ArrayList<Review>();    
            while (rs.next()) {             
                Review review = new Review(
                        rs.getInt("reviewId"), 
                        rs.getInt("starScore"), 
                        rs.getString("itLevel"), 
                        rs.getString("purpose"), 
                        rs.getString("recommObj"), 
                        rs.getString("onelineReview"), 
                        rs.getString("detailReview"), 
                        rs.getString("title"), 
                        rs.getString("nickname"), 
                        rs.getDate("SYSDATE")
                    );  
                reviewList.add(review);         
            }           
            return reviewList;                    
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();       
        }
        return null;
    }
    
    // 책 정보 조회 아래 리뷰 목록
    public List<Review> findReviewList(int isbn){

        StringBuilder query = new StringBuilder();
        query.append("SELECT reviewId, starscore, itLevel, keyword, onelinereview ");
        query.append("FROM review ");
        query.append("INNER JOIN member ON review.userId = member.userId ");
        query.append("INNER JOIN book ON review.isbn = book.isbn ");
        query.append("WHERE review.isbn = ?; ");
        try {
            ResultSet rs = jdbcUtil.executeQuery(); 
            
            List<Review> reviewList = new ArrayList<Review>();    
            while (rs.next()) {             
                Review review = new Review(
                        rs.getInt("reviewId"), 
                        rs.getInt("starScore"), 
                        rs.getString("itLevel"),  
                        rs.getString("onelineReview"), 
                        rs.getString("nickname")
                    );  
                reviewList.add(review);         
            }           
            return reviewList;                    
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();       
        }
        return null;
    }

    // 리뷰 검색 (책 제목)
    public List<Review> findReviewListByTitle(String title) throws SQLException {

        StringBuilder query = new StringBuilder();
        
        query.append("SELECT reviewId, starScore, itLevel, purpose, recommObj, keyword, "
                + "onelineReview, detailReview, title, nickname, createdate ");
        query.append("FROM review ");
        query.append("INNER JOIN member ON review.userId = member.userId ");
        query.append("INNER JOIN book ON review.isbn = book.isbn ");
        query.append("WHERE book.title LIKE '%?%' ");
        
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {title});
        try {
            ResultSet rs = jdbcUtil.executeQuery(); 
            
            List<Review> reviewList = new ArrayList<Review>();    
            while (rs.next()) {             
                Review review = new Review(
                        rs.getInt("reviewId"), 
                        rs.getInt("starScore"), 
                        rs.getString("itLevel"), 
                        rs.getString("purpose"), 
                        rs.getString("recommObj"), 
                        rs.getString("onelineReview"), 
                        rs.getString("detailReview"), 
                        rs.getString("title"), 
                        rs.getString("nickname"), 
                        rs.getDate("SYSDATE")
                    );  
                reviewList.add(review);         
            }           
            return reviewList;                    
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();       
        }
        return null;
    }
    
    // 리뷰 상세 조회
    public Review read(int reviewId) throws SQLException {
        
        StringBuilder query = new StringBuilder();
        
        query.append("SELECT reviewId, starScore, itLevel, purpose, recommObj, keyword, "
                + "onelineReview, detailReview, title, nickname, createdate ");
        query.append("FROM review ");
        query.append("INNER JOIN member ON review.userId = member.userId ");
        query.append("INNER JOIN book ON review.isbn = book.isbn ");
        query.append("WHERE reviewId = ?; ");
        
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {reviewId});
        
        try {
            ResultSet rs = jdbcUtil.executeQuery(); 
               
            if (rs.next()) {             
                Review review = new Review(
                        rs.getInt("reviewId"), 
                        rs.getInt("starScore"), 
                        rs.getString("itLevel"), 
                        rs.getString("purpose"), 
                        rs.getString("recommObj"), 
                        rs.getString("onelineReview"), 
                        rs.getString("detailReview"), 
                        rs.getString("title"), 
                        rs.getString("nickname"), 
                        rs.getDate("SYSDATE")
                    );   
                return review;        
            }                                
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();       
        }
        return null;
    }
    
    // 리뷰 생성
    public int createReview(Review review) throws SQLException {
        
        StringBuilder query = new StringBuilder();
        
        query.append("INSERT INTO review ");
        query.append("VALUES(Sequence_reviewId.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE);");
        
        jdbcUtil.setSqlAndParameters(query.toString(), 
                new Object[]{review.getStars(), review.getLevel(), review.getPurpose(), 
                        review.getRecommObj(), review.getKeyword(), review.getOnelineReview(),
                        review.getMultilineReview(), review.getNickname(), review.getUserId(), review.getISBN()});
        
        try {               
            int result;
            
            result = jdbcUtil.executeUpdate(); 
            
          //책 평점 매기기
            Float starAvg = this.setupBookStar(review.getISBN());
            //팩 평점 수정
            if(starAvg != -1) {
                StringBuilder query2 = new StringBuilder();
                query2.append("UPDATE Book set AVGSTAR = ? ");
                query2.append("WHERE ISBN =?");
                
                jdbcUtil.setSqlAndParameters(query2.toString(), new Object[]{starAvg, review.getISBN()});
                jdbcUtil.executeUpdate();      
            }else {
                StringBuilder query2 = new StringBuilder();
                query2.append("UPDATE Book set AVGSTAR = NULL ");
                query2.append("WHERE ISBN =?");
                
                jdbcUtil.setSqlAndParameters(query2.toString(), new Object[]{review.getISBN()});
                jdbcUtil.executeUpdate();
            }
            
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
    
    // 리뷰 수정
    public int modifyReview(Review review, int reviewId) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE review SET ");
        query.append("starscore = ?, ");
        query.append("itLevel = ?, ");
        query.append("purpose = ?, ");
        query.append("recommObj = ?, ");
        query.append("keyword = ?, ");
        query.append("onelineReview = ?, ");
        query.append("detailReview = ?, ");
        query.append("ISBN = ? ");
        query.append("WHERE reviewId = ?; ");
        
        jdbcUtil.setSqlAndParameters(query.toString(), 
                new Object[]{review.getStars(), review.getLevel(), review.getPurpose(), 
                        review.getRecommObj(), review.getKeyword(), review.getOnelineReview(),
                        review.getMultilineReview(), review.getISBN(), reviewId});

        try {
            int result = jdbcUtil.executeUpdate();
            
          //책 평점 매기기
            Float starAvg = this.setupBookStar(review.getISBN());
            //팩 평점 수정
            if(starAvg != -1) {
                StringBuilder query2 = new StringBuilder();
                query2.append("UPDATE Book set AVGSTAR = ? ");
                query2.append("WHERE ISBN =?");
                
                jdbcUtil.setSqlAndParameters(query2.toString(), new Object[]{starAvg, review.getISBN()});
                jdbcUtil.executeUpdate();      
            }else {
                StringBuilder query2 = new StringBuilder();
                query2.append("UPDATE Book set AVGSTAR = NULL ");
                query2.append("WHERE ISBN =?");
                
                jdbcUtil.setSqlAndParameters(query2.toString(), new Object[]{review.getISBN()});
                jdbcUtil.executeUpdate();
            }
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
    
    // 리뷰 삭제
    public int removeReview(int reviewId) throws SQLException {
        String ISBN=null;
      //ISBN찾기
        StringBuilder query2 = new StringBuilder();
        query2.append("SELECT ISBN ");
        query2.append("FROM Review WHERE reviewId = ? ");
        jdbcUtil.setSqlAndParameters(query2.toString(), new Object[]{reviewId} );
        
        ResultSet rs = jdbcUtil.executeQuery();
        if(rs.next()) {
            ISBN = rs.getString("ISBN");
        }
        
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM Review WHERE reviewId = ?;");
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {reviewId});    
        
        try {               
            int result = jdbcUtil.executeUpdate();  
            
            //책 평점 매기기
            Float starAvg = this.setupBookStar(ISBN);
            //팩 평점 수정
            if(starAvg != -1) {
                StringBuilder query3 = new StringBuilder();
                query3.append("UPDATE Book set AVGSTAR = ? ");
                query3.append("WHERE ISBN =?");
                
                jdbcUtil.setSqlAndParameters(query3.toString(), new Object[]{starAvg, ISBN});
                jdbcUtil.executeUpdate();      
            }else {
                StringBuilder query3 = new StringBuilder();
                query3.append("UPDATE Book set AVGSTAR = NULL ");
                query3.append("WHERE ISBN =?");
                
                jdbcUtil.setSqlAndParameters(query3.toString(), new Object[]{ISBN});
                jdbcUtil.executeUpdate();
            }
            
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
    
 // 마이페이지 - 사용자가 작성한 모든 리뷰 List에 저장 및 반환 -> ReviewDAO
    public List<Review> findReviewByUser(String userId) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT reviewId, starscore, onelineReview, createDate ");
        query.append("FROM REVIEW r JOIN MEMBER m ");
        query.append("ON r.USERID = m.USERID ");
        query.append("WHERE m.userId = ? ");
        
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {userId});
        
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<Review> reviews = new ArrayList<Review>();
            while(rs.next()) {
                Review review = new Review(
                        rs.getInt("reviewId"),
                        rs.getInt("starscore"),
                        rs.getString("onelineReview"),
                        rs.getDate("createDate"));
                reviews.add(review);
            }
            return reviews;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }
}
