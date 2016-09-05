package cn.edu.nwsuaf.model.dao;

import cn.edu.nwsuaf.model.entity.Data;
import cn.edu.nwsuaf.model.entity.Device_info;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by luxiaoping on 2016/8/4.
 */
@Repository
public interface DataDao {
    Data get(Data data);

    List<Data> find();
    //查询监测数据
    List<Data> selectDatas(Map map);

    Integer insert(Data data);

    Integer update(Data data);

    Integer delete(Data data);
}
