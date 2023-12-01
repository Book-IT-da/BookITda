package model;

import java.util.List;
//사용자 흥미 카테고리, 언어, 수준
public class Interest {
    List<Integer> categoryList;
    List<Integer> languageList;
    int levelId;

    public Interest(List<Integer> categoryList, List<Integer> languageList, int levelId) {
        super();
        this.categoryList = categoryList;
        this.languageList = languageList;
        this.levelId = levelId;
    }

    public List<Integer> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Integer> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Integer> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<Integer> languageList) {
        this.languageList = languageList;
    }
    
    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    @Override
    public String toString() {
        return "Interest [categoryList=" + categoryList + ", languageList=" + languageList + ", levelId=" + levelId
                + "]";
    }

}
