package cn.edu.nwsuaf.model.service;

import cn.edu.nwsuaf.model.dao.DataDao;
import cn.edu.nwsuaf.model.dao.DeviceDao;
import cn.edu.nwsuaf.model.entity.Data;
import cn.edu.nwsuaf.model.entity.Device_info;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by luxiaoping on 2016/8/4.
 */
@Service
public class DataService {
    @Resource
    private DataDao dataDao;

    public List<Data> getAllDevice() {
        return dataDao.find();
    }

    public Integer saveData(Data data,boolean flag) throws Exception {
        if (data == null) {
            throw new Exception("设备信息不能为空！");
        }
        if(data!=null&&flag)
            return dataDao.insert(data);
        else
            return dataDao.update(data);
    }

    public Data getDevice_info(Data data) {
        if (data == null) {
            data = new Data();
        }
        return dataDao.get(data);
    }

    public List<Data> selectDatas(Map map){
        return dataDao.selectDatas(map);
    }
}
