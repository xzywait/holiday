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
<!--
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
<script type="text/javascript" src="/js/json2.js"></script>
<style type="text/css">
#selectUser {
line-height: 1.8;
padding-left: 20px;
}

table {
font-size: 15px;
}
</style>
<script type="text/javascript">
var userDatagird;
$(function () {
userDatagird = $('#userDatagrid').datagrid({
url: '/user/getUsers',
iconCls: 'icon-save',
pagination: true,
pageSize: 10,
pageList: [10, 20, 30, 40],
fit: true,
fitColumns: true,
idField: 'id',
columns: [[
{
title: '编号',
field: 'id',
width: 100
}, {
title: '用户名',
field: 'username',
width: 100
}, {
title: '真实姓名',
field: 'realName',
width: 100
}, {
title: '密码',
field: 'passwd',
width: 100
}, {
title: '创建日日期',
field: 'createDate',
width: 100
}]],
toolbar: [{
text: '增加',
iconCls: 'icon-add',
handler: function () {

}
}, '-', {
text: '删除',
iconCls: 'icon-remove',
handler: function () {

}
}, '-', {
text: '修改',
iconCls: 'icon-edit',
handler: function () {

}
}, '-']
});
});

function search() {
userDatagird.datagrid({
url: '/user/searchUser',
queryParams: {
username: $('#searchForm').find('[name=username]').val(),
realName: $('#searchForm').find('[name=realName]').val(),
createTimeStart: $('#searchForm').find('[name=createTimeStart]').val(),
createTimeEnd: $('#searchForm').find('[name=createTimeEnd]').val(),
},
iconCls: 'icon-save',
pagination: true,
pageSize: 10,
pageList: [10, 20, 30, 40],
fit: true,
fitColumns: true,
idField: 'id',
columns: [[
{
title: '编号',
field: 'id',
width: 100
}, {
title: '用户名',
field: 'username',
width: 100
}, {
title: '真实姓名',
field: 'realName',
width: 100
}, {
title: '密码',
field: 'passwd',
width: 100
}, {
title: '创建日日期',
field: 'createDate',
width: 100
}]]
});
}
</script>
</head>
<body>
<div class="easyui-tabs" fit="true" border="false">
<div title="用户管理" border="false">
<div class="easyui-layout" fit="true" border="false">
<div region="north" title="过滤" border="false" style="height: 130px; overflow:hidden">
<form id="searchForm">
<table id="selectUser">
<tr>
<td>用户名：</td>
<td><input type="text" name="username" id="username"></td>
<td>真实姓名：</td>
<td><input type="text" name="realName" id="realName"></td>
</tr>
<tr>
<td>创建时间：</td>
<td><input class="easyui-datetimebox" editable="false" name="createTimeStart"
id="createTimeStart"></td>
<td align="center">至</td>
<td><input class="easyui-datetimebox" editable="false" name="createTimeEnd"
id="createTimeEnd"></td>
</tr>
<tr>
<td><input type="button" class="easyui-linkbutton" onclick="search();" value="查询"/>&nbsp;&nbsp;&nbsp;&nbsp;<a
href="" class="easyui-linkbutton" onclick="">清空</a></td>
</tr>
</table>
</form>
</div>
<div region="center" border="false" title="用户信息">
<table id="userDatagrid">

</table>
</div>
</div>
</div>
</div>
</body>
</html>
-->