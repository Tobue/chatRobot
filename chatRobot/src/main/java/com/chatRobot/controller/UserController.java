package com.chatRobot.controller;

import com.alibaba.fastjson.JSONObject;
import com.chatRobot.model.User;
import com.chatRobot.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/28.用户信息
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;
    /**
     *登录
     * @param request
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request){
        HttpSession session = request.getSession();
        String userString = request.getParameter("user");
        Map<String,Object> rtn = new HashMap<>();
        JSONObject jsonObject = JSONObject.parseObject(userString);
        String username = jsonObject.getString(User.USERNAME);
        //用户名存到session里面
        session.setAttribute("username",username);
        if(StringUtils.isEmpty(username)){
            rtn.put("success",false);
            rtn.put("message","用户名不能为空");
            return rtn;
        }
        String password = jsonObject.getString(User.PASSWORD);
        //把密码存到session里面
        session.setAttribute("password",password);
        if(StringUtils.isEmpty(password)){
            rtn.put("success",false);
            rtn.put("message","密码不能为空");
            return rtn;
        }

        Map<String,String> map = new HashMap<>();
        User user = null;
        if(username.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")){
            map.put("email",username);
            map.put("password",password);
            user = iUserService.getuserByemail(map);
        }else{
            map.put("username",username);
            map.put("password",password);
            user = iUserService.getUser(map);
        }
        try {
           if(null == user){
               rtn.put("success",false);
               rtn.put("message","用户名或密码错误");
               return rtn;
            }else{
               session.setAttribute("user",user);
               rtn.put("success",true);
               rtn.put("message","登录成功");
               return rtn;
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtn;
    }

    /**
     * 登出
     * @param request
     */
    @RequestMapping(value = "logout",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> logout(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        try {
            request.getSession().removeAttribute("user");
            map.put("success",true);
            map.put("message","登出成功");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
