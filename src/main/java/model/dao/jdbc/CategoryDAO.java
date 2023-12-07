package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;

//카테고리 정보를 database에 연동하여 데이터를 처리,관리하기 위함.
public class CategoryDAO {
	private JDBCUtil jdbcUtil = null;

	public CategoryDAO() {
		jdbcUtil = new JDBCUtil();
	}
	
	//카테고리 생성
	public int createCategory(String category) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO Category VALUES(sequence_categoryid.nextval,?)");
		jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{category});
		
		try {				
			int result = jdbcUtil.executeUpdate();	
			System.out.println(result);
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
	
	
	//카테고리 수정
	public int modifyCategory(String category, String renew) throws SQLException{
		StringBuilder query = new StringBuilder();
		query.append("UPDATE Category SET category =? WHERE category=?");
		jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{renew, category});
		
		try {
			int result = jdbcUtil.executeUpdate();
			System.out.println("result는 "+result);
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
	
	
	//카테고리 삭제
	public int deleteCategory(String category) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM Category WHERE category=?");
		jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {category});	
		try {				
			int result = jdbcUtil.executeUpdate();	
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
	
	// 카테고리 id 찾기
	public int findCategoryId(String category) throws SQLException{
		StringBuilder query= new StringBuilder();
		query.append("SELECT categoryId FROM Category WHERE category=?");	
		jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {category});	
		try {				
			ResultSet rs = jdbcUtil.executeQuery();	
			if(rs.next()) {
				int id = rs.getInt("categoryId");
				return id;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.close();
		}		
		return -1;
	}
	
	//카테고리 리스트 반환
		public List<Category> findAllCategory() throws SQLException{
			System.out.println("DAO에 왔음.");
			StringBuilder query = new StringBuilder();
			query.append( "SELECT categoryId, category FROM Category");
			jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {});
			
			try {
				ResultSet rs = jdbcUtil.executeQuery();					
				List<Category> categoryList = new ArrayList<Category>();	
				while (rs.next()) {
					Category cate = new Category(	
							rs.getInt("categoryId"),
							rs.getString("category")
						);	
					categoryList.add(cate);			
				}			
				return categoryList;	
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		
			}
			return null;
		}	
	
}
