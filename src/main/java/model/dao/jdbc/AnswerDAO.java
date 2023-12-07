package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Answer;

public class AnswerDAO {
    private JDBCUtil jdbcUtil = null;
    
    public AnswerDAO() {
        jdbcUtil = new JDBCUtil();
    }
    
    // 댓글 작성
    public int createAnswer(int qId, Answer ans) throws SQLException {
        int result = 0;
        
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ANSWER (AID, ANSWER, QID, USERID, CREATEDATE) ");
        query.append("VALUES (SEQUENCE_AID.nextval, ?, ?, ?, SYSDATE) ");
        
        Object[] param = new Object[] {ans.getAnswer(), ans.getqId(), ans.getUserId()};
        jdbcUtil.setSqlAndParameters(query.toString(), param);
        
        try {
            result = jdbcUtil.executeUpdate();
            System.out.println(ans.getaId() + " 댓글이 삽입되었습니다.");
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        return result;
    }
    
    // 댓글 수정
    public int updateAnswer(Answer ans) throws SQLException {
        int result = 0;
        
        StringBuilder query = new StringBuilder();
        query.append("UPDATE ANSWER ");
        query.append("SET answer = ? ");
        query.append("WHERE aId = ? ");
        
        Object[] param = new Object[] {ans.getAnswer(), ans.getaId()};
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
    
    // 댓글 삭제
    public int removeAnswer(int aId) throws SQLException {
        int result = 0;
        
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ANSWER ");
        query.append("WHERE aId = ? ");
        
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {aId});
        
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
    
    // 답변 리스트
    public List<Answer> findAnswerList(int qId) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT aId, answer, m.userId, m.nickname, a.createDate ");
        query.append("FROM ANSWER a ");
        query.append("JOIN MEMBER m ON a.userId = m.userId ");
        query.append("JOIN QUESTION q ON a.QID = q.QID ");
        query.append("WHERE a.qId = ? ");
        query.append("ORDER BY createDate DESC ");
        
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {qId});
        
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<Answer> answerList = new ArrayList<Answer>();
            while (rs.next()) {
                Answer ans = new Answer(
                        rs.getInt("aId"),
                        rs.getString("answer"),
                        rs.getString("userId"),
                        rs.getString("nickname"),
                        qId,
                        rs.getDate("createDate"));
                answerList.add(ans);
            }
            return answerList;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }
    
    // 마이페이지 - 사용자가 작성한 모든 답변 글을 List에 저장 및 반환
    public List<Answer> findAnswerByUser(String userId) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT a.aId, a.answer, a.qId, a.createDate ");
        query.append("FROM ANSWER a ");
        query.append("JOIN QUESTION q ON a.QID = q.QID ");
        query.append("JOIN MEMBER m ON a.USERID = m.USERID ");
        query.append("WHERE m.userId = ? ");
        
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {userId});
        
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<Answer> answers = new ArrayList<Answer>();
            while(rs.next()) {
                Answer answer = new Answer(
                        rs.getInt("aId"),
                        rs.getString("answer"),
                        rs.getInt("qId"),
                        rs.getDate("createDate"));
                answers.add(answer);
            }
            return answers;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }
}