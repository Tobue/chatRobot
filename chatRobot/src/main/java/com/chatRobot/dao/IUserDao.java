package com.chatRobot.dao;

import com.chatRobot.model.User;

import java.util.Map;

/**
 * Created by Administrator on 2017/12/28.
 */
public interface IUserDao {
    User getUser(Map<String, String> map);

    User getuserByemail(Map<String, String> map);
}
