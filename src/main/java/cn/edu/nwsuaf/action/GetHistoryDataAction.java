package cn.edu.nwsuaf.action;

import cn.edu.nwsuaf.model.entity.Data;
import cn.edu.nwsuaf.model.entity.Device_info;
import cn.edu.nwsuaf.model.entity.Station_info;
import cn.edu.nwsuaf.model.service.DataService;
import cn.edu.nwsuaf.model.service.DeviceService;
import cn.edu.nwsuaf.model.service.StationService;
import com.alibaba.druid.support.spring.stat.annotation.Stat;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by luxiaoping on 2016/8/21.
 * 获取历史数据
 */
public class GetHistoryDataAction {
    private List<String> stationName;
    //获取历史数据的参数
    private String startTime;
    private String endTime;

    //查询历史数据的参数
    private String searchStartTime;
    private String searchEndTime;
    private String searchStationName;

    private int page;//当前页数
    private int rowsInPage;//每页显示记录数(前台传过来的参数为rows，在set方法中给此变量赋值)
    private int total;//后台返回的结果记录数

    private List<List<Map<String, Object>>> result;//后台返回的结果——折线图数据
    private List<Map<String, Object>> rows;//后台返回的结果——表格datagrid数据
    private String tableTitle;

    @Resource
    private DeviceService deviceService;

    @Resource
    private DataService dataService;

    @Resource
    private StationService stationService;

    //公有参数
    Station_info returnStation = null;

    public String execute() {
        stationName =     stationService.getStationName();

        for (String s : stationName) {
            System.out.println(s);
        }
        return "success";
    }

   /* public String getHistoryDataByDay(){
        System.out.println("=============================获取当天数据============================");
        System.out.print("开始时间："+startTime+",结束时间:"+endTime);

        List<Device_info> deviceList = new ArrayList<>();

        deviceList = deviceService.getAllDevice();

        String[] start = startTime.split(" ");
        String[] end = endTime.split(" ");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");

        for (Device_info device : deviceList) {
            System.out.println(device.toString());
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            CloseableHttpClient httpClient = httpClientBuilder.build();

            //传感器网站数据请求
            String dataURL = "http://wlw.zjtpyun.com/openapi/v1/device/";
            dataURL += device.getDeviceId() + "/data?user_id=361&start_time=" + start[0]+"%20"+start[1] + "&end_time=" + end[0]+"%20"+end[1] + "&num=100";
            System.out.println("dataURL:"+dataURL);
            //String dataURL = "http://wlw.zjtpyun.com/openapi/v1/device/" + device.getDeviceId() + "/data?user_id=361&start_time=2016-08-10%2007:30:00&end_time=2016-08-10%2008:00:00&num=100";

            HttpPost httpPost = new HttpPost(dataURL);

            System.out.println(httpPost.getRequestLine());

            Integer statusCode = -1;
            try {
                //站点网站数据请求与数据获取
                httpPost.setHeader("accept", "application/json");
                httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");

                HttpResponse response = httpClient.execute(httpPost);

                statusCode = response.getStatusLine().getStatusCode();

                if (statusCode != HttpStatus.SC_OK) {
                    System.out.println("Http Status is error.");
                }

                HttpEntity entity = response.getEntity();
                StringBuffer result = new StringBuffer();
                BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
                String tempLine = rd.readLine();
                while (tempLine != null) {
                    result.append(tempLine);
                    tempLine = rd.readLine();
                }
                System.out.println("===========================result==============================");
                System.out.println("result:" + result.toString());

                // 将json格式字符串转换成json对象
                JSONObject json = JSONObject.fromObject(result.toString());
                System.out.println("=============================解析json数据========================");

                if (json.containsKey("result")) {
                    JSONArray jsonArray = JSONArray.fromObject(json.get("result"));
                    for (int i = 0; i < jsonArray.size(); i++) {

                        JSONObject data = (JSONObject) jsonArray.get(i);
                        String sensor_id = (String) data.get("sensor_id");
                        String data_id = (String) data.get("data_id");
                        String data_value = (String) data.get("data_value");
                        String sensor_type_id = (String) data.get("sensor_type_id");
                        String collect_time = (String) data.get("collect_time");
                        String unit = data.get("unit").toString();

                        Data dataObject = new Data();
                        dataObject.setDataId(Integer.valueOf(data_id));
                        dataObject.setDeviceId(device.getDeviceId());
                        dataObject.setSenserId(Integer.valueOf(sensor_id));
                        dataObject.setDataValue(Double.valueOf(data_value));
                        dataObject.setSensorTypeId(Integer.valueOf(sensor_type_id));
                        try {
                            dataObject.setCollectTime(format.parse(collect_time));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        dataObject.setUnit(unit);
                        System.out.println(dataObject.toString());
                        System.out.println("-----deviceAction save device_info----------------");

                        //查询数据库是否有这条记录
                        Data returnData = dataService.getDevice_info(dataObject);
                        if(returnData==null){
                            try {
                                dataService.saveData(dataObject,true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else{
                            try {
                                dataService.saveData(dataObject,false);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("------save successfully----------------");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "todayData";
    }*/

    //计算时间
    public void countDataTime() {
        Station_info station = new Station_info();
        station.setStationName(searchStationName);
        returnStation = stationService.getStation_info(station);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //查询时间为空，默认获取第一个地块当前月的历史数据
        if ((searchStartTime == null || searchStartTime.equals("")) && (searchEndTime == null || searchEndTime.equals(""))) {
            System.out.println("====================默认获取第一个地块当前月的历史数据====================");
            Date now = new Date();
            searchEndTime   = format.format(now);

            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;

            if (month >= 10) {
                searchStartTime = year + "-" + month + "-" + "01 00:00:00";
            } else {
                searchStartTime = year + "-0" + month + "-" + "01 00:00:00";
            }
        } else if ((searchStartTime == null || searchStartTime.equals("")) && (searchEndTime != null && !searchEndTime.equals(""))) {
            //查询的开始时间为空，默认从结束年份的当年开始获取的历史数据
            try {
                Date start = format.parse(searchEndTime);
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                searchStartTime = year + "-01-01 00:00:00";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if ((searchStartTime != null && !searchStartTime.equals("")) && (searchEndTime == null || searchEndTime.equals(""))) {
            //查询的结束时间为空，默认从获取开始时间到当前时间的历史数据
            Date now = new Date();
            searchEndTime = format.format(now);
        }

        tableTitle = searchStartTime+"——"+searchEndTime+"历史监测数据";
    }

    public String getHistoryDataBySearch() {
        countDataTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //构造表格数据
        rows = new ArrayList<>();
        System.out.println("=========================获取站点监测数据========================");
        System.out.println("page=" + page + ",rowsInPage=" + rowsInPage);

        List<Map<String, Object>> historyData = new ArrayList<>();
        //获取设备信息
        List<Device_info> deviceList = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        map.put("landId", returnStation.getLandId());
        deviceList = deviceService.selectDevice(map);

        Date endDateTime = null;
        Date startDateTime = null;
        try {
            endDateTime = format.parse(searchEndTime);
            startDateTime = format.parse(searchStartTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("startDateTime=" + startDateTime + ",startDateTime=" + startDateTime);
        SimpleDateFormat format1 = new SimpleDateFormat("mmss");
        SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
        String start = format3.format(startDateTime);
        String mmss = format1.format(endDateTime);
        if ("0000".equals(mmss) || "3000".equals(mmss)) {
            long times = endDateTime.getTime();
            times += 5;
            endDateTime = new Date(times);
        }

        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");
        int ii = 0;
        while (endDateTime.getTime() >= startDateTime.getTime()) {
            Map<String, Object> monitorData = new HashMap<>();
            //  计算半个小时前的时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endDateTime);
            calendar.add(Calendar.MINUTE, -30);
            Date startTime = calendar.getTime();

            //查找device_name为土壤温度或土壤水分的监测记录
            int i = 1;
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("createTimeStart", startTime);
            paramMap.put("createTimeEnd", endDateTime);

            paramMap.put("deviceIdStart", deviceList.get(0).getDeviceId());
            paramMap.put("deviceIdEnd", deviceList.get(deviceList.size() - 1).getDeviceId());
            List<Data> datas = dataService.selectDatas(paramMap);
            if (datas.size() > 0) {
                monitorData.put("date", format2.format(datas.get(0).getCollectTime()));
                for (Data data : datas) {
                    monitorData.put("deviceId" + i, data.getDataValue() + "" + data.getUnit());//监测值
                    i++;
                }
            }
            if (!monitorData.isEmpty()) {
                System.out.println(++ii + "一条记录:" + monitorData.toString());
                historyData.add(monitorData);//result.add(monitorData);
            }
            endDateTime = startTime;//往前推半个小时
        }
        total = historyData.size();

        for (int i = (page - 1) * rowsInPage; i < page * rowsInPage && i < historyData.size(); i++) {
            System.out.println("第" + i + "ci");
            rows.add(historyData.get(i));
        }
        System.out.println("rows的长度：" + rows.size());
        for (int i = 0; i < rows.size(); i++) {
            System.out.println(rows.get(i).toString());
        }


        return "historyDatas";
    }

    //获取图表数据
    public String getCharsDatas() {
        System.out.println("====================查询历史数据====================");

        System.out.println("当前时间：" + searchStartTime + ",本月时间：" + searchEndTime + ",站点名称：" + searchStationName);

        result = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        countDataTime();


        Map queryParam = new HashMap<String, Object>();
        //获取设备信息
        Map<String, Object> map = new HashMap<>();
        map.put("landId", returnStation.getLandId());
        List<Device_info> deviceList = new ArrayList<>();
        deviceList = deviceService.selectDevice(map);
        System.out.println("device的size():" + deviceList.size());

        System.out.println("当前时间：" + searchStartTime + ",本月时间：" + searchEndTime);
        //构造折线图数据
        for (int i = 0; i < deviceList.size(); i++) {
            queryParam.put("deviceId", deviceList.get(i).getDeviceId());
            queryParam.put("createTimeStart", searchStartTime);
            queryParam.put("createTimeEnd", searchEndTime);

            List<Data> datas = dataService.selectDatas(queryParam);

            List<Map<String, Object>> series = new ArrayList<>();

            for (Data data : datas) {
                Map<String, Object> serie = new HashMap<>();
                System.out.println(data.toString());
                serie.put("name", format.format(data.getCollectTime()));
                List<Object> value = new ArrayList<>();
                value.add(format.format(data.getCollectTime()));
                value.add(data.getDataValue());
                serie.put("value", value);

                series.add(serie);
            }
            result.add(series);
        }


        System.out.println("list size:" + result.size() + "+++++++++++++++++++++++++++++++++++");
        for (int i = 0; i < result.size(); i++) {
            System.out.println("=============第" + i + "个编号的数据==========");
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.println("第" + j + "条记录：" + result.get(i).get(j).toString());
            }
        }


        System.out.println("================================历史数据查询结束===========================");
        return "charDatas";
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<String> getStationName() {
        return stationName;
    }

    public void setSearchStartTime(String searchStartTime) {
        this.searchStartTime = searchStartTime;
    }

    public void setSearchEndTime(String searchEndTime) {
        this.searchEndTime = searchEndTime;
    }

    public void setSearchStationName(String searchStationName) {
        this.searchStationName = searchStationName;
    }

    public List<List<Map<String, Object>>> getResult() {
        return result;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setRowsInPage(int rowsInPage) {
        this.rowsInPage = rowsInPage;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setRows(int rows) {//给每页显示记录数赋值
        this.rowsInPage = rows;
    }

    public int getTotal() {
        return total;
    }

    public List<Map<String, Object>> getRows() {
        return rows;
    }

    public String getTableTitle() {
        return tableTitle;
    }
}
