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
            $('#monitorDatagrid').datagrid({
                url: '/monitoring/getMonitorData',
                queryParams: {
                    stationId: "${stationId }"
                },
                iconCls: 'icon-save',
                pagination: true,
                pageSize: 5,
                pageList: [5, 8, 10, 20, 30, 40],
                fit: true,
                fitColumns: false,
                width: 1060,
//                frozenColumns: [[
//                    {field: 'ck', checkbox: true}
//                ]],
                columns: [[
                    {field: 'date', title: '日期', rowspan: 2, width: 100, align: 'center'},
                    {title: '温度', halign: 'center', colspan: 4, width: 480},
                    {title: '湿度', halign: 'center', colspan: 4, width: 480}
                ], [
                    {field: 'deviceId1', title: '0-20cm', width: 120, align: 'center'},
                    {field: 'deviceId2', title: '20-40cm', width: 120, align: 'center'},
                    {field: 'deviceId3', title: '40-60cm', width: 120, align: 'center'},
                    {field: 'deviceId4', title: '60-80cm', width: 120, align: 'center'},
                    {field: 'deviceId5', title: '0-20cm', width: 120, align: 'center'},
                    {field: 'deviceId6', title: '20-40cm', width: 120, align: 'center'},
                    {field: 'deviceId7', title: '40-60cm', width: 120, align: 'center'},
                    {field: 'deviceId8', title: '60-80cm', width: 120, align: 'center'}
                ],]
            });
        });
    </script>
    <style type="text/css">
        #selectMonitorData {
            line-height: 1.8;
            padding-left: 20px;
        }

        table {
            font-size: 15px;
        }

        table th {
            align: right;
        }
    </style>

</head>
<body>
<div class="easyui-tabs" fit="true" border="false">
    <div title="站点数据监测管理" border="false">
        <div class="easyui-layout" fit="true" border="false">
            <div region="north" border="false" style="height: 60px;">
                <p>${stationName}实时数据监测</p>
            </div>
            <div region="center" border="false">
                <table id="monitorDatagrid">

                </table>
            </div>
            <div region="south" border="false" title="监测数据信息-动态折线图" style="height: 200px;">

            </div>
        </div>
    </div>
</div>
</body>
</html>
