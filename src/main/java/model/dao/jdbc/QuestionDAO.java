package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Question;

public class QuestionDAO {
    private JDBCUtil jdbcUtil = null;
    
    public QuestionDAO() {
        jdbcUtil = new JDBCUtil();
    }
    
    // 새로운 질문 글 작성
    public int createQuestion(Question ques) throws SQLException{
        int result = 0;
        
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO QUESTION (QID, TITLE, QCONTENT, VIEWS, USERID, CREATEDATE) ");
        query.append("VALUES (SEQUENCE_QID.nextval, ?, ?, ?, ?, SYSDATE) ");
        
        Object[] param = new Object[] {ques.getTitle(), ques.getqContent(), ques.getViews(), ques.getUserId()};
        jdbcUtil.setSqlAndParameters(query.toString(), param);
        
        try {
            result = jdbcUtil.executeUpdate();
            System.out.println(ques.getqId() + " 글이 삽입되었습니다.");
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        return result;
    }
    
    // 질문 글 수정(제목, 질문 내용 수정)
    public int updateQuestion(Question ques) throws SQLException {
        int result = 0;
        
        StringBuilder query = new StringBuilder();
        query.append("UPDATE QUESTION ");
        query.append("SET title = ?, qContent = ? ");
        query.append("WHERE qId = ? ");
        
        Object[] param = new Object[] {ques.getTitle(), ques.getqContent(), ques.getqId()};
        jdbcUtil.setSqlAndParameters(query.toString(), param);
        
        try {
            result = jdbcUtil.executeUpdate();
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        return result;
    }
    
    // 질문 글 삭제
    public int removeQuestion(int qId) throws SQLException {
        int result = 0;
        
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM QUESTION ");
        query.append("WHERE qId = ? ");
        
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {qId});
        
        try {
            result = jdbcUtil.executeUpdate();
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        return result;
    }
    
    // 질문 글 목록 조회
    public List<Question> findQuestionList() throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT qId, title, views, m.nickname, createDate ");
        query.append("FROM QUESTION q ");
        query.append("JOIN MEMBER m ON q.userId = m.userId ");
        query.append("ORDER BY createDate DESC "); // 최신 글이 맨 위로 오도록.
        
        jdbcUtil.setSqlAndParameters(query.toString(), null);
        
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<Question> questionList = new ArrayList<Question>();
            while (rs.next()) {
                Question ques = new Question(
                        rs.getInt("qId"),
                        rs.getString("title"),
                        rs.getInt("views"),
                        rs.getString("nickname"),
                        rs.getDate("createDate"));
                questionList.add(ques);
            }
            return questionList;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }
    
    // 질문 글 상세 보기
    public Question read(int qId) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT title, qContent, views, q.userId, m.nickname, createDate ");
        query.append("FROM QUESTION q ");
        query.append("JOIN MEMBER m ON q.userId = m.userId ");
        query.append("WHERE qId = ? ");
        
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {qId} );
        
        Question ques = null;
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if (rs.next()) {
                ques = new Question (
                        qId,
                        rs.getString("title"),
                        rs.getString("qContent"),
                        rs.getInt("views"),
                        rs.getString("userId"),
                        rs.getString("nickname"),
                        rs.getDate("createDate"));
            } 
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return ques;
    }
    
    // 제목으로 질문 글 검색
    public List<Question> findQuestionByTitle(String title) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT qId, title, views, m.nickname, createDate ");
        query.append("FROM QUESTION q ");
        query.append("JOIN MEMBER m ON q.userId = m.userId ");
        query.append("WHERE title LIKE ? ");
        query.append("ORDER BY createDate DESC "); // 최신 글이 맨 위로 오도록.
        
        String quesTitle = "%" + title + "%";
        
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {quesTitle});
        
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<Question> questionList = new ArrayList<Question>();
            while (rs.next()) {
                Question ques = new Question(
                        rs.getInt("qId"),
                        rs.getString("title"),
                        rs.getInt("views"),
                        rs.getString("nickname"),
                        rs.getDate("createDate"));
                questionList.add(ques);
            }
            return questionList;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }
    
    // 작성자(닉네임)으로 질문 글 검색
    public List<Question> findQuestionByName(String nickname) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT qId, title, views, m.nickname, createDate ");
        query.append("FROM QUESTION q ");
        query.append("JOIN MEMBER m ON q.userId = m.userId ");
        query.append("WHERE m.nickname LIKE ? ");
        query.append("ORDER BY createDate DESC "); // 최신 글이 맨 위로 오도록.
        
        String writer = "%" + nickname + "%";
        
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {writer});
        
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<Question> questionList = new ArrayList<Question>();
            while (rs.next()) {
                Question ques = new Question(
                        rs.getInt("qId"),
                        rs.getString("title"),
                        rs.getInt("views"),
                        rs.getString("nickname"),
                        rs.getDate("createDate"));
                questionList.add(ques);
            }
            return questionList;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }
    
    // 마이페이지 - 사용자가 작성한 모든 질문 글을 List에 저장 및 반환
    public List<Question> findQuestionByUser(String userId) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT qId, title, createDate ");
        query.append("FROM QUESTION q JOIN MEMBER m ");
        query.append("ON q.USERID = m.USERID ");
        query.append("WHERE m.userId = ? ");
        
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {userId});
        
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<Question> questions = new ArrayList<Question>();
            while(rs.next()) {
                Question question = new Question(
                        rs.getInt("qId"),
                        rs.getString("title"),
                        rs.getDate("createDate"));
                questions.add(question);
            }
            return questions;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }
}