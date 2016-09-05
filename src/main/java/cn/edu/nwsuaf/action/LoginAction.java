package cn.edu.nwsuaf.action;

import cn.edu.nwsuaf.model.entity.User;
import cn.edu.nwsuaf.model.service.UserServiec;

import javax.annotation.Resource;

/**
 * Created by luxiaoping on 2016/8/10.
 */
public class LoginAction {
    private String username;
    private String password;

    @Resource
    private UserServiec userServiec;

    public String login() {
        System.out.println("username:" + username + ",password:" + password);

        User user = new User();
        user.setName(username);
        user.setPasswd(password);

        User returnUser = userServiec.getUser(user);
        if (returnUser != null) {
            return "success";
        } else {
            return "error";
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
