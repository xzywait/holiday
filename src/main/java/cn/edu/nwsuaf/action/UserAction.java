package cn.edu.nwsuaf.action;

import cn.edu.nwsuaf.model.entity.User;
import cn.edu.nwsuaf.model.service.UserServiec;

import javax.annotation.Resource;
import java.util.*;

/**
 * <程序说明>
 *
 * @author lxp
 *         2016/8/14 14:42.
 */
public class UserAction {
    private String id;//增加或修改的字段
    private String name;
    private String passwd;
    private String createDate;
    private String realName;//查询、修改、添加的真实姓名
    private String username;//查询的用户名
    private String createTimeStart;//创建时间段的开始时间
    private String createTimeEnd;//创建时间段的结束时间
    private String sort;//排序字段
    private String order;//排序方式
    private int page;//当前页数
    private int rowsInPage;//每页显示记录数(前台传过来的参数为rows，在set方法中给此变量赋值)
    private int total;//后台返回的结果记录数
    private List<User> rows;//后台返回的结果

    private JsonResult result = new JsonResult();//返回给前台的操作结果


    private String ids;//批量删除的所有id

    @Resource
    UserServiec userServiec;

    /* *
    * 页面重定向
    * */
    public String execute() {
        return "success";
    }

    public String list() {
        System.out.println("============================查询所有===========================");
        rows = userServiec.getAllUser();
        total = rows.size();

        for (User s : rows) {
            System.out.println(s.toString());
        }
        return "success";
    }

    /* *
    * 多条件分页查询
    * */
    public String searchUser() {
        System.out.println("============================多条件查询===========================");
        System.out.println("rows=" + rowsInPage + ",page=" + page);
        System.out.println("username:" + username + ",realName:" + realName + ",createTimeStart=" + createTimeStart + ",createTimeEnd=" + createTimeEnd);
        System.out.println("sort:" + sort + ",order" + order);

        Map map = new HashMap();
        map.put("username", username);
        map.put("realName", realName);
        map.put("createTimeStart", createTimeStart);
        map.put("createTimeEnd", createTimeEnd);
        map.put("sort", sort);
        map.put("order", order);
        map.put("pageStart", (page - 1) * rowsInPage);
        map.put("rows", rowsInPage);

        rows = userServiec.getUsers(map);
        total = userServiec.getUserCount(map);

        for (User s : rows) {
            System.out.println(s.toString());
        }
        return "success";
    }

    /* *
    * 添加用户信息
    * */
    public String addUser() {
        System.out.println("===================添加用户======================");
        System.out.println("name:" + name + ",realName:" + realName + ",id=" + id + ",password=" + passwd);

        //先查询插入的用户名是否已存在

        User returnUser = userServiec.getUserByName(name);
        if (returnUser != null) {
            result.setSuccess(false);
            result.setMsg("添加【" + name + "】用户失败，已存在此用户！");
        } else {
            User user = new User();

            user.setName(name);
            user.setPasswd(passwd);
            user.setRealName(realName);

            int insertResult = userServiec.insert(user);
            if (insertResult > 0) {
                result.setSuccess(true);
                result.setMsg("该用户名插入成功");

            } else {
                result.setSuccess(false);
                result.setMsg("添加【" + name + "】用户失败，已存在此用户！");
            }
        }
        return "success";
    }

    /* *
    * 修改用户信息
    * */
    public String editUser() {
        System.out.println("===================修改用户======================");
        System.out.println("name:" + name + ",realName:" + realName + ",id=" + id + ",password=" + passwd + "createDate=" + createDate);

        User user = new User();
        user.setId(Integer.valueOf(id));
        user.setName(name);
        user.setPasswd(passwd);
        user.setRealName(realName);

        int t = userServiec.update(user);
        System.out.println(t);
        result.setSuccess(true);
        result.setMsg("该用户名修改成功");
        System.out.println("该用户名修改成功");

        return "success";
    }

    /* *
    * 批量删除用户信息
    * */
    public String deleteUsers() {
        System.out.println("===================删除用户===================");
        System.out.println("删除用户的ids:"+ids);

        String userIds[] = ids.split(",");
        for(int i=0;i<userIds.length;i++){
            userServiec.deleteUser(userIds[i]);
        }
        return "success";
    }

    /**
     * ================Gette、setter=============
     */
    public void setIds(String ids) {

        this.ids = ids;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public JsonResult getResult() {
        return result;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getPage() {
        return page;
    }

    public int getRowsInPage() {
        return rowsInPage;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public List<User> getRows() {
        return rows;
    }

    public void setRows(int rows) {//给每页显示记录数赋值
        this.rowsInPage = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setSort(String sort) {
        this.sort = sort;
        if(this.sort.equals("realName")){
            this.sort = "real_name";
        }
        if(this.sort.equals("createDate")){
            this.sort = "create_date";
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setCreateTimeStart(String createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

}
