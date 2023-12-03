package model;

import java.util.Date;

public class Question {
    private int qId;
    private String title;
    private String qContent;
    private int views; 
    private String userId;
    private String nickname;
    private Date createDate;
    
    public Question() { }

    public Question(int qId, String title, String qContent, int views, String userId, String nickname,
			Date createDate) {
		super();
		this.qId = qId;
		this.title = title;
		this.qContent = qContent;
		this.views = views;
		this.userId = userId;
		this.nickname = nickname;
		this.createDate = createDate;
	}

	public Question(int qId, String title, String qContent, int views, String userId, Date createDate) {
        super();
        this.qId = qId;
        this.title = title;
        this.qContent = qContent;
        this.views = views;
        this.userId = userId;
        this.createDate = createDate;
    }
    
    public Question(int qId, String title, int views, String nickname, Date createDate) {
        super();
        this.qId = qId;
        this.title = title;
        this.views = views;
        this.nickname = nickname;
        this.createDate = createDate;
    }

    public Question(int qId, String title, Date createDate) {
        super();
        this.qId = qId;
        this.title = title;
        this.createDate = createDate;
    }

    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getqContent() {
        return qContent;
    }

    public void setqContent(String qContent) {
        this.qContent = qContent;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    
    public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Question [qId=" + qId + ", title=" + title + ", qContent=" + qContent + ", views=" + views + ", userId="
                + userId + ", createDate=" + createDate + "]";
    }
}