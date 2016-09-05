<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/14
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${rootPath}/css/main.css" />
    <script src="${rootPath}/js/jquery.min.js"></script>
    <script src="${rootPath}/js/main.js"></script>
    <title>用户管理</title>
</head>
<body>
    <div class="container">
      <div class="header">
        <%@include file="../include/header.jsp"%>
      </div>
      <div class="middle">
        <div class="leftMenu">
          <%@include file="../include/leftmenu.jsp"%>
        </div>
        <div class="mainFrame">
          <table border="1" width="90%">
              <caption style="position: relative;font-size: 14pt;margin-bottom: 10px">用户列表
                 <a href="user_add" style="position: absolute;right: 20px;bottom: -10px">添加</a>
              </caption>
              <tr>
                  <th width="100">用户名</th>
                  <th>真实姓名</th>
                  <th>创建日期</th>
                  <th width="160">操作</th>
              </tr>
              <s:iterator value="users" var="user">
                  <tr>
                      <td>${user.name}</td>
                      <td>${user.realName}</td>
                      <td><s:date name="#user.createDate"  format="yyyy-MM-dd HH:mm" /></td>
                      <td align="center">
                          <a href="user_edit?user.id=${user.id}">编辑</a> |
                          <a href="user_delete?user.id=${user.id}">删除</a>
                      </td>
                  </tr>
              </s:iterator>
          </table>
        </div>
      </div>
      <div class="footer">
        <%@include file="../include/footer.jsp"%>
      </div>
    </div>
</body>
</html>
