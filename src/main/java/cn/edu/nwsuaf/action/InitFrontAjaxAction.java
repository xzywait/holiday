package cn.edu.nwsuaf.action;

import cn.edu.nwsuaf.model.entity.Station_info;
import cn.edu.nwsuaf.model.service.StationService;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luxiaoping on 2016/8/9.
 */
public class InitFrontAjaxAction {
    @Resource
    private StationService stationService;

    public String getStationNamesAjaxAction() throws IOException {
        List<Station_info> stationList = new ArrayList<Station_info>();
        stationList = stationService.getAllStation();

//        List<String> stationName = new ArrayList<String>();
//        for(Station_info station:stationList){
//            stationName.add(station.getStationName());
//        }

        JSONArray jsonArray = JSONArray.fromObject(stationList);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(jsonArray);
        response.getWriter().flush();
        response.getWriter().close();


        return null;
    }

}
