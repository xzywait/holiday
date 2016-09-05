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
    <script type="text/javascript" src="/js/user.js"></script>
    <style type="text/css">
        #selectUser {
            line-height: 1.8;
            padding-left: 20px;
        }
        table {
            font-size: 15px;
        }
    </style>

</head>
<body>
<div class="easyui-tabs" fit="true" border="false">
    <div title="用户管理" border="false">
        <div class="easyui-layout" fit="true" border="false">
            <div region="north" title="过滤" border="false" style="height: 130px; overflow:hidden">
                <form id="searchForm">
                    <table id="selectUser">
                        <tr>
                            <th>用户名：</th>
                            <td><input type="text" name="username" id="username"></td>
                            <th>真实姓名：</th>
                            <td><input type="text" name="realName" id="realName"></td>
                        </tr>
                        <tr>
                            <th>创建时间：</th>
                            <td><input class="easyui-datetimebox" editable="false" name="createTimeStart"
                                       id="createTimeStart"></td>
                            <th align="center">至</th>
                            <td><input class="easyui-datetimebox" editable="false" name="createTimeEnd"
                                       id="createTimeEnd"></td>
                        </tr>
                        <tr>
                            <td><input  type="button" class="easyui-linkbutton" onclick="search();" value="查询"/>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a
                                   href="javascript:void(0)" class="easyui-linkbutton" onclick="clearSearchForm();return false;">清空</a></td>
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
