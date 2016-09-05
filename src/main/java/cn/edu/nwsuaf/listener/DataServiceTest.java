package cn.edu.nwsuaf.listener;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Timer;

/**
 * Created by luxiaoping on 2016/8/8.
 */
@Service
public class DataServiceTest implements InitializingBean {
    @Resource
    private DataTask dataTask;
    @Override
    //半个小时刷新一次data数据
    public void afterPropertiesSet() throws Exception {
//        System.out.println("INIT");
//        System.out.println("==================hello world================");
//
//        Timer timer = new Timer();
//        System.out.println("-------RemindTask   Timer准备执行--------------");
//        timer.schedule(dataTask, 0, 30*60*1000);//半个小时刷新一次
//        System.out.println("-------RemindTask   Timer已在执行--------------");
    }
}
