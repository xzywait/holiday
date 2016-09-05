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
            <s:form action="user_save">
                <table border="1" width="400">
                    <caption>添加/修改用户</caption>
                    <tr>
                        <th width="60">用户名：</th>
                        <td>
                            <s:textfield name="user.name" />
                        </td>
                    </tr>
                    <tr>
                        <th>口令：</th>
                        <td><input name="user.passwd"  /></td>
                    </tr>
                    <tr>
                        <th>口令确认：</th>
                        <td><input name="repasswd"  /></td>
                    </tr>
                    <tr>
                        <th>真实姓名：</th>
                        <td><s:textfield name="user.realName" /></td>
                    </tr>
                    <tr>
                        <th colspan="2">
                            <input type="submit" value="确定" />
                            <s:hidden name="user.id" />
                        </th>
                    </tr>
                </table>
            </s:form>

        </div>
      </div>
      <div class="footer">
        <%@include file="../include/footer.jsp"%>
      </div>
    </div>
</body>
</html>
