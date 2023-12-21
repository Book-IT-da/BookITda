package model.dao.jdbc;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Book;
import model.RecommBook;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

//책 정보를 database에 연동하여 데이터를 처리,관리하기 위함.
public class BookDAO {
	private JDBCUtil jdbcUtil = null;
	private int category, level, language, keyword1, keyword2, keyword3;

	public BookDAO() {
		jdbcUtil = new JDBCUtil();
	}

	// 카테고리, 레벨, 언어, 키워드들의 id를 찾는다.
	public void findId(String c, String le, String la, String k1, String k2, String k3) throws SQLException {

		try {
			// 카테고리 id를 찾는다.
			StringBuilder query1 = new StringBuilder();
			query1.append("SELECT categoryId FROM Category WHERE category=?");

			jdbcUtil.setSqlAndParameters(query1.toString(), new Object[] { c });
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				category = rs.getInt("categoryId");
			}

			// 레벨 id를 찾는다,
			StringBuilder query2 = new StringBuilder();
			;
			query2.append("SELECT levelId FROM ItLevel WHERE itLevel=?");
			jdbcUtil.setSqlAndParameters(query2.toString(), new Object[] { le });
			ResultSet rs2 = jdbcUtil.executeQuery();
			if (rs2.next()) {
				level = rs2.getInt("levelId");
			}

			// 언어 id를 찾는다,
			StringBuilder query3 = new StringBuilder();
			query3.append("SELECT languageId FROM Language WHERE language=?");
			jdbcUtil.setSqlAndParameters(query3.toString(), new Object[] { la });
			ResultSet rs3 = jdbcUtil.executeQuery();
			if (rs3.next()) {
				language = rs3.getInt("languageId");
			}

			// 키워드1 id를 찾는다,
			StringBuilder query4 = new StringBuilder();
			query4.append("SELECT keywordId FROM keyword WHERE keyword=?");
			jdbcUtil.setSqlAndParameters(query4.toString(), new Object[] { k1 });
			ResultSet rs4 = jdbcUtil.executeQuery();
			if (rs4.next()) {
				keyword1 = rs4.getInt("keywordId");
			}

			// 키워드2 id를 찾는다,
			StringBuilder query5 = new StringBuilder();
			query5.append("SELECT keywordId FROM keyword WHERE keyword=?");
			jdbcUtil.setSqlAndParameters(query5.toString(), new Object[] { k2 });
			ResultSet rs5 = jdbcUtil.executeQuery();
			if (rs5.next()) {
				keyword2 = rs5.getInt("keywordId");
			}

			// 키워드3 id를 찾는다,
			StringBuilder query6 = new StringBuilder();
			query6.append("SELECT keywordId FROM keyword WHERE keyword=?");
			jdbcUtil.setSqlAndParameters(query6.toString(), new Object[] { k3 });
			ResultSet rs6 = jdbcUtil.executeQuery();
			if (rs6.next()) {
				keyword3 = rs6.getInt("keywordId");
			}

		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
	}

	// 새로운 책 생성
	public int createBook(Book book) throws SQLException {
		// id들 찾기
		findId(book.getCategory(), book.getItLevel(), book.getLanguage(), book.getKeyword1(), book.getKeyword2(),
				book.getKeyword3());

		// date로 포맷
		String stringDate = book.getPublicationDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
		java.sql.Date date = null;
		try {
		    Date buffDate = sdf.parse(stringDate);
		    date = new java.sql.Date(buffDate.getTime());
		} catch (ParseException e) {
		    e.printStackTrace();
		}

		// 책 생성
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO Book(ISBN, title, author, publisher, PUBLICATIONDATE, "
				+ "cover_path, bookInfo, bookIndex, authorInfo, keywordId1, categoryId, "
				+ "LEVELID, LANGUAGEID, keywordId2, keywordId3) " 
				+ "VALUES(? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?)");

		Object[] param = new Object[] { book.getISBN(), book.getTitle(), book.getAuthor(), book.getPublisher(),
				date, book.getcover_path(), book.getBookInfo(), book.getBookIndex(), book.getAuthorInfo(),
				keyword1, category, level, language, keyword2, keyword3 };

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

	// 책 정보 수정
	public int ModifyBook(Book book) throws SQLException {
		// id들 찾기
		findId(book.getCategory(), book.getItLevel(), book.getLanguage(), book.getKeyword1(), book.getKeyword2(),
				book.getKeyword3());

		// 책 수정
		StringBuilder query = new StringBuilder();
		query.append("UPDATE Book set TITLE=?, AUTHOR=?, PUBLISHER=?, PUBLICATIONDATE=?, ");
		query.append("COVER_PATH=?, BOOKINFO=?, BOOKINDEX=?, AUTHORINFO=?, ");
		query.append("KEYWORDID1=?, CATEGORYID=?, LEVELID=?, LANGUAGEID=?, ");
		query.append("KEYWORDID2=?, KEYWORDID3=? WHERE ISBN=? ");
	
		String stringDate = book.getPublicationDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
		java.sql.Date date = null;
		try {
		    Date buffDate = sdf.parse(stringDate);
		    date = new java.sql.Date(buffDate.getTime());
		} catch (ParseException e) {
		    e.printStackTrace();
		}

		Object[] param = new Object[] { book.getTitle(), book.getAuthor(), book.getPublisher(), 
				date, book.getcover_path(), book.getBookInfo(), book.getBookIndex(), 
				book.getAuthorInfo(), keyword1, category,
				level, language, keyword2, keyword3, book.getISBN() };
		
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

	// 기존에 존재하던 책 삭제
	public int removeBook(String ISBN) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM Book WHERE ISBN=?");
		jdbcUtil.setSqlAndParameters(query.toString(), new Object[] { ISBN });
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

	// 책제목으로 책 검색
	public List<Book> findBookByTitle(String title) throws SQLException {

		StringBuilder query = new StringBuilder();
		query.append("SELECT ISBN, title, cover_path, author, publisher, AVGSTAR " + "FROM Book "
				+ "Where title LIKE ? " + "ORDER BY title");

		String likeTitle = "%" + title + "%";

		jdbcUtil.setSqlAndParameters(query.toString(), new Object[] { likeTitle });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Book> bookList = new ArrayList<Book>();
			while (rs.next()) {
				Book book = new Book(rs.getString("ISBN"), rs.getString("title"), rs.getString("author"),
						rs.getString("publisher"), rs.getString("cover_path"), rs.getFloat("AVGSTAR"));
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

	// 저자로 책 검색
	public List<Book> findBookByAuthor(String author) throws SQLException {

		StringBuilder query = new StringBuilder();
		query.append("SELECT ISBN, title, cover_path, author, publisher, AVGSTAR " + "FROM Book "
				+ "Where author LIKE ? " + "ORDER BY author");
		String likeAuthor = "%" + author + "%";
		jdbcUtil.setSqlAndParameters(query.toString(), new Object[] { likeAuthor });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Book> bookList = new ArrayList<Book>();
			while (rs.next()) {
				Book book = new Book(rs.getString("ISBN"), rs.getString("title"), rs.getString("author"),
						rs.getString("publisher"), rs.getString("cover_path"), rs.getFloat("AVGSTAR"));
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

	// 출판사로 책 검색
	public List<Book> findBookByPublisher(String publisher) throws SQLException {
		StringBuilder query = new StringBuilder();


		query.append("SELECT ISBN,title, cover_path, author, publisher, AVGSTAR " + "FROM Book "
				+ "Where publisher LIKE ? " + "ORDER BY publisher");
		String likePublisher = "%" + publisher + "%";
		jdbcUtil.setSqlAndParameters(query.toString(), new Object[] { likePublisher });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Book> bookList = new ArrayList<Book>();
			while (rs.next()) {
				Book book = new Book(rs.getString("ISBN"), rs.getString("title"), rs.getString("author"),
						rs.getString("publisher"), rs.getString("cover_path"), rs.getFloat("AVGSTAR"));
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

	// 키워드로 책 검색
	public List<Book> findBookByKeyword(String keyword) throws SQLException {
		StringBuilder query1 = new StringBuilder();
	
		query1.append("SELECT DISTINCT ISBN, title, cover_path, author, publisher, AVGSTAR ");
		query1.append("FROM Book b ");
		query1.append("Where b.keywordid1 IN (Select keywordid from Keyword Where keyword LIKE ?) OR ");
		query1.append("b.keywordid2 IN (Select keywordid from Keyword Where keyword LIKE ?) OR ");
		query1.append("b.keywordid3 IN (Select keywordid from Keyword Where keyword LIKE ?)");
		query1.append("ORDER BY ISBN");
		
		String likeKeyword = "%" + keyword + "%";
		
		jdbcUtil.setSqlAndParameters(query1.toString(), new Object[] { likeKeyword, likeKeyword, likeKeyword });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Book> bookList = new ArrayList<Book>();
			while (rs.next()) {
				Book book = new Book(rs.getString("ISBN"), rs.getString("title"), rs.getString("author"),
						rs.getString("publisher"), rs.getString("cover_path"), rs.getFloat("AVGSTAR"));
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

	// 랜던 책 리스트
	public List<Book> findRandomBook() throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ISBN,title, cover_path, author, publisher, AVGSTAR ");
		query.append("FROM (select * FROM book order by dbms_random.random) ");
		query.append("where rownum<=10");
		jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {});

		try {
			ResultSet rs = jdbcUtil.executeQuery();

			List<Book> bookList = new ArrayList<Book>();
			while (rs.next()) {
				Book book = new Book(rs.getString("ISBN"), rs.getString("title"), rs.getString("author"),
						rs.getString("publisher"), rs.getString("cover_path"), rs.getFloat("AVGSTAR"));
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
	
	//모든 책 리스트
	public List<Book> findAllBook() throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ISBN,title, cover_path, author, publisher, AVGSTAR ");
		query.append("FROM Book");
		jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {});

		try {
			ResultSet rs = jdbcUtil.executeQuery();

			List<Book> bookList = new ArrayList<Book>();
			while (rs.next()) {
				Book book = new Book(rs.getString("ISBN"), rs.getString("title"), rs.getString("author"),
						rs.getString("publisher"), rs.getString("cover_path"), rs.getFloat("AVGSTAR"));
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

	// 리스트에서 특정 책을 클릭(책 자세히 보기)	
	public Book findBookInfo(String ISBN) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ISBN, title, cover_path, author, publisher, publicationDate, ");
		query.append("bookInfo, bookIndex, authorInfo, AVGSTAR, ");
		query.append("c.category, i.ITLEVEL, l.LANGUAGE, ");
		query.append("k1.keyword AS keyword1, k2.keyword  AS keyword2, k3.keyword AS keyword3 ");
		query.append("FROM Book b ");
		query.append("JOIN category c ON b.categoryid = c.categoryid ");
		query.append("JOIN itlevel i ON b.levelid = i.levelid ");
		query.append("JOIN language l ON b.languageid = l.languageid ");
		query.append("JOIN keyword k1 ON b.keywordid1 = k1.keywordid ");
		query.append("JOIN keyword k2 ON b.keywordid2 = k2.keywordid ");
		query.append("JOIN keyword k3 ON b.keywordid3 = k3.keywordid ");
		query.append("WHERE ISBN = ?");
		jdbcUtil.setSqlAndParameters(query.toString(), new Object[] { ISBN });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {

				String title = rs.getString("title");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				String publicationDate = rs.getString("publicationDate");
				String cover = rs.getString("cover_path");
				String bookInfo = rs.getString("bookInfo");
				String bookIndex = rs.getString("bookIndex");
				String authorInfo = rs.getString("authorInfo");
				Float star = rs.getFloat("AVGSTAR");

				String cateN = rs.getString("category");
				String levelName = rs.getString("itlevel");
				String lanName = rs.getString("language");
				String k1Name = rs.getString("keyword1");
				String k2Name = rs.getString("keyword2");
				String k3Name = rs.getString("keyword3");
				
				Book book = new Book(ISBN, title, author, publisher, publicationDate, cover, bookInfo, bookIndex,
						authorInfo, cateN, levelName, lanName, k1Name, k2Name, k3Name, star);
				return book;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	//도서 추천을 위함
	public List<RecommBook> findRecommList(String userId) throws SQLException {
        StringBuilder query = new StringBuilder();

        query.append("SELECT * FROM (");
        query.append("    SELECT DISTINCT b.isbn, b.title, b.cover_path, b.author, b.avgstar, ");
        query.append("    CASE "
                    + "    WHEN ic.categoryId IS NOT NULL AND il.languageId IS NOT NULL THEN 1 "
                    + "    WHEN ic.categoryId IS NOT NULL OR il.languageId IS NOT NULL THEN 2 "
                    + "    ELSE 3 "
                    + "    END as custom_order ");
        query.append("    FROM Book b ");
        query.append("    JOIN Member m ON m.levelId = b.levelId ");
        query.append("    LEFT JOIN InterestCat ic ON ic.userId = m.userId AND ic.categoryId = b.categoryId ");
        query.append("    LEFT JOIN InterestLang il ON il.userId = m.userId AND il.languageId = b.languageId ");
        query.append("    WHERE m.userId = ? ");
        query.append("    ORDER BY custom_order ");
        query.append(") WHERE ROWNUM <= 6");
        
        jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {userId}); 
        try {
            ResultSet rs = jdbcUtil.executeQuery();                 
            List<RecommBook> recommBookList = new ArrayList<RecommBook>();    
            while (rs.next()) {
                RecommBook recommBook = new RecommBook(   
                    rs.getString("isbn"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("cover_path"),
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
