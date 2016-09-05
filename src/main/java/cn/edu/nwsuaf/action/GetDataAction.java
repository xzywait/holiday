package cn.edu.nwsuaf.action;

import cn.edu.nwsuaf.model.entity.Device_info;
import cn.edu.nwsuaf.model.entity.Station_info;
import cn.edu.nwsuaf.model.service.DeviceService;
import cn.edu.nwsuaf.model.service.StationService;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luxiaoping on 2016/8/1.
 */
public class GetDataAction {
    private Station_info station_info;
    private List<Station_info> station_infoList;

    @Resource
    private StationService stationService;

    @Resource
    private DeviceService deviceService;

    //获取站点信息
    public String getStationData() {
        station_info = new Station_info();

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpClient = httpClientBuilder.build();
        //站点网站数据请求
        HttpPost httpPost = new HttpPost("http://wlw.zjtpyun.com/openapi/v1/user/station?user_id=361");
        System.out.println(httpPost.getRequestLine());

        Integer statusCode = -1;
        try {
            //站点网站数据请求与数据获取
            httpPost.setHeader("accept", "application/json");
            httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");

            HttpResponse response = httpClient.execute(httpPost);

            statusCode = response.getStatusLine().getStatusCode();
            System.out.println("status:" + statusCode);
            if (statusCode != HttpStatus.SC_OK) {
                System.out.println("Http Status is error.");
            }

            HttpEntity entity = response.getEntity();
            StringBuffer result = new StringBuffer();
            BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
            String tempLine = rd.readLine();
            while (tempLine != null) {
                result.append(tempLine);
                System.out.println("tempLine:" + tempLine);
                tempLine = rd.readLine();
            }
            System.out.println("===========================result==============================");
            System.out.println("result:" + result.toString());

            // 将json格式字符串转换成json对象
            JSONObject json = JSONObject.fromObject(result.toString());
            System.out.println("=============================解析json数据========================");
            System.out.println("code=" + json.get("code"));
            System.out.println("msg=" + json.get("msg"));
            System.out.println("result=" + json.get("result"));

            JSONObject jsonResult = (JSONObject) json.get("result");
            System.out.println("result.count=" + jsonResult.get("count"));
            JSONArray jsonArray = JSONArray.fromObject(jsonResult.get("data_array"));
            System.out.println("data_array=" + jsonArray);


            for (int i = 0; i < jsonArray.size(); i++) {
                System.out.println("data_array[" + i + "]=" + jsonArray.get(i));
                JSONObject data = (JSONObject) jsonArray.get(i);
                String station_id = (String) data.get("station_id");

                String name = (String) data.get("name");
                String land_count = (String) data.get("land_count");
                System.out.println("station_id=" + station_id + ",name=" + name + ",land_count=" + land_count);

                station_info.setStationId(Integer.parseInt(station_id));
                station_info.setStationName(name);
                System.out.println("-----stationAction save station_info----------------");
                try {
                    Station_info returnStation = stationService.getStation_info(station_info);
                    if (returnStation == null){
                        System.out.println("**************returnStation is null**********");
                        stationService.saveStation(station_info,true);
                    }
                    else{
                        stationService.saveStation(station_info,false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("------save successfully----------------");
            }
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //获取地块数据
    public String getLandData() {
        station_info = new Station_info();

        List<Station_info> stationList = new ArrayList<Station_info>();
        stationList = stationService.getAllStation();

        String landURL = "http://wlw.zjtpyun.com/openapi/v1/station/";

        for (Station_info s : stationList) {
            System.out.println(s.toString());
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            CloseableHttpClient httpClient = httpClientBuilder.build();
            //地块网站数据请求
            HttpPost httpPost = new HttpPost(landURL+s.getStationId()+"/land?user_id=361");
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


                JSONObject jsonResult = (JSONObject) json.get("result");
                String station_name = (String) jsonResult.get("station_name");
                JSONArray jsonArray = JSONArray.fromObject(jsonResult.get("land_array"));
                for (int i = 0; i < jsonArray.size(); i++) {

                    JSONObject data = (JSONObject) jsonArray.get(i);
                    String land_id = (String) data.get("land_id");
                    String land_name = (String) data.get("land_name");
                    String longitude = (String) data.get("longitude");
                    String latitude = (String) data.get("latitude");
                    System.out.println("land_id=" + land_id + ",land_name=" + land_name + ",longitude=" + longitude + ",latitude=" + latitude);

                    station_info.setStationName(station_name);
                    station_info.setLandId(Integer.parseInt(land_id));
                    station_info.setLandName(land_name);
                    station_info.setLongituade(Double.parseDouble(longitude));
                    station_info.setLatitude(Double.parseDouble(latitude));

                    System.out.println("-----stationAction save station_info----------------");
                    try {
                        stationService.saveStation(station_info, false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("------save successfully----------------");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "fail";
            }try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "success";
    }

    //获取设备信息
    public String getDevice(){
        List<Station_info> stationList = new ArrayList<Station_info>();
        stationList = stationService.getAllStation();

        String landURL = "http://wlw.zjtpyun.com/openapi/v1/land/";

        for (Station_info s : stationList) {
            System.out.println(s.toString());
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            CloseableHttpClient httpClient = httpClientBuilder.build();
            //地块网站数据请求
            HttpPost httpPost = new HttpPost(landURL+s.getLandId()+"/device?user_id=361");
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


                JSONObject jsonResult = (JSONObject) json.get("result");
                String station_name = (String) jsonResult.get("station_name");
                JSONArray jsonArray = JSONArray.fromObject(jsonResult.get("data_array"));
                for (int i = 0; i < jsonArray.size(); i++) {

                    JSONObject data = (JSONObject) jsonArray.get(i);
                    String device_id = (String) data.get("device_id");
                    String device_name = (String) data.get("device_name");
                    String device_type_id = (String) data.get("device_type_id");
                    String sensor_type_id = (String) data.get("sensor_type_id");
                    String sensor_tag = null;

                    if(!(data.get("sensor_tag") instanceof JSONNull)){
                        System.out.println(data.get("sensor_tag"));
                       sensor_tag = (String) data.get("sensor_tag");
                    }

                    Device_info device_info = new Device_info();
                    device_info.setLandId(s.getLandId());
                    device_info.setDeviceId(Integer.valueOf(device_id));
                    device_info.setDeviceName(device_name);
                    device_info.setDeviceTypeId(Integer.valueOf(device_type_id));
                    device_info.setSensorTypeId(Integer.valueOf(sensor_type_id));
                    if(sensor_tag !=null) {
                        device_info.setSensorTag(Integer.valueOf(sensor_tag));
                    }else {
                        device_info.setSensorTag(null);
                    }
                    Device_info returnDevice = deviceService.getDevice_info(device_info);

                    System.out.println("-----deviceAction save device_info----------------");
                    if(returnDevice==null){
                        try {
                            deviceService.saveDevice(device_info,true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        try {
                            deviceService.saveDevice(device_info,false);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("------save successfully----------------");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "fail";
            }try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "success";
    }

    /**
     * ================ gettet and setter=============
     ***/
    public Station_info getStation_info() {
        return station_info;
    }

    public void setStation_info(Station_info station_info) {
        this.station_info = station_info;
    }

    public List<Station_info> getStation_infoList() {
        return station_infoList;
    }

}
