/**
 * Created by luxiaoping on 2016/8/31.
 */

var monitorDatagrid;
var temperatureChar;
var humidityChar;
$(function () {
    monitorDatagrid = $('#historyDataDatagrid').datagrid({
       url: '/historyData/getHistoryDataBySearch',
        queryParams: {
            searchStationName: $('#searchHistoryDataForm').find('[name=searchStationName]').val(),
            searchStartTime: $('#searchHistoryDataForm').find('[name=searchStartTime]').val(),
            searchEndTime: $('#searchHistoryDataForm').find('[name=searchEndTime]').val()
        },
        iconCls: 'icon-save',
        pagination: true,
        pageSize: 5,
        pageList: [5, 8, 10, 20, 30, 40],
        fit: true,
        fitColumns: false,
        width: 1060,
        height: 400,
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

    //  myChart.hideLoading();
    initChar();//生成图表
    getChartData()//aja后台交互

});

function initChar() {
    temperatureChar = echarts.init(document.getElementById('temperature'));
    var option = {
        title: {
            text: '温度'
        },
        legend: {
            data: ['0-20cm', '20-40cm', '40-60cm', '60-80cm']
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        tooltip: {
            trigger: 'axis'
        },
        xAxis: {
            type: 'time',
            splitLine: {
                show: false
            }
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%'],
            splitLine: {
                show: false
            }
        },
        series: [{
            name: '0-20cm',
            type: 'line',
            showSymbol: false,
            hoverAnimation: false,
            data: []
        },
            {
                name: '20-40cm',
                type: 'line',
                showSymbol: false,
                hoverAnimation: false,
                data: []
            },
            {
                name: '40-60cm',
                type: 'line',
                showSymbol: false,
                hoverAnimation: false,
                data: []
            },
            {
                name: '60-80cm',
                type: 'line',
                showSymbol: false,
                hoverAnimation: false,
                data: []
            }
        ]
    };
    temperatureChar.setOption(option);


    humidityChar = echarts.init(document.getElementById('humidity'));
    var humidityCharOption = {
        title: {
            text: '湿度'
        },
        legend: {
            data: ['0-20cm', '20-40cm', '40-60cm', '60-80cm']
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        tooltip: {
            trigger: 'axis'
        },
        xAxis: {
            type: 'time',
            splitLine: {
                show: false
            }
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%'],
            splitLine: {
                show: false
            }
        },
        series: [{
            name: '0-20cm',
            type: 'line',
            showSymbol: false,
            hoverAnimation: false,
            data: []
        },
            {
                name: '20-40cm',
                type: 'line',
                showSymbol: false,
                hoverAnimation: false,
                data: []
            },
            {
                name: '40-60cm',
                type: 'line',
                showSymbol: false,
                hoverAnimation: false,
                data: []
            },
            {
                name: '60-80cm',
                type: 'line',
                showSymbol: false,
                hoverAnimation: false,
                data: []
            }
        ]
    };

    humidityChar.setOption(humidityCharOption);
}

function getChartData() {
    //获得图表的options对象
    var options = temperatureChar.getOption();
    var humidityCharOptions = humidityChar.getOption()
    //通过Ajax获取数据
    $.ajax({
        type: "post",
        async: true, //同步执行
        url: "/historyData/getCharsDatas",
        data: {
            searchStationName: $('#searchHistoryDataForm').find('[name=searchStationName]').val(),
            searchStartTime: $('#searchHistoryDataForm').find('[name=searchStartTime]').val(),
            searchEndTime: $('#searchHistoryDataForm').find('[name=searchEndTime]').val()
        },
        dataType: "json", //返回数据形式为json
        success: function (result) {
            if (result) {
                options.series[0].data = result.result[0];
                options.series[1].data = result.result[1];
                options.series[2].data = result.result[2];
                options.series[3].data = result.result[3];
                //  myChart.hideLoading();
                temperatureChar.setOption(options);
                humidityCharOptions.series[0].data = result.result[4];
                humidityCharOptions.series[1].data = result.result[5];
                humidityCharOptions.series[2].data = result.result[6];
                humidityCharOptions.series[3].data = result.result[7];
                humidityChar.setOption(humidityCharOptions);

                document.getElementById("tableTitle").innerHTML = result.tableTitle.toString();
            }
        },
        error: function (errorMsg) {
            alert("不好意思，大爷，图表请求数据失败啦!");
            //temperatureChar.hideLoading();
        }
    });
}

function getData() {
    monitorDatagrid.datagrid('load', {
        searchStationName: $('#searchHistoryDataForm').find('[name=searchStationName]').val(),
        searchStartTime: $('#searchHistoryDataForm').find('[name=searchStartTime]').val(),
        searchEndTime: $('#searchHistoryDataForm').find('[name=searchEndTime]').val()
    });

    //获得图表的options对象
    var options = temperatureChar.getOption();
    var humidityCharOptions = humidityChar.getOption()
    //通过Ajax获取数据
    $.ajax({
        type: "post",
        async: true, //同步执行
        url: "/historyData/getCharsDatas",
        data: {
            searchStationName: $('#searchHistoryDataForm').find('[name=searchStationName]').val(),
            searchStartTime: $('#searchHistoryDataForm').find('[name=searchStartTime]').val(),
            searchEndTime: $('#searchHistoryDataForm').find('[name=searchEndTime]').val()
        },
        dataType: "json", //返回数据形式为json
        success: function (result) {
            if (result) {
                options.series[0].data = result.result[0];
                options.series[1].data = result.result[1];
                options.series[2].data = result.result[2];
                options.series[3].data = result.result[3];
                //  myChart.hideLoading();
                temperatureChar.setOption(options);
                humidityCharOptions.series[0].data = result.result[4];
                humidityCharOptions.series[1].data = result.result[5];
                humidityCharOptions.series[2].data = result.result[6];
                humidityCharOptions.series[3].data = result.result[7];
                humidityChar.setOption(humidityCharOptions);

                document.getElementById("tableTitle").innerHTML = result.tableTitle.toString();
            }
        },
        error: function (errorMsg) {
            alert("不好意思，大爷，图表请求数据失败啦!");
            //temperatureChar.hideLoading();
        }
    });
}