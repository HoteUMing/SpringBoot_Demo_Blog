package Blog.entity;

import java.io.Serializable;

public class BlogDetail implements Serializable {

    private Integer blogID;/*文章ID*/
    private String blogTitle;/*文章标题*/
    private String blogKeyword;/*文章关键词*/
    private String blogSummary;/*文章摘要*/
    private String blogContent;/*文章内容*/
    private Long blogCreateTime;/*文章创建时间*/
    private Long blogModifyTime;/*文章更改时间*/
    private Integer userID;/*用户ID*/

    public Integer getBlogID() {
        return blogID;
    }

    public void setBlogID(Integer blogID) {
        this.blogID = blogID;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogKeyword() {
        return blogKeyword;
    }

    public void setBlogKeyword(String blogKeyword) {
        this.blogKeyword = blogKeyword;
    }

    public String getBlogSummary() {
        return blogSummary;
    }

    public void setBlogSummary(String blogSummary) {
        this.blogSummary = blogSummary;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public Long getBlogCreateTime() {
        return blogCreateTime;
    }

    public void setBlogCreateTime(Long blogCreateTime) {
        this.blogCreateTime = blogCreateTime;
    }

    public Long getBlogModifyTime() {
        return blogModifyTime;
    }

    public void setBlogModifyTime(Long blogModifyTime) {
        this.blogModifyTime = blogModifyTime;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "BlogDetail{" +
                "blogID=" + blogID +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogKeyword='" + blogKeyword + '\'' +
                ", blogSummary='" + blogSummary + '\'' +
                ", blogContent='" + blogContent + '\'' +
                ", blogCreateTime=" + blogCreateTime +
                ", blogModifyTime=" + blogModifyTime +
                ", userID=" + userID +
                '}';
    }

}
