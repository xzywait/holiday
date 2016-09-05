<%--
  Created by IntelliJ IDEA.
  User: luxiaoping
  Date: 2016/8/2
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>test</title>
    <link rel="stylesheet" href="/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" href="/js/easyui/themes/icon.css">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function () {
            $('#userDatagrid').datagrid({
                iconCls: 'icon-save',
                pagination: true,
                pageSize: 10,
                pageList: [10, 20, 30, 40],
                fit: true,
                fitColumns: false,
                idField: 'id'
            });
        });
    </script>
</head>
<body>
<div class="easyui-tabs" fit="true" border="false">
    <div title="用户管理" border="false">
        <table id="userDatagrid">
            <thead>
            <tr>
                <th data-options="field:'id'">编号</th>
                <th data-options="field:'name'">用户名</th>
                <th data-options="field:'realName'">真实姓名</th>
                <th data-options="field:'passwd'">密码</th>
                <th data-options="field:'createDate'">创建日期</th>
            </tr>
            </thead>
            <tbody>
            <s:iterator id="user" value="usetList">
                <tr>
                    <td><s:property value="#user.id"/> </td>
                    <td><s:property value="#user.name"/> </td>
                    <td><s:property value="#user.realName"/> </td>
                    <td><s:property value="#user.passwd"/> </td>
                    <td><s:property value="#user.createDate"/> </td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
