package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Question;
import model.dao.QuestionDAO;

public class QuestionManager {
	private static QuestionManager quesMan = new QuestionManager();
	private QuestionDAO questionDAO;
	
	private QuestionManager() {
		try {
			questionDAO = new QuestionDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static QuestionManager getInstance() {
		return quesMan;
	}
	
	
	// Q&A 처음 접속 시 질문 글 리스트
	public List<Question> findQuestionList() throws SQLException {
		return questionDAO.findQuestionList();
	}
	
	// 질문 글 검색 (제목)
	public List<Question> findQuestionByTitle(String title) throws SQLException {
		return questionDAO.findQuestionByTitle(title);
	}
	
	// 질문 글 검색 (닉네임)
	public List<Question> findQuestionByName(String nickname) throws SQLException {
		return questionDAO.findQuestionByName(nickname);
	}
	
	// 질문 글 상세 조회
	public Question read(int qId) throws SQLException {
		return questionDAO.read(qId);
	}
	
	// 질문 글 생성
	public int create(Question ques) throws SQLException {
		return questionDAO.createQuestion(ques);
	}
	
	// 질문 글 수정
	public int update(Question ques) throws SQLException {
		return questionDAO.updateQuestion(ques);
	}
	
	// 질문 글 삭제
	public int remove(int qId) throws SQLException {
		return questionDAO.removeQuestion(qId);
	}
	
	// 마이페이지 - 사용자가 작성한 모든 질문
	public List<Question> findQuestionByUser(String userId) throws SQLException {
		return questionDAO.findQuestionByUser(userId);
	}
}
