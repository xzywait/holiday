package cn.edu.nwsuaf.listener;

import cn.edu.nwsuaf.action.GetDataAction;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;
import java.util.Timer;

/**
 * Created by luxiaoping on 2016/8/7.
 */
public class ReflushDataListener extends HttpServlet implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        DataTask flushData = new DataTask();
//        Timer timer = new Timer();
//        System.out.println("-------RemindTask   Timer准备执行--------------");
//        timer.schedule(flushData,60*1000);//24*60*60*
//        System.out.println("-------RemindTask   Timer已在执行--------------");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
