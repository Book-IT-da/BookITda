package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Category;
import model.dao.CategoryDAO;

public class CategoryManager {
	private static CategoryManager cateM = new CategoryManager();
	private CategoryDAO categoryDAO;
	
	private CategoryManager() {
		try {
			categoryDAO = new CategoryDAO();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static CategoryManager getInstance() {
		return cateM;
	}
	
	//카테고리 생성
	public int createCategory(String category) throws SQLException {
		return categoryDAO.createCategory(category);
	}
	
	//카테고리 수정
	public int modifyCategory(String category, String renew) throws SQLException{
		return categoryDAO.modifyCategory(category, renew);
	}
	
	//카테고리 삭제
	public int deleteCategory(String category) throws SQLException{
		return categoryDAO.deleteCategory(category);
	}
	
	//모든 카테고리 리스트
	public List<Category> findAllCategory() throws SQLException{
		return categoryDAO.findAllCategory();
	}
}
