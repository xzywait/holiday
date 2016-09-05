package cn.edu.nwsuaf.listener;

import cn.edu.nwsuaf.model.entity.Data;
import cn.edu.nwsuaf.model.entity.Device_info;
import cn.edu.nwsuaf.model.entity.Station_info;
import cn.edu.nwsuaf.model.service.DataService;
import cn.edu.nwsuaf.model.service.DeviceService;
import cn.edu.nwsuaf.model.service.StationService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by luxiaoping on 2016/8/7.
 */
@Service
public class DataTask extends TimerTask {
    @Resource
    private DeviceService deviceService;

    @Resource
    private DataService dataService;

    @Override
    public void run() {
        List<Device_info> deviceList = new ArrayList<Device_info>();

        deviceList = deviceService.getAllDevice();

        Calendar calendar = Calendar.getInstance();
        Date endTime = calendar.getTime();//当前时间
        System.out.println("now:" + endTime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        System.out.println("now:" + format.format(endTime));
        calendar.add(Calendar.MINUTE, -30);
        Date startTime = calendar.getTime();
        System.out.println("a half time ago:" + startTime);
        System.out.println("a half time ago:" + format.format(startTime));

        for (Device_info device : deviceList) {
            System.out.println(device.toString());
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            CloseableHttpClient httpClient = httpClientBuilder.build();

            //传感器网站数据请求
            String dataURL = "http://wlw.zjtpyun.com/openapi/v1/device/";
            dataURL += device.getDeviceId() + "/data?user_id=361&start_time=" + format.format(startTime) + "&end_time=" + format.format(endTime) + "&num=100";

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
    }
}
