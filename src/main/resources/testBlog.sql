DROP DATABASE IF EXISTS testBlog;

CREATE DATABASE IF NOT EXISTS testBlog;

USE testBlog;

/* ————————————————————用户登录表———————————————————— */

DROP TABLE IF EXISTS userLogin;

CREATE TABLE userLogin(
	userID INT PRIMARY KEY,/*用户ID*/
	userPassword VARCHAR(20)/*密码*/
);

INSERT INTO userLogin(userID,userPassword) VALUES(1,"123456");

SELECT * FROM userLogin;

/* ————————————————————用户信息表———————————————————— */

DROP TABLE IF EXISTS userInfo;

CREATE TABLE userInfo(
	userID INT PRIMARY KEY AUTO_INCREMENT,/*用户ID*/
	userName VARCHAR(10),/*昵称*/
	userGender VARCHAR(2) DEFAULT "未知",/*性别*/
	userDesc VARCHAR(20) DEFAULT "这个人很懒，什么都没有留下...",/*签名*/
	createTime BIGINT,/*创建时间*/
	modifyTime BIGINT/*更改时间*/
);

INSERT INTO userInfo(userName,createTime,modifyTime) VALUES("UMing",1587575647798,1587575647798);

SELECT * FROM userInfo;

/* ————————————————————角色表———————————————————— */

DROP TABLE IF EXISTS userRole;

CREATE TABLE userRole(
	roleID INT PRIMARY KEY AUTO_INCREMENT,/*角色ID*/
	roleName VARCHAR(5) /*角色*/
);

INSERT INTO userRole(roleName) VALUES("普通用户");
INSERT INTO userRole(roleName) VALUES("管理员");

SELECT * FROM userRole;

/* ————————————————————用户角色表———————————————————— */

DROP TABLE IF EXISTS roleList;

CREATE TABLE roleList(
	userID INT PRIMARY KEY,/*用户ID*/
	roleID INT/*角色ID*/
);

INSERT INTO roleList(userID,roleID) VALUES(1,1);

SELECT * FROM roleList;

/* ————————————————————文章表———————————————————— */

DROP TABLE IF EXISTS BlogList;

CREATE TABLE BlogList(
	blogID INT PRIMARY KEY AUTO_INCREMENT,/*文章ID*/
	blogTitle VARCHAR(20),/*文章标题*/
	blogKeyword VARCHAR(50),/*文章关键词*/
	blogSummary TEXT,/*文章摘要*/
	blogContent TEXT,/*文章内容*/
	blogCreateTime BIGINT,/*文章创建时间*/
	blogModifyTime BIGINT,/*文章更改时间*/
	userID INT/*用户ID*/
);

INSERT INTO BlogList(blogTitle,blogKeyword,blogSummary,blogContent,blogCreateTime,blogModifyTime,userID) VALUES("正式的第一篇文章","临时笔记","在此次项目中的部分临时笔记",". 临时笔记，后面信息不再过多显示，仅参考示范",1587575881072,1587575881072,1);

SELECT * FROM BlogList;
