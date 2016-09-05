/**
 * Created by luxiaoping on 2016/8/16.
 */

var stationDatagrid;
$(function () {
    stationDatagrid = $('#stationDatagrid').datagrid({
        url: '/station/searchStations',
        iconCls: 'icon-save',
        pagination: true,
        pageSize: 5,
        pageList: [5, 8, 10, 20, 30, 40],
        fit: true,
        fitColumns: true,
        idField: 'id',
        sortName: 'stationId',
        sortOrder: 'desc',
        columns: [[
            {
                title: '编号',
                field: 'stationId',
                width: 100,
                sortable: true
            }, {
                title: '站点名称',
                field: 'stationName',
                width: 100,
                sortable: true
            }, {
                title: '地块编号',
                field: 'landId',
                width: 100,
                sortable: true
            }, {
                title: '地块名称',
                field: 'landName',
                width: 100,
                sortable: true
            }, {
                title: '经度',
                field: 'longituade',
                width: 100,
                sortable: true,
                sortable: true
            }, {
                title: '纬度',
                field: 'latitude',
                width: 100,
                sortable: true
            }]],
        toolbar: [{
            text: '刷新站点信息',
            iconCls: 'icon-reload',
            handler: function () {
                $.ajax({
                    url: '/station/reloadStations',
                    dataType: 'json',
                    success: function (r) {
                        $.messager.show({
                            title: '提示',
                            msg: r.resultToJSP.msg
                        });
                        stationDatagrid.datagrid('load');
                    }
                });
            }
        }]
    });
});

//情况查询表单数据
function searchStation() {
    stationDatagrid.datagrid('load', {
        stationId: $('#searchForm').find('[name=stationId]').val(),
        stationName: $('#searchForm').find('[name=stationName]').val(),
        longituade: $('#searchForm').find('[name=longituade]').val(),
        latitude: $('#searchForm').find('[name=latitude]').val(),
    });
}

//清空查询表单数据
function clearSearchForm() {
    stationDatagrid.datagrid('load');
    $('#searchForm').find('input').val('');
}

