package Blog.entity;

import java.io.Serializable;

public class UserLogin implements Serializable {

    private Integer userID;/*用户ID*/
    private String userPassword;/*密码*/

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "userID=" + userID +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }

}
