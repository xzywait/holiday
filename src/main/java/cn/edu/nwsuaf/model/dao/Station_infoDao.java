package cn.edu.nwsuaf.model.dao;

import cn.edu.nwsuaf.model.entity.Station_info;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 对station_info的增删改查操作
 * Created by luxiaoping on 2016/8/2.
 */
@Repository
public interface Station_infoDao {
    Station_info get(Station_info station_info);

    List<Station_info> find();

    Integer insert(Station_info station_info);

    Integer update(Station_info station_info);

    Integer delete(Station_info station_info);

    Integer getStationsCount(Map map);

    List<Station_info> getStations(Map map);

    List<String> getStationName();
}
