package model;

import java.util.List;
import java.util.Iterator;

/**
 * 책 정보 관리
 */
public class Book {
	private String ISBN;	//책 고유 번호
	private String title;	// 책 제목
	private String author;	// 책 저자
	private String publisher;	// 책 출판사
	private String publicationDate;		// 책 출판일자
	private String cover_path;	//책 표지
	private String bookInfo;	// 책 정보
	private String bookIndex;	// 책 목차
	private String authorInfo;	// 책 저자 정보
	private String category;
	private String itLevel;
	private String language;
	private String keyword1;	// 책 키워드
	private String keyword2;
	private String keyword3;
	private float averageStar;	// 책 총 평점
	private List<Review> reviews; //리뷰들을 reviews에 모아둔다.
		
	//생성자
	public Book() {}
	
	public Book(String iSBN, String title, String author, String publisher, String publicationDate, String cover_path,
			String bookInfo, String bookIndex, String authorInfo, String category, String itLevel, String language,
			String keyword1, String keyword2, String keyword3, float averageStar, List<Review> reviews) {
		super();
		ISBN = iSBN;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publicationDate = publicationDate;
		this.cover_path = cover_path;
		this.bookInfo = bookInfo;
		this.bookIndex = bookIndex;
		this.authorInfo = authorInfo;
		this.category = category;
		this.itLevel = itLevel;
		this.language = language;
		this.keyword1 = keyword1;
		this.keyword2 = keyword2;
		this.keyword3 = keyword3;
		this.averageStar = averageStar;
		this.reviews = reviews;
	}
	
	//reviews가 없는 생성자
	public Book(String iSBN, String title, String author, String publisher, String publicationDate, String cover_path,
			String bookInfo, String bookIndex, String authorInfo, String category, String itLevel, String language,
			String keyword1, String keyword2, String keyword3, float averageStar) {
		super();
		ISBN = iSBN;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publicationDate = publicationDate;
		this.cover_path = cover_path;
		this.bookInfo = bookInfo;
		this.bookIndex = bookIndex;
		this.authorInfo = authorInfo;
		this.category = category;
		this.itLevel = itLevel;
		this.language = language;
		this.keyword1 = keyword1;
		this.keyword2 = keyword2;
		this.keyword3 = keyword3;
		this.averageStar = averageStar;
	}
	
	
	public Book(String iSBN, String title, String author, String publisher, String cover_path, float averageStar) {
		super();
		ISBN = iSBN;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.cover_path = cover_path;
		this.averageStar = averageStar;
	}

	//getter, setter
	public void setTitle(String title) {
		this.title=title;
	}
	

	public String getTitle() {
		return title;
	}
	
	public void setover_path(String cover_path) {
		this.cover_path=cover_path;
	}
	
	public String getcover_path() {
		return cover_path;
	}
	
	public void setISBN(String ISBN) {
		this.ISBN=ISBN;
	}
	
	public String getISBN() {
		return ISBN;
	}
	
	public void setAuthor(String author) {
		this.author=author;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setPublisher(String publisher) {
		this.publisher=publisher;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublicationDate(String publicationDate) {
		this.publicationDate=publicationDate;
	}
	
	public String getPublicationDate() {
		return publicationDate;
	}

	public void setBookInfo(String bookInfo) {
		this.bookInfo=bookInfo;
	}
	
	public String getBookInfo() {
		return bookInfo;
	}
	
	public void setBookIndex(String bookIndex) {
		this.bookIndex=bookIndex;
	}
	
	public String getBookIndex() {
		return bookIndex;
	}
	
	public void setAuthorInfo(String authorInfo) {
		this.authorInfo=authorInfo;
	}
	
	public String getAuthorInfo() {
		return authorInfo;
	}
	
	
	public float getAverageStar() {
		return averageStar;
	}
	
	
	
	public String getCover_path() {
		return cover_path;
	}


	public void setCover_path(String cover_path) {
		this.cover_path = cover_path;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getItLevel() {
		return itLevel;
	}


	public void setItLevel(String itLevel) {
		this.itLevel = itLevel;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public List<Review> getReviews() {
		return reviews;
	}


	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}


	public void setAverageStar(float averageStar) {
		this.averageStar = averageStar;
	}
	
	public String getKeyword1() {
		return keyword1;
	}

	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}

	public String getKeyword2() {
		return keyword2;
	}

	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}

	public String getKeyword3() {
		return keyword3;
	}

	public void setKeyword3(String keyword3) {
		this.keyword3 = keyword3;
	}

	//평점 계산 후 저장 , 리뷰가 갱신될 때마다 호출되어야 함.
	public void setAverageStar() {
		Iterator<Review> iterator= reviews.iterator();
		int total=0;
		int count=0;
		
		while(iterator.hasNext()) {
			total+=iterator.next().getStars();
			count++;
		}
		averageStar=total/count;
	}
	 
	 @Override
		public String toString() {
			return "User [title=" + title + ", author=" + author + ", ISBN=" + ISBN + ", publisher=" + publisher + ", publicationDate="
					+ publicationDate + "bookInfo=" + bookInfo + ", bookIndex=" + bookIndex + "authorInfo=" + authorInfo  + "]";
		}	
	
	
}
