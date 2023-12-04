package model.dao;

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
			System.out.println("c:" + c);
			query1.append("SELECT categoryId FROM Category WHERE category=?");

			jdbcUtil.setSqlAndParameters(query1.toString(), new Object[] { c });
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				category = rs.getInt("categoryId");
				System.out.println("1" + category);
			}

			// 레벨 id를 찾는다,
			StringBuilder query2 = new StringBuilder();
			System.out.println("le:" + le);
			;
			query2.append("SELECT levelId FROM ItLevel WHERE itLevel=?");
			jdbcUtil.setSqlAndParameters(query2.toString(), new Object[] { le });
			ResultSet rs2 = jdbcUtil.executeQuery();
			if (rs2.next()) {
				level = rs2.getInt("levelId");
				System.out.println("2" + level);
			}

			// 언어 id를 찾는다,
			StringBuilder query3 = new StringBuilder();
			query3.append("SELECT languageId FROM Language WHERE language=?");
			jdbcUtil.setSqlAndParameters(query3.toString(), new Object[] { la });
			ResultSet rs3 = jdbcUtil.executeQuery();
			if (rs3.next()) {
				language = rs3.getInt("languageId");
				System.out.println("3" + language);
			}

			// 키워드1 id를 찾는다,
			StringBuilder query4 = new StringBuilder();
			query4.append("SELECT keywordId FROM keyword WHERE keyword=?");
			jdbcUtil.setSqlAndParameters(query4.toString(), new Object[] { k1 });
			ResultSet rs4 = jdbcUtil.executeQuery();
			if (rs4.next()) {
				keyword1 = rs4.getInt("keywordId");
				System.out.println("4" + keyword1);
			}

			// 키워드2 id를 찾는다,
			StringBuilder query5 = new StringBuilder();
			query5.append("SELECT keywordId FROM keyword WHERE keyword=?");
			jdbcUtil.setSqlAndParameters(query5.toString(), new Object[] { k2 });
			ResultSet rs5 = jdbcUtil.executeQuery();
			if (rs5.next()) {
				keyword2 = rs5.getInt("keywordId");
				System.out.println("5" + keyword2);
			}

			// 키워드3 id를 찾는다,
			StringBuilder query6 = new StringBuilder();
			query6.append("SELECT keywordId FROM keyword WHERE keyword=?");
			jdbcUtil.setSqlAndParameters(query6.toString(), new Object[] { k3 });
			ResultSet rs6 = jdbcUtil.executeQuery();
			if (rs6.next()) {
				keyword3 = rs6.getInt("keywordId");
				System.out.println(keyword3);
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
		System.out.println(stringDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
		java.sql.Date date = null;
		try {
		    Date buffDate = sdf.parse(stringDate);
		    date = new java.sql.Date(buffDate.getTime());
		    System.out.println(date);
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
			System.out.print("!!!result!는 "+result+"이다");
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
		System.out.print("문제1");
		findId(book.getCategory(), book.getItLevel(), book.getLanguage(), book.getKeyword1(), book.getKeyword2(),
				book.getKeyword3());

		// 책 수정
		System.out.print("문제2");
		StringBuilder query = new StringBuilder();
		query.append("UPDATE Book set TITLE=?, AUTHOR=?, PUBLISHER=?, PUBLICATIONDATE=?, ");
		query.append("COVER_PATH=?, BOOKINFO=?, BOOKINDEX=?, AUTHORINFO=?, ");
		query.append("KEYWORDID1=?, CATEGORYID=?, LEVELID=?, LANGUAGEID=?, ");
		query.append("KEYWORDID2=?, KEYWORDID3=? WHERE ISBN=? ");
	
		String stringDate = book.getPublicationDate();
		System.out.println(stringDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
		java.sql.Date date = null;
		try {
		    Date buffDate = sdf.parse(stringDate);
		    date = new java.sql.Date(buffDate.getTime());
		    System.out.println(date);
		} catch (ParseException e) {
		    e.printStackTrace();
		}

		System.out.print("문제3");
		Object[] param = new Object[] { book.getTitle(), book.getAuthor(), book.getPublisher(), 
				date, book.getcover_path(), book.getBookInfo(), book.getBookIndex(), 
				book.getAuthorInfo(), keyword1, category,
				level, language, keyword2, keyword3, book.getISBN() };
		System.out.print("문제4");
		
		jdbcUtil.setSqlAndParameters(query.toString(), param);

		try {
			int result = jdbcUtil.executeUpdate();
			System.out.println("result = " + result);
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
				System.out.print("sql 결과 있음");
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
		System.out.print("sql 결과 findBookByAuthor");

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

		System.out.print("sql 결과 findBookByAuthor");

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
		int keywordId = 0;
		StringBuilder query1 = new StringBuilder();
		query1.append("SELECT keywordId FROM keyword WHERE keyword=?");
		jdbcUtil.setSqlAndParameters(query1.toString(), new Object[] { keyword });

		try {
			ResultSet rs4 = jdbcUtil.executeQuery();
			if (rs4.next()) {
				keywordId = rs4.getInt("keywordId");
			}
			System.out.print("  !!!!!! " + keywordId + " !!!!!!!!!!");

			StringBuilder query2 = new StringBuilder();
			query2.append("SELECT ISBN, title, cover_path, author, publisher, AVGSTAR " + "FROM Book "
					+ "Where keywordId1 = ? OR keywordId2 = ? OR keywordId3 = ? " + "ORDER BY ISBN");
			jdbcUtil.setSqlAndParameters(query2.toString(), new Object[] { keywordId, keywordId, keywordId });

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
		System.out.println("BookDAO의 findRandomBook");
		StringBuilder query = new StringBuilder();
		query.append("SELECT ISBN,title, cover_path, author, publisher, AVGSTAR ");
		query.append("FROM (select * FROM book order by dbms_random.random) ");
		query.append("where rownum<=6");
		jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {});

		try {
			System.out.print("execute 전");
			ResultSet rs = jdbcUtil.executeQuery();

			List<Book> bookList = new ArrayList<Book>();
			while (rs.next()) {
				Book book = new Book(rs.getString("ISBN"), rs.getString("title"), rs.getString("author"),
						rs.getString("publisher"), rs.getString("cover_path"), rs.getFloat("AVGSTAR"));
				bookList.add(book);
			}
			System.out.print("execute 완료");
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
		System.out.println("BookDAO의 findAllBook");
		StringBuilder query = new StringBuilder();
		query.append("SELECT ISBN,title, cover_path, author, publisher, AVGSTAR ");
		query.append("FROM Book");
		jdbcUtil.setSqlAndParameters(query.toString(), new Object[] {});

		try {
			System.out.print("execute 전");
			ResultSet rs = jdbcUtil.executeQuery();

			List<Book> bookList = new ArrayList<Book>();
			while (rs.next()) {
				Book book = new Book(rs.getString("ISBN"), rs.getString("title"), rs.getString("author"),
						rs.getString("publisher"), rs.getString("cover_path"), rs.getFloat("AVGSTAR"));
				bookList.add(book);
			}
			System.out.print("execute 완료");
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
		System.out.println("!!! " + ISBN + " !!");
		StringBuilder query = new StringBuilder();
		query.append("SELECT ISBN,title, cover_path, author, publisher, publicationDate, "
				+ "bookInfo, bookIndex, authorInfo, keywordId1, AVGSTAR, "
				+ "categoryId , LEVELID, languageId, keywordId2, keywordId3 ");
		query.append("FROM Book ");
		query.append("WHERE ISBN = ?");
		jdbcUtil.setSqlAndParameters(query.toString(), new Object[] { ISBN });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			String cateN, levelName, lanName, k1Name, k2Name, k3Name;
			cateN = levelName = lanName = k1Name = k2Name = k3Name = null;
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

				String cateId = rs.getString("categoryId");
				String levelId = rs.getString("levelId");
				String languageId = rs.getString("languageId");
				String k1 = rs.getString("keywordId1");
				String k2 = rs.getString("keywordId2");
				String k3 = rs.getString("keywordId3");

				// 카테고리 name을 찾는다.
				StringBuilder query1 = new StringBuilder();
				System.out.println(cateId);
				query1.append("SELECT category FROM Category WHERE categoryId=?");
				jdbcUtil.setSqlAndParameters(query1.toString(), new Object[] { cateId });
				ResultSet rs1 = jdbcUtil.executeQuery();
				if (rs1.next()) {
					cateN = rs1.getString("category");
				}

				// 레벨 name을 찾는다,
				StringBuilder query2 = new StringBuilder();
				System.out.println(levelId);
				query2.append("SELECT itLevel FROM ItLevel WHERE levelId=?");
				jdbcUtil.setSqlAndParameters(query2.toString(), new Object[] { levelId });
				ResultSet rs2 = jdbcUtil.executeQuery();
				if (rs2.next()) {
					levelName = rs2.getString("itLevel");
				}

				// 언어 name을 찾는다,
				StringBuilder query3 = new StringBuilder();
				System.out.println(languageId);
				query3.append("SELECT language FROM Language WHERE languageId=?");
				jdbcUtil.setSqlAndParameters(query3.toString(), new Object[] { languageId });
				ResultSet rs3 = jdbcUtil.executeQuery();
				if (rs3.next()) {
					lanName = rs3.getString("language");
				}

				// 키워드1 name을 찾는다,
				StringBuilder query4 = new StringBuilder();
				System.out.println(k1);
				query4.append("SELECT keyword FROM keyword WHERE keywordId=?");
				jdbcUtil.setSqlAndParameters(query4.toString(), new Object[] { k1 });
				ResultSet rs4 = jdbcUtil.executeQuery();
				if (rs4.next()) {
					k1Name = rs4.getString("keyword");
				}

				// 키워드2 name을 찾는다,
				StringBuilder query5 = new StringBuilder();

				System.out.println(k2);
				query5.append("SELECT keyword FROM keyword WHERE keywordId=?");
				jdbcUtil.setSqlAndParameters(query5.toString(), new Object[] { k2 });
				ResultSet rs5 = jdbcUtil.executeQuery();
				if (rs5.next()) {
					k2Name = rs5.getString("keyword");
				}

				// 키워드3 name을 찾는다,
				StringBuilder query6 = new StringBuilder();
				System.out.println(k3);
				query6.append("SELECT keyword FROM keyword WHERE keywordId=?");
				jdbcUtil.setSqlAndParameters(query6.toString(), new Object[] { k3 });
				ResultSet rs6 = jdbcUtil.executeQuery();
				if (rs6.next()) {
					k3Name = rs6.getString("keyword");
				}

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
	///��õ å ����Ʈ ���������
	public List<RecommBook> findRecommList(String userId) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT isbn, title, cover_path, author, avgstar ");
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
