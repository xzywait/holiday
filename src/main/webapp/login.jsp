<%--
  Created by IntelliJ IDEA.
  User: luxiaoping
  Date: 2016/8/2
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <link rel="stylesheet" href="js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" href="js/easyui/themes/icon.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <style type="text/css">
        table{
            line-height: 1.8;
        }
    </style>
    <script type="text/javascript">
        var loingDialog;
        $(function () {
            loingDialog = $('#login').dialog({
                title:'用户登录',
                top:20,
                width:300,
                height:300,
                closable:false,
                buttons:[{
                    text:'登录',
                    handler:function () {
                        var username = document.getElementById('username').value;
                        var password = document.getElementById('password').value;
                        document.forms["loginForm"].submit();

                }
                },{
                    text:'取消',
                    handler:function () {
                        $('#loginForm').form('clear');
                    }
                }]
            })
        });
    </script>
</head>
<body>
    <div id="login">
        <form id="loginForm" method="post" action="/login/loginAction">
        <table>
            <tr>
                <td>用户名：</td>
                <td><input type="text" id="username" name="username"/></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="password" id="password" name="password"/></td>
            </tr>
        </table>
        </form>
    </div>
</body>
</html>
