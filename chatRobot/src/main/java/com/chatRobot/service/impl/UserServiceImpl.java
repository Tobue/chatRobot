package com.chatRobot.service.impl;

import com.chatRobot.dao.IUserDao;
import com.chatRobot.model.User;
import com.chatRobot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2017/12/28.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao iUserDao;

    @Override
    public User getUser(Map<String, String> map) {
        return iUserDao.getUser(map);
    }

    @Override
    public User getuserByemail(Map<String, String> map) {
        return iUserDao.getuserByemail(map);
    }
}
