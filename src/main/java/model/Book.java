package model;

import java.util.List;
import java.util.Iterator;

/**
 * å ���� ����
 */
public class Book {
	private String ISBN;
	private String title;	//å����
	private String author;	//����
	private String publisher;	//���ǻ�
	private String publicationDate;	//��������
	private String cover_path;	//å ǥ��
	private String bookInfo;	//å ����
	private String bookIndex;	//å ����
	private String authorInfo;	//���� ����
	private String category;
	private String itLevel;
	private String language;
	private String keyword1;	//	Ű���� 3��
	private String keyword2;
	private String keyword3;
	private float averageStar;	//����
	private List<Review> reviews;  //������� reviews�� ��Ƶд�.
		
	//������
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
		return bookInfo;
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

	//���� ��� �� ���� , ���䰡 ���ŵ� ������ ȣ��Ǿ�� ��.
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
