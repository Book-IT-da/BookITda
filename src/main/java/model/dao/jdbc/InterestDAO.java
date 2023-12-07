package model.dao.jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Book;
import model.Category;
import model.Interest;

public class InterestDAO {
    private JDBCUtil jdbcUtil = null;   
    
    public InterestDAO() {               
        jdbcUtil = new JDBCUtil();      
    }
  //카테고리 리스트 반환
    public List<Category> findCategoryList() throws SQLException {
    	
		StringBuilder query = new StringBuilder();
		query.append("SELECT categoryId, category "
     		   + "FROM Category");
		jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			List<Category> categoryList = new ArrayList<Category>();	
			while (rs.next()) {				
				Category category = new Category(
						rs.getInt("categoryId"),
						rs.getString("category")
					);	
				categoryList.add(category);			
			}			
			return categoryList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
    //사용자의 흥미 언어, 카테고리, 수준 초기 생성
    public void createUserInterest(String userId, Interest userInterest) throws SQLException {
        //사용자 흥미 카테고리 설정
        StringBuilder query1 = new StringBuilder(); 
        query1.append("INSERT INTO INTERESTCAT VALUES(sequence_interestCatId.nextval,?,?)");
        
        try {
            for (int i = 0; i < userInterest.getCategoryList().size(); i++) {
                Object[] param = new Object[] {userId, userInterest.getCategoryList().get(i)};
                jdbcUtil.setSqlAndParameters(query1.toString(), param); 
                
                try {
                    jdbcUtil.executeUpdate();
                } catch (Exception ex) {
                    jdbcUtil.rollback();
                    ex.printStackTrace();
                } finally {
                    jdbcUtil.commit();
                }
            }
        } finally {
            jdbcUtil.close();
        }
        
        //사용자 흥미 언어 설정
        StringBuilder query2 = new StringBuilder(); 
        query2.append("INSERT INTO INTERESTLANG VALUES(sequence_interestLangId.nextval,?,?)");
        
        try {
            for (int i = 0; i < userInterest.getLanguageList().size(); i++) {
                Object[] param = new Object[] {userId, userInterest.getLanguageList().get(i)};
                jdbcUtil.setSqlAndParameters(query2.toString(), param); 
                
                try {
                    jdbcUtil.executeUpdate();
                } catch (Exception ex) {
                    jdbcUtil.rollback();
                    ex.printStackTrace();
                } finally {
                    jdbcUtil.commit();
                }
            }
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        //사용자 수준 설정
        StringBuilder query3 = new StringBuilder();
        query3.append("UPDATE Member SET levelId= ? WHERE userId=?");
        jdbcUtil.setSqlAndParameters(query3.toString(), new Object[] {userInterest.getLevelId(), userId});
        
        try {
            jdbcUtil.executeUpdate();
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();  
        }       
    }
    //사용자의 흥미 카테고리 수정
    public void modifyInterestCat(String userId, Interest userInterest) throws SQLException {
        //사용자의 기존 흥미 카테고리 삭제
        StringBuilder query1 = new StringBuilder();
        query1.append("DELETE FROM INTERESTCAT WHERE userId=?");
        jdbcUtil.setSqlAndParameters(query1.toString(), new Object[] {userId});
        
        try {
            jdbcUtil.executeUpdate();
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();  
        }       
        //사용자의 새로운 흥미 카테고리 추가
        StringBuilder query2 = new StringBuilder(); 
        query2.append("INSERT INTO INTERESTCAT VALUES(sequence_interestCatId.nextval,?,?)");
        
        for (int i = 0; i < userInterest.getCategoryList().size(); i++) {
            Object[] param = new Object[] {userId, userInterest.getCategoryList().get(i)};
            jdbcUtil.setSqlAndParameters(query2.toString(), param);
            try {
                jdbcUtil.executeUpdate();
            } catch (Exception ex) {
                jdbcUtil.rollback();
                ex.printStackTrace();
            } 
            finally {
                jdbcUtil.commit();
            }
        }
    }
    //사용자의 흥미 언어 수정
    public void modifyInterestLang(String userId, Interest userInterest) throws SQLException {
      //사용자의 기존 흥미 언어 삭제
        StringBuilder query1 = new StringBuilder();
        query1.append("DELETE FROM INTERESTLANG WHERE userId=?");
        jdbcUtil.setSqlAndParameters(query1.toString(), new Object[] {userId});
        
        try {
            jdbcUtil.executeUpdate();
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();  
        }       
        //사용자의 새로운 흥미 언어 추가
        StringBuilder query2 = new StringBuilder(); 
        query2.append("INSERT INTO INTERESTLANG VALUES(sequence_interestLangId.nextval,?,?)");
        
        for (int i = 0; i < userInterest.getLanguageList().size(); i++) {
            Object[] param = new Object[] {userId, userInterest.getLanguageList().get(i)};
            jdbcUtil.setSqlAndParameters(query2.toString(), param);
            try {
                jdbcUtil.executeUpdate();
            } catch (Exception ex) {
                jdbcUtil.rollback();
                ex.printStackTrace();
            } 
            finally {
                jdbcUtil.commit();
            }
        }
    }
    //사용자의 it 수준 수정
    public void modifyUserLevel(String userId, Interest userInterest) throws SQLException {
        //기존 사용자의 it수준 새로운 it수준으로 수정
        //userLevel은 member 테이블에서 관리
        StringBuilder query1 = new StringBuilder();
        query1.append("UPDATE MEMBER SET levelId=? WHERE userId=?");
        jdbcUtil.setSqlAndParameters(query1.toString(), new Object[] {userInterest.getLevelId(), userId});
        
        try {
            jdbcUtil.executeUpdate();
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();  
        } 
    }
    //사용자의 기존 흥미 카테고리, 언어, 수준 넘기기
    public Interest findInterestById(String userId) throws SQLException {
        //InterestDTO 객체 생성
        Interest userInterest = new Interest(new ArrayList<>(), new ArrayList<>(), 0);
        
        try {            
            StringBuilder query1 = new StringBuilder();
            query1.append("SELECT categoryId FROM INTERESTCAT WHERE userId=?");
            jdbcUtil.setSqlAndParameters(query1.toString(), new Object[] {userId});
            
            ResultSet rs1 = jdbcUtil.executeQuery();

            while (rs1.next()) {
                int categoryId = rs1.getInt("categoryId");
                userInterest.getCategoryList().add(categoryId);
            }

            StringBuilder query2 = new StringBuilder();
            query2.append("SELECT languageId FROM INTERESTLANG WHERE userId=?");
            jdbcUtil.setSqlAndParameters(query2.toString(), new Object[] {userId});
            
            ResultSet rs2 = jdbcUtil.executeQuery();

            while (rs2.next()) {
                int languageId = rs2.getInt("languageId");
                userInterest.getLanguageList().add(languageId);
            }
            
            StringBuilder query3 = new StringBuilder();
            query3.append("SELECT levelId FROM MEMBER WHERE userId=?");
            jdbcUtil.setSqlAndParameters(query3.toString(), new Object[] {userId});
            
            ResultSet rs3 = jdbcUtil.executeQuery();

            while (rs3.next()) {
                int levelId = rs3.getInt("levelId");
                userInterest.setLevelId(levelId);
            }
            
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();  
        } 
        return userInterest;
    }
}
    