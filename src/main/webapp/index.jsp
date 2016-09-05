<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base target="mainFrame"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="//cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">

    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" href="js/easyui/themes/icon.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="js/json2.js"></script>
    <!--
    <style type="text/css">
        .menuBtn {
            font-size: 14px;
            font-weight: bold;
            -moz-border-radius: 10px; /* Gecko browsers */
            -webkit-border-radius: 10px; /* Webkit browsers */
            border-radius: 10px; /* W3C syntax */
            border: 1px solid #ccc;
            padding: 5px;
            margin: 10px auto;
            text-decoration: none;
            width: 120px;
            display: block;
            text-align: center;
        }

        .btnActive {
            background-color: yellow;
        }

        .btnSelected {
            background-color: #900;
            color: #fff;
        }

        a {
            color: blue;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $(".menuBtn").hover(
                    function () {
                        $(this).addClass("btnActive");
                    },
                    function () {
                        $(this).removeClass("btnActive");
                    }
            ).click(function () {
                        $(".menuBtn").removeClass("btnSelected");
                        $(this).addClass("btnSelected");
                    });
        });
    </script>
    <title>墒情监测系统</title>
</head>
<body class="easyui-layout">
<div region="north" class="mainTitle">
    <div style="color:red;font-weight: bolder;font-size: 24pt">
        墒情监测系统
    </div>
		<span id="logout">
			<a href="/user/user_logout" class="btn btn-default btn-sm"><i class="fa fa-users" aria-hidden="true"></i> 用户管理</a>
			<a href="/user/user_logout" target="_top" class="btn btn-danger btn-sm"><i class="fa fa-sign-out" aria-hidden="true"></i> 退出系统</a>
		</span>
</div>
<div region="south" class="mainFooter">
</div>
<div region="west" class="mainLeft">
    <div>
        <ul class="kid">
            <li><a target="mainFrame" href="/data/getStationData">获取数据</a> </li>
        </ul>
         <ul class="kid">
        <li><a target="mainFrame" href="/data/getDevice">获取设备数据</a> </li>
    </ul>
    </div>
</div>
<div region="center" style="overflow:hidden">
    <div style="position: absolute;width: 10px;height: 50px;top:300px;z-index: 1000;font-size: 18pt;left:0px"><i class="fa fa-angle-double-left" aria-hidden="false"></i></div>
    <iframe name="mainFrame" width="100%" height="100%" src="about:blank" frameborder="0"></iframe>
</div>
</body>
</html>
-->
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

        #stationName, #search {
            padding-left: 30px;
        }

        .historyData {
            width: 100%;
            height: 100px;
            border-color: #1f637b;
        }

        #history {
            width: 100%;
            height: 30px;
            line-height:30px;
            background-color: rgb(231,242,255);
            //background:-moz-linear-gradient(top,rgb(231,236,255),rgb(255,243,255));/*火狐*/
            //background:-moz-linear-gradient(bottom,rgb(231,236,255),rgb(255,243,255));/*火狐*/
            background-color: rgb(231, 240, 255);
            border:1px solid rgb(149,184,231);
            border-right-style: none;
            font-size: 13px;
            font-family: 黑体;
            color: rgb(14,45,95);
        }

    </style>

    <script type="text/javascript">
        var bodyLayout;
        $(function () {
            bodyLayout = $('#bodyLayout').layout();

            $.getJSON('/data/getStationNames', function (stations) {
                //alert(JSON.stringify(stations));
                for (var s in stations) {
                    var obj = document.all("stationName");
                    var lli = document.createElement("li"); //创建一个OPTION节点
                    //lli.innerHTML = "<a href='personDetail?personName="+op+"+'>"+op+"</a>"; //设置选择展示的信息4
                    var stationId = stations[s].stationId;
                    lli.innerHTML = "<a href='/monitoring/toListJsp?stationId="+stationId+"&stationName="+stations[s].stationName+"'>" + stations[s].stationName + "</a>";
                   // lli.value = stationNames[s]; //设置选项的值
                    obj.appendChild(lli); //将节点加入city选项中
                }
            });
        });

        function showHistoryData() {
            var centerPanel = $('#searchHistoryData').layout('panel','center');
            centerPanel.panel('load')
        }

    </script>
</head>
<body id="bodyLayout">
<!--头部信息-->
<div region="north" style="height: 70px;">
    <div class="header" id="header-left">
        <h2>陕西省土壤墒情检测预警系统</h2>
    </div>
    <div class="header" id="header-right">
        <p><a>首页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/login.jsp" target="_blank">登录</a>&nbsp;&nbsp;&nbsp;&nbsp;<a>帮助</a>
    </div>
</div>
<!--尾部信息-->
<div region="south" style="height: 50px;">
    <p align="center">Copyright&copy;西北农林科技大学版权所有2008&nbsp;All&nbsp;Rights&nbsp;Reserved陕ICP备05001586号</p>
</div>
<!--网站内容主体信息-->
<div region="west" title="墒情检测站" split="true" style="width: 200px;">
    <div>
        <ul id="stationName"></ul>
    </div>
    <div class="historyData">
        <div id="history">历史数据</div>
        <ul id="search">
            <li>
                <a href="/data/getDevice" target="mainFrame">查询</a>
            </li>
            <li>
                <a href="/historyData/toListJsp" target="mainFrame">获取历史数据</a>
            </li>
        </ul>
    </div>
</div>
<div region="center" split="true" style="">
    <iframe name="mainFrame" width="100%" height="100%" src="about:blank" frameborder="0"></iframe>
</div>
</body>
</html>