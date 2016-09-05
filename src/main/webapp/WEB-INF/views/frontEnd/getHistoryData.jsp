<%--
  Created by IntelliJ IDEA.
  User: luxiaoping
  Date: 2016/8/21
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>获取历史监测数据</title>
    <link rel="stylesheet" href="/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" href="/js/easyui/themes/icon.css">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/js/json2.js"></script>
    <script type="text/javascript" src="/js/echarts.min.js"></script>
    <script type="text/javascript" src="/js/historyData.js"></script>

    <style type="text/css">
        #getHistoryDataForm {
            line-height: 2.2;
            padding-top: 15px;
        }

        .echar {
            display: inline;
            height: 300px;
        }

        #temperature {
            float: left;
            width: 500px;
        }

        #humidity {
            float: right;
            width: 500px;
        }

        /*#centerData{*/
        /*height: 400px;*/
        /*}*/
    </style>
</head>
<body>
<div class="easyui-tabs" fit="true" border="false">
    <div title="查询历史监测数据" border="false">
        <div region="center" border="false">
            <div>
                <form id="searchHistoryDataForm" action="/historyData/getHistoryData" method="post">
                    <table id="searchHistoryData">
                        <tr>
                            <td>试点名称：</td>
                            <td>
                                <select name="searchStationName">
                                    <s:iterator value="stationName" var="sname">
                                        <option><s:property value="sname"></s:property></option>
                                    </s:iterator>
                                </select>
                            </td>
                            <td>开始时间：</td>
                            <td><input class="easyui-datetimebox" editable="false" name="searchStartTime"
                                       id=searchStartTime"></td>

                            <td align="center">结束时间：</td>
                            <td><input class="easyui-datetimebox" editable="false" name="searchEndTime"
                                       id="searchEndTime"></td>

                            <td><input type="button" class="easyui-linkbutton" onclick="getData();" value="查询"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>

            <div id="centerData" >
                <p align="center" id="tableTitle"></p>
                <table id="historyDataDatagrid"  >

                </table>
            </div>

            <div id="" >
                <p align="center">数据对应的图表显示</p>
                <div>
                    <%--温度折线图--%>
                    <div id="temperature" class="echar">
                        数据加载中。。。
                    </div>
                    <%--湿度折线图--%>
                    <div id="humidity" class="echar">
                        数据加载中。。。
                    </div>
                </div>
            </div>

        </div>
    </div>

    <div title="获取历史监测数据" border="false">
        <div class="easyui-layout" fit="true" border="false">
            <div region="north" border="false" style="height: 130px; overflow:hidden">
                <form id="getHistoryDataForm" action="/historyData/getHistoryData" method="post">
                    <table id="getHistoryData">
                        <tr>
                            <th>开始时间：</th>
                            <td><input class="easyui-datetimebox" editable="false" name="startTime"
                                       id=startTime"></td>
                        </tr>
                        <tr>
                            <th align="center">结束时间：</th>
                            <td><input class="easyui-datetimebox" editable="false" name="endTime"
                                       id="endTime"></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="获取">
                                <a href="" class="easyui-linkbutton">清空</a>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div region="center" border="false" title="历史数据">
                <table id="historyDatagrid">

                </table>
            </div>
        </div>
    </div>

</div>
</body>
</html>
