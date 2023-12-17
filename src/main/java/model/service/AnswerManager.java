package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Answer;
import model.dao.jdbc.AnswerDAO;

public class AnswerManager {
	private static AnswerManager ansMan = new AnswerManager();
	private AnswerDAO answerDAO;
	
	private AnswerManager() {
		try {
			answerDAO = new AnswerDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static AnswerManager getInstance() {
		return ansMan;
	}
	
	// 답변 목록
	public List<Answer> findAnswerList(int qId) throws SQLException {
	    return answerDAO.findAnswerList(qId);
	}
	
	// 답변 생성
	public int create(int qId, Answer ans) throws SQLException {
	    return answerDAO.createAnswer(qId, ans);
	}
	
	// 답변 수정
	public int update(Answer ans) throws SQLException {
	    return answerDAO.updateAnswer(ans);
	}
	
	// 답변 삭제
	public int remove(int aId) throws SQLException {
	    return answerDAO.removeAnswer(aId);
	}
	
	// 마이페이지 - 사용자가 작성한 모든 답변
	public List<Answer> findAnswerByUser(String userId) throws SQLException {
		return answerDAO.findAnswerByUser(userId);
	}
}
