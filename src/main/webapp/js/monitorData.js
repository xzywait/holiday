/**
 * Created by luxiaoping on 2016/8/12.
 */

var monitorDatagrid;
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

function search() {
    userDatagird.datagrid('load', {
        username: $('#searchForm').find('[name=username]').val(),
        realName: $('#searchForm').find('[name=realName]').val(),
        createTimeStart: $('#searchForm').find('[name=createTimeStart]').val(),
        createTimeEnd: $('#searchForm').find('[name=createTimeEnd]').val(),
    });
}
