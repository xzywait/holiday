package cn.edu.nwsuaf.model.dao;

import cn.edu.nwsuaf.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 校园植物<程序说明>
 *
 * @author zq@nwsuaf.edu.cn
 *         2016/7/14 16:20.
 */
@Repository
public interface UserDao {
    User get(User user);

    List<User> getAllUser();

    List<User> find(Map map);

    Integer insert(User user);

    Integer update(User user);

    Integer delete(int id);

    //获得查询结果数，用于分页查询
    Integer getUserCount(Map map);

    User getUserByName(String name);
}
