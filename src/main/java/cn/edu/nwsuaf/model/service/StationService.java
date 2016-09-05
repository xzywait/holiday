package cn.edu.nwsuaf.model.service;

import cn.edu.nwsuaf.model.dao.Station_infoDao;
import cn.edu.nwsuaf.model.entity.Station_info;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static org.apache.struts2.interceptor.DateTextFieldInterceptor.DateWord.s;

/**
 * 对station_info操作的service层
 * Created by luxiaoping on 2016/8/3.
 */
@Service
public class StationService {
    @Resource
    public Station_infoDao stationDao;

    public Integer saveStation(Station_info station_info,boolean flag) throws Exception{
        if(station_info==null){
            throw new Exception("站点信息不能为空！");
        }
        if(station_info.getStationId()!=null&&flag){//第一次插入数据
            System.out.println("station_id 不为空,并且是第一次插入&&&&&&&&&&&");
            return stationDao.insert(station_info);
        }else{
            return stationDao.update(station_info);
        }
    }

    public Station_info getStation_info(Station_info station_info){
        if(station_info==null){
            station_info = new Station_info();
        }
        Station_info v = stationDao.get(station_info);
        System.out.println(v);
        return stationDao.get(station_info);
    }

    //获得符合条件的总站点数
    public Integer getStationsCount(Map map){
        return stationDao.getStationsCount(map);
    }
    //获得符合条件的总站点
    public List<Station_info> getStations(Map map){
        return stationDao.getStations(map);
    }

    public List<Station_info> getAllStation(){
        return stationDao.find();
    }

    public List<String> getStationName(){return stationDao.getStationName();}

}
