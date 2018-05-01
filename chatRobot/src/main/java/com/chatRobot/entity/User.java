package com.chatRobot.entity;

import com.google.gson.annotations.SerializedName;


import java.util.List;

public class User {

    @SerializedName("oId")
    private String oId;

    @SerializedName("userName")
    private String userName;

    @SerializedName("userNickname")
    private String userNickname;

    @SerializedName("userAvatarURL210")
    private String userAvatarURL210;

    @SerializedName("userAvatarURL48")
    private String userAvatarURL48;

    @SerializedName("userCheckinTime")
    private String userCheckinTime;

    @SerializedName("userIntro")
    private String userIntro;

    @SerializedName("userNo")
    private Integer userNo;

    @SerializedName("userCommentCount")
    private Integer userCommentCount;

    @SerializedName("userArticleCount")
    private Integer userArticleCount;

    @SerializedName("userPoint")
    private Integer userPoint;

    @SerializedName("userTags")
    private String userTags;

    @SerializedName("userURL")
    private String userURL;

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserAvatarURL210() {
        return userAvatarURL210;
    }

    public void setUserAvatarURL210(String userAvatarURL210) {
        this.userAvatarURL210 = userAvatarURL210;
    }

    public String getUserAvatarURL48() {
        return userAvatarURL48;
    }

    public void setUserAvatarURL48(String userAvatarURL48) {
        this.userAvatarURL48 = userAvatarURL48;
    }

    public String getUserCheckinTime() {
        return userCheckinTime;
    }

    public void setUserCheckinTime(String userCheckinTime) {
        this.userCheckinTime = userCheckinTime;
    }

    public String getUserIntro() {
        return userIntro;
    }

    public void setUserIntro(String userIntro) {
        this.userIntro = userIntro;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getUserCommentCount() {
        return userCommentCount;
    }

    public void setUserCommentCount(Integer userCommentCount) {
        this.userCommentCount = userCommentCount;
    }

    public Integer getUserArticleCount() {
        return userArticleCount;
    }

    public void setUserArticleCount(Integer userArticleCount) {
        this.userArticleCount = userArticleCount;
    }

    public Integer getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(Integer userPoint) {
        this.userPoint = userPoint;
    }

    public String getUserTags() {
        return userTags;
    }

    public void setUserTags(String userTags) {
        this.userTags = userTags;
    }

    public String getUserURL() {
        return userURL;
    }

    public void setUserURL(String userURL) {
        this.userURL = userURL;
    }
}
