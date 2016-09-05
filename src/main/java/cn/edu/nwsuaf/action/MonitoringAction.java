package cn.edu.nwsuaf.action;

import cn.edu.nwsuaf.model.entity.Data;
import cn.edu.nwsuaf.model.entity.Device_info;
import cn.edu.nwsuaf.model.entity.Station_info;
import cn.edu.nwsuaf.model.service.DataService;
import cn.edu.nwsuaf.model.service.DeviceService;
import cn.edu.nwsuaf.model.service.StationService;
import org.springframework.context.annotation.Scope;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by luxiaoping on 2016/8/20.
 */
public class MonitoringAction {
    private String stationId;
    private String stationName;
    @Resource
    private StationService stationService;

    @Resource
    private DeviceService deviceService;

    @Resource
    private DataService dataService;

    private int page;//当前页数
    private int rowsInPage;//每页显示记录数(前台传过来的参数为rows，在set方法中给此变量赋值)
    private int total;//后台返回的结果记录数
    private List<Map<String,Object>> rows;//后台返回的结果


    /* *
    * 页面重定向
    * */
    public String execute()
    {
        System.out.println("stationId="+stationId);
        return "success";
    }

    /* *
    * 获取站点的监测数据
    * 通过stationId找到landId，根据landId查找data表中单位为℃和%的记录
    * 从当前时间开始查找，不断循环往前半个小时查找
    * */
    public String getMonitorData(){
        rows = new ArrayList<>();
        System.out.println("=========================获取站点监测数据========================");
        System.out.println("stationId="+stationId);
        System.out.println("page="+page+",rowsInPage="+rowsInPage);

        List<Map<String,Object>> result = new ArrayList<>();

        Station_info station_info = new Station_info();
        station_info.setStationId(Integer.valueOf(stationId));
        //获取station信息
        Station_info returnStation = stationService.getStation_info(station_info);
        //获取设备信息
        List<Device_info> deviceList = new ArrayList<>();
        if(returnStation!=null){
            Map<String,Object> map = new HashMap<>();
            map.put("landId",returnStation.getLandId());

            deviceList = deviceService.selectDevice(map);

        }

        Date endDateTime = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("mmss");
        SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
        String today = format3.format(endDateTime);
        String mmss = format1.format(endDateTime);
        if("0000".equals(mmss)||"3000".equals(mmss)){
            long times = endDateTime.getTime();
            times += 5;
            endDateTime = new Date(times);
        }

        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");
        int ii = 0;
        while(today.equals(format3.format(endDateTime))){
            Map<String,Object> monitorData = new HashMap<>();
            //  计算半个小时前的时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endDateTime);
            calendar.add(Calendar.MINUTE, -30);
            Date startTime = calendar.getTime();

            //查找device_name为土壤温度或土壤水分的监测记录
            int i=1;
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("createTimeStart",startTime);
            paramMap.put("createTimeEnd",endDateTime);
//            for(Device_info device:deviceList){
//
//                paramMap.put("deviceId",device.getDeviceId());
//                List<Data> dataLst = dataService.selectDatas(paramMap);
//                if(dataLst.size()>0){
//                    monitorData.put("deviceId"+i,dataLst.get(0).getDataValue()+""+dataLst.get(0).getUnit());//监测值
//                    i++;
//                    monitorData.put("date",format2.format(dataLst.get(0).getCollectTime()));
//                }
//            }

            paramMap.put("deviceIdStart",deviceList.get(0).getDeviceId());
            paramMap.put("deviceIdEnd",deviceList.get(deviceList.size()-1).getDeviceId());
            List<Data> datas = dataService.selectDatas(paramMap);
            if(datas.size()>0){
                monitorData.put("date",format2.format(datas.get(0).getCollectTime()));
                for(Data data:datas){
                    monitorData.put("deviceId"+i,data.getDataValue()+""+data.getUnit());//监测值
                   i++;
                }
            }

            if(!monitorData.isEmpty()){
                System.out.println( ++ii + "一条记录:"+monitorData.toString());
                result.add(monitorData);//result.add(monitorData);
            }
            endDateTime = startTime;//往前推半个小时
        }

//        for(int i=0;i<result.size();i++){
//            System.out.println(result.get(i).toString());
//        }
        total = result.size();

//        System.out.println("====================================返回结果============================");
//        System.out.println("total="+total);
//        System.out.println("开始条数："+(page - 1) * rowsInPage+",结束条数："+page*rowsInPage);
        for(int i=(page - 1) * rowsInPage;i<page*rowsInPage&&i<result.size();i++){
            System.out.println("第"+i+"ci");
            rows.add(result.get(i));
        }
        System.out.println("rows的长度："+rows.size());
        for(int i=0;i<rows.size();i++){
            System.out.println(rows.get(i).toString());
        }
        return "listByPage";
    }


    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
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

    public List<Map<String,Object>> getRows() {
        return rows;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
