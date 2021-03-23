package Blog.entity;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private Integer userID;/*用户ID*/
    private String userName;/*昵称*/
    private String userGender;/*性别*/
    private String userDesc;/*签名*/
    private Long createTime;/*创建时间*/
    private Long modifyTime;/*更改时间*/

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userDesc='" + userDesc + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }

}
