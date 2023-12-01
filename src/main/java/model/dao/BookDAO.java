package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Book;
import model.RecommBook;

//å ������ database�� �����Ͽ� �����͸� ó��,�����ϱ� ����.
public class BookDAO {
	private JDBCUtil jdbcUtil = null;
	private int category, level, language, keyword1, keyword2, keyword3;

	public BookDAO() {			
		jdbcUtil = new JDBCUtil();		
	}	
	
	//ī�װ�, ����, ���, Ű������� id�� ã�´�.
	public void findId(String c, String le, String la, String k1, String k2, String k3) throws SQLException{
		
		try {
			//ī�װ� id�� ã�´�.
			StringBuilder query1 = new StringBuilder();
			query1.append("SELECT categoryId FROM Category WHERE category=?");
			jdbcUtil.setSqlAndParameters(query1.toString(), new Object[] {c});
			ResultSet rs= jdbcUtil.executeQuery();
			if(rs.next()) {
				category = rs.getInt("categoryId");
			}
			
			//���� id�� ã�´�,
			StringBuilder query2 = new StringBuilder();
			query2.append("SELECT levelId FROM ItLevel WHERE itLevel=?");
			jdbcUtil.setSqlAndParameters(query2.toString(), new Object[] {le});
			ResultSet rs2 = jdbcUtil.executeQuery();
			if(rs2.next()) {
				level = rs2.getInt("levelId");
			}
			
			//��� id�� ã�´�,
			StringBuilder query3 = new StringBuilder();
			query3.append("SELECT languageId FROM Language WHERE language=?");
			jdbcUtil.setSqlAndParameters(query3.toString(), new Object[] {la});
			ResultSet rs3 = jdbcUtil.executeQuery();
			if(rs3.next()) {
				language = rs3.getInt("languageId");
			}
			
			//Ű����1 id�� ã�´�,
			StringBuilder query4 = new StringBuilder();
			query4.append("SELECT keywordId FROM keyword WHERE keyword=?");
			jdbcUtil.setSqlAndParameters(query4.toString(), new Object[] {k1});
			ResultSet rs4 = jdbcUtil.executeQuery();
			if(rs4.next()) {
				keyword1 = rs4.getInt("keywordId");
			}
			
			//Ű����2 id�� ã�´�,
			StringBuilder query5 = new StringBuilder();
			query5.append("SELECT keywordId FROM keyword WHERE keyword=?");
			jdbcUtil.setSqlAndParameters(query5.toString(), new Object[] {k2});
			ResultSet rs5 = jdbcUtil.executeQuery();
			if(rs5.next()) {
				keyword2 = rs5.getInt("keywordId");
			}
			
			//Ű����3 id�� ã�´�,
			StringBuilder query6 = new StringBuilder();
			query6.append("SELECT keywordId FROM keyword WHERE keyword=?");
			jdbcUtil.setSqlAndParameters(query6.toString(), new Object[] {k3});
			ResultSet rs6 = jdbcUtil.executeQuery();
			if(rs6.next()) {
				keyword3 = rs6.getInt("keywordId");
			}
						
		}catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.close();
		}		
		
	}
	
	//���ο� å ����
	public int createBook(Book book) throws SQLException {
		//id�� ã��
		findId(book.getCategory(), book.getItLevel(),book.getLanguage()
				, book.getKeyword1(), book.getKeyword2(), book.getKeyword3());	
		
		//å ����
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO Book(ISBN, title, author, publisher, publicationDate"
				+ ", cover_path, bookInfo, bookIndex, authorInfo, keywordId1, categoryId, "
				+ "levelId, languageId, keywordId2, keywordId3)"
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Object[] param = new Object[] {
				book.getISBN(), book.getTitle(), book.getAuthor(), book.getPublisher(),
				book.getPublicationDate(), book.getcover_path(), book.getBookInfo(),
				book.getBookIndex(), book.getAuthorInfo(), keyword1,
				category, level, language,
				keyword2, keyword3
		};
		jdbcUtil.setSqlAndParameters(query.toString(), param);
		
		try {				
			int result = jdbcUtil.executeUpdate();	
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
	
	// å ���� ����
	public int ModifyBook(Book book) throws SQLException{
		//id�� ã��
		findId(book.getCategory(), book.getItLevel(),book.getLanguage()
				, book.getKeyword1(), book.getKeyword2(), book.getKeyword3());	
		
		//å ����
		StringBuilder query = new StringBuilder();
		query.append("UPDATE Book set title=?, author=?, publisher=?, publicationDate=?,"
				+ "cover_path=?, bookInfo=?, BookIndex=?, authorInfo=?, keywordId1=?, categoryId=?,"
				+ " levelId=?, languageId=?, keywordId2=?, keywordId3=? "
				+ "WHERE ISBN=?");
	
		Object[] param = new Object[] {
				book.getTitle() ,book.getAuthor() ,
				book.getPublisher(),book.getPublicationDate(), 
				book.getcover_path(), book.getBookInfo(),
				book.getBookIndex(), book.getAuthorInfo(),
				keyword1, category,
				level, language, keyword2, keyword3
			};
		jdbcUtil.setSqlAndParameters(query.toString(), param);
		
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
	
	// ������ �����ϴ� å ����
	public int removeBook(String ISBN) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM Book WHERE ISBN=?");
		jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {ISBN});	
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
	
	//å�������� å �˻�
	public List<Book> findBookByTitle(String title) throws SQLException {
		
		StringBuilder query = new StringBuilder();
		query.append( "SELECT ISBN, title, cover_path, author, publisher, AVGSTAR "
     		   + "FROM Book "
     		   + "Where title LIKE ? "
     		   + "ORDER BY title");
		
		String likeTitle = "%" + title + "%";
		
		jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {likeTitle});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			List<Book> bookList = new ArrayList<Book>();	
			while (rs.next()) {
				System.out.print("sql 결과 있음");
				Book book = new Book(
					rs.getString("ISBN"),
					title,
					rs.getString("author"),
					rs.getString("publisher"),
					rs.getString("cover_path"),
					rs.getFloat("AVGSTAR")
						);	
				bookList.add(book);			
			}			
			return bookList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	
	//���ڷ� å �˻�
	public List<Book> findBookByAuthor(String author) throws SQLException {
	
		StringBuilder query = new StringBuilder();
		query.append("SELECT ISBN,title, cover_path, author, publisher, AVGSTAR"
     		   + "FROM Book "
     		   + "Where author LIKE '%?%' "
     		   + "ORDER BY author");
		jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {author});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			List<Book> bookList = new ArrayList<Book>();	
			while (rs.next()) {				
				Book book = new Book(
						rs.getString("ISBN"),
						rs.getString("title"),
						author,
						rs.getString("publisher"),
						rs.getString("cover_path"),
						rs.getFloat("AVGSTAR")
					);	
				bookList.add(book);			
			}			
			return bookList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	//���ǻ�� å �˻�
	public List<Book> findBookByPublisher(String publisher) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ISBN,title, cover_path, author, publisher, AVGSTAR"
     		   + "FROM Book "
     		   + "Where publisher LIKE '%?%' "
     		   + "ORDER BY publisher");
		jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {publisher});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			List<Book> bookList = new ArrayList<Book>();	
			while (rs.next()) {				
				Book book = new Book(
						rs.getString("ISBN"),
						rs.getString("title"),
						rs.getString("author"),
						publisher,
						rs.getString("cover_path"),
						rs.getFloat("AVGSTAR")
					);	
				bookList.add(book);			
			}			
			return bookList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	//Ű����� å �˻�
	public List<Book> findBookByKeyword(String keyword) throws SQLException {
		//Ű����1 id�� ã�´�,
		int keywordId = 0;
		StringBuilder query4 = new StringBuilder();
		query4.append("SELECT keywordId FROM keyword WHERE keyword=?");
		jdbcUtil.setSqlAndParameters(query4.toString(), new Object[] {keyword});
		ResultSet rs4 = jdbcUtil.executeQuery();
		if(rs4.next()) {
			keywordId = rs4.getInt("keywordId");
		}
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT ISBN,title, cover_path, author, publisher, AVGSTAR"
     		   + "FROM Book"
     		   + "Where keywordId1 = ? OR keywordId2 = ? OR keywordId3 = ?  "
     		   + "ORDER BY ISBN");
		jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {keywordId, keywordId, keywordId});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			List<Book> bookList = new ArrayList<Book>();	
			while (rs.next()) {				
				Book book = new Book(
						rs.getString("ISBN"),
						rs.getString("title"),
						rs.getString("author"),
						rs.getString("publisher"),
						rs.getString("cover_path"),
						rs.getFloat("AVGSTAR")
					);	
				bookList.add(book);			
			}			
			return bookList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	
	//���� å ����Ʈ
	public List<Book> findRandomBook() throws SQLException{
		StringBuilder query = new StringBuilder();
		query.append("SELECT ISBN,title, cover_path, author, publisher, AVGSTAR "); 
     	query.append("FROM (select * FROM book order by dbms_random.random)");
     	query.append("where rownum<=6");
		jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{});
     	
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			
			List<Book> bookList = new ArrayList<Book>();
			while(rs.next()) {
				Book book = new Book(
						rs.getString("ISBN"),
						rs.getString("title"),
						rs.getString("author"),
						rs.getString("publisher"),
						rs.getString("cover_path"),
						rs.getFloat("AVGSTAR")
					);	
				bookList.add(book);		
			}
			return bookList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	//����Ʈ���� Ư�� å�� Ŭ��(å �ڼ��� ����)
	public Book findBookInfo(String ISBN) throws SQLException{
		StringBuilder query = new StringBuilder();
		query.append("SELECT ISBN,title, cover_path, author, publisher, publicationDate,"
				+ "bookInfo, bookIndex, authorInfo, keywordId1, AVGSTAR, "
				+ "categoryId , itLevelId, languageId, keywordId2, keywordId3");
		query.append("FROM Book and Category ");
		query.append("WHERE ISBN = ?");
		jdbcUtil.setSqlAndParameters(query.toString(), new Object[]{ISBN});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			String cateN, levelName, lanName, k1Name, k2Name, k3Name;
			cateN = levelName = lanName = k1Name = k2Name = k3Name = null;
			if(rs.next()) {
				//ī�װ� name�� ã�´�.
				StringBuilder query1 = new StringBuilder();
				query1.append("SELECT category FROM Category WHERE categoryId=?");
				jdbcUtil.setSqlAndParameters(query1.toString(), new Object[] {rs.getString("category")});
				ResultSet rs1= jdbcUtil.executeQuery();
				if(rs.next()) {
					cateN = rs1.getString("category");
				}
				
				//���� name�� ã�´�,
				StringBuilder query2 = new StringBuilder();
				query2.append("SELECT itLevel FROM ItLevel WHERE levelId=?");
				jdbcUtil.setSqlAndParameters(query2.toString(), new Object[] {rs.getString("itLevel")});
				ResultSet rs2 = jdbcUtil.executeQuery();
				if(rs2.next()) {
					levelName = rs2.getString("itLevel");
				}
				
				//��� name�� ã�´�,
				StringBuilder query3 = new StringBuilder();
				query3.append("SELECT language FROM Language WHERE languageId=?");
				jdbcUtil.setSqlAndParameters(query3.toString(), new Object[] {rs.getString("language")});
				ResultSet rs3 = jdbcUtil.executeQuery();
				if(rs3.next()) {
					lanName = rs3.getString("language");
				}
				
				//Ű����1 name�� ã�´�,
				StringBuilder query4 = new StringBuilder();
				query4.append("SELECT keyword FROM keyword WHERE keywordId=?");
				jdbcUtil.setSqlAndParameters(query4.toString(), new Object[] {rs.getString("keywordId1")});
				ResultSet rs4 = jdbcUtil.executeQuery();
				if(rs4.next()) {
					k1Name = rs4.getString("keyword");
				}
				
				//Ű����2 name�� ã�´�,
				StringBuilder query5 = new StringBuilder();
				query5.append("SELECT keyword FROM keyword WHERE keywordId=?");
				jdbcUtil.setSqlAndParameters(query5.toString(), new Object[] {rs.getString("keywordId2")});
				ResultSet rs5 = jdbcUtil.executeQuery();
				if(rs5.next()) {
					k2Name = rs5.getString("keyword");
				}
				
				//Ű����3 name�� ã�´�,
				StringBuilder query6 = new StringBuilder();
				query6.append("SELECT keyword FROM keyword WHERE keywordId=?");
				jdbcUtil.setSqlAndParameters(query6.toString(), new Object[] {rs.getString("keywordId3")});
				ResultSet rs6 = jdbcUtil.executeQuery();
				if(rs6.next()) {
					k3Name = rs6.getString("keyword");
				}
				
				Book book = new Book(	
						ISBN,
						rs.getString("title"),
						rs.getString("author"),
						rs.getString("publisher"),
						rs.getString("publicationDate"),
						rs.getString("cover_path"),
						rs.getString("bookInfo"),
						rs.getString("bookIndex"),
						rs.getString("authorInfo"),
						cateN,
						levelName,
						lanName,
						k1Name,
						k2Name,
						k3Name,
						rs.getFloat("AVGSTAR"),
						null
						);
				return book;
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	//��õ å ����Ʈ ��������
	public List<RecommBook> findRecommList(String userId) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT isbn, title, cover_path, author, avgstar ");
        query.append("FROM Book b ");
        query.append("INNER JOIN InterestCat ic ON ic.categoryId = b.categoryId ");
        query.append("INNER JOIN InterestLang il ON il.languageId = b.languageId ");
        query.append("INNER JOIN Member m ON m.levelId = b.levelId ");
        query.append("WHERE m.userId = ?");
        
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {userId}); 
        try {
            ResultSet rs = jdbcUtil.executeQuery();                 
            List<RecommBook> recommBookList = new ArrayList<RecommBook>();    
            while (rs.next()) {
                RecommBook recommBook = new RecommBook(   
                    rs.getString("isbn"),
                    rs.getString("title"),
                    rs.getString("cover_path"),
                    rs.getString("author"),
                    rs.getDouble("avgstar"));  
                recommBookList.add(recommBook);             
            }           
            return recommBookList;                    
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();       
        }
        return null;
    } 
}
