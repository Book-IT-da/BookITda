package model;

import java.util.Date;

public class Answer {
    private int aId;
    private String answer;
    private int qId;
    private String userId;
    private String nickname;
    private Date createDate;
    
    public Answer() {}

    public Answer(int aId, String answer, int qId, String userId, Date createDate) {
        super();
        this.aId = aId;
        this.answer = answer;
        this.qId = qId;
        this.userId = userId;
        this.createDate = createDate;
    }

    public Answer(int aId, String answer, String nickname, int qId, Date createDate) {
        super();
        this.aId = aId;
        this.answer = answer;
        this.nickname = nickname;
        this.qId = qId;
        this.createDate = createDate;
    }

    public Answer(int aId, String answer, Date createDate) {
        super();
        this.aId = aId;
        this.answer = answer;
        this.createDate = createDate;
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Answer [aId=" + aId + ", answer=" + answer + ", qId=" + qId + ", userId=" + userId + ", createDate="
                + createDate + "]";
    }
}