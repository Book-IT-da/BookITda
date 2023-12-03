package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Answer;
import model.dao.AnswerDAO;

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
	
	// 마이페이지 - 사용자가 작성한 모든 답변
	public List<Answer> findAnswerByUser(String userId) throws SQLException {
		return answerDAO.findAnswerByUser(userId);
	}
}
