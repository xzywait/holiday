package cn.edu.nwsuaf.action;

import cn.edu.nwsuaf.model.entity.Station_info;
import cn.edu.nwsuaf.model.service.StationService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luxiaoping on 2016/8/16.
 */
public class StationAction {
    //查询条件
    private String stationId;
    private String stationName;
    private String longituade;
    private String latitude;

    private String sort;//排序字段
    private String order;//排序方式
    private int page;//当前页数
    private int rowsInPage;//每页显示记录数(前台传过来的参数为rows，在set方法中给此变量赋值)
    private int total;//后台返回的结果记录数

    private List<Station_info> rows;//后台返回的结果
    private JsonResult resultToJSP = new JsonResult();//返回给前台的操作结果

    @Resource
    private StationService stationService;

    public String execute() {
        return "success";
    }

    /* *
     * 多条件分页查询站点信息
    * */
    public String searchStations() {
        System.out.println("============================查询===========================");
        System.out.println("rows=" + rowsInPage + ",page=" + page);
        System.out.println("stationId:" + stationId + ",stationName:" + stationName + ",longituade=" + longituade + ",latitude=" + latitude);
        System.out.println("sort:" + sort + ",order" + order);

        Map map = new HashMap();

        map.put("stationId",stationId);
        map.put("stationName",stationName);
        map.put("longituade",longituade);
        map.put("latitude",latitude);

        map.put("sort", sort);
        map.put("order", order);
        map.put("pageStart", (page - 1) * rowsInPage);
        map.put("rows", rowsInPage);


        total = stationService.getStationsCount(map);
        rows = stationService.getStations(map);
        System.out.println("total=" + total);
        for (Station_info s : rows) {
            System.out.println(s.toString());
        }

        return "success";
    }

    /* *
     * 多条件分页查询站点信息
    * */
    public String reloadStations() {
        Station_info station_info = new Station_info();
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
                    if (returnStation == null) {
                        System.out.println("**************returnStation is null**********");
                        stationService.saveStation(station_info, true);
                    } else {
                        System.out.println("**************更新站点信息**********");
                        stationService.saveStation(station_info, false);
                    }
                    resultToJSP.setSuccess(true);
                    resultToJSP.setMsg("站点信息更新成功！");
                    System.out.println(resultToJSP.getMsg());
                } catch (Exception e) {
                    resultToJSP.setSuccess(false);
                    resultToJSP.setMsg("站点信息更新失败！");
                    System.out.println(resultToJSP.getMsg());
                    e.printStackTrace();
                }
                System.out.println("------save successfully----------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return "success";
        }
    }

    /**
     * ================Gette、setter=============
     */
    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setLongituade(String longituade) {
        this.longituade = longituade;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public JsonResult getResultToJSP() {
        return resultToJSP;
    }

    public int getTotal() {
        return total;
    }

    public List<Station_info> getRows() {
        return rows;
    }

    public void setSort(String sort) {
        this.sort = sort;
        if (this.sort.equals("stationId")) {
            this.sort = "station_id";
        }
        if (this.sort.equals("stationName")) {
            this.sort = "station_name";
        }
        if (this.sort.equals("landId")) {
            this.sort = "land_id";
        }
        if (this.sort.equals("landName")) {
            this.sort = "land_name";
        }
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rowsInPage = rows;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
