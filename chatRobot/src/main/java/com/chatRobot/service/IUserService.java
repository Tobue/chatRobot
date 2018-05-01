package com.chatRobot.service;

import com.chatRobot.model.User;

import java.util.Map;

/**
 * Created by Administrator on 2017/12/28.
 */
public interface IUserService {
    User getUser(Map<String,String> map);

    User getuserByemail(Map<String, String> map);
}
