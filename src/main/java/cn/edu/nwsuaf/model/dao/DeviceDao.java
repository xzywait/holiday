package cn.edu.nwsuaf.model.dao;

import cn.edu.nwsuaf.model.entity.Device_info;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by luxiaoping on 2016/8/4.
 */
@Repository
public interface DeviceDao {
    Device_info get(Device_info device_info);

    List<Device_info> selectDevice(Map map);

    List<Device_info> find();

    Integer insert(Device_info device_info);

    Integer update(Device_info device_info);

    Integer delete(Device_info device_info);
}
