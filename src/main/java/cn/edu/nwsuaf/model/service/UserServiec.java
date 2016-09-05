package cn.edu.nwsuaf.model.service;

import cn.edu.nwsuaf.model.dao.UserDao;
import cn.edu.nwsuaf.model.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <程序说明>
 *
 * @author zq@nwsuaf.edu.cn
 *         2016/7/14 16:38.
 */
@Service
public class UserServiec {
    @Resource
    UserDao userDao;
    public User getUser(User user){
        if(user == null) user = new User();
        return userDao.get(user);
    }

    public List<User> getUsers(Map map){
        return userDao.find(map);
    }

    //获得查询结果数，用于分页查询
   public Integer getUserCount(Map map){
        return userDao.getUserCount(map);
    }

    public List<User> getAllUser(){
        return userDao.getAllUser();
    }

    public Integer insert(User user){
        return userDao.insert(user);
    }

    public Integer update(User user){
        return userDao.update(user);
    }

    public Integer deleteUser(String id){
        return userDao.delete(Integer.parseInt(id));
    }

    public User getUserByName(String name){
        return userDao.getUserByName(name);
    }
}
