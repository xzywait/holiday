<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <base target="mainFrame"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="//cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">


    <link rel="stylesheet" href="/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" href="/js/easyui/themes/icon.css">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/json2.js"></script>

    <style type="text/css">
        .header {
            display: inline-block;
        }

        #header-left {
            width: 75%;
        }

        #header-left h2 {
            padding-left: 150px;
        }

        #header-right {
            width: 20%;
            padding-left: 50px;
            position:absolute;
        }

        #header-right a {
            font-size: 16px;
        }

        #header-right p {
            font-size: 16px;
        }

        ul{
            line-height: 1.8;
        }
        ul li a{
            font-size: 15px;
        }
        #footer{
            font-size: 13px;
        }

    </style>

    <script type="text/javascript">
        $(function () {
            $('#bodyLayout').layout();
        });
    </script>
</head>
<body id="bodyLayout">
<!--头部信息-->
<div region="north" style="height: 80px;">
    <div class="header" id="header-left">
        <h2>陕西省土壤墒情检测预警系统</h2>
    </div>
    <div class="header" id="header-right">
        <p><a href="/WEB-INF/views/backstage/index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/login.jsp">退出</a>&nbsp;&nbsp;&nbsp;&nbsp;<a>帮助</a>
        </p>
        <p>用户名：<s:property value="username"/></p>
    </div>
</div>
<!--尾部信息-->
<div region="south" style="height: 50px;">
    <p align="center" id="footer">Copyright&copy;西北农林科技大学版权所有2008&nbsp;All&nbsp;Rights&nbsp;Reserved陕ICP备05001586号</p>
</div>
<!--网站内容主体信息-->
<div region="west" title="墒情检测站" split="true" style="width: 240px;">
    <ul>
        <li><a href="/user/toListJsp" target="mainFrame">用户信息</a> </li>
        <li><a href="/station/toListJsp" target="mainFrame">站点管理</a></li>
        <li><a href="#" target="mainFrame">预警信息管理</a></li>
    </ul>
</div>
<div region="center" split="true" style="">
    <iframe name="mainFrame" width="100%" height="100%" src="about:blank" frameborder="0"></iframe>
</div>
</body>
</html>