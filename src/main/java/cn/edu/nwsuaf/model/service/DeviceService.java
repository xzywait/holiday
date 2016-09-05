package cn.edu.nwsuaf.model.service;

import cn.edu.nwsuaf.model.dao.DeviceDao;
import cn.edu.nwsuaf.model.entity.Device_info;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by luxiaoping on 2016/8/4.
 */
@Service
public class DeviceService {
    @Resource
    private DeviceDao deviceDao;

    public List<Device_info> getAllDevice(){
        return deviceDao.find();
    }

    public List<Device_info> selectDevice(Map map){return deviceDao.selectDevice(map);}

    public Integer saveDevice(Device_info device_info,boolean flag) throws Exception{
        if(device_info==null){
            throw new Exception("设备信息不能为空！");
        }
        if(flag){//第一次插入数据
            System.out.println("station_id 不为空,并且是第一次插入&&&&&&&&&&&");
            return deviceDao.insert(device_info);
        }else{
            return deviceDao.update(device_info);
        }
    }

    public Device_info getDevice_info(Device_info device_info){
        if(device_info==null){
            device_info = new Device_info();
        }
        Device_info v = deviceDao.get(device_info);
        System.out.println(v);
        return deviceDao.get(device_info);
    }
}
